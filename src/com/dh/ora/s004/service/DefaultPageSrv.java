package com.dh.ora.s004.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface DefaultPageSrv
{

	public abstract int add(HttpServletRequest httpservletrequest, String s) throws Exception;

	public abstract int modify(HttpServletRequest httpservletrequest) throws Exception;

	public abstract int remove(HttpServletRequest httpservletrequest) throws Exception;

	public abstract List<Map<String,Object>> list() throws Exception;

	public abstract void setRealPath(String s);

	public abstract List<Map<String,Object>> productKindList(String s, String s1) throws Exception;

	public abstract List<Map<String,Object>> ftlList(HttpServletRequest httpservletrequest) throws Exception;

	public abstract List<Map<String,Object>> ftlAtrrList(String s, String s1) throws Exception;

	public abstract int opftlAttr(HttpServletRequest httpservletrequest) throws Exception;

	public abstract void genXmlData(int i) throws Exception;

	public abstract List<Map<String,Object>> advList() throws Exception;
}
