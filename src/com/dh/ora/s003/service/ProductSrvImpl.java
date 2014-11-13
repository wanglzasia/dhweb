package com.dh.ora.s003.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.HtmlUtils;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s003.bean.ProductBean;
import com.x.orange.Service;
import com.x.orange.dao.Dao;
import com.x.orange.dao.PageBean;

public class ProductSrvImpl extends Service implements ProductSrv{
	
	private User usr;
	public User getUsr()
	{
		return usr;
	}

	public void setUsr(User usr)
	{
		this.usr = usr;
	}
	
	@Override
	public int Save(HttpServletRequest request) throws Exception {
		ProductBean product = new ProductBean();
		product.setUserId(getUsr().getUserId());
		int ret = product.save(request);
		return ret;
	}

	@Override
	public int Modify(HttpServletRequest request) throws Exception {
		ProductBean product = new ProductBean();
		int ret = 0 ;
		ret = product.Modify(request);
		return ret;
	}
	
	@Override
	public int Remove(String proNo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProductBean Show(String proId) throws Exception {
		ProductBean product = new ProductBean();
		return product.show(proId);
	}

	@Override
	public int putOnShelves(String proNo) throws Exception {
		ProductBean product = new ProductBean();
		int ret = product.putOnShelves(proNo);
		return ret;
	}

	@Override
	public int pullOffShelves(String proNo) throws Exception {
		ProductBean product = new ProductBean();
		int ret = product.pullOffShelves(proNo);
		return ret;
	}

	@Override
	public int auditPass(String proId) throws Exception {
		/*
		String productKind="PRODUCT_PHONE";
		//根据proNO定位productKind;
		productKind = "BEAN_"+productKind;
		Class<?> c = Class.forName(ConfigUtil.getConfig(productKind));
		product = c.newInstance();
		Method method = c.getMethod("auditPass",String.class);
		Object ret = method.invoke(product,proNo);
		*/
		ProductBean product = new ProductBean();
		int ret = product.auditPass(proId);
		return ret;

	}

	@Override
	public int auditRefuse(String proNo) throws Exception {
		int ret = 0;
		ProductBean product = new ProductBean();
		ret = product.auditRefuse(proNo);
		return ret;
	}

	@Override
	public List<Map<String, Object>> queryList(HttpServletRequest request)
			throws Exception {
		Dao dao = getDao();
		String proNo = "";
		proNo = request.getParameter("pro_no");
		String proKind = "";
		proKind = request.getParameter("pro_kind");
		String proName = "";
		proName = request.getParameter("pro_name");
		String sql = "select @rownum:=@rownum+1 rownum ,a.product_no,a.product_name,a.brand_code,a.product_stat,b.value,a.product_id,a.product_kind,c.status_name ";
		sql = (new StringBuilder(String.valueOf(sql))).append(" from dproductmsg a  ,s_cfg_unit b,s_cfg_productstatusmsg c ,(SELECT @rownum:=0) d where a.unit_code = b.code and a.product_stat=c.status_id ").toString();
		List<String> paramlist = new ArrayList<String>();
		if (proNo != null && !proNo.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.product_no like ?").toString();
			paramlist.add((new StringBuilder("%")).append(HtmlUtils.htmlEscape(proNo)).append("%").toString());
		}
		if (proKind != null && !proKind.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.product_kind like ?").toString();
			paramlist.add((new StringBuilder("%")).append(HtmlUtils.htmlEscape(proKind)).append("%").toString());
		}
		if (proName != null && !proName.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.product_name like ?").toString();
			paramlist.add((new StringBuilder("%")).append(HtmlUtils.htmlEscape(proName)).append("%").toString());
		}
		String sql1 = "";
		sql1 = (new StringBuilder("select count(1) count_ from ( ")).append(sql).append(" ) xxxxxx").toString();
		Object param[] = null;
		if (paramlist.size() > 0)
		{
			param = new Object[paramlist.size()];
			for (int i = 0; i < paramlist.size(); i++)
				param[i] = (String)paramlist.get(i);

			dao.addParam(param);
		}
		String countStr = getDao().find(sql1);
		PageBean pageBean = new PageBean(request, Integer.valueOf(countStr).intValue());
		if (paramlist.size() > 0)
			dao.addParam(param);
		List<Map<String,Object>> list = pageBean.pageList(dao, sql);
		request.setAttribute("pageBean", pageBean);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryProduct(String proId)
			throws Exception {
		List<Map<String,Object>> list = null;
		String sql = "select product_id,user_id from dproductmsg where product_id=?";
		Dao dao = getDao();
		dao.addParam(new Object[] {
			proId
		});
		list = dao.query(sql);
		return list;
	}

	@Override
	public void setUser(User usr) {
		this.usr = usr;
		
	}

	@Override
	public double price(String pid, int count) throws Exception {
		double price = 0.0D;
		String sql = "select ifnull(min_count,0) min_count,round(ifnull(sale_price,0.00),2) sale_price from dproductpricemsg where product_id=? order by min_count";
		Dao dao = getDao();
		dao.addParam(new Object[] {
			pid
		});
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		list = dao.query(sql);
		String tmpPrice = "0.00";
		int minCount = 1;
		int preCount = 1;
		for (int i = 0; i < list.size(); i++)
		{
			map = (Map<String, Object>)list.get(i);
			minCount = Integer.valueOf((String)map.get("min_count")).intValue();
			tmpPrice = (String)map.get("sale_price");
			if (count == 1)
			{
				price = Double.valueOf(tmpPrice).doubleValue();
				break;
			}
			if (count <= 1)
				continue;
			if (preCount < count && count <= minCount)
			{
				price = Double.valueOf(tmpPrice).doubleValue();
				break;
			}
			if (preCount < minCount)
				preCount = minCount;
		}

		return price;
	}

	@Override
	public String kindTree(String proid) throws Exception {
		String sql = " SELECT a.parent_kind_code,b.kind_name,a.denorm_level";
		sql = (new StringBuilder(String.valueOf(sql))).append(" FROM s_cfg_productinfo a, s_cfg_productkind b,dproductmsg c").toString();
		sql = (new StringBuilder(String.valueOf(sql))).append(" WHERE  a.parent_kind_code = b.kind_code").toString();
		sql = (new StringBuilder(String.valueOf(sql))).append(" and a.parent_kind_code <>'1000' and a.kind_code = c.product_kind and c.product_id='").append(proid).append("'").toString();
		sql = (new StringBuilder(String.valueOf(sql))).append(" order by a.denorm_level desc").toString();
		Dao dao = getDao();
		List<Map<String, Object>> list = null;
		list = dao.query(sql);
		Map<String, Object> map = null;
		int j = list.size() - 1;
		String tree = "";
		String tmpname = "";
		for (int i = 0; i < list.size(); i++)
		{
			map = (Map<String, Object>)list.get(i);
			tmpname = (String)map.get("kind_name");
			tree = (new StringBuilder(String.valueOf(tree))).append(tmpname).toString();
			if (j > i)
				tree = (new StringBuilder(String.valueOf(tree))).append("->").toString();
		}

		return tree;
	}

}
