package com.dh.ora.s003.action;

import com.x.orange.Action;

public class SendAction extends Action{

	/** 
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -5894057911424636313L;
	
	public String init(){
		String uri = "seller_send";
		getSession().put("uri", uri);
		return "init";
	}
	
}
