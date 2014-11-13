package com.dh.ora.s006.serivce;

import java.util.List;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s006.bean.ShoppingCart;

public interface ShoppingCartService {
	
	public boolean addShoppingCart(ShoppingCart sc) throws Exception;
	
	public boolean deleteShoppingCart(String  shoppingcart_id) throws Exception;
	
	public boolean deleteShoppingCart(List ls) throws Exception;
	
	public List queryShoppingCart(User u) throws Exception;
	
	public boolean updateShoppingCartCount(String  shoppingcart_id,String product_count);
	
	
	public int addProduct(String pid,String count,String seller ,String buyer) throws Exception;
	public int removeProduct(String pid,String buyer) throws Exception;
	public int updateCount(String pid,int afterCount,String seller ,String buyer) throws Exception;

	
}
