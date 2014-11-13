package com.dh.ora.s005.action;

import java.util.List;
import java.util.Map;

import com.x.orange.Action;
import com.x.orange.Config;
import com.x.orange.dao.Dao;
import com.x.orange.dao.PageBean;

public class SearchAction extends Action{
	private static final long serialVersionUID = 3716676850901512348L;

	public String search(){
		
		String keyWord =null==(String)getRequest().getParameter("keyword")?"":(String)getRequest().getParameter("keyword");
		String kindcode = null ==(String)getRequest().getParameter("kc")?"":(String)getRequest().getParameter("kc");
		
		
		
		Dao dao = Config.getDao();
		String sql="";
		sql = sql +"SELECT a.product_id,ifnull(a.product_name,\"\") product_name,ifnull(a.simple_desc,\"\") simple_desc,ifnull(b.pic_name_1,\"\") pic_name_1,c.min_count,c.max_count,                             ";
		sql = sql +"  c.min_price,c.max_price,d.value,d.value_en,ifnull(b.html_full_name,\"\") html_full_name,f.login_no ,f.user_id    ";
		sql = sql +"  FROM dproductmsg a,dproductothermsg b,dProductPriceLHMsg c,s_cfg_unit d,dusermsg f                               ";
		sql = sql +" WHERE a.product_id = b.product_id                                                                                 ";
		sql = sql +" and b.product_id = c.product_id                                                                                   ";
		sql = sql +" and a.unit_code = d.code                                                                                          ";
		sql = sql +" and a.user_id = f.user_id                                                                                         ";
		sql = sql +" and a.product_stat='3'                                                                                       ";
		if(!"".equals(kindcode)){
		sql = sql +" and a.product_kind='"+kindcode+"'																					   ";
		}
		sql = sql +" and (a.product_name like ? or  a.simple_desc LIKE ?)                                                              ";
		sql = sql +" union                                                                                                             ";
		sql = sql +"SELECT distinct b.product_id,ifnull(c.product_name,\"\") product_name,ifnull(c.simple_desc,\"\") simple_desc,ifnull(d.pic_name_1,\"\") pic_name_1 ,e.min_count,e.max_count,                    ";
		sql = sql +"  e.min_price,e.max_price,f.value,f.value_en ,ifnull(d.html_full_name,\"\") html_full_name,g.login_no,g.user_id    ";
		sql = sql +"  FROM s_cfg_product_attr a, dprodcutattrmsg b, dproductmsg c,dproductothermsg d,dProductPriceLHMsg e,s_cfg_unit f ";
		sql = sql +"  ,dusermsg g ";
		sql = sql +" WHERE a.attr_name = b.attr_name                                                                                   ";
		sql = sql +" and b.product_id = c.product_id                                                                                   ";
		sql = sql +" and c.product_id = d.product_id                                                                                   ";
		sql = sql +" and d.product_id = e.product_id                                                                                   ";
		sql = sql +" and c.unit_code = f.code                                                                                          ";
		sql = sql +" and c.user_id = g.user_id                                                                                         ";
		sql = sql +" and c.product_stat='3'                                                                                            ";
		if(!"".equals(kindcode)){
			sql = sql +" and c.product_kind='"+kindcode+"'																					   ";
		}		
		sql = sql +" and b.attr_value like ?                                                                                           ";
		
		String param="%"+keyWord+"%";
	
		/*分页准备*/
		String sql1 = "";
		sql1 = "select count(1) count_ from ( "+sql+" ) xxxxxx";
		dao.addParam(new Object[]{param,param,param});
		String countStr = getDao().find(sql1);
		PageBean pageBean = new PageBean(request,Integer.valueOf(countStr));
		
		/*查询数据*/
		dao.addParam(new Object[]{param,param,param});
		List<Map<String,Object>> list = pageBean.pageList(dao, sql);
		request.setAttribute("pageBean", pageBean);		
		assgin("plist",list);
		return "list";
	}
	
	public String view() throws Exception{
		return "ok";
	}

}
