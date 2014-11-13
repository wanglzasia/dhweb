package com.dh.ora.s008.bean;


/**
 * 订单确认明细
 * @author liuxm
 *
 */
public class OrderConfirmationDetail {
	
	private String shoppingcard_id;

	private String product_id; //商品id
	
	private String product_img; //商品图片链接
	
	private String product_name; //商品名称
	
	private String product_note; //商品描述
	
	private String product_count; //商品数量
	
	private String product_value; //商品单价
	
	private String seller_fav ;//卖家优惠
	
	private double statistics;//小计
	
	private double fav_value;//商品优惠金额
	
	public String getShoppingcard_id() {
		return shoppingcard_id;
	}

	public void setShoppingcard_id(String shoppingcard_id) {
		this.shoppingcard_id = shoppingcard_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_note() {
		return product_note;
	}

	public void setProduct_note(String product_note) {
		this.product_note = product_note;
	}

	public String getProduct_count() {
		return product_count;
	}

	public void setProduct_count(String product_count) {
		this.product_count = product_count;
	}

	public String getProduct_value() {
		return product_value;
	}

	public void setProduct_value(String product_value) {
		this.product_value = product_value;
	}

	public double getStatistics() {
		return statistics;
	}

	public void setStatistics(double statistics) {
		this.statistics = statistics;
	}

	public String getSeller_fav() {
		return seller_fav;
	}

	public void setSeller_fav(String seller_fav) {
		this.seller_fav = seller_fav;
	}
	
	public double getFav_value() {
		return fav_value;
	}

	public void setFav_value(double fav_value) {
		this.fav_value = fav_value;
	}

}
