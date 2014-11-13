package com.dh.ora.s008.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s008.bean.DeliveryMode;
import com.dh.ora.s008.bean.Ordermsg;
import com.dh.ora.s008.bean.Orderproductrel;
import com.dh.ora.s008.bean.ProductOtherMsg;
import com.dh.ora.s008.bean.UserFav;
import com.dh.ora.s009.bean.Postalinfo;
import com.x.orange.Config;
import com.x.orange.Service;
import com.x.orange.dao.Dao;

public class OrdersServiceImpl extends Service implements OrdersService {

	/**
	 * 获取收件人地址信息
	 * @liuxm
	 */
	@Override
	public List getUserAddrs(User u) throws Exception {
		
		Dao dao = getDao();
		
		List addrs_list = new ArrayList();
		
		String sql = "select user_id,addr_id,recipient,address,postal_code,phone_no from dpostalinfo ";
		sql += " where user_id = '"+u.getUserId()+"'";
		
		List <Map<String , Object>> ls= dao.query(sql);
		
		if(ls != null){
			
			for(Iterator it = ls.iterator() ; it.hasNext();){
				
				Map<String , Object> map = ( Map<String , Object> )it.next();
				
				Postalinfo pi = new Postalinfo();
				
				pi.setUser_id((String)map.get("user_id"));
				
				pi.setAddr_id((String)map.get("addr_id"));
				
				pi.setRecipient((String)map.get("recipient"));
				
				pi.setAddress((String)map.get("address"));
				
				pi.setPostal_code((String)map.get("postal_code"));
				
				pi.setPhone_no((String)map.get("phone_no"));
				
				addrs_list.add(pi);
				
			}
			
		}
		
		return addrs_list;
	}

	/**
	 * 获取卖家的配送方式
	 * @liuxm
	 */
	@Override
	public DeliveryMode getDeliveryMode(String delivery_mode_id) throws Exception {
		Dao dao = getDao();
		
		String sql = "select delivery_mode_id, seller_id, delivery_mode_name, delivery_mode_value from sdeliverymodemsg ";
		sql += " where delivery_mode_id = '"+delivery_mode_id+"'";
		
		List <Map<String , Object>> ls= dao.query(sql);
		
		List<DeliveryMode> list = new ArrayList<DeliveryMode>();
		
		if(ls != null){
			
			for(Iterator it = ls.iterator(); it.hasNext();){
				Map<String, Object> map = (Map<String, Object>) it.next();
				DeliveryMode dm = new DeliveryMode();
				dm.setDelivery_mode_id((String) map.get("delivery_mode_id"));
				dm.setSeller_id((String) map.get("seller_id"));
				dm.setDelivery_mode_name((String) map.get("delivery_mode_name"));
				dm.setDelivery_mode_value((Double) map.get("delivery_mode_value"));
				
				list.add(dm);
			}
			
		}
		
		DeliveryMode ret_dm = new DeliveryMode();
		
		if(list.size() > 0 ){
			ret_dm = list.get(0);
		}
		
		return ret_dm;
	}
	
	
	/**
	 * 获取商品优惠后金额
	 * @liuxm
	 */	
	public double getProductFav(String user_fav_id, double product_value){
		
		if(user_fav_id == null){
			return product_value;
		}
		
		Dao dao = getDao();
		
		String sql = "select user_fav_id, seller_id, user_fav_type, user_fav_name, user_fav_rule from suserfavmsg ";
		sql += " where user_fav_id='"+user_fav_id+"'";
		
		List <Map<String , Object>> ls= dao.query(sql);
		
		if(ls != null){
			
			for(Iterator it = ls.iterator(); it.hasNext();){
				
				Map<String , Object> map = (Map<String , Object>)it.next();
				
				UserFav userFav = new UserFav();
				
				userFav.setUser_fav_id((String) map.get("user_fav_id"));
				userFav.setSeller_id((String) map.get("seller_id"));
				userFav.setUser_fav_type((String) map.get("user_fav_type"));
				userFav.setUser_fav_name((String) map.get("user_fav_name"));
				userFav.setUser_fav_rule((String) map.get("user_fav_rule"));
				
				///当优惠方式为 - 时，为降价规则
				if("-".equals(userFav.getUser_fav_type())){
					
					product_value = product_value - Double.parseDouble(userFav.getUser_fav_rule());
				}
				///当优惠方式为 * 时，为打折规则
				else if("*".equals(userFav.getUser_fav_type())){
					product_value = product_value * Double.parseDouble(userFav.getUser_fav_rule());
				}
				
			}
			
		}
		
		
		return product_value;
	}
	
	/**
	 * 获取产品其他信息
	 * @liuxm
	 */
	public ProductOtherMsg getProductOtherMsg(String product_id){
		
		
		 
		Dao dao = getDao();
		
		String sql = "select a.product_id,a.product_no,a.pic_name_1,a.pic_name_2,a.pic_name_3,a.pic_name_4,a.pic_name_5,a.pic_name_6,a.html_full_name,a.delivery_key,b.product_name";
		sql = sql+" from dproductothermsg a ,dproductmsg b ";
		sql += " where a.product_id='"+product_id+"' and a.product_id = b.product_Id";
		
		List<ProductOtherMsg> list = new ArrayList();
		
		List <Map<String , Object>> ls= dao.query(sql);
		
		if(ls != null){
			for(Iterator it = ls.iterator(); it.hasNext();){
				Map<String , Object> map = (Map<String , Object>) it.next();
				
				ProductOtherMsg msg = new ProductOtherMsg();
				
				msg.setProduct_id((String) map.get("product_id"));
				msg.setPic_name_1((String) map.get("pic_name_1"));
				msg.setPic_name_2((String) map.get("pic_name_2"));
				msg.setPic_name_3((String) map.get("pic_name_3"));
				msg.setPic_name_4((String) map.get("pic_name_4"));
				msg.setPic_name_5((String) map.get("pic_name_5"));
				msg.setPic_name_6((String) map.get("pic_name_6"));
				msg.setHtml_full_name((String) map.get("html_full_name"));
				msg.setDelivery_key((String) map.get("delivery_key"));
				//msg.setProdcut_name((String) map.get("product_name"));
				list.add(msg);
			}
		}
			
		ProductOtherMsg ret_msg = new ProductOtherMsg();
		
		if(list.size() > 0){
			ret_msg = list.get(0);
		}
		
		return ret_msg;
	}
	
	/**
	 * 更新订单状态,记录订单工作流
	 */
	public int updateOrderMsg(String orderId,String status,String addrid,User u){
		int ret = 1;
		Dao dao = getDao();
		String sql=" update dordermsg set order_status='"+status+"' ,addr_id='"+addrid+"' where order_id='"+orderId+"'";
		dao.addBatch(sql);
		sql="insert into dorder_op_msg (order_id,op_login,op_code,op_time,op_note,user_id) VALUES ('"+orderId+"','"+u.getLoginNo()+"','1001',now(),'订单确认','"+u.getUserId()+"')";
		dao.addBatch(sql);
		int[] retV = dao.execBatch();
		for(int i = 0 ;i<retV.length;i++){
			if(!(retV[i]>0)){
				ret = 0;
				break;
			}
		}
		return ret;
	}
	
	public double getOrderPrice(String orderId){
		double ret = 0.00;
		Dao dao = getDao();
		String sql="select trans_price from dordermsg where order_id=?";
		dao.addParam(new Object[]{orderId});
		List<Map<String,Object>> list = null;
		Map<String,Object> map = null;
		list = dao.query(sql);
		map = list.get(0);
		ret = Double.parseDouble((String)map.get("trans_price"));
		return ret;
	}
	
	public int updateOrderStatus(String orderId,String status){
		int ret = 0;
		Dao dao = getDao();
		String sql="update dordermsg set order_status=? where order_id=?";
		dao.addParam(new Object[]{status,orderId});
		ret = dao.execute(sql);
		return ret;
	}
	
	public boolean updateOrderStatus(List order_id_list, String order_status){
		
		Dao dao = getDao();
		
		if( order_id_list != null ){
			
			for(Iterator it = order_id_list.iterator(); it.hasNext();){
				String order_id = (String) it.next();
				
				String sql = "update dordermsg set order_status='"+order_status+"' ";
				sql += " where order_id='"+order_id+"'";
				
				dao.addBatch(sql);
			}
			
		}
		
		dao.execBatch();
		
		return true;
	}
	/**
	 * 
	 */
	public int saveOrder(List<Ordermsg> orderList ,List<Orderproductrel> proRelList){
		int ret = 1;
		String order_msg_sql="";
		String order_rel_sql="";
		String cart_msg_sql="";
		Ordermsg order = null;
		String orderId="";
		Dao dao = Config.getDao();
		Orderproductrel orderrel=null;
		
		String buyId="";
		String productIds="";

		List<String> productList = new ArrayList<String>();
		for(int i = 0 ;i<orderList.size(); i++){
			
			order = orderList.get(i);
			orderId = order.getOrder_id();
			order_msg_sql = " insert into dordermsg(";
			order_msg_sql +=  "order_id, seller_id, buyer_id,";
			order_msg_sql +=  "order_time, update_time, postage,";
			order_msg_sql +=  "addr_id, fixed_price, fav_price,";
			order_msg_sql +=  "trans_Price, order_status, note )";
			order_msg_sql += "values (";
			order_msg_sql +=	"'"+orderId+"','"+order.getSeller_id()+"','"+ order.getBuyer_id()+"',";
			order_msg_sql +=  "now(),now(),"+ order.getPostage()+",";
			order_msg_sql +=  "'"+order.getAddr_id()+"',"+ order.getFixed_price()+","+ order.getFav_price()+",";
			order_msg_sql +=  order.getTrans_Price()+",'1',''";
			order_msg_sql += ") ";
			dao.addBatch(order_msg_sql);
			buyId = order.getBuyer_id();
			
			for(int j = 0 ;j<proRelList.size();j++){
				orderrel = proRelList.get(j);
				order_rel_sql = " insert into dorderproductrel(";
				order_rel_sql += "order_id, product_id, product_count,";
				order_rel_sql += "fixed_price, trans_price, update_time,remark,postage";
				order_rel_sql += ") values(";
				order_rel_sql += "'"+orderId+"','"+ orderrel.getProduct_id()+"',"+ orderrel.getProduct_count()+",";
				order_rel_sql += orderrel.getFixed_price()+","+ orderrel.getTrans_price()+", now(),'"+orderrel.getRemark()+"'";
				order_rel_sql += ",'"+orderrel.getShip_cost()+"' ) ";
				dao.addBatch(order_rel_sql);
				
				if(!productList.contains(orderrel.getProduct_id())){
					productList.add(orderrel.getProduct_id());
				}
			}
		}
		
		for(int k =0 ;k<productList.size() ;k++){
			productIds += "'"+productList.get(k)+"',";
		}
		productIds = productIds.substring(0, productIds.length()-1);
		cart_msg_sql="delete from wshoppingcartmsg where product_id in ("+productIds+") and user_id='"+buyId+"'";
		dao.addBatch(cart_msg_sql);
		
		int retvalue[] = dao.execBatch();
		for(int k = 0 ;k<retvalue.length; k++){				
			if(retvalue[k]<1){
				ret = 0;
				break;
			}
		}
		
		return ret;
	}
	
}
