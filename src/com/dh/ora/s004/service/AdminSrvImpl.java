package com.dh.ora.s004.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dh.ora.tool.FreeMarkerToHtml;
import com.x.orange.Config;
import com.x.orange.Service;
import com.x.orange.dao.Dao;
import com.x.orange.util.ConfigUtil;
import com.x.orange.util.MD5;

public class AdminSrvImpl extends Service implements AdminSrv {

	public List<Map<String, Object>> shopCfg() throws Exception {
		List<Map<String,Object>> list = null;
		String sql="select shop_name,shop_home_url,ifnull(shop_footer,\"\") shop_footer,ifnull(shop_footer_cn,\"\") shop_footer_cn from s_cfg_shop";
		Dao dao = getDao();
		list = dao.query(sql);
		return list;
	}
	
	public int update(HttpServletRequest request) throws Exception{
		int ret = 0;
		String  shop_name="",shop_home_url="",shop_footer="",shop_footer_cn="";
		shop_name = request.getParameter("shop_name");
		shop_home_url = request.getParameter("shop_home_url");
		shop_footer = request.getParameter("shop_footer");
		shop_footer_cn = request.getParameter("shop_footer_cn");
		
		Dao dao = getDao();
		String sql="update s_cfg_shop set shop_name=?,shop_home_url=?,shop_footer=?,shop_footer_cn=? ";
		dao.addParam(new Object[]{shop_name,shop_home_url,shop_footer,shop_footer_cn});
		ret = dao.execute(sql);
		return ret ;
	}
	public List<Map<String,Object>> metaAtrList() throws Exception{
		List<Map<String,Object>> list = null;
		String sql=" select attr_id,attr_name,attr_value from s_cfg_meta_attr order by attr_id";
		Dao dao = getDao();
		list = dao.query(sql);
		return list;
	}
	public List<Map<String,Object>> metaContentList(String msg_id) throws Exception{
		List<Map<String,Object>> list = null;
		String sql="";//=" select attr_id,attr_name,attr_value from s_cfg_meta_attr";
		sql ="SELECT a.msg_id,b.attr_name,b.attr_value,a.meta_content,b.attr_note,a.attrid";
		sql = sql +" FROM dmetamsg a, s_cfg_meta_attr b WHERE a.attrid = b.attr_id";
		Dao dao = getDao();
		if(!msg_id.equals("")){
			sql = sql +" where a.msg_id =?";
			dao.addParam(new Object[]{msg_id});
		}
		sql = sql +" order by a.msg_id";
		list = dao.query(sql);
		return list;
	}

	public int metaOp(String act,String msg_id,String attr_id,String meta_content) throws Exception{
		int ret = 0;
		String sql ="";
		Dao dao = getDao();
		if(act.equals("add")){
			sql="insert into dmetamsg(attrid,meta_content) values(?,?)";
			dao.addParam(new Object[]{attr_id,meta_content});
		}
		if(act.equals("mod")){
			sql="update dmetamsg set meta_content=?,attrid=? where msg_id=?";
			dao.addParam(new Object[]{meta_content,attr_id,msg_id});
		}
		if(act.equals("del")){
			sql="delete from  dmetamsg where msg_id=?";
			dao.addParam(new Object[]{msg_id});
		}
		ret = dao.execute(sql);
		return ret; 
	}
	
	public int updateMail(HttpServletRequest request) throws Exception{
		int ret = 0;
		String mail_title =  request.getParameter("mail_title");
		String mail_body =  request.getParameter("mail_body");
		String sql="update s_cfg_regmail set mail_title=?,mail_body=?";
		Dao dao = getDao();
		dao.addParam(new Object[]{mail_title,mail_body});
		ret = dao.execute(sql);
		return ret;
	}
	
	public List<Map<String,Object>> mailCfg() throws Exception{
		String sql="select mail_title, mail_body from s_cfg_regmail;";
		List<Map<String,Object>> list = null;
		Dao dao = getDao();
		list = dao.query(sql);
		return list;
	}
	
	public int opuser(String act, String uid,String uname,String pwd) throws Exception{
		int ret = 0;
		Dao dao = getDao();
		String sql="";
		String ucount = "";
		pwd = MD5.getMD5ofStr(pwd,3);
		if(act.equals("add")){
			sql="select count(*) _coutn from dadminmsg where user_name =? and status='1'";
			dao.addParam(new Object[]{uname});
			ucount = dao.find(sql);
			ret = Integer.valueOf(ucount);
			if(ret>0){
				return -100;
			}
			sql="insert into dadminmsg(user_name,pass_word,reg_date,status) value (?,?,sysdate(),1)";
			dao.addParam(new Object[]{uname,pwd});
			ret = dao.execute(sql);
		}
		if(act.equals("mod")){
			sql="update dadminmsg set pass_word=?,update_date=sysdate() where user_id=?";/*user_name=?*/
			dao.addParam(new Object[]{pwd,uid});
			ret = dao.execute(sql);
		}
		if(act.equals("del")){
			sql="update dadminmsg set status=0,update_date=sysdate() where user_id=?";
			dao.addParam(new Object[]{uid});		
			ret = dao.execute(sql);
		}
		if(act.equals("check")){
			ucount="0";
			sql ="select count(1) _count from dadminmsg where user_name=? and pass_word=? ";
			dao.addParam(new Object[]{uname,pwd});
			ucount = dao.find(sql);
			ret = Integer.valueOf(ucount);
		}
		return ret;
	}
	public List<Map<String,Object>>  userlist(String uid) throws Exception{
		List<Map<String,Object>> list = null;
		Dao dao = getDao();
		String sql ="select user_id,user_name,pass_word,reg_date,ifnull(update_date,\"\") update_date from dadminmsg where status='1' ";
		if(!uid.equals("")){
			sql = sql +" and user_id=? ";
			dao.addParam(new Object[]{uid});
		}
		list = dao.query(sql);
		return list;
	}
	
	public void footerToFile(String content,String content_cn,String uripath) throws Exception{
		//英文静态页脚
		String ftlFile = uripath+File.separator+"ftl"+File.separator+"common"+File.separator+"footer_en.htm";
		genfile(ftlFile,content);
		//中文静态页脚
		ftlFile = uripath+File.separator+"ftl"+File.separator+"common"+File.separator+"footer.htm";
		genfile(ftlFile,content);
		//中文jsp页脚
		String jspFile = uripath+File.separator+"common"+File.separator+"footer.jsp";
		content_cn = "<%@ page language=\"java\" contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>" +"\r\n" +content_cn;
		genfile(jspFile,content_cn);
		//英文jsp页脚
		jspFile = uripath+File.separator+"common"+File.separator+"footer_en.jsp";
		content = "<%@ page language=\"java\" contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>" +"\r\n" +content;
		genfile(jspFile,content);


	}
	
	private void genfile(String fullpath,String content) throws Exception{
		File file = new File(fullpath);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));  
        writer.write(content);  
        writer.close(); 
	}
	
	public List<Map<String,Object>> protolList(String proid) throws Exception{
		Dao dao = getDao();
		String sql="";
		sql="select p_id,pro_name,pro_name_en,pro_content,pro_content_en from s_publish_protocol";
		if(!proid.equals("0")){
			sql+=" where p_id=?";
			dao.addParam(new Object[]{proid});
		}
		sql+=" order by p_id";
		List<Map<String,Object>> proList = null;
		proList = dao.query(sql);
		return proList;
	}
	public int updateProtol(HttpServletRequest request) throws Exception{
		
		String pid = null == request.getParameter("pro_id")?"":request.getParameter("pro_id");
		String content = null == request.getParameter("pro_content")?"":request.getParameter("pro_content");
		String content_en = null == request.getParameter("pro_content_en")?"":request.getParameter("pro_content_en");
		String pro_name = null == request.getParameter("pro_name")?"":request.getParameter("pro_name");
		String pro_name_en = null == request.getParameter("pro_name_en")?"":request.getParameter("pro_name_en");
		
		Dao dao = getDao();
		String sql="";
		sql="update s_publish_protocol set pro_content=?,pro_content_en=?,pro_name=?,pro_name_en=? where p_id =?";
		dao.addParam(new Object[]{content,content_en,pro_name,pro_name_en,pid});
		int ret = 0;
		ret = dao.execute(sql);
		
		sql="select shop_footer,shop_footer_cn from s_cfg_shop";
		List<Map<String,Object>> list = dao.query(sql);
		Map<String,Object> map = list.get(0);
		
		//生成HTML文件
		Map<String,String> root = new HashMap<String,String>();
		if(ret==1){
			root.put("content", content);
			root.put("content_en", content_en);
			root.put("pro_name", pro_name);
			root.put("pro_name_en", pro_name_en);
			root.put("footer", (String)map.get("shop_footer"));
			root.put("footer_en", (String)map.get("shop_footer_cn"));
			FreeMarkerToHtml toHtml = new FreeMarkerToHtml();
			
			String htmlFilePath="";
			String templateFileName="protol.ftl";
			String tplPath = ConfigUtil.getConfig("WEB_PATH");
			tplPath = tplPath +"\\ftl";
			toHtml.setTemplate_path(tplPath);
			htmlFilePath = ConfigUtil.getConfig("WEB_PATH");
			boolean ret1 = false;
			ret1 = toHtml.geneHtmlFile(templateFileName, root, htmlFilePath, "protol.htm");
			if(!ret1){
				ret = 0;
			}
		}
		
		return ret;	
	}
	
	public List<Map<String,Object>> subMenusList(String meun_id) throws Exception{
		List<Map<String,Object>> list = null;
		Dao dao = Config.getDao();
		String sql="select menu_id,menu_name,menu_name_en,m_index,http_addr from s_cfg_sub_menus where status='1'";
		if(!"".equals(meun_id)){
		 	sql = sql + " and menu_id='"+meun_id+"'";
		}
		sql = sql +" order by m_index,menu_id";
		list = dao.query(sql);
		return list;
	}
	
	public int opSubMenu(HttpServletRequest request) throws Exception{
		int ret = 0;
		String act=(String)request.getParameter("act");
		String menu_id =(String)request.getParameter("menu_id");
		String menu_name=(String)request.getParameter("menu_name");
		String menu_name_en = (String)request.getParameter("menu_name_en");
		String m_index = (String)request.getParameter("m_index");
		String http_addr=(String)request.getParameter("http_addr");
		String sql="";
		Dao dao = Config.getDao();
		if(act.equals("add")){
			sql="insert into s_cfg_sub_menus (menu_name,menu_name_en,status,m_index,http_addr) values"
					+ "(?,?,'1',?,?)";
			dao.addParam(new Object[]{menu_name,menu_name_en,m_index,http_addr});
		}
		if(act.equals("mod")){
			sql="update s_cfg_sub_menus set menu_name=?,menu_name_en=?,m_index=?,http_addr=? where menu_id=?";
			dao.addParam(new Object[]{menu_name,menu_name_en,m_index,http_addr,menu_id});
		}
		if(act.equals("del")){
			sql="update s_cfg_sub_menus set status=0 where menu_id=?";
			dao.addParam(new Object[]{menu_id});
		}
		
		ret = dao.execute(sql);
		
		return ret;
	}
	
	

	
}
