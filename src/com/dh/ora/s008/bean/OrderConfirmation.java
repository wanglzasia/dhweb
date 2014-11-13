package com.dh.ora.s008.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.x.orange.Config;
import com.x.orange.dao.Dao;

/**
 * 订单确认
 * @author liuxm
 *
 */

public class OrderConfirmation {
	
	
	private String seller_id;//卖家id
	
	private String delivery_mode;//配送方式
	
	private String delivery_mode_value;///配送金额
	
	private double fixed_price;//订单金额

	private String buyer_note;///买家留言
	
	private String seller_msg;
	
	private String companyNameEn;
	private String addressEn;
	private String faxNo;
	private String mobileNo;
	private String contact_man_en;
	private String eMail;
	private String tel_no;
	 
	

	public String getTel_no() {
		return tel_no;
	}

	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}

	public String getCompanyNameEn() {
		return companyNameEn;
	}

	public void setCompanyNameEn(String companyNameEn) {
		this.companyNameEn = companyNameEn;
	}

	public String getAddressEn() {
		return addressEn;
	}

	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getContact_man_en() {
		return contact_man_en;
	}

	public void setContact_man_en(String contact_man_en) {
		this.contact_man_en = contact_man_en;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public void setSeller_msg() {
		Dao dao = Config.getDao();
		String sql="select company_name_en,address_en,fax_no,tel_no,mobile_no,contact_man_en,e_mail from dsellermsg";
		List<Map<String,Object>>  list = null;
		Map<String,Object> map = null;
		list = dao.query(sql);
		for(int i = 0 ;i<list.size() ;i++){
			map = list.get(i);
			setCompanyNameEn((String)map.get("company_name_en"));
			setAddressEn((String)map.get("address_en"));
			setFaxNo((String)map.get("fax_no"));
			setMobileNo((String)map.get("mobile_no"));
			setContact_man_en((String)map.get("contact_man_en"));
			setTel_no((String)map.get("tel_no"));
			seteMail((String)map.get("e_mail"));
		}
	}


	private List<OrderConfirmationDetail> orderConfirmationDetail = new ArrayList<OrderConfirmationDetail>();
	
	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getDelivery_mode() {
		return delivery_mode;
	}

	public void setDelivery_mode(String delivery_mode) {
		this.delivery_mode = delivery_mode;
	}

	public String getDelivery_mode_value() {
		return delivery_mode_value;
	}

	public void setDelivery_mode_value(String delivery_mode_value) {
		this.delivery_mode_value = delivery_mode_value;
	}

	public List<OrderConfirmationDetail> getOrderConfirmationDetail() {
		return orderConfirmationDetail;
	}

	public void setOrderConfirmationDetail(
			List<OrderConfirmationDetail> orderConfirmationDetail) {
		this.orderConfirmationDetail = orderConfirmationDetail;
	}

	public double getFixed_price() {
		return fixed_price;
	}

	public void setFixed_price(double fixed_price) {
		this.fixed_price = fixed_price;
	}
	
	public String getBuyer_note() {
		return buyer_note;
	}

	public void setBuyer_note(String buyer_note) {
		this.buyer_note = buyer_note;
	}

	
}
