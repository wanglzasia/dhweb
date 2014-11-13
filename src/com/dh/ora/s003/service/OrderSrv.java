package com.dh.ora.s003.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dh.ora.s001.bean.User;

public interface OrderSrv
{

	public abstract List<Map<String,Object>> orderList(HttpServletRequest httpservletrequest, String s)throws Exception;
	public abstract List<Map<String,Object>> orderProductListByOrderList(List<Map<String,Object>> list)throws Exception;
	public abstract String getOrderFav(String s, String s1) throws Exception;

	public abstract int setOrderFav(String s, String s1, String s2) throws Exception;

	public abstract int delOrder(String s, User user) throws Exception;

	public abstract List<Map<String,Object>> orderProListByUid(String s) throws Exception;

	public abstract List<Map<String,Object>> orderProListByOrderId(String s) throws Exception;

	public abstract List<Map<String,Object>> orderOpMsgList(String s, String s1, int i) throws Exception;

	public abstract List<Map<String,Object>> orderShipList(String s, String s1) throws Exception;

	public abstract List<Map<String,Object>> orderList(String s, String s1, int i) throws Exception;

	public abstract int sendOrderMsg(User user, String s, String s1, String s2) throws Exception;

	public abstract int recevieMsg(User user, String s) throws Exception;

	public abstract List<Map<String,Object>> orderCourierMsg(String s) throws Exception;
}
