package com.dh.ora.s008.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s003.service.OrderSrv;
import com.dh.ora.s003.service.ProductSrv;
import com.dh.ora.s004.service.CourierSrvImpl;
import com.dh.ora.s006.serivce.ShoppingCartService;
import com.dh.ora.s008.bean.Ordermsg;
import com.dh.ora.s008.bean.Orderproductrel;
import com.dh.ora.s008.service.OrdersService;
import com.dh.ora.s009.service.AddrService;
import com.x.orange.Action;
import com.x.orange.Config;
import com.x.orange.dao.Dao;

/**
 * 订单信息
 * @author liuxm
 *
 */
public class OrdersAction extends Action {

	/**
	 * 订单确认，一个商品
	 * @liuxm
	 */
	public String orderConfirmationSingle() throws Exception{
		//1、进入购物车,2、引导进入购物车界面
				
		String seller = (String)getRequest().getParameter("seller_id");
		String pid = (String)getRequest().getParameter("product_id");
		String pcount = (String)getRequest().getParameter("product_count");
		ShoppingCartService shoppingCart = (ShoppingCartService)getBean("shoppingCartService");
		User u = (User) getSessionUser();
		int ret =0;
		ret = shoppingCart.addProduct(pid, pcount, seller, u.getUserId());
		String msg="操作失败";
		if(ret==1){
			//记录购物车成功,引导进入购物车界面
			getResponse().sendRedirect("/member/queryShoppingCart.do");
		}else{
			assgin("msg",msg);
			return "gl_error";
		}
		return null;
	}
	
	/**
	 * 
	 * @Method:	OrdersAction::orderConfirmationBatch
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年9月24日下午2:26:49
	 * @throws 
	 * @author wanglz
	 * @Description: 
	 */
	public String orderConfirmationBatch() throws Exception{
		
		String products = (String)getRequest().getParameter("products");
		String buycounts = (String)getRequest().getParameter("buycounts");
		String shipfees = (String)getRequest().getParameter("shipfees");
		String profees = (String)getRequest().getParameter("profees");
		String sellers = (String)getRequest().getParameter("sellers");
		String singlefees =(String)getRequest().getParameter("singlefees");
		
		StringTokenizer st_pro = new StringTokenizer(products,",");
		StringTokenizer st_buycount = new StringTokenizer(buycounts,",");
		StringTokenizer st_shipfee = new StringTokenizer(shipfees,",");
		StringTokenizer st_fee = new StringTokenizer(profees,",");
		StringTokenizer st_seller = new StringTokenizer(sellers,",");
		StringTokenizer st_singlefee = new StringTokenizer(singlefees,",");
		
		
		List<String> sellerList = new ArrayList<String>();
		
		String proid="";
		String buycount="";
		String shipfee="";
		String profee="";
		String seller="";
		String remark="";
		String singlefee="";
		String orderids="";
		
		Dao dao = Config.getDao();
		List<Ordermsg> orderList = new ArrayList<Ordermsg>();
		List<Orderproductrel> proRelList = new ArrayList<Orderproductrel>();
		while(st_pro.hasMoreElements()){
			//记录此笔交易中产品信息
			proid = st_pro.nextToken();
			buycount = st_buycount.nextToken();
			shipfee = st_shipfee.nextToken();
			profee = st_fee.nextToken();
			//cart = st_cart.nextToken();
			seller = st_seller.nextToken();
			singlefee = st_singlefee.nextToken();
			if(!sellerList.contains(seller)){
				sellerList.add(seller);
			}
			remark = getRequest().getParameter("remark_"+proid);
			Orderproductrel orderProRel = new Orderproductrel();
			orderProRel.setProduct_id(proid);
			orderProRel.setProduct_count(Integer.valueOf(buycount));
			orderProRel.setFixed_price(Double.parseDouble(singlefee));
			orderProRel.setTrans_price(Double.parseDouble(singlefee));
			orderProRel.setRemark(remark);
			orderProRel.setSellerid(seller);
			orderProRel.setShip_cost(Double.parseDouble(shipfee));
			//orderProRel.setCartid(cart);
			proRelList.add(orderProRel);
		}
		User u = (User) getSessionUser();
		//拆分订单,一个卖家一个订单
		Orderproductrel tmpOrderProRel = null;
		st_seller = new StringTokenizer(sellers,",");
		double postage = 0.00;
		double fixPrice = 0.00;
		double trans_price = 0.00;
		
		for(int l =0 ; l<sellerList.size() ;l++){
			seller = sellerList.get(l);
			for(int i = 0 ;i<proRelList.size() ;i++){
				tmpOrderProRel = proRelList.get(i);
				if(seller.equals(tmpOrderProRel.getSellerid())){
					
					postage += tmpOrderProRel.getShip_cost();
					fixPrice += tmpOrderProRel.getFixed_price() * tmpOrderProRel.getProduct_count();
				}
			}
			trans_price = postage + fixPrice;
			
			Ordermsg order = new Ordermsg();
			order.setOrder_id(dao.find("select nextval('orderid')"));
			order.setBuyer_id(u.getUserId());
			order.setFav_price(0.00);
			order.setFixed_price(fixPrice);
			order.setPostage(postage);
			order.setSeller_id(seller);
			order.setAddr_id("0");//
			order.setTrans_Price(trans_price);
			orderids = "'"+order.getOrder_id()+"',";
			orderList.add(order);
		}
		
		int ret = 0;
		OrdersService orders = (OrdersService) getBean("orders");
		ret = orders.saveOrder(orderList,proRelList);
		String msg="";
		if(ret!=1){
			msg ="操作失败";
			assgin("msg",msg);
			return "gl_error";
		}else{
			//操作成功,返回结算中心界面
			//包含：1用户地址信息,2订单数据信息
			//确定后,更新订单状态，邮寄地址
			orderids = orderids.substring(0,orderids.length()-1);
			OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
			AddrService addrService = (AddrService)getBean("addr");

			List<Map<String,Object>> list = null;
			list = orderSrv.orderProListByOrderId(orderids);
			
			List<Map<String,Object>> addrlist = null;
			addrlist = addrService.getList(u.getUserId());
			
			getRequest().setAttribute("adrrlist", addrlist);
			getRequest().setAttribute("orderlist", list);
			return "ok";
		}
	}
	
	/**
	 * 付款成功
	 * @return
	 * @throws Exception
	 */
	public String submit_orders() {
		
		try {
			
			// 获取买方用户信息
			User u = (User) getSessionUser();
			
			List<String> order_id_list = (List<String>) getSession().get("order_id_list");
			
			OrdersService orders = (OrdersService) getBean("orders");
			
			String order_status = "2";///卖家已付款
			
			orders.updateOrderStatus(order_id_list, order_status);
			
			return "ok";
			
		} catch (Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	
	
	public String confirOrder() throws Exception{
		OrderSrv orderSrv = (OrderSrv)getBean("orderSrv");
		AddrService addrService = (AddrService)getBean("addr");
		String orderid = (String)getRequest().getParameter("orderid");
		
		List<Map<String,Object>> list = null;
		List<Map<String,Object>> addrlist = null;
		list = orderSrv.orderProListByOrderId("'"+orderid+"'");
		User u = (User) getSessionUser();
		addrlist = addrService.getList(u.getUserId());
		getRequest().setAttribute("adrrlist", addrlist);	
		getRequest().setAttribute("orderlist", list);
		return "ok";
	}
	
	public String confirmAndPay() throws Exception{
		String addrid = (String)getRequest().getParameter("default_addr");
		String orderid = (String)getRequest().getParameter("orderid");
		OrdersService orders = (OrdersService) getBean("orders");
		User u = (User)getSessionUser();
		//这步为订单确定过程,并没有付款,确认后,引导进入第三方界面，进行支付，然后确认付款.
		orders.updateOrderMsg(orderid, "1", addrid,u);
		double orderPrice = 0;
		orderPrice = orders.getOrderPrice(orderid);
		assgin("orderid",orderid);
		assgin("price",orderPrice);
		return "parter";
	}
	
	public String pay() throws Exception{
		String orderid = (String)getRequest().getParameter("orderid");
		OrdersService orders = (OrdersService) getBean("orders");

		int ret = 0;
		//卖家已经付款
		ret = orders.updateOrderStatus(orderid,"2");
		String msg="error";
		if(ret==1){
			return "ok";
		}else{
			assgin("msg",msg);
			return "gl_error"; 
		}
	}
}
