package com.dh.ora.s009.service;

import java.util.List;
import java.util.Map;

import com.dh.ora.s009.bean.Postalinfo;

public interface AddrService {

	public String getAddr_id() throws Exception ;
	
	public boolean addAddr(Postalinfo postalinfo) throws Exception ;
	
	public List<Map<String,Object>> getList(String uid) throws Exception;
	
}
