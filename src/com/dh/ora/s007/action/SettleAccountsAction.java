package com.dh.ora.s007.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s007.service.SettleAccountsService;
import com.dh.ora.s008.bean.OrderConfirmation;
import com.dh.ora.s008.bean.OrderConfirmationDetail;
import com.dh.ora.s008.bean.Ordermsg;
import com.dh.ora.s008.bean.Orderproductrel;
import com.x.orange.Action;

public class SettleAccountsAction extends Action {

	/**
	 * 结算
	 * 结算时将结算数据记录到订单表中，订单状态为 1 买家未付款
	 * @return
	 */
	public String settleAccounts() {

		try {

			// //配送id
			String addr_id = request.getParameter("addr_id");
			
			// //批量提交的订单
			List<Ordermsg> om_list = new ArrayList();

			// 获取买方用户信息
			User u = (User) getSessionUser();

			// /获取订单
			List<OrderConfirmation> order_confirmation_list = (List<OrderConfirmation>) getSession().get("order_confirmation");
			
			// //删除购物车中买过的商品
			List<String> shoppingcar_id_attr = new ArrayList<String>();

			// //获取总金额
//			double order_accounts = (Double) getSession().get("order_accounts");

			SettleAccountsService settleAccountsService = (SettleAccountsService) getBean("settleAccountsService");
			
			///拼装订单id，供修改状态使用
			List<String> order_id_list = new ArrayList<String> ();

			if (order_confirmation_list != null) {
				for (Iterator it = order_confirmation_list.iterator(); it.hasNext();) {

					OrderConfirmation oc = (OrderConfirmation) it.next();

					String order_id = settleAccountsService.getOrder_id();
					
					order_id_list.add(order_id);
					
					String seller_id = oc.getSeller_id();
					String buyer_id = u.getUserId();
					double postage = Double.parseDouble(oc.getDelivery_mode_value());
					double fixed_price = oc.getFixed_price();
					double fav_price = 0;
					double trans_Price = fixed_price + postage;
					long order_status = 1;
					// //买家留言
					String note = request.getParameter( "buyer_note_" + oc.getSeller_id());;
					if (note == null) {
						note = "";
					}

					Ordermsg om = new Ordermsg();
					om.setOrder_id(order_id);
					om.setSeller_id(seller_id);
					om.setBuyer_id(buyer_id);
					om.setPostage(postage);
					om.setFixed_price(fixed_price);
					om.setFav_price(fav_price);
					om.setTrans_Price(trans_Price);
					om.setOrder_status(order_status);
					om.setNote(note);
					om.setAddr_id(addr_id);

					List<OrderConfirmationDetail> ocd_list = oc.getOrderConfirmationDetail();

					List<Orderproductrel> ot_list = new ArrayList<Orderproductrel>();

					for (Iterator ite = ocd_list.iterator(); ite.hasNext();) {
						OrderConfirmationDetail ocd = (OrderConfirmationDetail) ite.next();

						String product_id = ocd.getProduct_id();
						String product_count = ocd.getProduct_count();
						double fixed_price_s = Double.parseDouble(ocd.getProduct_value());
						double trans_price_s = Double.parseDouble(ocd.getProduct_value()) - ocd.getFav_value();

						Orderproductrel ot = new Orderproductrel();
						ot.setProduct_id(product_id);
						ot.setProduct_count(Long.parseLong(product_count));
						ot.setFixed_price(fixed_price_s);
						ot.setTrans_price(trans_price_s);
						ot_list.add(ot);

						// ///为删除购物车做准备
						String shoppingcar_id = ocd.getShoppingcard_id();
						shoppingcar_id_attr.add(shoppingcar_id);
					}
					om.setOrderproductrel_list(ot_list);

					om_list.add(om);

				}
			}

			settleAccountsService.addOrderMsg(om_list, shoppingcar_id_attr);
			
			getSession().put("order_id_list", order_id_list);

			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

}
