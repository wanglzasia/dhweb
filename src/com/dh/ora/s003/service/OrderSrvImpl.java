package com.dh.ora.s003.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dh.ora.s001.bean.User;
import com.x.orange.Config;
import com.x.orange.Service;
import com.x.orange.dao.Dao;
import com.x.orange.dao.PageBean;

public class OrderSrvImpl extends Service implements OrderSrv {
	public List<Map<String,Object>> orderList(HttpServletRequest request,String usrid) throws Exception{
		
		String sql="";	
		sql = sql +" SELECT a.order_id,a.postage,a.fixed_price,a.fav_price,a.trans_price,a.order_time,b.addr,b.postal_code,";
		sql = sql +" b.mobile_phone,b.telephone,b.user_name,a.note, c.status_name order_status,a.order_status status_code,a.fixed_price-a.fav_price finnal_fee ";
		sql = sql +" FROM dordermsg a, duseraddrmsg b,dorderstatmsg c WHERE a.addr_id = b.addr_id and a.order_status!=0";
		sql = sql +" and a.order_status = c.order_status";
		
		List<String> paramlist = new ArrayList<String>();

		String beginDate =(String)request.getParameter("begin_date");
		String endDate =(String)request.getParameter("end_date");
		String status =(String)request.getParameter("status");
		String orderid =(String)request.getParameter("order_id");
		if(null!=beginDate && !beginDate.equals("")){
			sql = sql +" and a.order_time>= ?";
			paramlist.add(beginDate);
		}
		if(null!=endDate && !endDate.equals("")){
			sql = sql +" and a.order_time<= ?";
			paramlist.add(endDate);
		}
		if(null!=status && !status.equals("")){
			sql = sql +" and a.order_status=?";
			paramlist.add(status);
		}
		if(null!=orderid && !orderid.equals("")){
			sql = sql +" and a.order_id=?";
			paramlist.add(orderid);			
		}
		if(usrid!=""){
			sql = sql +" and a.seller_id=? ";
			paramlist.add(usrid);
		}
		
		sql = sql +" order by a.order_id ";
		
		
		Dao dao = getDao();
		Object[] param = null;
		if(paramlist.size()>0){
			param = new Object[paramlist.size()];
			for(int i = 0 ;i<paramlist.size();i++){
				param[i] = (String)paramlist.get(i);
			}
			dao.addParam(param);
		}
		String sql1 = "";
		sql1 = "select count(1) count_ from ( "+sql+" ) xxxxxx";
		String countStr = dao.find(sql1);
		
		PageBean pageBean = new PageBean(request,Integer.valueOf(countStr));
		if(paramlist.size()>0){
			dao.addParam(param);
		}
		List<Map<String,Object>> list = pageBean.pageList(dao, sql);
		request.setAttribute("pageBean", pageBean);
		return list;
	}
	
	//public List<Map<String,Object>> orderProductList(HttpServletRequest request,String usrId) throws Exception{		
		//方案1
		/*
		String sql="";
		sql = sql +" SELECT a.order_id,";
		sql = sql +" c.product_id,e.product_name,c.product_count,c.fixed_price,c.trans_price,f.pic_name_1,";
		sql = sql +" c.product_count*c.trans_price sum_price,g.value,g.value_en";
		sql = sql +" FROM dordermsg a ,duseraddrmsg b, dorderproductrel c, dproductmsg e,dproductothermsg f,s_cfg_unit g";
		sql = sql +" WHERE a.addr_id = b.addr_id AND a.order_id = c.order_id AND c.product_id = e.product_id ";
		sql = sql +" and e.unit_code = g.code ";
		sql = sql +" and c.product_id = f.product_id and a.order_id =?";
        sql = sql +" ORDER BY a.order_id";
        Dao dao  = getDao();
        dao.addParam(new Object[]{orderId});
		List<Map<String,Object>> list = dao.query(sql);
		*/
		//方案2
//		List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
//		orderList = orderList(request,usrId);
//		List<Map<String,Object>> list = orderProductListByOrderList(orderList);
//		return list;
	//}
	
	public List<Map<String,Object>> orderProductListByOrderList(List<Map<String,Object>> list) throws Exception{
		String sql="";
		Map<String,Object> map = null;
		String tmpOrderId="";
		String orderList ="";
		for(int i = 0 ;i<list.size();i++){
			map = list.get(i);
			tmpOrderId = (String)map.get("order_id");
			if(i<(list.size()-1)){
				orderList = orderList +"'"+tmpOrderId+"',";
			}else{
				orderList = orderList + "'"+tmpOrderId+"'";
			}
		}
		Dao dao = getDao();
		sql = sql +" SELECT a.order_id,";
		sql = sql +" c.product_id,e.product_name,c.product_count,c.fixed_price,c.trans_price,f.pic_name_1,";
		sql = sql +" c.product_count*c.trans_price sum_price,g.value,g.value_en";
		sql = sql +" FROM dordermsg a ,duseraddrmsg b, dorderproductrel c, dproductmsg e,dproductothermsg f,s_cfg_unit g";
		sql = sql +" WHERE a.addr_id = b.addr_id AND a.order_id = c.order_id AND c.product_id = e.product_id ";
		sql = sql +" and e.unit_code = g.code ";
		sql = sql +" and c.product_id = f.product_id and a.order_id ";
		if(list.size()==1){
			sql = sql + " = ?";
			dao.addParam(new Object[]{tmpOrderId});
		}else{
			sql = sql + " in ( "+orderList +" )";

		}
        sql = sql +" ORDER BY a.order_id";
		List<Map<String,Object>> retList = dao.query(sql);
		return retList;
	}
	
	public String getOrderFav(String orderId,String favValue) throws Exception{
		String sql="select postage+fixed_price - ? as trans_price from dordermsg where order_id=?";
		Object[] params = new Object[]{favValue,orderId};
		Dao dao = getDao();
		dao.addParam(params);
		String retValue = dao.find(sql);
		return retValue;
	}
	public int setOrderFav(String orderId,String favValue,String usrid) throws Exception{
		int ret = 0;
		String sql ="update dordermsg set fav_price=?,trans_price=postage+fixed_price-? where order_id=? and seller_id=?";
		Object[] params = new Object[]{favValue,favValue,orderId,usrid};
		Dao dao = getDao();
		dao.addParam(params);
		ret = dao.execute(sql);
		return ret;
	}
	
	public int delOrder(String orderId,User user) throws Exception{
		int ret = 1;
		String sql="update dordermsg set order_status=0 where order_id='"+orderId+"' and buyer_id='"+user.getUserId()+"' and order_status=1";
		Dao dao = getDao();
		dao.addBatch(sql);
		
		sql="insert into dorder_op_msg(order_id,op_login,op_code,op_time,op_note,user_id) values (";
		sql = sql +" '"+orderId+"' ,'"+user.getLoginNo()+"' ,'1004' ,now(),'删除订单','"+user.getUserId()+"'";
		sql = sql +")";
		dao.addBatch(sql);
		int[] ret1 = dao.execBatch();
		for(int i = 0 ;i<ret1.length ;i++){
			if(ret1[i]<1){
				ret = 0;
				break;
			}
		}
		
		return ret;
	}

	public List<Map<String,Object>> orderProListByUid(String uid) throws Exception{
		List<Map<String,Object>> list = null;
		String sql = "";
		sql = sql +" SELECT a.order_id,";
		sql = sql +" c.product_id,e.product_name,c.product_count,c.fixed_price,c.trans_price,f.pic_name_1,";
		sql = sql +" c.product_count*c.trans_price sum_price,g.value,g.value_en,";
		sql = sql +" a.postage,a.fixed_price order_fixed_price,a.fav_price order_fav_price,a.trans_price order_trans_price,a.order_time,h.postal_code,";
		sql = sql +" h.mobile_phone,h.telephone,h.user_name,h.addr,i.status_name_en,j.login_no,i.order_status,ifnull(c.remark,'') remark";
		sql = sql +" FROM dordermsg a ,duseraddrmsg b, dorderproductrel c, dproductmsg e,dproductothermsg f,s_cfg_unit g,";
		sql = sql +" duseraddrmsg h,dorderstatmsg i,dusermsg j";
		sql = sql +" WHERE a.addr_id = b.addr_id AND a.order_id = c.order_id AND c.product_id = e.product_id ";
		sql = sql +" and e.unit_code = g.code  AND a.addr_id = h.addr_id and a.order_status=i.order_status and a.seller_id = j.user_id";
		sql = sql +" and c.product_id = f.product_id and a.order_id and a.buyer_id = '"+uid+"' order by a.order_id desc";
		Dao dao = Config.getDao();
		list = dao.query(sql); 
		return list;
	}
	public  List<Map<String,Object>> orderProListByOrderId(String orders) throws Exception{
		List<Map<String,Object>> list = null;
		//String order = "";
		//StringTokenizer st = new StringTokenizer(orders,",");
		String sql = "";
		sql = sql +" SELECT a.order_id,";
		sql = sql +" c.product_id,e.product_name,c.product_count,c.fixed_price,c.trans_price,f.pic_name_1,";
		sql = sql +" c.product_count*c.trans_price sum_price,g.value,g.value_en,";
		sql = sql +" a.postage,a.fixed_price order_fixed_price,a.fav_price order_fav_price,a.trans_price order_trans_price,a.order_time,h.postal_code,";
		sql = sql +" h.mobile_phone,h.telephone,h.user_name,h.addr,i.status_name_en,j.login_no,i.order_status,ifnull(c.remark,'') remark,";
		sql = sql +" a.courier_id,a.waybill_no";
		sql = sql +" FROM dordermsg a ,duseraddrmsg b, dorderproductrel c, dproductmsg e,dproductothermsg f,s_cfg_unit g,";
		sql = sql +" duseraddrmsg h,dorderstatmsg i,dusermsg j";
		sql = sql +" WHERE a.addr_id = b.addr_id AND a.order_id = c.order_id AND c.product_id = e.product_id ";
		sql = sql +" and e.unit_code = g.code  AND a.addr_id = h.addr_id and a.order_status=i.order_status and a.seller_id = j.user_id";
		sql = sql +" and c.product_id = f.product_id and a.order_id ";
		/*
		while(st.hasMoreElements()){
			order = order+"'"+st.nextToken()+"',";
		}
		if(st.countTokens()>0){
			order = order.substring(0,order.length()-1);
		}else{
			order = "'"+order+"',";
		}*/
		
		sql = sql +" and a.order_id in ("+orders+")";
		sql = sql +" order by a.order_id desc";
		Dao dao = Config.getDao();
		list = dao.query(sql); 
		return list;
	}
	
	public List<Map<String,Object>> orderOpMsgList(String orderId,String uid,int cType) throws Exception{
		String sql="";
		Dao dao = Config.getDao();
		List<Map<String,Object>> list = null;
		sql="SELECT a.order_id,a.op_login,a.op_code,b.func_name,ifnull(b.func_name_en,'') "
				+" func_name_en ,a.op_note,a.op_time FROM dorder_op_msg a, sfunccode b,dordermsg c"
				+ " WHERE a.op_code = b.func_code ";
		if(!"".equals(orderId)){
			sql= sql + " and a.order_id=?";
			if(cType==0){
				sql= sql + " and c.seller_id=?";
			}else{
				sql= sql + " and c.buyer_id=?";
			}
			dao.addParam(new Object[]{orderId,uid});
		}
		
		sql= sql +" and a.order_id = c.order_id ";
		sql= sql +" order by a.op_time";
		list = dao.query(sql);
		return list;
	}
	
	public List<Map<String,Object>> orderShipList(String orderId,String user_id) throws Exception{
		String sql="";
		List<Map<String,Object>> list = null;
		List<String> paramlist = new ArrayList<String>();
		Object[] params = null;
		Dao dao = getDao();
		sql="";

		sql = sql +" SELECT a.order_id,a.login_no,a.user_id,a.op_date,                    "; 
		sql = sql +"       a.courier_id,b.courier_cn_name,b.courier_en_name,a.waybill_no  ";
		sql = sql +"  FROM dordershipmsg a,dcouriermsg b                                  ";
		sql = sql +"  where a.courier_id = b.courier_id                                   ";
		
		if(!"".equals(orderId)){
			sql= sql + " and a.order_id=?";
			paramlist.add(orderId);
		}		
		if(!"".equals(user_id)){
			sql= sql + " and a.user_id=?";
			paramlist.add(user_id);			
		}
		sql = sql +" ORDER BY a.op_date                                                   ";
		int j = 0;
		j = paramlist.size();
		if(paramlist.size()>0){
			params = new Object[j];
		}
		for(int i = 0 ;i<paramlist.size();i++){
			params[i] = paramlist.get(i);
		}
		if(paramlist.size()>0){
			dao.addParam(params);
		}
		list = dao.query(sql);
		return list;
	}

	public List<Map<String, Object>> orderList(String uid, String orderids,int uType)
			throws Exception {
		List<Map<String,Object>> list = null;

		String sql = "";
		sql = sql +" SELECT a.order_id,";
		sql = sql +" c.product_id,e.product_name,c.product_count,c.fixed_price,c.trans_price,f.pic_name_1,";
		sql = sql +" c.product_count*c.trans_price sum_price,g.value,g.value_en,";
		sql = sql +" a.postage,a.fixed_price order_fixed_price,a.fav_price order_fav_price,a.trans_price order_trans_price,a.order_time,h.postal_code,";
		sql = sql +" h.mobile_phone,h.telephone,h.user_name,h.addr,i.status_name_en,j.login_no,i.order_status,ifnull(c.remark,'') remark,";
		sql = sql +" a.courier_id,a.waybill_no";
		sql = sql +" FROM dordermsg a ,duseraddrmsg b, dorderproductrel c, dproductmsg e,dproductothermsg f,s_cfg_unit g,";
		sql = sql +" duseraddrmsg h,dorderstatmsg i,dusermsg j";
		sql = sql +" WHERE a.addr_id = b.addr_id AND a.order_id = c.order_id AND c.product_id = e.product_id ";
		sql = sql +" and e.unit_code = g.code  AND a.addr_id = h.addr_id and a.order_status=i.order_status and a.seller_id = j.user_id";
		sql = sql +" and c.product_id = f.product_id ";
		sql = sql +" and a.order_id in ("+orderids+")";
		
		if(uType==0){
			sql = sql +" and a.seller_id='"+uid+"'";
		}
		if(uType==1){
			sql = sql +" and a.buyer_id='"+uid+"'";
		}
		
		sql = sql +" order by a.order_id desc";
		Dao dao = Config.getDao();
		list = dao.query(sql); 
		return list;
	}
	
	public int sendOrderMsg(User user,String orderid,String courier_id,String waybill_no) throws Exception{
		int ret = 1;
		Dao dao = Config.getDao();
		String sql ="";
		sql="update dordermsg set order_status=3,courier_id='"+courier_id+"' ,waybill_no='"+waybill_no+"' "
				+ " where order_id='"+orderid+"' and seller_id='"+user.getUserId()+"'";
		dao.addBatch(sql);
		
		sql="insert into dorder_op_msg(order_id,op_login,op_code,op_time,op_note,user_id) values (";
		sql = sql +" '"+orderid+"' ,'"+user.getLoginNo()+"' ,'1003' ,now(),'订单发货','"+user.getUserId()+"'";
		sql = sql +")";
		dao.addBatch(sql);
		int[] ret1 = dao.execBatch();
		for(int i = 0 ;i<ret1.length ;i++){
			if(ret1[i]<1){
				ret = 0;
				break;
			}
		}
		return ret;
	}
	public int recevieMsg(User u,String orderId) throws Exception{
		int ret = 1;
		Dao dao = Config.getDao();
		String sql ="";
		sql="update dordermsg set order_status=4 "
				+ " where order_id='"+orderId+"' and buyer_id='"+u.getUserId()+"'";
		dao.addBatch(sql);
		
		sql="insert into dorder_op_msg(order_id,op_login,op_code,op_time,op_note,user_id) values (";
		sql = sql +" '"+orderId+"' ,'"+u.getLoginNo()+"' ,'1004' ,now(),'确认收货','"+u.getUserId()+"'";
		sql = sql +")";
		dao.addBatch(sql);
		int[] ret1 = dao.execBatch();
		for(int i = 0 ;i<ret1.length ;i++){
			if(ret1[i]<1){
				ret = 0;
				break;
			}
		}
		return ret;
	}
 
	public List<Map<String,Object>> orderCourierMsg(String orderId) throws Exception{
		List<Map<String,Object>> list = null;
		String sql ="";
		sql = sql +"SELECT b.courier_en_name, b.courier_cn_name           ";
		sql = sql +"  FROM dordermsg a, dcouriermsg b                     ";
		sql = sql +" WHERE a.courier_id = b.courier_id AND a.order_id = '"+orderId+"'";
		Dao dao = Config.getDao();
		list = dao.query(sql);
		return list;
	}

 
}
