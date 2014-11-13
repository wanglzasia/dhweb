package com.dh.ora.s001.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dh.ora.s001.bean.User;
import com.dh.ora.servlet.SendMail;
import com.dh.ora.tool.Encrypt;
import com.x.orange.Service;
import com.x.orange.dao.Dao;
import com.x.orange.exception.BusinessException;
import com.x.orange.util.ConfigUtil;
import com.x.orange.util.MD5;

public class LoginSrvImpl extends Service implements LoginSrv {

	//private User user = null;
    
	public User saveUsr(HttpServletRequest request) throws Exception {
		int retValue = 0;
		String content="";
		String subject ="";
		String shopHome="";
		User user = null;
		Encrypt encrypt = null; 
		String miMail = "";
		String miToken ="";
		String miUserId="";
		String url = "";
		Dao dao = getDao();
		String userId = "";
		String loginNo = (String)request.getParameter("login_no");
		String eMail = (String)request.getParameter("e_mail");
		String passWord = (String)request.getParameter("pass_word");
		passWord = MD5.getMD5ofStr(passWord,3);
		String chksql="select count(1) count_ from dusermsg where login_no=? or e_mail=?";
		dao.addParam(new Object[]{loginNo,eMail});
		String chkCountS = dao.find(chksql);
		int chkCount = Integer.parseInt(chkCountS);
		if(chkCount>0){
			throw new BusinessException("User "+loginNo+" already exists");
		}
		userId = dao.find("select nextval('userid')");
		String sql="insert into dusermsg ";
		sql=sql+"(user_id, login_no, e_mail, pass_word, status, update_date)";
		sql=sql+" VALUES ";
		sql=sql+" ('"+userId+"', '"+loginNo+"', '"+eMail+"', '"+passWord+"', 0, now())";
		retValue = dao.execute(sql);
		List<Map<String,Object>> list = null;
		Map<String,Object> mailMap = null;
		
		if(retValue==1){
			
			sql ="select ifnull(mail_title,\"\") mail_title,ifnull(mail_body,\"\") mail_body from s_cfg_regmail";
			list = dao.query(sql);
			for(int i = 0 ;i<list.size();i++){
				mailMap = list.get(i);
				subject =(String)mailMap.get("mail_title");
				content = (String)mailMap.get("mail_body");
			}
			sql ="select ifnull(shop_home_url,\"\") shop_home_url from s_cfg_shop";
			shopHome = dao.find(sql);
			content = content.replace("${user_name}",loginNo);
			user = new User();
			user.seteMail(eMail);
			user.setLoginNo(loginNo);
			user.setUserId(userId);
			//content="Hi, "+loginNo+"<br>";
			//content=content+ConfigUtil.getConfig("EMAIL_SUBJECT");
			encrypt = new Encrypt();
			miMail = encrypt.encrypt(user.geteMail());
			java.text.DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMdd");
			miToken = encrypt.encrypt(format2.format(new Date()));
			miUserId = encrypt.encrypt(user.getUserId());
			url = shopHome+"/emailVerify.do?email="+miMail+"&token="+miToken+"&d="+miUserId;
			url=url+"<a href=\""+url+"\">"+url+"</a>";
			content = content.replace("${valid_url}",url);
			SendMail.sendmail(eMail, subject , content);
		}
		return user;
	}

	public int checkReg(HttpServletRequest request) throws Exception {
		return 0;
	}
	

	public User checkLogin(HttpServletRequest request) throws Exception {
		Dao dao = getDao();
		String loginNo = (String)request.getParameter("login_no");
		String passWord = (String)request.getParameter("pass_word");
		String sql="select user_id,login_no,e_mail from dusermsg where status=1 and login_no=? and pass_word=?";
		dao.addParam(new Object[]{loginNo,MD5.getMD5ofStr(passWord, 3)});
		List<Map<String,Object>> mapList = dao.query(sql);
		Map<String,Object> map = null;
		User user = null;
		for(int i = 0 ;i<mapList.size() ;i++){
			map = (Map<String,Object>)mapList.get(i);
			user = new User();
			user.seteMail((String)map.get("e_mail"));
			user.setLoginNo((String)map.get("login_no"));
			user.setUserId((String)map.get("user_id"));
		}
		return user;
	}

	public boolean isSeller(String userId) throws Exception {
		boolean isSeller = true;
		String sql="select count(user_id) usr_count from dsellerMsg where user_id=? and status=1";
		Dao dao = getDao();
		dao.addParam(new Object[]{userId});
		int usr_count = Integer.parseInt(dao.find(sql));
		if(usr_count<=0){
			isSeller = false;
		}
		return isSeller;
	}


	public boolean existRegMsg(String chktype, String chkvalue) throws Exception {
		boolean retValue = false;
		String sql="";
		if(chktype.equals("loginno")){
			sql="select count(1) count_ from dusermsg where login_no='"+chkvalue+"'";
		}
		if(chktype.equals("email")){
			sql="select count(1) count_ from dusermsg where e_mail='"+chkvalue+"'";
		}
		Dao dao = getDao();
		String count="0";
		count = dao.find(sql);
		int recCount = 0;
		recCount = Integer.parseInt(count);
		if(recCount>0){
			retValue = true;
		}
		return retValue;
	}
	
	public boolean validRegMsg(String email,String token,String userid) throws Exception{
		boolean ret = true;
		int status = 0;
		int diffDay= 0;
		int sqlstatus = 0;
		String sql="select status,update_date,datediff('"+token+"',date_format(update_date,'%Y%m%d')) diff_day from dusermsg where user_id=? and e_mail=?";
		Dao dao = getDao();
		dao.addParam(new Object[]{userid,email});
		List<Map<String,Object>> list = dao.query(sql);
		Map<String,Object> map = null;
		//1、查看记录数,如果为 0 ,那么没有这个注册信息，邮件系伪造,发送404错误
		if(list.size()==0){
			return false; 
		}
		//2、查看状态,如果为1,那么已经激活完毕，发送404错误
		map = list.get(0);
		status = Integer.parseInt((String)map.get("status"));
		if(status==1){
			return false;
		}
		//3、查看注册时间,与当前时间比，是否在规定时限内，如果不是,那么发送404错误
		diffDay = Integer.parseInt((String)map.get("diff_day"));
		if(diffDay>7){
			return false;
		}
		//4、以上都通过了,那么视为正常情况,激活用户,并更新激活时间。
		sql="update dusermsg set active_date=now(),status=1 where user_id=? and e_mail=?";
		dao.addParam(new Object[]{userid,email});
		sqlstatus = dao.execute(sql);
		if(sqlstatus<=0){
			return false;
		}
		return ret;
	}
}
