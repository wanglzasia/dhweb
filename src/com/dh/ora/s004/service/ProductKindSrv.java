package com.dh.ora.s004.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ProductKindSrv {

	public String addKind(HttpServletRequest request) throws Exception;
	public String addSection(HttpServletRequest request) throws Exception;
	public String addAttr(HttpServletRequest request) throws Exception;
	public String parserAttr(int type ,int usr ,String kindCode,String productId) throws Exception;
	public String loadTree(String parentCode) throws Exception;
	public String loadSectionTree(String kindCode) throws Exception;
	public List<Map<String,Object>> kindSectionList(String kindCode) throws Exception;
	public List<Map<String,Object>> kindAttrList(String kindCode,String sectionCode) throws Exception;
	public List<Map<String,Object>> productList(String status, HttpServletRequest request) throws Exception;
	
	public List<Map<String,Object>> getSubKindList(String parent_kind_code) throws Exception;
	
	public List<Map<String,Object>> kindList() throws Exception;
	public int updateKindModel(String kindcode,String ftlid) throws Exception;
}
