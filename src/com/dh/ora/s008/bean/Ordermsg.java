package com.dh.ora.s008.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ordermsg {
	
	private String order_id;//'订单id'
	private String seller_id;//'卖家id',
	private String buyer_id;//'买家id',
	private Date order_time;//'下单日期',
	private Date update_time;//'最后更新日期',
	private double postage;//'邮费',
	private String addr_id;//'邮寄地址信息',
	private double fixed_price;// '原价',
	private double fav_price;//'优惠价格',
	private double trans_Price;//'成交价格',
	private long order_status;//'订单状态',
	private String note;//'留言'
	
	private List<Orderproductrel> orderproductrel_list = new ArrayList<Orderproductrel>();///订单关系
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public double getPostage() {
		return postage;
	}
	public void setPostage(double postage) {
		this.postage = postage;
	}
	public String getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(String addr_id) {
		this.addr_id = addr_id;
	}
	public double getFixed_price() {
		return fixed_price;
	}
	public void setFixed_price(double fixed_price) {
		this.fixed_price = fixed_price;
	}
	public double getFav_price() {
		return fav_price;
	}
	public void setFav_price(double fav_price) {
		this.fav_price = fav_price;
	}
	public double getTrans_Price() {
		return trans_Price;
	}
	public void setTrans_Price(double trans_Price) {
		this.trans_Price = trans_Price;
	}
	public long getOrder_status() {
		return order_status;
	}
	public void setOrder_status(long order_status) {
		this.order_status = order_status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Orderproductrel> getOrderproductrel_list() {
		return orderproductrel_list;
	}
	public void setOrderproductrel_list(List<Orderproductrel> orderproductrel_list) {
		this.orderproductrel_list = orderproductrel_list;
	}
	
	
}
