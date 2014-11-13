package com.dh.ora.s003.action;

import com.x.orange.Action;

public class StaticAction extends Action{

	private static final long serialVersionUID = -7658086290416007302L;
	public String init(){
		String uri = "seller_static";
		getSession().put("uri", uri);
		return "init";
	}
}
