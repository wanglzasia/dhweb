package com.dh.ora.s006.action;

import java.util.List;
import java.util.Map;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s003.service.OrderSrv;
import com.dh.ora.s006.serivce.ShoppingCartService;
import com.dh.ora.s009.service.AddrService;
import com.x.orange.Action;

public class MemberAction extends Action {
	/** 
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 1L;

	public String center(){
		String include_uri = "welcome";
		getRequest().setAttribute("uri", include_uri);
		assgin("nav_msg","Buyer Center");

		return "index";
	}
	
	
	
	public String logout() throws Exception{
		removeSessionUser();
		getResponse().sendRedirect("/");
		
		return null;
	}
	
	public String orderlist() throws Exception{
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		List<Map<String,Object>> list = null;
		User u = (User) getSessionUser();
		list = orderSrv.orderProListByUid(u.getUserId());
		String include_uri = "orderlist";
		getRequest().setAttribute("uri", include_uri);
		getRequest().setAttribute("orderlist", list);
		assgin("nav_msg","Orders");
		return "orderlist";
	}
	
	public String shopchart() throws Exception{	
		User u = (User) getSessionUser();
		ShoppingCartService shoppingCart = (ShoppingCartService)getBean("shoppingCartService");
		List list = shoppingCart.queryShoppingCart(u);
		String include_uri = "shopcart";
		getRequest().setAttribute("uri", include_uri);
		getRequest().setAttribute("list",list);
		assgin("nav_msg","Shopping Cart");
		return "shoppingcart";
	}
	public String address() throws Exception{
		User u = (User) getSessionUser();
		AddrService addrService = (AddrService)getBean("addr");
		List<Map<String,Object>> list = null;
		list = addrService.getList(u.getUserId());
		String include_uri = "addrlist";
		getRequest().setAttribute("uri", include_uri);
		getRequest().setAttribute("adrrlist", list);
		assgin("nav_msg","Address");
		return "addrlist";
	}
	
	public String toaddaddress() throws Exception{
		String include_uri = "addrmsg";
		getRequest().setAttribute("uri", include_uri);
		return "addrmsg";
	}
	
	public String orderPay() throws Exception{
		
		return "ok";
	}
	
	
	public String removeOrder() throws Exception{
		String order = (String)getRequest().getParameter("order");
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		User u = (User)getSessionUser();
		int ret = 0;
		ret = orderSrv.delOrder(order, u);
		String include_uri = "msg";
		String msg="";
		if(ret!=1){
			msg="Remove Order Error";
		}else{
			msg ="Remove Order Success";
		}
		getRequest().setAttribute("uri", include_uri);
		assgin("msg",msg);
		assgin("nav_msg","Remove Order");
		return "msg";
	}
	
	public String confirmReceipt() throws Exception{
		String orderid = (String)getRequest().getParameter("order_id");
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		User u = (User)getSessionUser();
		int ret = 0;
		ret = orderSrv.recevieMsg(u, orderid);
		String msg="";
		String result="msg";
		String include_uri = "msg";
		if(ret!=1){
			msg="Confirm Receipt Error";
		}else{
			msg ="Confirm Receipt Success";
		}
		getRequest().setAttribute("uri", include_uri);
		assgin("msg",msg);
		
		return result;
	}

	public String orderFlow() throws Exception{
		String orderId = (String)getRequest().getParameter("oid");
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		User u = (User)getSessionUser();
		List<Map<String,Object>> orderList = null;
		List<Map<String,Object>> flowList = null;
		List<Map<String,Object>> shiplist = null; 
		List<Map<String,Object>> couriermsg = null; 
		
		flowList = orderSrv.orderOpMsgList(orderId,u.getUserId(),1);
		shiplist = orderSrv.orderShipList(orderId,u.getUserId());
		couriermsg = orderSrv.orderCourierMsg(orderId);
		orderId = "'"+orderId+"'";
		orderList = orderSrv.orderProListByOrderId(orderId);
		Map<String,Object> map = null;
		for(int i = 0 ;i<couriermsg.size() ;i++){
			map = couriermsg.get(0);
			assgin("c_name",map.get("courier_cn_name"));
			assgin("c_name_en",map.get("courier_en_name"));
		}
			
					
		String include_uri = "orderdetail";
		getRequest().setAttribute("uri", include_uri);
		assgin("orderlist",orderList);
		assgin("flowlist",flowList);
		assgin("shiplist",shiplist);

		return "orderdetail";
	}
	
	
	
	
	
}
