package com.dh.ora.s004.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AdminSrv
{

	public abstract List<Map<String,Object>> shopCfg() throws Exception;

	public abstract int update(HttpServletRequest httpservletrequest) throws Exception;

	public abstract List<Map<String,Object>> mailCfg() throws Exception;

	public abstract int updateMail(HttpServletRequest httpservletrequest) throws Exception;

	public abstract List<Map<String,Object>> metaAtrList() throws Exception;

	public abstract List<Map<String,Object>> metaContentList(String msg_id) throws Exception;

	public abstract int metaOp(String act,String msg_id,String attr_id,String meta_content) throws Exception;

	public abstract int opuser(String act, String uid,String uname,String pwd) throws Exception;

	public abstract List<Map<String,Object>> userlist(String uid) throws Exception;

	public abstract void footerToFile(String content,String content_cn,String uripath) throws Exception;

	public abstract List<Map<String,Object>> protolList(String proid) throws Exception;

	public abstract int updateProtol(HttpServletRequest httpservletrequest) throws Exception;

	public abstract List<Map<String,Object>> subMenusList(String meun_id) throws Exception;

	public abstract int opSubMenu(HttpServletRequest httpservletrequest) throws Exception;
}
