package com.dh.ora.s003.action;

import java.io.File;
import java.util.Random;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s003.bean.ProductBean;
import com.dh.ora.s003.service.ProductSrv;
import com.dh.ora.s004.service.ProductKindSrv;
import com.x.orange.Action;
import com.x.orange.Config;
import com.x.orange.dao.Dao;
import com.x.orange.util.ConfigUtil;

public class ProductAction extends Action{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 9124887695919041587L;
	/**
	 * 
	 * @Method:	ProductAction::add
	 * @param @return
	 * @return String
	 * @throws Exception 
	 * @date: 2014年6月27日下午9:35:36
	 * @throws 
	 * @author wanglz
	 * @Description: 引导进入产品种类选择界面
	 */
	public String add() throws Exception{
		String uri = "seller_add";
		getSession().put("uri", uri);
		ProductKindSrv proKindSrv = (ProductKindSrv)getBean("proKindSrv");
		java.util.List list = proKindSrv.getSubKindList("1000");
		getRequest().setAttribute("kindlist", list);
		assgin("nav_msg", "添加产品");
		return "add";
	}
	
	/**
	 * 
	 * @Method:	ProductAction::addpro
	 * @param @return
	 * @return String
	 * @date: 2014年6月27日下午9:35:57
	 * @throws 
	 * @author wanglz
	 * @Description: 引导进入产品添加界面
	 */
	public String addpro(){
		String uri = "seller_add_pro";
		String kind_code = getRequest().getParameter("kind_code");
		String kind_tree = getRequest().getParameter("kindtree");
		getRequest().setAttribute("kindcode", kind_code);
		getRequest().setAttribute("uri", uri);
		getRequest().setAttribute("kindtree", kind_tree);
		assgin("nav_msg", "添加产品");
		return "addpro";			
	}
	/**
	 * 
	 * @Method:	ProductAction::save
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年6月27日下午9:36:27
	 * @throws 
	 * @author wanglz
	 * @Description: 保存产品
	 */
	public String save() throws Exception{
		getRequest();
		ProductSrv product = (ProductSrv)getBean("productSrv");
		User usr = (User)getSessionUser();
		product.setUser(usr);
		product.Save(request);
		getSession().put("msg", (new StringBuilder("产品: ")).append(request.getParameter("pro_name")).append(" ").append(request.getParameter("pro_no")).append("添加成功").toString());
		String uri = "product_ok";
		getRequest().setAttribute("uri", uri);
		return "prolist";
	}
	/**
	 * 
	 * @Method:	ProductAction::query
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年6月27日下午9:36:37
	 * @throws 
	 * @author wanglz
	 * @Description: 查询产品
	 */
	public String query() throws Exception{
		String uri = "product_list";
		ProductSrv product = (ProductSrv)getBean("productSrv");
		java.util.List list = product.queryList(getRequest());
		getRequest().setAttribute("list", list);
		getRequest().setAttribute("uri", uri);
		assgin("nav_msg", "产品管理");
		return "list";
	}
	//TODO::修改产品
	public String modify() throws Exception{
		String uri = "product_ok";
		ProductSrv product = (ProductSrv)getBean("productSrv");
		product.Modify(request);
		getRequest().setAttribute("uri", uri);
		getRequest().setAttribute("msg", "操作成功");
		return "ok";
	}
	/**
	 * 
	 * @Method:	ProductAction::show
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年6月27日下午9:37:14
	 * @throws 
	 * @author wanglz
	 * @Description: 展示单个产品信息
	 */
	public String show() throws Exception{
		String proId = getParam("pro_id");
		ProductSrv product = (ProductSrv)getBean("productSrv");
		ProductBean productBean = (ProductBean)product.Show(proId);
		String uri = "seller_pro_show";
		String kindTree = product.kindTree(proId);
		getRequest().setAttribute("kindtree", kindTree);
		getRequest().setAttribute("product", productBean);
		getRequest().setAttribute("kindcode", productBean.getProKind());
		getRequest().setAttribute("uri", uri);
		return "proshow";
	}
	
	/**
	 * 
	 * @Method:	ProductAction::auditPass
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年6月20日下午1:08:06
	 * @author wanglz
	 * @Description: 产品审批通过,生成HTML文件
	 */
	public String auditPass() throws Exception{
		String proId = getParam("pro_id");
		ProductSrv product = (ProductSrv)getBean("productSrv");
		String uid = "";
		String sql = "select user_id from dproductmsg where product_id=?";
		Dao dao = Config.getDao();
		dao.addParam(new Object[] {proId});
		uid = dao.find(sql);
		product.auditPass(proId);
		String htmlUrl = (new StringBuilder(String.valueOf(ConfigUtil.getConfig("HTML_PATH")))).append(File.separator).append(uid).toString();
		String uri = "product_ok";
		htmlUrl = (new StringBuilder(String.valueOf(htmlUrl))).append(File.separator).append(uid).append("-").append(proId).append(ConfigUtil.getConfig("HTML_EXTENSION")).toString();
		getRequest().setAttribute("uri", uri);
		Random rd1 = new Random();
		getRequest().setAttribute("msg", (new StringBuilder("操作成功,<a href=\"/admin/view_")).append(uid).append("_").append(proId).append(".do?p=").append(rd1.nextInt()).append("\">查看</a>").toString());
		return "ok";
	}
	/**
	 * 
	 * @Method:	ProductAction::auditRefuse
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年6月20日下午1:07:51
	 * @author wanglz
	 * @Description: 产品审批不通过
	 */
	public String auditRefuse() throws Exception{
		String proId = getParam("pro_id");
		ProductSrv product = (ProductSrv)getBean("productSrv");
		int ret = product.auditRefuse(proId);
		String msg = "操作成功";
		if (ret != 1)
		{
			msg = "操作失败!";
			return "gl_error";
		} else
		{
			return "ok";
		}
	}
	/**
	 * 
	 * @Method:	ProductAction::putOnShelves
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年6月20日下午1:07:23
	 * @author wanglz
	 * @Description: 货物上架
	 */
	public String putOnShelves() throws Exception{
		String proId = getParam("pro_id");
		ProductSrv product = (ProductSrv)getBean("productSrv");
		int ret = product.putOnShelves(proId);
		String msg = "操作成功";
		if (ret != 1)
		{
			msg = "操作失败!";
			return "gl_error";
		} else
		{
			return "ok";
		}
	}
	/**
	 * 
	 * @Method:	ProductAction::pullOffShelves
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年6月20日下午1:07:38
	 * @author wanglz
	 * @Description: 货物下架
	 */
	public String pullOffShelves() throws Exception{
		String proId = getParam("pro_id");
		ProductSrv product = (ProductSrv)getBean("productSrv");
		int ret = product.pullOffShelves(proId);
		String msg = "操作成功";
		if (ret != 1)
		{
			msg = "操作失败!";
			return "gl_error";
		} else
		{
			return "ok";
		}
	}
	public String view() throws Exception{
		String pid = getRequest().getParameter("p") != null ? getRequest().getParameter("p") : "";
		if (pid.equals(""))
			getResponse().sendError(404, "没有此产品");
		return "ok";
	}
	
}
