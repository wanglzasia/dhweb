package com.dh.ora.s003.action;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s001.service.LoginSrv;
import com.x.orange.Action;

public class IndexAction extends Action{

	private static final long serialVersionUID = 8958808799999028627L;
	
 
	public String init(){
		String uri ="seller_message";
		getSession().put("uri", uri);
		return "main";
	}
	
	public String center() throws Exception{
		User usr = (User)getSessionUser();
		LoginSrv loginSrv = (LoginSrv)getBean("loginSrv");
		if(null!=usr){
			//已经登录，查看是否注册成卖家了，如是则进入卖家中心，否则进入注册界面
			if(loginSrv.isSeller(usr.getUserId())){
				String uri ="seller_message";
				getSession().put("uri", uri);
				return "main";
			}else{
				getRequest().setAttribute("loginno", usr.getLoginNo());
				getRequest().setAttribute("userid", usr.getUserId());
				return "reg";
			}
		}else{
			//没有登录，导向登录界面进行登录
			return "login";
		}
	}
}
