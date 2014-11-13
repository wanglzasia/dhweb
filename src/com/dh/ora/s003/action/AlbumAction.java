package com.dh.ora.s003.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s003.service.AlbumSrv;
import com.x.orange.Action;

public class AlbumAction extends Action {

	private static final long serialVersionUID = -1458043459179964736L;

	public String loadAlbum() throws Exception{
		User usr = (User) getSessionUser();
		AlbumSrv albumSrv = (AlbumSrv)getBean("albumSrv");
		String webpathdir = ServletActionContext.getServletContext().getRealPath("/");
		List<String> list = albumSrv.picList(usr.getUserId(),webpathdir);
		getRequest().setAttribute("list", list);
		return "album";
	}
}
