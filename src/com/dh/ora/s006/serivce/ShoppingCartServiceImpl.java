package com.dh.ora.s006.serivce;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s003.service.ProductSrv;
import com.dh.ora.s006.bean.ShoppingCart;
import com.dh.ora.util.FormateMoney;
import com.x.orange.Config;
import com.x.orange.Service;
import com.x.orange.dao.Dao;

public class ShoppingCartServiceImpl extends Service implements ShoppingCartService{
	
	
	
	/**
	 * 给购物车增加一件商品
	 * @liuxm
	 */
	@Override
	public boolean addShoppingCart(ShoppingCart sc){
//		
//		Dao dao = Config.getDao();
//		
//		String kindCode = dao.find("select nextval('kindcode')"); //获取购物车唯一id
//		
//		sc.setShoppingcart_id(kindCode);
//		
//		String sql = "insert into wshoppingcartmsg(" ;
//		sql += "shoppingcart_id,seller_id,user_id,product_id," ;
//		sql += "product_img,product_name,product_value," ;
//		sql += "product_count,opt_date,product_note,";
//		sql += "user_fav_id,user_fav_name";
//		sql += ") " ;
//		sql += " values('"+sc.getShoppingcart_id()+"','"+sc.getSeller_id()+"','"+sc.getUser_id()+"','"+sc.getProduct_id()+"',";
//		sql += " ' "+sc.getProduct_img()+"','"+sc.getProduct_name()+"',"+sc.getProduct_value()+",";
//		sql += ""+sc.getProduct_count()+",now(),'"+sc.getProduct_note()+"',";
//		sql += "'"+sc.getUser_fav_id()+"','"+sc.getUser_fav_name()+"'";
//		sql += ")";
//		
//		dao.execute(sql);
		
		return true;
	}
	
	/**
	 * 给购物车删除一件商品
	 * @liuxm
	 */
	@Override
	public boolean deleteShoppingCart(String  shoppingcart_id){
		
		Dao dao = getDao();
		
		String sql = "delete from wshoppingcartmsg " ;
		sql += " where shoppingcart_id = '"+shoppingcart_id+"'";
		
		dao.execute(sql);
		
		return true;
	}
	
	/**
	 * 给购物车删除一件商品
	 * @liuxm
	 */
	@Override
	public boolean updateShoppingCartCount(String  shoppingcart_id,String product_count){
		
		Dao dao = getDao();
		
		String sql = "update wshoppingcartmsg set product_count="+product_count ;
		sql += " where shoppingcart_id = '"+shoppingcart_id+"'";
		
		dao.execute(sql);
		
		return true;
	}
	
	/**
	 * 给购物车删除多件商品
	 * @liuxm
	 */
	@Override
	public boolean deleteShoppingCart(List ls){
		
		Dao dao = getDao();
		
		String sql = "";
		
		if(ls != null){
			
			for(Iterator it = ls.iterator() ; it.hasNext();){
				
				String shoppingcart_id = (String)it.next();
				
				sql = "delete from wshoppingcartmsg " ;
				sql += " where shoppingcart_id = '"+shoppingcart_id+"'";
				
				dao.addBatch(sql);
				
			}
			
			dao.execBatch();
			
		}
		
		return true;
	}
	
	
	/**
	 * 查询用户购物车
	 * @liuxm
	 */
	@Override
	public List queryShoppingCart(User u){
		
	   Dao dao = getDao();
	   List<ShoppingCart> list = new ArrayList<ShoppingCart> ();
	   String sql="SELECT a.shoppingcart_id,a.seller_id,a.user_id,a.product_id, ";
       sql = sql + " ifnull(pic_name_1,'') product_img,b.product_name,a.product_count,a.product_value,";
       sql = sql + " ifnull(a.product_note,'') product_note,a.user_fav_id,a.user_fav_name,d.login_no, ";
       sql = sql +"  e.value,e.value_en ,a.product_count*a.product_value as single_sum_price";
       sql = sql + " FROM wshoppingcartmsg a, dproductmsg b,dproductothermsg c, dusermsg d,s_cfg_unit e";
       sql = sql + " WHERE a.user_id = '"+u.getUserId()+"'";
       sql = sql + " and a.product_id = b.product_id";
       sql = sql + " and a.product_id = c.product_id and a.seller_id = d.user_id and b.unit_code=e.code";
       sql = sql +" order by a.seller_id";
       List <Map<String , Object>> ls= dao.query(sql);
		
		if(ls != null){
			
			for(Iterator it =  ls.iterator() ;it.hasNext();){
				
				Map<String , Object> map = ( Map<String , Object> )it.next();
				
				ShoppingCart sc = new ShoppingCart();
				
				sc.setShoppingcart_id((String)map.get("shoppingcart_id"));
				sc.setSeller_id((String)map.get("seller_id"));
				sc.setUser_id((String)map.get("user_id"));
				sc.setProduct_id((String)map.get("product_id"));
				sc.setProduct_img((String)map.get("product_img"));
				sc.setProduct_name((String)map.get("product_name"));				
				sc.setProduct_count((String)map.get("product_count"));
				sc.setProduct_value((String)map.get("product_value"));
				sc.setProduct_note((String)map.get("product_note"));
				sc.setUser_fav_id((String)map.get("user_fav_id"));
				sc.setUser_fav_name((String)map.get("user_fav_name"));
				sc.setLoginNo((String)map.get("login_no"));
				sc.setSingleSumPrice((String)map.get("single_sum_price"));
				sc.setUnitValue((String)(map).get("value"));
				sc.setUnitValueEn((String)(map).get("value_en"));
				sc.setProduct_all_value(FormateMoney.formatMoney(Double.parseDouble((String)map.get("product_value"))*Long.parseLong((String)map.get("product_count"))+"",2));
				list.add(sc);
			}
			
		}
		
		return list;
	}
	
	
	public int addProduct(String pid,String count,String seller ,String buyer) throws Exception{
		int ret = 0;
		Dao dao = Config.getDao();
		ProductSrv productSrv =(ProductSrv)getBean("productSrv");
		String sql="";
		int pCount = 0;
		double price = 0.00;
		sql = "select count(1) count_ from wshoppingcartmsg where user_id=? and product_id=? and seller_id=?";
		dao.addParam(new Object[]{buyer,pid,seller});
		pCount = Integer.valueOf(dao.find(sql));
		int afterCount = pCount + Integer.valueOf(count);
		if(pCount==0){
			//根据购买数量,获取产品单价
			price = productSrv.price(pid, afterCount);
			sql="insert into wshoppingcartmsg(shoppingcart_id,seller_id,user_id,product_id,product_count,opt_date,product_value)"
					+ " values (0,?,?,?,?,now(),?)";
			dao.addParam(new Object[]{seller,buyer,pid,count,price});
			ret = dao.execute(sql);
		}else{
			ret = updateCount(pid,afterCount,seller ,buyer);
		}
		return ret;
	}
	
	public int removeProduct(String pid,String buyer) throws Exception{
		int ret = 0;
		String sql="delete from wshoppingcartmsg where product_id=? and user_id=?";
		Dao dao = Config.getDao();
		dao.addParam(new Object[]{pid,buyer});
		ret = dao.execute(sql);
		return ret;
	}
	
	public int updateCount(String pid,int afterCount,String seller ,String buyer) throws Exception{
		int ret = 0;
		Dao dao = Config.getDao();
		ProductSrv productSrv =(ProductSrv)getBean("productSrv");
		String sql="";	
		double price =0.00;
		price = productSrv.price(pid, afterCount);
		
		sql="update wshoppingcartmsg set product_count="+afterCount+",product_value=? where user_id=? "
				+ "and product_id=? and seller_id=?";
		dao.addParam(new Object[]{price,buyer,pid,seller});
		ret = dao.execute(sql);
		return ret;
	}

}
