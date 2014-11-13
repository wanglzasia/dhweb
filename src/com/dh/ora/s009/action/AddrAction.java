package com.dh.ora.s009.action;

import java.io.PrintWriter;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s009.bean.Postalinfo;
import com.dh.ora.s009.service.AddrService;
import com.x.orange.Action;

public class AddrAction extends Action {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String addAddr(){
		try {
			// 获取买方用户信息
			User u = (User) getSessionUser();
			String user_id = u.getUserId();//用户ID
			AddrService addr = (AddrService) getBean("addr");
			String recipient = request.getParameter("recipient"); //收件人姓名
			String address = request.getParameter("address"); //收件地址
			String postal_code = request.getParameter("postal_code"); //邮政编码
			String phone_no = request.getParameter("phone_no"); //联系电话
			String addr_id = request.getParameter("addr_id"); //获取地址ID
			if(addr_id == null){
				addr_id = addr.getAddr_id();//地址id
			}
			
			Postalinfo postalinfo = new Postalinfo(); 
			postalinfo.setAddr_id(addr_id);
			postalinfo.setAddress(address);
			postalinfo.setPhone_no(phone_no);
			postalinfo.setPostal_code(postal_code);
			postalinfo.setRecipient(recipient);
			postalinfo.setUser_id(user_id);
			addr.addAddr(postalinfo);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String addAddr_ajax(){
		
		this.getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null ;
		
		try {
			
			out = response.getWriter();
			
			// 获取买方用户信息
			User u = (User) getSessionUser();
			
			String user_id = u.getUserId();//用户ID
	
			AddrService addr = (AddrService) getBean("addr");
			
			String recipient = request.getParameter("recipient"); //收件人姓名
			
			String address = request.getParameter("address"); //收件地址
			
			String postal_code = request.getParameter("postal_code"); //邮政编码
			
			String phone_no = request.getParameter("phone_no"); //联系电话
			
			String addr_id = request.getParameter("addr_id"); //获取地址ID
			
			if(addr_id == null){
				addr_id = addr.getAddr_id();//地址id
			}
			
			Postalinfo postalinfo = new Postalinfo(); 
			postalinfo.setAddr_id(addr_id);
			postalinfo.setAddress(address);
			postalinfo.setPhone_no(phone_no);
			postalinfo.setPostal_code(postal_code);
			postalinfo.setRecipient(recipient);
			postalinfo.setUser_id(user_id);
			
			boolean flag = addr.addAddr(postalinfo);
			
			if(flag){
				out.print("ok|"+addr_id);
			}else{
				out.print("error");
			}
			
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			out.print("error");
			out.close();
		}
		
		return null;
	}
	
	public String loadAddAddr(){
		try {
			AddrService addr = (AddrService) getBean("addr");
			String addr_id = addr.getAddr_id();//地址id
			request.setAttribute("addr_id", addr_id);
			
			System.out.println("==========addr_id======"+addr_id);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

}
