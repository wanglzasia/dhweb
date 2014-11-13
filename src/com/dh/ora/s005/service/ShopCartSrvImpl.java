package com.dh.ora.s005.service;

import java.util.List;
import java.util.Map;

import com.x.orange.Service;
import com.x.orange.dao.Dao;

public class ShopCartSrvImpl extends Service implements ShopCartSrv {

	public List<Map<String,Object>> goodList(String userid) throws Exception{
		List<Map<String,Object>> list = null;
		Dao dao = getDao();
		String sql ="SELECT a.product_id,c.product_name,a.product_count,a.product_count*a.product_value as product_value, a.product_value as single_value,b.pic_name_1 ";
		sql= sql+" FROM wshoppingcartmsg a, dproductothermsg b, dproductmsg c WHERE  a.user_id = ? ";
		sql= sql+" AND a.product_id = b.product_id AND a.product_id = c.product_id";
		dao.addParam(new Object[]{userid});
		list = dao.query(sql);
		return list;
	}
}
