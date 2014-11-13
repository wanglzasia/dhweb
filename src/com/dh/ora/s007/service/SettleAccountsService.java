package com.dh.ora.s007.service;

import java.util.List;

import com.dh.ora.s008.bean.Ordermsg;

public interface SettleAccountsService {
	
	public boolean addOrderMsg(List<Ordermsg> om_list ,List<String> shoppingcart_id_attr) throws Exception;
	
	public String getOrder_id() throws Exception;

}
