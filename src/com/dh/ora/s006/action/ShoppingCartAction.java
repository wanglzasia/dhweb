package com.dh.ora.s006.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s003.service.ProductSrv;
import com.dh.ora.s006.bean.ShoppingCart;
import com.dh.ora.s006.serivce.ShoppingCartService;
import com.x.orange.Action;

public class ShoppingCartAction  extends Action{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 给用户的购物车添加一件商品
	 * @return
	 * @throws IOException
	 */
	public String addShoppingCart() throws Exception{
		
		this.getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		User u = (User) getSessionUser();
		
		String seller_id = request.getParameter("seller_id");

		System.out.println("=============seller_id======" + seller_id);
		String product_id = request.getParameter("product_id");
		String product_count = request.getParameter("product_count");
		//1、根据product_id 购买数量,配合dproductpricemsg表,获取优惠后的价格,并计算总价格
		String product_note = "";
		String product_value = "";
		
		String product_img = "";
		String product_name = "";
		String user_fav_id = "";
		String user_fav_name = "";
		
		ProductSrv productSrv =(ProductSrv)getBean("productSrv");
		double price = 0.00;
		price = productSrv.price(product_id, Integer.valueOf(product_count));
		//price = price * Double.valueOf(Integer.valueOf(product_count));
		product_value = String.valueOf(price);
		ShoppingCartService shoppingCart = (ShoppingCartService)getBean("shoppingCartService");
			
		ShoppingCart sc = new ShoppingCart();
		sc.setUser_id(u.getUserId());
		sc.setProduct_count(product_count);
		sc.setProduct_id(product_id);
		sc.setProduct_note(product_note);
		sc.setProduct_value(product_value);
		sc.setProduct_img(product_img);
		sc.setProduct_name(product_name);
		sc.setSeller_id(seller_id);
		sc.setUser_fav_id(user_fav_id);
		sc.setUser_fav_name(user_fav_name);
			
		boolean flag = shoppingCart.addShoppingCart(sc);
		
		if(null == getSession().get("shoppingcart")){
			
			Map<String,ShoppingCart> sc_map = new HashMap<String, ShoppingCart>();
			
			sc_map.put(sc.getShoppingcart_id(),sc);
			
			getSession().put("shoppingcart", sc_map);
			
		}else{
			Map<String,ShoppingCart> sc_map = new HashMap<String, ShoppingCart>();
			sc_map = (Map<String,ShoppingCart>) getSession().get("shoppingcart");
			
			sc_map.put(sc.getShoppingcart_id(),sc);
			
			getSession().remove("shoppingcart");
			getSession().put("shoppingcart", sc_map);
		}
		
		
		if(flag){
			out.println("添加购物车成功");
		}else{
			out.println("添加购物车失败！");
		}
		
		out.close();
		return null;
		
	}
	
	
	/**
	 * 查询一个用户的购物车
	 * @return
	 */
	public String queryShoppingCart() throws Exception{
		
		User u = (User) getSessionUser();
		
		ShoppingCartService shoppingCart = (ShoppingCartService)getBean("shoppingCartService");
		
		List list = shoppingCart.queryShoppingCart(u);
		
		getRequest().setAttribute("list",list);
		
		return "shoppingcart";
	}
	
	
	/**
	 * 修改购物车的单件商品的数量
	 * 目前未启用
	 * @throws IOException
	 */
	public void updateShoppingCartCount() throws IOException{
		
		String shoppingcart_id = request.getParameter("shoppingcart_id");
		String product_count = request.getParameter("product_count");
		
		ShoppingCartService shoppingCart = (ShoppingCartService)getBean("shoppingCartService");
		
		boolean flag = shoppingCart.updateShoppingCartCount(shoppingcart_id, product_count);
		
		this.getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(flag){
			
			Map<String,ShoppingCart> sc_map = new HashMap<String, ShoppingCart>();
			sc_map = (Map<String,ShoppingCart>) getSession().get("shoppingcart");
			
			sc_map.get(shoppingcart_id).setProduct_count(product_count);

			getSession().remove("shoppingcart");
			getSession().put("shoppingcart", sc_map);
			
			out.println("true");
		}else
			out.println("false");
		
		out.close();
	}
	
	/**
	 * 删除用户购物车的一件商品
	 * @throws IOException
	 */
	public void deleteShoppingCart() throws Exception{
		
		String shoppingcart_id = request.getParameter("shoppingcart_id");
		
		ShoppingCartService shoppingCart = (ShoppingCartService)getBean("shoppingCartService");
		
		boolean flag = shoppingCart.deleteShoppingCart(shoppingcart_id);
		
		this.getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(flag){
			
//			Map<String,ShoppingCart> sc_map = new HashMap<String, ShoppingCart>();
//			sc_map = (Map<String,ShoppingCart>) getSession().get("shoppingcart");
//			sc_map.remove(shoppingcart_id);
//			
//			getSession().remove("shoppingcart");
//			getSession().put("shoppingcart", sc_map);
			
			out.print("true");
		}else
			out.print("false");
		
		out.close();
	}
	
	public String fillCart() throws Exception{
		
		String seller = (String)getRequest().getParameter("seller_id");
		String pid = (String)getRequest().getParameter("product_id");
		String pcount = (String)getRequest().getParameter("product_count");
		ShoppingCartService shoppingCart = (ShoppingCartService)getBean("shoppingCartService");
		User u = (User) getSessionUser();
		int ret =0;
		ret = shoppingCart.addProduct(pid, pcount, seller, u.getUserId());
		String msg="操作失败";
		if(ret==1){
			return "ok";
		}else{
			assgin("msg",msg);
			return "gl_error";
		}
	}
	/**
	 * 
	 * @Method:	ShoppingCartAction::removePro
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年9月25日下午4:32:21
	 * @throws 
	 * @author wanglz
	 * @Description: 移除购物车内的产品,并返回当前购物车
	 */
	public String removePro() throws Exception{
		String pid = (String)getRequest().getParameter("p");
		ShoppingCartService shoppingCart = (ShoppingCartService)getBean("shoppingCartService");
		User u = (User) getSessionUser();
		int ret =0;
		ret = shoppingCart.removeProduct(pid,u.getUserId());
		String msg="操作失败";
		if(ret==1){
			return "ok";
		}else{
			assgin("msg",msg);
			return "gl_error";
		}
	}
	/**
	 * 
	 * @Method:	ShoppingCartAction::updateCart
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年9月25日下午4:31:59
	 * @throws 
	 * @author wanglz
	 * @Description: 更新购物车产品的数量,ajax返回
	 */
	public String updateCart()throws Exception{
		String seller = (String)getRequest().getParameter("seller_id");
		String pid = (String)getRequest().getParameter("product_id");
		String pcount = (String)getRequest().getParameter("product_count");
		ShoppingCartService shoppingCart = (ShoppingCartService)getBean("shoppingCartService");
		User u = (User) getSessionUser();
		int ret =0;
		//ret = shoppingCart.updateCount(pid, pcount, seller, u.getUserId());
		if(ret==1){
			
		}else{
			
		}
		return null;
	}
}
