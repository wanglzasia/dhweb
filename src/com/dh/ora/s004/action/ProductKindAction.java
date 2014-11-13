package com.dh.ora.s004.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.dh.ora.s004.service.ProductKindSrv;
import com.dh.ora.tool.JsonUtil;
import com.x.orange.Action;

public class ProductKindAction extends Action{
	private static final long serialVersionUID = 5988961485638318121L;

	public String index() throws Exception{
		request.setAttribute("kindcode", "");
		request.setAttribute("kindname", "");
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		String ret = proKindSrv.loadTree("1000");
		
		String code = (String)getRequest().getParameter("code");
		String value = (String)getRequest().getParameter("value");
				
		getRequest().setAttribute("dtree",ret);
		getRequest().setAttribute("code",code);
		getRequest().setAttribute("value",value);
		
		return "init";
	}
	
	public String initattr(){
		request.setAttribute("kindcode", "");
		request.setAttribute("sectioncode", "");
		request.setAttribute("kindname", "");
		request.setAttribute("sectionname", "");
		return "init";
	}
	public String initProList(){
		return "init";
	}
	
	
	public String addKind() throws Exception{
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		String ret = proKindSrv.addKind(getRequest());
		
		getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(ret);
		return null;
	}
	public String addSection() throws Exception{
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		String ret = proKindSrv.addSection(getRequest());
		
		getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(ret);
		return null;
	}
	public String addAttr() throws Exception{
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		String ret = proKindSrv.addAttr(getRequest());
		
		getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(ret);
		return null;
	}
	
	public String loadKindTree() throws Exception{
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		String ret = proKindSrv.loadTree("1000");
		
		String code = (String)getRequest().getParameter("code");
		String value = (String)getRequest().getParameter("value");
				
		getSession().put("dtree",ret);
		getSession().put("code",code);
		getSession().put("value",value);

		return "kind_tree";
	}
	
	public String loadSectionTree() throws Exception{
		String kindCode = (String)getRequest().getParameter("kind_code");
		String code = (String)getRequest().getParameter("code");
		String value = (String)getRequest().getParameter("value");
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		String ret = proKindSrv.loadSectionTree(kindCode);
		getSession().put("dtree",ret);
		getSession().put("code",code);
		getSession().put("value",value);
		return "section_tree";
	}
	
	public String kindSecList() throws Exception{
		String kindCode = (String)getRequest().getParameter("kind_code");
		String kindName = (String)getRequest().getParameter("kind_name");
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		List<Map<String,Object>> list = proKindSrv.kindSectionList(kindCode);
		request.setAttribute("kindcode", kindCode);
		request.setAttribute("kindname", kindName);
		request.setAttribute("list", list);
		//getSession().put("list",list);
		return "list";
	}
	
	public String kindAttrList() throws Exception{
		String kindCode = (String)getRequest().getParameter("kind_code");
		String sectionCode = (String)getRequest().getParameter("section_code");
		String kindName = (String)getRequest().getParameter("kind_name");
		String sectionname = (String)getRequest().getParameter("section_name");
		
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		List<Map<String,Object>> list = proKindSrv.kindAttrList(kindCode, sectionCode);
		
		request.setAttribute("kindcode", kindCode);
		request.setAttribute("sectioncode", sectionCode);
		request.setAttribute("kindname", kindName);
		request.setAttribute("sectionname", sectionname);
		//getSession().put("list", list);
		request.setAttribute("list", list);
		return "list";
	}
	
	public String productList() throws Exception{
		String status = (String)getRequest().getParameter("status");
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		List<Map<String,Object>> list = proKindSrv.productList(status,getRequest());
		request.setAttribute("list", list);
		return "list";
	}
	
	public String productKind() throws Exception{
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		String parentKindCode = (String)getRequest().getParameter("kind_code");
		List<Map<String,Object>> list = proKindSrv.getSubKindList(parentKindCode);
		String json = JsonUtil.listToJson(list);
		System.out.println(json);
		JsonUtil.outprint(getResponse(),json);
		return null;
	}
}

