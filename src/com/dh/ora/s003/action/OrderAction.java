package com.dh.ora.s003.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s003.service.OrderSrv;
import com.dh.ora.s004.service.CourierSrv;
import com.x.orange.Action;

public class OrderAction extends Action{
	
	private static final long serialVersionUID = -1877669330191708433L;

	public String init(){
		String uri = "seller_order";
		getRequest().setAttribute("uri", uri);
		assgin("nav_msg","订单管理");
		return "init";
	}

	public String orderList() throws Exception{
		String uri = "seller_order";
		User usr = (User) getSessionUser();
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		List<Map<String,Object>> list = orderSrv.orderList(getRequest(), usr.getUserId());
		List<Map<String,Object>> productList = orderSrv.orderProductListByOrderList(list);
		request.setAttribute("uri", uri);
		request.setAttribute("list", list);
		request.setAttribute("proList", productList);
		assgin("nav_msg","订单管理");
		return "orderlist";
	}
	
	public String orderFav() throws Exception{
		String uri = "order_fav";
		User usr = (User) getSessionUser();
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		List<Map<String,Object>> list = orderSrv.orderList(getRequest(), usr.getUserId());
		if(list.size()==0){
			getResponse().sendError(404, "没有此订单");
			return null;
		}
		List<Map<String,Object>> productList = orderSrv.orderProductListByOrderList(list);
		request.setAttribute("uri", uri);
		request.setAttribute("list", list);
		request.setAttribute("proList", productList);
		assgin("nav_msg","订单优惠");
		return "orderfav";
	}
	
	public String getOrderFav() throws Exception{
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		String orderId = (String)getRequest().getParameter("order_id");
		String favValue = (String)getRequest().getParameter("fav_value");
		String retValue = orderSrv.getOrderFav(orderId, favValue); 
		this.getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(retValue);
		return null;
	}
	
	public String chgOrderPrice() throws Exception{
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		User usr = (User) getSessionUser();
		String orderId = (String)getRequest().getParameter("order_id");
		String favValue = (String)getRequest().getParameter("fav_value");
		orderSrv.setOrderFav(orderId, favValue, usr.getUserId());
		String uri = "order_fav";

		List<Map<String,Object>> list = orderSrv.orderList(getRequest(), usr.getUserId());
		if(list.size()==0){
			getResponse().sendError(404, "没有此订单");
			return null;
		}
		List<Map<String,Object>> productList = orderSrv.orderProductListByOrderList(list);
		request.setAttribute("uri", uri);
		request.setAttribute("list", list);
		request.setAttribute("proList", productList);
		return "orderfav";
	}
	
	public String delOrderMain() throws Exception{
		String uri = "order_del";
		User usr = (User) getSessionUser();
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		List<Map<String,Object>> list = orderSrv.orderList(getRequest(), usr.getUserId());
		if(list.size()==0){
			getResponse().sendError(404, "没有此订单");
			return null;
		}
		List<Map<String,Object>> productList = orderSrv.orderProductListByOrderList(list);
		request.setAttribute("uri", uri);
		request.setAttribute("list", list);
		request.setAttribute("proList", productList);
		assgin("nav_msg","删除订单");
		return "orderdel";
	}
	
	public String delOrder() throws Exception{
		String uri = "msg";
		User usr = (User) getSessionUser();
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		String orderId = (String)getRequest().getParameter("order_id");
		int ret = 0 ;
		ret = orderSrv.delOrder(orderId, usr);
		String msg="订单"+orderId+"删除成功!";
		if(ret==1){
			 msg="订单"+orderId+"删除成功!";
		}else
		{
			msg ="error";
		}
 		request.setAttribute("uri", uri);
 		request.setAttribute("msg", msg);
		return "msg";
	}
	
	public String updateShipMsg() throws Exception{
		String order = (String)getRequest().getParameter("order_id");
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		List<Map<String,Object>> flowList = null;
 		List<Map<String,Object>> orderList = null;
 		List<Map<String,Object>> courierlist = null;
 		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		courierlist = courierSrv.courierList(getRequest());
		
 		User u = (User)getSessionUser();
 		orderList = orderSrv.orderList(u.getUserId(), "'"+order+"'",0);
 		flowList = orderSrv.orderOpMsgList(order,u.getUserId(),0);
 		
 		Map<String,Object> map = null;
 		map = orderList.get(0);
 		String cid = "";
 		String tcid="";
 		cid = (String)map.get("courier_id");
 		for(int i = 0 ;i<courierlist.size();i++){
 			map = courierlist.get(i);
 			tcid = (String)map.get("courier_id");
 			if(tcid.equals(cid)){
 				assgin("c_name",(String)map.get("courier_cn_name"));
 		 		assgin("c_name_en",(String)map.get("courier_en_name"));
 			}
 		}
 	
		String include_uri = "orderdetail";
		getRequest().setAttribute("uri", include_uri);
		assgin("orderlist",orderList);
		assgin("courierlist",courierlist);
		assgin("flowlist",flowList);
		assgin("nav_msg","订单管理");
		return "msg";
	}
	
	public String sendOk() throws Exception{
		String order = (String)getRequest().getParameter("order_id");
		String courier_id = (String)getRequest().getParameter("courier_id");
		String waybill_no = (String)getRequest().getParameter("waybill_no");
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		User u = (User)getSessionUser();
		int ret = 0;
		ret = orderSrv.sendOrderMsg(u, order, courier_id, waybill_no);
		String msg="操作失败";
		if(ret!=1){
			assgin("msg",msg);
			return "gl_error";
		}
		msg="操作成功";
		String include_uri = "msg";
		assgin("msg",msg);
		getRequest().setAttribute("uri", include_uri);
		return "msg";
	}
}
