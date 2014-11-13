package com.dh.ora.s009.service;

import java.util.List;
import java.util.Map;

import com.dh.ora.s009.bean.Postalinfo;
import com.x.orange.Config;
import com.x.orange.dao.Dao;

public class AddrServiceImpl implements AddrService {
	
	/**
	 * 获取唯一id
	 */
	public String getAddr_id() throws Exception {
		
		Dao dao = Config.getDao();
		
		String Addr_id = dao.find("select nextval('kindcode')");
		
		return Addr_id;
	}
	
	public boolean addAddr(Postalinfo postalinfo) throws Exception {
		
		Dao dao = Config.getDao();
		
		String sql = "insert into dpostalinfo(" ;
		sql += "user_id,addr_id,recipient," ;
		sql += "address,postal_code,phone_no," ;
		sql += "update_time";
		sql += ") " ;
		sql += " values('"+postalinfo.getUser_id()+"','"+postalinfo.getAddr_id()+"','"+postalinfo.getRecipient()+"',";
		sql += " '"+postalinfo.getAddress()+"','"+postalinfo.getPostal_code()+"','"+postalinfo.getPhone_no()+"',";
		sql += " now()";
		sql += ")";
		
		dao.execute(sql);
		
		return true;
	}
	
	public List<Map<String,Object>> getList(String uid) throws Exception{
		List<Map<String,Object>> list = null;
		Dao dao = Config.getDao();
		String sql="select user_id,addr_id,recipient,address,postal_code,phone_no,update_time,is_default from dpostalinfo";
		if(!"".equals(uid)){
			sql = sql + " where user_id=?";
			dao.addParam(new Object[]{uid});
		}
		sql = sql +" order by addr_id";
		list = dao.query(sql);
		return list;
	}
	

}
