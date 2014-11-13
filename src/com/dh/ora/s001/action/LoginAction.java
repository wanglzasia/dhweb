package com.dh.ora.s001.action;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s001.service.LoginSrv;
import com.dh.ora.tool.Encrypt;
import com.x.orange.Action;

public class LoginAction extends Action{
	/** 
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -1258981082191933756L;
	
	public String reg() throws Exception {
		//初始化验证码  
		return "reg"; 
	}
	
	public String save() throws Exception{
		
		LoginSrv loginSrv = (LoginSrv)getBean("loginSrv");
		User usr = loginSrv.saveUsr(getRequest());
		String msg = "";
		if(null!=usr){ msg="成功";}else { msg="失败" ;return null;}
		setSessionUser(usr);
 		setCookies(usr);
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("messageResources", locale);
        msg = rb.getString("REG_STATUS_OK_MSG");
 		getRequest().setAttribute("msg", msg);
 		getRequest().setAttribute("mailaddress", usr.geteMail());
 		getRequest().setAttribute("redirectUrl", "/");
 		return "report";

	}
	
	public String checkRegMsg(){
		return null;
	}
	
	public String log(){
		//引导登陆界面
		return "log";
	}
	
	public String login() throws Exception{
	
		LoginSrv loginSrv = (LoginSrv)getBean("loginSrv");
		User usr = loginSrv.checkLogin(getRequest());
		String msg = "";
		if(null!=usr){ msg="成功"; 
		}else { 
			msg="失败" ;
			String loginno = (String)request.getParameter("login_no");
			getRequest().setAttribute("errmsg", "用户名或者密码错误"); 
			getRequest().setAttribute("username", loginno); 

			return "login";
		}
		getSession().put("msg", msg);
		setSessionUser(usr);
		setCookies(usr);
		getResponse().sendRedirect("/");
		
		return null;
	}
	
	public String welcome(){
		/*
		String username ="刘德华";
		getSession().put("username", username);
		Dao dao = (Dao)this.getBean("springJdbcDao");//jdbcDao
		String tmpSql="insert into tmp_a( t_id,t_name) values (3,'qqqq')";
		dao.addBatch(tmpSql);
		tmpSql="insert into tmp_a( t_id,t_name) values (4,'wwww')";
		dao.addBatch(tmpSql);
		dao.execBatch();*/
		return "OK";
	}
	public String chkParam() throws Exception{
		String chkparam = (String)getRequest().getParameter("chkparam");
		String chkvalue = (String)request.getParameter("chkvalue");
		LoginSrv loginSrv = (LoginSrv)getBean("loginSrv");
		boolean retValue = loginSrv.existRegMsg(chkparam, chkvalue);
		this.getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(retValue){
			out.print("1");
		}else{
			out.print("0");
		}
		return null;
	}
	public void setCookies(User usr){
		Cookie cookie = null;
		cookie = new Cookie("loginno", usr.getLoginNo());
		cookie.setMaxAge(3000);
		getResponse().addCookie(cookie);
		cookie = new Cookie("email", usr.geteMail());
		cookie.setMaxAge(3000);
		getResponse().addCookie(cookie);		
		cookie = new Cookie("userid", usr.getUserId());
		cookie.setMaxAge(3000);
		getResponse().addCookie(cookie);
	}
	
	public String vaildRegUsr() throws Exception{
		String email = (String)getRequest().getParameter("email");
		String token = (String)getRequest().getParameter("token");
		String usrid = (String)getRequest().getParameter("d");
		Encrypt encrypt = new Encrypt();
		email = encrypt.decrypt(email);
		token = encrypt.decrypt(token);
		usrid = encrypt.decrypt(usrid);
		LoginSrv loginSrv = (LoginSrv)getBean("loginSrv");
		boolean ret = loginSrv.validRegMsg(email, token, usrid);
		if(!ret){
			getResponse().sendError(404, "激活失败!");
		}
		return "validok";
	}
}