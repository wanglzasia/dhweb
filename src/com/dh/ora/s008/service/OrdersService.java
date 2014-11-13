package com.dh.ora.s008.service;

import java.util.List;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s008.bean.DeliveryMode;
import com.dh.ora.s008.bean.Ordermsg;
import com.dh.ora.s008.bean.Orderproductrel;
import com.dh.ora.s008.bean.ProductOtherMsg;

public interface OrdersService {
	
	public List getUserAddrs(User u) throws Exception;
	
	public DeliveryMode getDeliveryMode(String delivery_mode_id) throws Exception;
	public double getProductFav(String user_fav_id, double product_value) throws Exception;
	public ProductOtherMsg getProductOtherMsg(String product_id) throws Exception;
	public boolean updateOrderStatus(List order_id_list, String order_status) throws Exception;
	public int saveOrder(List<Ordermsg> orderList ,List<Orderproductrel> proRelList);
	public int updateOrderMsg(String orderId,String status,String addrid,User u);
	public double getOrderPrice(String orderId);
	public int updateOrderStatus(String orderId,String status);
}
