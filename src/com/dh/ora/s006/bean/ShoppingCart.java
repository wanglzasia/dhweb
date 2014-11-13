package com.dh.ora.s006.bean;

import java.util.Date;


/**
 * 购物车属性
 * @author liuxm
 *
 */
public class ShoppingCart {
	
	private String shoppingcart_id;//购物车id
	
	private String seller_id;//卖家id
	
	private String user_id; //购物车拥有者
	
	private String product_id; //商品id
	
	private String product_name; //商品名称
	
	private String product_note; //商品描述
	
	private String product_value; //商品单价
	
	private String product_count; //商品数量
	
	private String product_img; //商品图片链接
	
	private String user_fav_id;
	
	private String user_fav_name;
	
	private Date opt_date; //操作时间
	
	private String loginNo;
	
	private String singleSumPrice;//
	
	private String unitValue;
	public String getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}

	public String getUnitValueEn() {
		return unitValueEn;
	}

	public void setUnitValueEn(String unitValueEn) {
		this.unitValueEn = unitValueEn;
	}

	private String unitValueEn;
	
	
	public String getSingleSumPrice() {
		return singleSumPrice;
	}

	public void setSingleSumPrice(String singleSumPrice) {
		this.singleSumPrice = singleSumPrice;
	}

	public String getLoginNo() {
		return loginNo;
	}

	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	private String product_all_value;//商品总价

	public String getShoppingcart_id() {
		return shoppingcart_id;
	}

	public void setShoppingcart_id(String shoppingcart_id) {
		this.shoppingcart_id = shoppingcart_id;
	}
	
	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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

	public String getProduct_value() {
		return product_value;
	}

	public void setProduct_value(String product_value) {
		this.product_value = product_value;
	}

	public String getProduct_count() {
		return product_count;
	}

	public void setProduct_count(String product_count) {
		this.product_count = product_count;
	}

	public Date getOpt_date() {
		return opt_date;
	}

	public void setOpt_date(Date opt_date) {
		this.opt_date = opt_date;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public String getProduct_all_value() {
		return product_all_value;
	}

	public void setProduct_all_value(String product_all_value) {
		this.product_all_value = product_all_value;
	}

	public String getUser_fav_id() {
		return user_fav_id;
	}

	public void setUser_fav_id(String user_fav_id) {
		this.user_fav_id = user_fav_id;
	}

	public String getUser_fav_name() {
		return user_fav_name;
	}

	public void setUser_fav_name(String user_fav_name) {
		this.user_fav_name = user_fav_name;
	}
	
}
