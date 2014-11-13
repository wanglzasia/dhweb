package com.dh.ora.s003.service;

import java.util.List;

import com.dh.ora.tool.MetaFilAssiant;
import com.x.orange.Service;

public class AlbumSrvImpl extends Service implements AlbumSrv{

	public List<String> picList(String userId,String webpathdir) throws Exception {
		String dirname=webpathdir+"/img/postImg/"+userId;
		List<String> list = MetaFilAssiant.fileList(dirname, webpathdir);
		return list;
	}
}
