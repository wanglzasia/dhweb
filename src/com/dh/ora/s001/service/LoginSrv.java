package com.dh.ora.s001.service;

import javax.servlet.http.HttpServletRequest;

import com.dh.ora.s001.bean.User;

public interface LoginSrv {
	
	public User saveUsr(HttpServletRequest request) throws Exception;
	public int checkReg(HttpServletRequest request) throws Exception;
	public User checkLogin(HttpServletRequest request) throws Exception;
	public boolean isSeller(String userId) throws Exception;
	public boolean existRegMsg(String chktype,String chkvalue) throws Exception;
	
	public boolean validRegMsg(String email,String token,String userid) throws Exception;
}
