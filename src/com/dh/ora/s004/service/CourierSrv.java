package com.dh.ora.s004.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CourierSrv {

	public int opCourier(HttpServletRequest request) throws Exception;
	public List<Map<String,Object>> courierList(HttpServletRequest request) throws Exception;
	public List<Map<String,Object>> courierzoneList(HttpServletRequest request) throws Exception;
	public int opCourierZone(HttpServletRequest request) throws Exception;
	public List<Map<String,Object>>  countrylist(HttpServletRequest request) throws Exception;
	public String zoneListToJson(List<Map<String,Object>> zonelist) throws Exception;
	public int saveCourierTpl(HttpServletRequest request) throws Exception;
	public String freightJson(List<Map<String,Object>> list) throws Exception;
	public List<Map<String,Object>> ComputFreight(HttpServletRequest request) throws Exception;
}
