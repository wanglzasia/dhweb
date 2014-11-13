package com.dh.ora.s007.service;

import com.dh.ora.s008.bean.Ordermsg;
import com.dh.ora.s008.bean.Orderproductrel;
import com.x.orange.Config;
import com.x.orange.dao.Dao;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;


public class SettleAccountsServiceImpl
	implements SettleAccountsService
{

	public SettleAccountsServiceImpl()
	{
	}

	public boolean addOrderMsg(List om_list, List shppingcart_id_attr)
	{
		Dao dao = Config.getDao();
		if (om_list != null)
		{
			for (Iterator it = om_list.iterator(); it.hasNext();)
			{
				Ordermsg om = (Ordermsg)it.next();
				String order_id = om.getOrder_id();
				String seller_id = om.getSeller_id();
				String buyer_id = om.getBuyer_id();
				double postage = om.getPostage();
				String addr_id = om.getAddr_id();
				double fixed_price = om.getFixed_price();
				double fav_price = om.getFav_price();
				double trans_price = om.getTrans_Price();
				long order_status = om.getOrder_status();
				String note = om.getNote();
				String order_msg_sql = "";
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append("insert into dordermsg(").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append("order_id, seller_id, buyer_id,").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append("order_time, update_time, postage,").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append("addr_id, fixed_price, fav_price,").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append("trans_Price, order_status, note )").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append("values (").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append("'").append(order_id).append("','").append(seller_id).append("','").append(buyer_id).append("',").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append("now(),now(),").append(postage).append(",").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append("'").append(addr_id).append("',").append(fixed_price).append(",").append(fav_price).append(",").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append(trans_price).append(",").append(order_status).append(",'").append(note).append("'").toString();
				order_msg_sql = (new StringBuilder(String.valueOf(order_msg_sql))).append(")").toString();
				dao.addBatch(order_msg_sql);
				List opr_list = om.getOrderproductrel_list();
				if (opr_list != null)
				{
					String orderproductrel_sql;
					for (Iterator ite = opr_list.iterator(); ite.hasNext(); dao.addBatch(orderproductrel_sql))
					{
						Orderproductrel opr = (Orderproductrel)ite.next();
						String product_id = opr.getProduct_id();
						long product_count = opr.getProduct_count();
						double fixed_price_s = opr.getFixed_price();
						double trans_price_s = opr.getTrans_price();
						orderproductrel_sql = "";
						orderproductrel_sql = (new StringBuilder(String.valueOf(orderproductrel_sql))).append("insert into dorderproductrel(").toString();
						orderproductrel_sql = (new StringBuilder(String.valueOf(orderproductrel_sql))).append("order_id, product_id, product_count,").toString();
						orderproductrel_sql = (new StringBuilder(String.valueOf(orderproductrel_sql))).append("fixed_price, trans_price, update_time").toString();
						orderproductrel_sql = (new StringBuilder(String.valueOf(orderproductrel_sql))).append(") values(").toString();
						orderproductrel_sql = (new StringBuilder(String.valueOf(orderproductrel_sql))).append("'").append(order_id).append("','").append(product_id).append("',").append(product_count).append(",").toString();
						orderproductrel_sql = (new StringBuilder(String.valueOf(orderproductrel_sql))).append(fixed_price_s).append(",").append(trans_price_s).append(", now()").toString();
						orderproductrel_sql = (new StringBuilder(String.valueOf(orderproductrel_sql))).append(")").toString();
					}

				}
			}

		}
		if (shppingcart_id_attr != null)
		{
			String sql;
			for (Iterator iter = shppingcart_id_attr.iterator(); iter.hasNext(); dao.addBatch(sql))
			{
				String shoppingcart_id = (String)iter.next();
				sql = "delete from wshoppingcartmsg ";
				sql = (new StringBuilder(String.valueOf(sql))).append(" where shoppingcart_id = '").append(shoppingcart_id).append("'").toString();
			}

		}
		int retValue[] = dao.execBatch();
		System.out.println((new StringBuilder("------------")).append(retValue[0]).toString());
		return true;
	}

	public String getOrder_id()
		throws Exception
	{
		Dao dao = Config.getDao();
		String order_id = dao.find("select nextval('kindcode')");
		return order_id;
	}
}
