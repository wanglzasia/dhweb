package com.dh.ora.s008.bean;

import java.util.Date;

public class Orderproductrel {
	
	private String product_id;//'产品id',
	private long product_count;//'购买数量',
	private double fixed_price;//'单价-定价',
	private double trans_price;//'单价-成交价格',
	private Date update_time;// '操作时间'
	private String remark;
	private String sellerid; //卖家
	private double ship_cost;//运费
	private String orderId; //订单编号
	private String cartid;//购物车ID
	
	public String getCartid() {
		return cartid;
	}
	public void setCartid(String cartid) {
		this.cartid = cartid;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public double getShip_cost() {
		return ship_cost;
	}
	public void setShip_cost(double ship_cost) {
		this.ship_cost = ship_cost;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public long getProduct_count() {
		return product_count;
	}
	public void setProduct_count(long product_count) {
		this.product_count = product_count;
	}
	public double getFixed_price() {
		return fixed_price;
	}
	public void setFixed_price(double fixed_price) {
		this.fixed_price = fixed_price;
	}
	public double getTrans_price() {
		return trans_price;
	}
	public void setTrans_price(double trans_price) {
		this.trans_price = trans_price;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	

}
