package com.dh.ora.tld;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.dh.ora.s004.service.ProductKindSrvImpl;

public class ProductAttrTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private int type = 0;
	private int usr = 0;
	private String kindCode = null;
	private String productId = null;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getKindCode() {
		return kindCode;
	}

	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getUsr() {
		return usr;
	}

	public void setUsr(int usr) {
		this.usr = usr;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE; 
	}
	
	public int doStartTag() throws JspException{
		ProductKindSrvImpl prodcutKind = new ProductKindSrvImpl();
		String htmlBody = "";
		try {
			htmlBody = prodcutKind.parserAttr(type, usr, kindCode, productId);
			JspWriter out = this.pageContext.getOut();
			out.print(htmlBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
}