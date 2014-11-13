package com.dh.ora.s004.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.x.orange.Config;
import com.x.orange.Service;
import com.x.orange.dao.Dao;
import com.x.orange.dao.PageBean;

public class ProductKindSrvImpl extends Service implements ProductKindSrv {

	@Override
	public String addKind(HttpServletRequest request) throws Exception {
		String retValue="操作成功";
		Dao dao = getDao();
		String parKindCode = (String)request.getParameter("parent_kind_code");
		String kindName = (String)request.getParameter("kind_name");
		String kindNameEn = (String)request.getParameter("kind_name_en");
		String kindCode = dao.find("select nextval('kindcode')");
		String sql="insert into s_cfg_productkind(kind_code,kind_name,kind_name_en,status) values ('"+kindCode+"','"+kindName+"','"+kindNameEn+"','1')";
		dao.addBatch(sql);
		sql="insert into s_cfg_productinfo select '"+kindCode+"',parent_kind_code,denorm_level+1 from s_cfg_productinfo where kind_code='"+parKindCode+"'";
		dao.addBatch(sql);
		sql="insert into s_cfg_productinfo values('"+kindCode+"','"+kindCode+"','0')";
		dao.addBatch(sql);
		int ret[] = dao.execBatch();
		for(int i = 0 ;i<ret.length ;i++){
			if(ret[i]<1){
				retValue="操作失败!";
				break;
			}
		}
		return retValue;
	}

	@Override
	public String addSection(HttpServletRequest request) throws Exception {
		String retValue="操作成功";
		Dao dao = getDao();
		String kindCode = (String)request.getParameter("kind_code1");
		String sectionName = (String)request.getParameter("section_name");
		String sectionNameEn = (String)request.getParameter("section_name_en");
		String note = (String)request.getParameter("note");
		String sectionCode = dao.find("select nextval('sectioncode')");
		int status = 1; 
		String sql="insert into s_cfg_product_attr_section (kind_code,section_code,section_name,section_name_en,note,status) values (?,?,?,?,?,?)";
		Object params[]={kindCode,sectionCode,sectionName,sectionNameEn,note,status};
		dao.addParam(params);
		int ret = dao.execute(sql);
		if(ret!=1){
			retValue="操作失败!";
		}	
		return retValue;
	}

	@Override
	public String addAttr(HttpServletRequest request) throws Exception {
		String retValue="操作成功";
		Dao dao = getDao();
		String kindCode = (String)request.getParameter("kind_code");
		String sectionCode = (String)request.getParameter("section_code");
		String attrType = (String)request.getParameter("attr_type");
		String arrtLabel = (String)request.getParameter("attr_label");
		String arrtLabelEn = (String)request.getParameter("attr_label_en");
		String defaultValue = (String)request.getParameter("default_value");
		String note =(String)request.getParameter("note");
		String is_keyword =(String)request.getParameter("is_key_word");
		String attrName = dao.find("select nextval('attrCode')");
		attrName = "attr"+attrName;
				
		String sql="insert into s_cfg_product_attr(kind_code,section_code";
		sql = sql+",attr_type,attr_name,attr_label,attr_label_en,defaule_value,note,status,is_keyword)";
		sql = sql +" values (?,?,?,?,?,?,?,?,?,?)";
 
		Object params[]={kindCode,sectionCode,attrType,attrName,arrtLabel,arrtLabelEn,defaultValue,note,1,is_keyword};
		dao.addParam(params);
		int ret=dao.execute(sql);
		if(ret!=1){
			retValue="操作失败!";
		}	
		return retValue;
	}

	/**
	 * type :是否带值 0 不带 1带
	 * usr  :用户(前台 or 后台)0前台en 1后台ch
	 */
	public String parserAttr(int type, int usr,String kindCode, String productId)
			throws Exception {
		String sql="";
		String curSection="";
		String preSection="";
		int tmpAttrType = 0;
		String tmpAttrName="";
		String tmpAttrlabel="";
		String tmpAttrlabelEn="";
		String tmpDefaultValue="";
		String tmpListValue = "";
		//String tmpSectionName="";
		//String tmpSectionNameEn="";
		
		Dao dao = getDao();
		List<Map<String,Object>> list = null;
		String retValue = "";
		String labelvalue ="";
		//String sectionvalue="";
		String tokenValue = "";
		Map<String,Object> tmpMap = null;
				
		if(type==0){
			//显示页面不代值
			
			sql="SELECT a.kind_code, ";
			sql=sql+"  a.section_code,  ";
			sql=sql+"  a.section_name,  ";
			sql=sql+"  a.section_name_en, ";
			sql=sql+"  b.attr_type,   ";
			sql=sql+"  b.attr_name,   ";
			sql=sql+"  b.attr_label,  ";
			sql=sql+"  b.attr_label_en, ";
			sql=sql+"  b.defaule_value ";
			sql=sql+" FROM s_cfg_product_attr_section a, s_cfg_product_attr b ";
			sql=sql+" WHERE     a.kind_code = ?";
			sql=sql+"     AND a.section_code = b.section_code ";
			sql=sql+"     AND b.status = 1 ";
			sql=sql+"     order by a.z_index,b.attr_name";
			
			dao.addParam(new Object[]{kindCode});
			list = dao.query(sql);
			
			for(int i =0 ;i<list.size();i++){
					tmpMap = list.get(i);
					tmpAttrType = Integer.parseInt((String)tmpMap.get("attr_type"));
					tmpAttrName = (String)tmpMap.get("attr_name");
					tmpAttrlabel = (String)tmpMap.get("attr_label");
					tmpAttrlabelEn = (String)tmpMap.get("attr_label_en");
					tmpDefaultValue = (String)tmpMap.get("defaule_value");
					curSection = (String)tmpMap.get("section_code");
					
					//英文
					if(usr==0){
						labelvalue="<label class=\"attr_label\">"+tmpAttrlabelEn+"</label>";
					}
					//中文
					if(usr==1){
						labelvalue="<label class=\"attr_label\">"+tmpAttrlabel+"</label>";
					}
					
					//checkBox
					if(tmpAttrType==1){
						retValue = retValue +"<div class=\"pro_item\">"+labelvalue+"<div class=\"multi_opr_area\"><input type=\"checkbox\" name=\""+tmpAttrName+"\" id=\""+tmpAttrName+"\"";
						if("1".equals(tmpDefaultValue)){
							retValue = retValue +"checked";
						}
						retValue = retValue +"/></div></div>";
					}
					//text
					if(tmpAttrType==2){
						retValue = retValue +"<div class=\"pro_item\">"+labelvalue+"<div class=\"multi_opr_area\"><input type=\"text\" name=\""+tmpAttrName+"\" id=\""+tmpAttrName+"\"";
						retValue = retValue +" value=\""+tmpDefaultValue+"\" /></div></div>";
					}
					//select
					if(tmpAttrType==3){
						retValue = retValue +"<div class=\"pro_item\">"+ labelvalue+"<div class=\"multi_opr_area\"><select name=\""+tmpAttrName+"\" >";
						StringTokenizer st = new StringTokenizer(tmpDefaultValue,"\r\n");
						while(st.hasMoreTokens()){
							tokenValue= st.nextToken();
							retValue = retValue +"<option value=\""+tokenValue+"\">"+tokenValue+"</option>\r\n";
						}
						retValue = retValue+"</select></div></div>";
						
					}

					if(!curSection.equals(preSection)){
						preSection = curSection;
						//retValue = retValue; 
					}
			}
		}
		if(type==1){
			
			//显示页面并且代值
			sql ="select * from ( ";
			sql=sql+" SELECT a.kind_code,                                                ";
			sql=sql+"       a.attr_type,                                              ";
			sql=sql+"       a.attr_name,                                              ";
			sql=sql+"       a.attr_label,                                             ";
			sql=sql+"       a.attr_label_en,                                          ";
			sql=sql+"       a.defaule_value,                                          ";
			sql=sql+"       b.product_id,                                             ";
			sql=sql+"       ifnull(b.attr_value,'') attr_value,                       ";
			sql=sql+"       a.section_code,                                           ";
			sql=sql+"       c.section_name,                                           ";
			sql=sql+"       c.section_name_en                                         ";
			sql=sql+"  FROM s_cfg_product_attr a                                      ";
			sql=sql+"       LEFT JOIN dprodcutattrmsg b                               ";
			sql=sql+"          ON     a.attr_name = b.attr_name                       ";
			sql=sql+"             AND a.kind_code = b.kind_code                       ";
			sql=sql+"             AND b.product_id = ?                                ";
			sql=sql+"             AND a.status = 1                                    ";
			sql=sql+"       LEFT JOIN s_cfg_product_attr_section c                    ";
			sql=sql+"          ON a.section_code = c.section_code AND c.status = 1    ";
			//sql=sql+" ORDER BY a.section_code, a.attr_name                            ";
			sql =sql +") x where x.kind_code in (select product_kind from dproductmsg where product_id=?) ";
			sql=sql+" ORDER BY x.section_code, x.attr_name                            ";
			
			dao.addParam(new Object[]{productId,productId});
			list = dao.query(sql);
			
			for(int i =0 ;i<list.size();i++){
				tmpMap = list.get(i);
				tmpAttrType = Integer.parseInt((String)tmpMap.get("attr_type"));
				tmpAttrName = (String)tmpMap.get("attr_name");
				tmpAttrlabel = (String)tmpMap.get("attr_label");
				tmpAttrlabelEn = (String)tmpMap.get("attr_label_en");
				tmpDefaultValue = (String)tmpMap.get("attr_value");
				curSection = (String)tmpMap.get("section_code");
				tmpListValue = (String)tmpMap.get("defaule_value");
				//英文
				if(usr==0){
					labelvalue="<label class=\"attr_label\">"+tmpAttrlabelEn+"</label>";
				}
				//中文
				if(usr==1){
					labelvalue="<label class=\"attr_label\">"+tmpAttrlabel+"</label>";
				}
				
				//checkBox
				if(tmpAttrType==1){
					
					retValue = retValue +"<div class=\"pro_item\">"+labelvalue+"<div class=\"multi_opr_area\"><input type=\"checkbox\" name=\""+tmpAttrName+"\" id=\""+tmpAttrName+"\"";
					if(null!=tmpDefaultValue && !"null".equals(tmpDefaultValue)  ){
						retValue = retValue +"checked";
					}
					retValue = retValue +"/></div></div>";
					//retValue = retValue +labelvalue+"<input type=\"checkbox\" name=\""+tmpAttrName+"\" id=\""+tmpAttrName+"\"";
					//retValue = retValue +"/>\r\n";
				}
				//text
				if(tmpAttrType==2){
					if(null!=tmpDefaultValue && !"null".equals(tmpDefaultValue)  ){
						tmpDefaultValue="";
					}
					retValue = retValue +"<div class=\"pro_item\">"+labelvalue+"<div class=\"multi_opr_area\"><input type=\"text\" name=\""+tmpAttrName+"\" id=\""+tmpAttrName+"\"";
					retValue = retValue +" value=\""+tmpDefaultValue+"\" /></div></div>";

				}
				//select
				if(tmpAttrType==3){
					retValue = retValue +"<div class=\"pro_item\">"+ labelvalue+"<div class=\"multi_opr_area\"><select name=\""+tmpAttrName+"\" >";
					if(null==tmpDefaultValue && "null".equals(tmpDefaultValue)  ){
						tmpDefaultValue="";
					}
					if(null==tmpListValue && "null".equals(tmpListValue)  ){
						tmpListValue="";
					}
					StringTokenizer st = new StringTokenizer(tmpListValue,"\r\n");
					while(st.hasMoreTokens()){
						tokenValue= st.nextToken();
						if(tmpDefaultValue.equals(tokenValue)){
							retValue = retValue +"<option value=\""+tokenValue+"\"  selected >"+tokenValue+"</option>\r\n";
						}else{
							retValue = retValue +"<option value=\""+tokenValue+"\"   >"+tokenValue+"</option>\r\n";
						}
						
					}
					retValue = retValue+"</select></div></div>";
				}

				if(!curSection.equals(preSection)){
					preSection = curSection;
					retValue = retValue +"<br>"; 
				}
			}
		}
		
		System.out.println(retValue);
		return retValue;
	}

	public String loadTree(String parentCode) throws Exception{
		String sql = "";
		sql = "SELECT a.parent_kind_code,                            ";
		sql = sql +"       a.kind_code,                              ";
		sql = sql +"       b.kind_name,                              ";
		sql = sql +"       b.kind_name_en                            ";
		sql = sql +"  FROM s_cfg_productinfo a, s_cfg_productkind b  ";
		sql = sql +" WHERE     a.parent_kind_code = ?                ";
		sql = sql +"       AND a.kind_code = b.kind_code             ";
		sql = sql +"       AND b.status = '1' and a.denorm_level=1   ";
		Dao dao = getDao();
		dao.addParam(new Object[]{parentCode});
		List<Map<String,Object>> retList = dao.query(sql);
		String tmpParentCode="";
		String tmpKindCode="";
		String tmpKindName="";
		String tmpKindNameEn="";
		Map<String,Object> map = null;
		String dTree ="";
		dTree="";
		for(int i = 0 ;i<retList.size() ;i++){
			map = retList.get(i);
			tmpParentCode = (String)map.get("parent_kind_code");
			tmpKindCode = (String)map.get("kind_code");
			tmpKindName = (String)map.get("kind_name");
			tmpKindNameEn= (String)map.get("kind_name_en");
			dTree = dTree + "d.add("+tmpKindCode+","+tmpParentCode+",'"+tmpKindName+" ("+tmpKindNameEn+")',\"javascript:selectnode('"+tmpKindCode+"','"+tmpKindName+" ("+tmpKindNameEn+")')\");";
			dTree = dTree+"\r\n";
			dTree = dTree+ loadTree(tmpKindCode);
		}
		return dTree;
	}
	
	public String loadSectionTree(String kindCode) throws Exception{
		String ret = "";
		String sql="";
		sql = sql +"SELECT section_code,                       ";
		sql = sql +"       section_name,                       ";
		sql = sql +"       section_name_en                     ";
		sql = sql +"  FROM s_cfg_product_attr_section a        ";
		sql = sql +" WHERE kind_code = ? AND status = 1        ";
		sql = sql +"ORDER BY z_index                           ";
		Dao dao = getDao();
		dao.addParam(new Object[]{kindCode});
		List<Map<String,Object>> list = dao.query(sql);
		Map<String,Object> map = null;
		String sectionCode = null;
		String sectionName= null;
		String sectionNameEn = null;
		String dis_name = null;
		ret =" d = new dTree('d');"+"\r\n";
		ret = ret +" d.add(1000,-1,'根目录');"+"\r\n";
		for(int i = 0 ;i<list.size() ;i++){
			map = list.get(i);
			sectionCode = (String)map.get("section_code");
			sectionName = (String)map.get("section_name");
			sectionNameEn = (String)map.get("section_name_en");
			dis_name = sectionName +" ("+sectionNameEn+")";
			ret = ret +" d.add("+sectionCode+",1000,'"+dis_name+"',"+"\"javascript:selectnode('"+sectionCode+"','"+dis_name+"')"+"\");"+"\r\n";
		}
		ret = ret +"document.write(d);";
		return ret;
	}
	
	public List<Map<String,Object>> kindSectionList(String kindCode) throws Exception{
		Dao dao = getDao();
		List<Map<String,Object>> list = null;
		String sql ="";
		sql = sql +" SELECT a.kind_code,a.kind_name,a.kind_name_en,                      ";
		sql = sql +"        b.section_code,b.section_name,b.section_name_en,b.note       ";
		sql = sql +"   FROM s_cfg_productkind a, s_cfg_product_attr_section b            ";
		sql = sql +"  WHERE a.status = 1 AND a.kind_code = b.kind_code AND b.status = 1  ";
		if(!("".equals(kindCode)) && !("NULL".equals(kindCode)) &&!("null".equals(kindCode)) ){
			sql = sql +"    AND a.kind_code = '"+kindCode+"'";
		}
		sql = sql +" ORDER BY a.kind_code, b.section_code, b.z_index                     ";
		list = dao.query(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> kindAttrList(String kindCode,String sectionCode) throws Exception {
		Dao dao = getDao();
		String sql="";
		sql = sql +"SELECT a.kind_code,c.kind_name,c.kind_name_en,a.section_code,                            ";
		sql = sql +"       a.section_name,a.section_name_en,b.attr_type,b.attr_name,                         ";
		sql = sql +"       b.attr_label,b.attr_label_en,b.defaule_value,b.note                               ";
		sql = sql +"  FROM s_cfg_product_attr_section a,s_cfg_product_attr b,s_cfg_productkind c             ";
		sql = sql +" WHERE a.kind_code = b.kind_code AND a.section_code = b.section_code                     ";
		
		if(!("".equals(kindCode)) && !("NULL".equals(kindCode)) &&!("null".equals(kindCode)) ){
			sql = sql +"    AND a.kind_code = '"+kindCode+"'";
		}
		
		if(!("".equals(sectionCode)) && !("NULL".equals(sectionCode)) &&!("null".equals(sectionCode)) ){
			sql = sql +"       AND a.section_code = '"+sectionCode+"'                                 ";
		}		
		sql = sql +"       AND a.status = 1 AND b.status = 1 AND a.kind_code = c.kind_code AND c.status = 1  ";
		
		List<Map<String,Object>> list = dao.query(sql);
		return list;
	}
	
	public List<Map<String,Object>> productList(String status,HttpServletRequest request) throws Exception{
		String sql="";
		List<String> parmalist = new ArrayList<String>();
		sql= sql +"SELECT a.product_name,a.product_no,a.product_id,b.kind_name,b.kind_name_en,         ";
		sql= sql +"a.brand_code,c.value,c.value_en,a.user_id,a.init_date,a.update_date,a.product_stat,d.status_name ";
		sql= sql +"  FROM dproductmsg a, s_cfg_productkind b, s_cfg_unit c ,s_cfg_productstatusmsg d   ";
		sql= sql +" WHERE a.product_kind = b.kind_code AND a.unit_code = c.code and  a.product_stat = d.status_id  ";
		Dao dao = getDao();
		if( null!=status && !"".equals(status) && !"NULL".equals(status) &&!"null".equals(status)){
			sql= sql +"  AND a.product_stat = '"+status+"'";
		}
		
		String prodcutName = null==((String)request.getParameter("product_name"))? "":(String)request.getParameter("product_name");
		String kindcode = null==((String)request.getParameter("kind_code"))? "":(String)request.getParameter("kind_code");
		String kindname = null==((String)request.getParameter("kind_name"))? "":(String)request.getParameter("kind_name");

		Object[] params = {};
		if(!prodcutName.equals("")){
			sql = sql +" and a.product_name like ?";
			parmalist.add("%"+prodcutName+"%");
		}
		
		if(!kindcode.equals("")){
			sql = sql +" and b.kind_code = ?";
			parmalist.add(kindcode);
		}
		String tmpParam = "";
		if(parmalist.size()>0){
			params = new Object[parmalist.size()];
			for(int i = 0 ;i<parmalist.size();i++){
				tmpParam = parmalist.get(i);
				params[i] = tmpParam;
			}
			if(params.length>0){
				dao.addParam(params);
			}
		}
		String sql1 = "";
		sql1 = "select count(1) count_ from ( "+sql+" ) xxxxxx";
		String countStr = dao.find(sql1);
		
		
		
		PageBean pageBean = new PageBean(request,Integer.valueOf(countStr));
		if(params.length>0){
			dao.addParam(params);
		}		
		List<Map<String,Object>> list = pageBean.pageList(dao, sql);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("kind_code", kindcode);
		request.setAttribute("kind_name", kindname);
		request.setAttribute("product_name",prodcutName);
		request.setAttribute("status",status);
		return list;
	}
	
	public List<Map<String,Object>> getSubKindList(String parent_kind_code) throws Exception{
		String sql="";
		sql = sql +"SELECT a.kind_code, b.kind_name FROM s_cfg_productinfo a, s_cfg_productkind b   ";
		sql = sql +" WHERE a.parent_kind_code = ? AND a.kind_code = b.kind_code AND b.status = 1 and a.denorm_level='1' ";
		List<Map<String,Object>> list = null;
		Dao dao = getDao();
		dao.addParam(new Object[]{parent_kind_code});
		list = dao.query(sql);
		return list;
	}
	
	public List<Map<String,Object>> kindList() throws Exception{
		List<Map<String,Object>> list = null;
		String sql="";
		Dao dao = Config.getDao();
		sql = sql +"SELECT t1.kind_code,                                      ";
		sql = sql +"       t1.kind_name,                                      ";
		sql = sql +"       t1.kind_name_en,                                   ";
		sql = sql +"       t1.denorm_level,                                   ";
		sql = sql +"       ifnull(t2.ftl_id, '') ftl_id,                      ";
		sql = sql +"       ifnull(t2.ftl_name, '') ftl_name,                  ";
		sql = sql +"       ifnull(t2.file_name, '') file_name                 ";
		sql = sql +"  FROM    (SELECT a.kind_code,                            ";
		sql = sql +"                  a.kind_name,                            ";
		sql = sql +"                  a.kind_name_en,                         ";
		sql = sql +"                  b.denorm_level                          ";
		sql = sql +"             FROM s_cfg_productkind a, s_cfg_productinfo b";
		sql = sql +"            WHERE     a.kind_code = b.kind_code           ";
		sql = sql +"                  AND b.parent_kind_code = '1000'         ";
		sql = sql +"                  AND a.status = '1') t1                  ";
		sql = sql +"       LEFT JOIN                                          ";
		sql = sql +"          (SELECT d.ftl_id, d.ftl_name, c.kind_code,d.file_name ";
		sql = sql +"             FROM s_cfg_kind_tpl c, s_cfg_ftl d           ";
		sql = sql +"            WHERE c.ftl_id = d.ftl_id) t2                 ";
		sql = sql +"       ON t1.kind_code = t2.kind_code                     ";
		sql = sql +"       order by t1.kind_code,t1.denorm_level              ";
		list = dao.query(sql);
		return list;
	}
	
	public int updateKindModel(String kindcode,String ftlid) throws Exception{
		int ret = 0;
		Dao dao = getDao();
	    int i_count = 0;
	    i_count = Integer.parseInt(dao.find("select count(1) _count from s_cfg_kind_tpl where kind_code='"+kindcode+"'"));
	    String sql = "";
	    if(i_count==0){
	    	sql="insert into s_cfg_kind_tpl values ('"+kindcode+"','"+ftlid+"')";
	    }
	    if(i_count==1){
	    	sql="update s_cfg_kind_tpl set ftl_id='"+ftlid+"' where kind_code='"+kindcode+"'";
	    }
	    ret = dao.execute(sql);
		return ret;
	}

}


