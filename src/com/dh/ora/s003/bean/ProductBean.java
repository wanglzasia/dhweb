package com.dh.ora.s003.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.HtmlUtils;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s004.service.AdminSrvImpl;
import com.dh.ora.tool.FreeMarkerToHtml;
import com.x.orange.Config;
import com.x.orange.dao.Dao;
import com.x.orange.util.ConfigUtil;

public class ProductBean {
	private User usr;
	private String proId = null;
	private String proKind = null;
	private String proName = null;
	private String proBrand = null;
	private String proNo = null;
	private String proUnit = null;
	private String selType = null;
	private String proStat = null;
	private String proPrepareCount = null;
	private String proPrepareDay = null;
	//private List<Map<String,String>> proBuyCountList = null;
	//private List<Map<String,String>> proBuyPriceList = null;
	private String singlePicUrl1 = null;
	private String singlePicUrl2 = null;
	private String singlePicUrl3 = null;
	private String singlePicUrl4 = null;
	private String singlePicUrl5 = null;
	private String singlePicUrl6 = null;
	private String proSimpleDescript = null;
	private String proDetailDescript = null;
	private String proPackageLength = null;
	private String proPackageWidth = null;
	private String proPackageHeight = null;
	private String proPackageWeight = null;
	private String proDeliveryKey = null;
	private String userId = null;
	private String prodStatus = null;
	private String html_full_name = null;

	private String proUri = null;
	
	private List<String> sqlList = null;

	//private Map<String,String> attrMap = new HashMap<String,String>();
	
	/*产品信息,供生成html文件使用  键值对*/
	public Map<String, Object> proHtmlAttrValue = null;
	/*产品价格数据*/
	public List<Map<String,Object>> priceListMap = null;
	/*产品属性数据*/
	public List<Map<String,Object>> proAttrListMap = null;
	
	public List<String> getSqlList() {
		return sqlList;
	}
	public void setSqlList(List<String> sqlList) {
		this.sqlList = sqlList;
	}
	
	public ProductBean(String proId){
		
	}
	
	public ProductBean(){
		
	}
	
	public String getParam(HttpServletRequest request,String paramName){
		String retValue = null;
		retValue = HtmlUtils.htmlEscape((String)request.getParameter(paramName));
		/*
		if(null == retValue || "null".equals(retValue) || "".equals(retValue)){
			retValue="NULL-1-XX-ZZ-LL";
		}*/
		return retValue;
	}
	public String decodeHtml(String input){
		String retValue = null;
		retValue = HtmlUtils.htmlUnescape(input);
		/*
		if(null == retValue || "null".equals(retValue)){
			retValue="";
		}*/
		//System.out.println(retValue);
		return retValue;
	}
	public String readMap(Map<String,Object> map,String key){
		String retValue = decodeHtml((String)map.get(key));
		if("".equals(retValue)){retValue="";}
		proHtmlAttrValue.put(key, retValue);
		return retValue;
	}
	
	
	
	
	/**
	 * 
	 * @Method:	ProductBean::save
	 * @param @param request
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月30日上午11:18:47
	 * @throws 
	 * @author wanglz
	 * @Description: 产品保存
	 */
	public int save(HttpServletRequest request) throws Exception {
		int ret = 1;
		parserAttr(request);
		Dao dao = Config.getDao();
		for(int i = 0 ;i<this.getSqlList().size(); i++){
			dao.addBatch(getSqlList().get(i));
			//System.out.println(getSqlList().get(i));
		}
		int retValue []= dao.execBatch();
		for(int i = 0 ;i<retValue.length;i++){
			if(retValue[i]==0){
				ret = retValue[i];
				break;
			}
		}
		return ret; 
	}
	
	/**
	 * 
	 * @Method:	ProductBean::show
	 * @param @param proId
	 * @param @return
	 * @param @throws Exception
	 * @return Object
	 * @date: 2014年6月27日下午9:39:59
	 * @throws 
	 * @author wanglz
	 * @Description: 根据ProID读取产品信息
	 * ifnull(colname,0)
	 */
	public ProductBean show(String proId)  throws Exception{
		this.setProId(proId);
		String sql="select a.product_id,a.product_kind,a.product_no,ifnull(a.product_name,\"\") product_name,ifnull(a.brand_code,\"\") brand_code ,a.unit_code,a.sale_type,";
		sql=sql+" a.prepare_stat,a.prepare_count,a.prepare_day,ifnull(a.package_length,\"\") package_length,ifnull(a.package_width,\"\") package_width,ifnull(a.package_height,\"\") package_height,";
		sql=sql+" ifnull(a.package_weight,\"\") package_weight,ifnull(a.simple_desc,\"\") simple_desc, ifnull(a.detail_desc,\"\") detail_desc ,a.product_stat,ifnull(b.pic_name_1,\"\") pic_name_1 ,ifnull(b.pic_name_2,\"\") pic_name_2,ifnull(b.pic_name_3,\"\") pic_name_3,";
		sql=sql+" ifnull(b.pic_name_4,\"\") pic_name_4,ifnull(b.pic_name_5,\"\") pic_name_5,ifnull(b.pic_name_6,\"\") pic_name_6,";
		sql=sql+" b.html_full_name,b.delivery_key,a.user_id";
		sql=sql+" from dproductmsg a, dproductothermsg b ";
		sql=sql+" where a.product_id = b.product_id";
		sql=sql+" and a.product_id =?";
		Dao dao = Config.getDao();
		dao.addParam(new Object[]{getProId()});
		List<Map<String,Object>> mapList = dao.query(sql);
		fillValue(mapList);
		
		//价格数据
		sql="SELECT ifnull(min_count,0) min_count , ifnull(sale_price,0) sale_price FROM dproductpricemsg ";
		sql = sql +"WHERE product_id = ? ORDER BY min_count";
		dao.addParam(new Object[]{getProId()});
		priceListMap = dao.query(sql);
		
		//属性数据
		sql="SELECT a.section_code,                          ";
		sql=sql+"       a.attr_type,                         ";
		sql=sql+"       a.attr_name,                         ";
		sql=sql+"       a.attr_label,                        ";
		sql=sql+"       a.attr_label_en,                     ";
		sql=sql+"       a.defaule_value,                     ";
		sql=sql+"       ifnull(b.product_id,'-') product_id, ";
		sql=sql+"       ifnull(b.attr_value,'-') attr_value  ";
		sql=sql+"  FROM    s_cfg_product_attr a              ";
		sql=sql+"       LEFT JOIN                            ";
		sql=sql+"          dprodcutattrmsg b                 ";
		sql=sql+"       ON     a.attr_name = b.attr_name     ";
		sql=sql+"          AND a.kind_code = b.kind_code     ";
		sql=sql+"          AND b.product_id = ?   			 ";
		sql=sql+" where a.kind_code in (  select product_Kind from dproductmsg where product_id = ? )";
		sql=sql+" ORDER BY a.section_code, a.attr_name;       ";
		
		dao.addParam(new Object[]{getProId(),getProId()});
		proAttrListMap = dao.query(sql);
		
		return this;
	}
	
	/**
	 * 
	 * @Method:	ProductBean::Modify
	 * @param @param request
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月30日上午11:19:40
	 * @throws 
	 * @author wanglz
	 * @Description: 修改
	 */
	public int Modify(HttpServletRequest req) throws Exception {
		Dao dao = Config.getDao();
		//setProId(dao.find("select nextval('productid')"));
		setProId(getParam(req,"pro_id"));
		setProKind(getParam(req,"pro_kind"));
		setProBrand(getParam(req,"pro_brand"));
		setProName(getParam(req,"pro_name"));
		setProNo(getParam(req,"pro_no"));
		setProUnit(getParam(req,"pro_unit"));
		setSelType(getParam(req,"sel_type"));
		setProStat(getParam(req,"pro_stat"));
		setProPrepareCount(getParam(req,"pro_prepare_count"));
		setProPrepareDay(getParam(req,"pro_prepare_day"));
 		setSinglePicUrl1(getParam(req,"single_pic_url_1"));//
 		setSinglePicUrl2(getParam(req,"single_pic_url_2"));
 		setSinglePicUrl3(getParam(req,"single_pic_url_3"));
 		setSinglePicUrl4(getParam(req,"single_pic_url_4"));
 		setSinglePicUrl5(getParam(req,"single_pic_url_5"));
 		setSinglePicUrl6(getParam(req,"single_pic_url_6"));
 		setProSimpleDescript(getParam(req,"pro_simple_descript"));
 		setProDetailDescript(getParam(req,"pro_detail_descript"));
 		setProPackageLength(getParam(req,"pro_package_length"));
 		setProPackageWidth(getParam(req,"pro_package_width"));
 		setProPackageHeight(getParam(req,"pro_package_height"));
 		setProPackageWeight(getParam(req,"pro_package_weight"));
 		setProDeliveryKey(getParam(req,"pro_delivery_key"));
		
 		if(getProNo().equals("")){
 			setProNo(getProId());
 		}
 		
 		@SuppressWarnings("rawtypes")
		Enumeration paramNames = req.getParameterNames();
		String paramName = null;
		String tmpBuyCount = null;
		String tmpBuyPrice = null;
		String tmpAttrSql ="";
		String tmpSql="";
		
		String sqlPrice ="";
		sqlList = new ArrayList<String>();
		
		/*1、产品属性数据*/
		tmpAttrSql ="delete from dprodcutattrmsg where product_id='"+proId+"'";
		sqlList.add(tmpAttrSql);
		while(paramNames.hasMoreElements()){
			paramName = (String)paramNames.nextElement();
			if(paramName.contains("attr")){
				tmpAttrSql = "insert into dprodcutattrmsg(product_id,attr_name,attr_value,update_time,kind_code) values ";
				tmpAttrSql = tmpAttrSql+"('"+proId+"','"+paramName+"','"+getParam(req,paramName)+"',sysdate(),'"+getProKind()+"')";
				sqlList.add(tmpAttrSql);
			}
		}
		
		/*2、产品价格数据*/
		sqlPrice  = "delete from dProductPriceMsg where product_id='"+proId+"'";
		sqlList.add(sqlPrice);
		
		String priceSecNum = getParam(req,"probprice_c");
		int priceSecInt = Integer.parseInt(priceSecNum);
		String lowSaleCount = "0";
		String highSaleCount = "0";
		String lowSalePrice="0.00";
		String highSalePrice="0.00";
		int loop =0;
		for(int j = 0 ;j<=priceSecInt;j++){
			tmpBuyCount = getParam(req,"pro_buy_count"+j);
			tmpBuyPrice = getParam(req,"pro_buy_price"+j);
			if(null==tmpBuyCount){continue;}
			if(loop==0){
				lowSaleCount = tmpBuyCount;
				highSaleCount = tmpBuyCount;
				lowSalePrice = tmpBuyPrice;
				highSalePrice = tmpBuyPrice;
			}else{
				highSaleCount = tmpBuyCount;
				highSalePrice = tmpBuyPrice;
			}
			sqlPrice="insert into dProductPriceMsg(product_id,min_count,sale_price) values ('"+proId+"','"+tmpBuyCount+"','"+tmpBuyPrice+"')";
			sqlList.add(ReplaceSqlNull(sqlPrice));
			loop++;
		}
		/*价格两端趋势*/
		sqlPrice="insert into dProductPriceLHMsg(product_id,min_count,max_count,min_price,max_price) values ('"+proId+"','"+lowSaleCount+"','"+highSaleCount+"','"+lowSalePrice+"','"+highSalePrice+"')";
		sqlList.add(ReplaceSqlNull(sqlPrice));
		
		/*3、产品基本信息*/
		tmpSql="update dProductMsg set product_no='"+getProNo()+"', product_name='"+getProName()+"',brand_code='"+getProBrand()+"',";
		tmpSql = tmpSql+"unit_code='"+getProUnit()+"', sale_type='"+getSelType()+"', prepare_stat='"+getProStat()+"',prepare_count='"+getProPrepareCount()+"',";
		tmpSql = tmpSql+"prepare_day='"+getProPrepareDay()+"',package_length='"+getProPackageLength()+"',package_width='"+getProPackageWidth()+"',";
		tmpSql = tmpSql+"package_height='"+getProPackageHeight()+"',package_weight='"+getProPackageWeight()+"',simple_desc='"+getProSimpleDescript()+"',";
		tmpSql = tmpSql+"detail_desc='"+getProDetailDescript()+"',product_stat='0' ";
		tmpSql = tmpSql+" where product_id='"+proId+"'";
		sqlList.add(ReplaceSqlNull(tmpSql));
		
		/*4、产品其他信息*/
		tmpSql = "update dProductOtherMsg set pic_name_1='"+getSinglePicUrl1()+"', pic_name_2='"+getSinglePicUrl2()+"',";
		tmpSql = tmpSql +" pic_name_3='"+getSinglePicUrl3()+"',pic_name_4='"+getSinglePicUrl4()+"',pic_name_5='"+getSinglePicUrl5()+"',";
		tmpSql = tmpSql +" pic_name_6='"+getSinglePicUrl6()+"',delivery_key='"+getProDeliveryKey()+"'";
		tmpSql = tmpSql +" where product_id='"+proId+"'";
		sqlList.add(ReplaceSqlNull(tmpSql));
		
		int ret = 0;
		for(int i = 0 ;i<this.getSqlList().size(); i++){
			dao.addBatch(getSqlList().get(i));
			//System.out.println(getSqlList().get(i));
		}
		int retValue []= dao.execBatch();
		for(int i = 0 ;i<retValue.length;i++){
			if(retValue[i]==0){
				ret = retValue[i];
				break;
			}
		}
		return ret; 
	}
	
	/**
	 * 
	 * @Method:	ProductBean::parserAttr
	 * @param @param req
	 * @return void
	 * @date: 2014年6月19日下午4:38:39
	 * @throws 
	 * @Description: 解析request封装的参数
	 */
	public void parserAttr(HttpServletRequest req){
		Dao dao = Config.getDao();
		setProId(dao.find("select nextval('productid')"));
		setProKind(getParam(req,"pro_kind"));
		setProBrand(getParam(req,"pro_brand"));
		setProName(getParam(req,"pro_name"));
		setProNo(getParam(req,"pro_no"));
		setProUnit(getParam(req,"pro_unit"));
		setSelType(getParam(req,"sel_type"));
		setProStat(getParam(req,"pro_stat"));
		setProPrepareCount(getParam(req,"pro_prepare_count"));
		setProPrepareDay(getParam(req,"pro_prepare_day"));
 		setSinglePicUrl1(getParam(req,"single_pic_url_1"));//
 		setSinglePicUrl2(getParam(req,"single_pic_url_2"));
 		setSinglePicUrl3(getParam(req,"single_pic_url_3"));
 		setSinglePicUrl4(getParam(req,"single_pic_url_4"));
 		setSinglePicUrl5(getParam(req,"single_pic_url_5"));
 		setSinglePicUrl6(getParam(req,"single_pic_url_6"));
 		setProSimpleDescript(getParam(req,"pro_simple_descript"));
 		setProDetailDescript(getParam(req,"pro_detail_descript"));
 		setProPackageLength(getParam(req,"pro_package_length"));
 		setProPackageWidth(getParam(req,"pro_package_width"));
 		setProPackageHeight(getParam(req,"pro_package_height"));
 		setProPackageWeight(getParam(req,"pro_package_weight"));
 		setProDeliveryKey(getParam(req,"pro_delivery_key"));
		
 		if(getProNo().equals("")){
 			setProNo(getProId());
 		}
 		
 		@SuppressWarnings("rawtypes")
		Enumeration paramNames = req.getParameterNames();
		String paramName = null;
		String tmpBuyCount = null;
		String tmpBuyPrice = null;
		String tmpAttrSql ="";
		String tmpSql="";
		String sqlPrice ="";
		sqlList = new ArrayList<String>();
	
		while(paramNames.hasMoreElements()){
			paramName = (String)paramNames.nextElement();
			if(paramName.contains("attr")){
				tmpAttrSql = "insert into dprodcutattrmsg(product_id,attr_name,attr_value,update_time,kind_code) values ";
				tmpAttrSql = tmpAttrSql+"('"+proId+"','"+paramName+"','"+getParam(req,paramName)+"',sysdate(),'"+getProKind()+"')";
				sqlList.add(tmpAttrSql);
			}
		}
		String priceSecNum = getParam(req,"probprice_c");
		int priceSecInt = Integer.parseInt(priceSecNum);
		String lowSaleCount = "0";
		String highSaleCount = "0";
		String lowSalePrice="0.00";
		String highSalePrice="0.00";
		int loop =0;
		for(int j = 0 ;j<=priceSecInt;j++){
			tmpBuyCount = getParam(req,"pro_buy_count"+j);
			tmpBuyPrice = getParam(req,"pro_buy_price"+j);
			if(null==tmpBuyCount){continue;}
			if(loop==0){
				lowSaleCount = tmpBuyCount;
				highSaleCount = tmpBuyCount;
				lowSalePrice = tmpBuyPrice;
				highSalePrice = tmpBuyPrice;
			}else{
				highSaleCount = tmpBuyCount;
				highSalePrice = tmpBuyPrice;
			}
			sqlPrice="insert into dProductPriceMsg(product_id,min_count,sale_price) values ('"+proId+"','"+tmpBuyCount+"','"+tmpBuyPrice+"')";
			sqlList.add(ReplaceSqlNull(sqlPrice));
			loop++;
		}
		
		/*价格两端趋势*/
		sqlPrice="insert into dProductPriceLHMsg(product_id,min_count,max_count,min_price,max_price) values ('"+proId+"','"+lowSaleCount+"','"+highSaleCount+"','"+lowSalePrice+"','"+highSalePrice+"')";
		sqlList.add(ReplaceSqlNull(sqlPrice));		
		
		String userId=getUserId();
		tmpSql="insert into dProductMsg(product_id,product_kind,product_no,product_name,brand_code,unit_code,sale_type,prepare_stat,";
		tmpSql=tmpSql+"prepare_count,prepare_day,package_length,package_width,package_height,package_weight,simple_desc,detail_desc,";
		tmpSql=tmpSql+"product_stat,init_date,user_id) values ('"+proId+"','"+getProKind()+"','"+getProNo()+"',";
		tmpSql=tmpSql+"'"+getProName()+"','"+getProBrand()+"','"+getProUnit()+"','"+getSelType()+"','"+getProStat()+"'";
		tmpSql=tmpSql+",'"+getProPrepareCount()+"','"+getProPrepareDay()+"','"+getProPackageLength()+"','"+getProPackageWidth()+"'";
		tmpSql=tmpSql+",'"+getProPackageHeight()+"','"+getProPackageWeight()+"','"+getProSimpleDescript()+"','"+getProDetailDescript()+"'";
		tmpSql=tmpSql+",'0',sysdate(),'"+userId+"')";
		sqlList.add(ReplaceSqlNull(tmpSql));

		tmpSql="insert into dProductOtherMsg(product_id,pic_name_1,pic_name_2,pic_name_3,pic_name_4,pic_name_5,pic_name_6,delivery_key)";
		tmpSql=tmpSql+"values ('"+proId+"','"+getSinglePicUrl1()+"','"+getSinglePicUrl2()+"','"+getSinglePicUrl3()+"','"+getSinglePicUrl4()+"'";
		tmpSql=tmpSql+",'"+getSinglePicUrl5()+"','"+getSinglePicUrl6()+"','"+getProDeliveryKey()+"')";
		sqlList.add(ReplaceSqlNull(tmpSql));
	}
	
	public String ReplaceSqlNull(String sql){
		return sql.replace("\'null\'","NULL").replace("\'\'", "NULL");
	}
	/**
	 * 
	 * @Method:	ProductBean::putOnShelves
	 * @param @param proNo
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月30日上午11:23:28
	 * @throws 
	 * @author wanglz
	 * @Description: 上架
	 */
	public int putOnShelves(String proNo) throws Exception {
		String sql="update dproductmsg set product_stat='3',update_date=sysdate() where   product_id=?";
		Dao dao = Config.getDao();
		dao.addParam(new Object[]{proNo});
		int ret = dao.execute(sql);
 		return ret;
	} 
	/**
	 * 
	 * @Method:	ProductBean::pullOffShelves
	 * @param @param proNo
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月30日上午11:23:28
	 * @throws 
	 * @author wanglz
	 * @Description: 下架
	 */
	public int pullOffShelves(String proNo) throws Exception {
		String sql="update dproductmsg set product_stat='4',update_date=sysdate() where   product_id=?";
		Dao dao = Config.getDao();
		dao.addParam(new Object[]{proNo});
		int ret = dao.execute(sql);
 		return ret;
	}
	/**
	 * 
	 * @Method:	ProductBean::auditRefuse
	 * @param @param proNo
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月30日上午11:23:28
	 * @throws 
	 * @author wanglz
	 * @Description: 审批未通过
	 */
	public int auditRefuse(String proNo) throws Exception {
		String sql="update dproductmsg set product_stat='1',update_date=sysdate() where   product_id=?";
		Dao dao = Config.getDao();
		dao.addParam(new Object[]{proNo});
		int ret = dao.execute(sql);
 		return ret;
	}
	
	public void fillValue(List<Map<String,Object>> list) throws Exception{
		proHtmlAttrValue = new HashMap<String ,Object>();
		for(int i = 0 ;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			this.setProId(readMap(map,"product_id"));
			this.setProKind(readMap(map,"product_kind"));
			this.setProNo(readMap(map,"product_no"));
			this.setProName(readMap(map,"product_name"));
			this.setProBrand(readMap(map,"brand_code"));
			this.setProUnit(readMap(map,"unit_code"));
			this.setSelType(readMap(map,"sale_type"));
			this.setProStat(readMap(map,"prepare_stat"));
			this.setProPrepareCount(readMap(map,"prepare_count"));
			this.setProPrepareDay(readMap(map,"prepare_day"));
			this.setProPackageLength(readMap(map,"package_length"));
			this.setProPackageWidth(readMap(map,"package_width"));
			this.setProPackageHeight(readMap(map,"package_height"));
			this.setProPackageWeight(readMap(map,"package_weight"));
			this.setProSimpleDescript(readMap(map,"simple_desc"));
			this.setProDetailDescript(readMap(map,"detail_desc"));			
			this.setProdStatus(readMap(map,"product_stat"));
			this.setSinglePicUrl1(readMap(map,"pic_name_1"));
			this.setSinglePicUrl2(readMap(map,"pic_name_2"));
			this.setSinglePicUrl3(readMap(map,"pic_name_3"));
			this.setSinglePicUrl4(readMap(map,"pic_name_4"));
			this.setSinglePicUrl5(readMap(map,"pic_name_5"));
			this.setSinglePicUrl6(readMap(map,"pic_name_6"));
			this.setHtml_full_name(readMap(map,"html_full_name"));
			this.setProDeliveryKey(readMap(map,"delivery_key"));
			this.setUserId((String)map.get("user_id"));
		}
		proHtmlAttrValue.put("user_id", this.getUserId());
		proHtmlAttrValue.put("simple_desc", this.getProSimpleDescript());

	}
	/**
	 * 
	 * @Method:	ProductBean::auditPass
	 * @param @param proId
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月20日下午1:30:23
	 * @author wanglz
	 * @Description: 审核通过,父类只负责更新数据库中状态更新,子类负责html文件的生成
	 */
	public int auditPass(String proId) throws Exception {
		//1、查询数据,供生成html展示用
		show(proId);
		
		//2、价格信息处理
		String priceHtml = "";
		Map<String,Object> tmpMap = null;
		String minCount="";
		String maxCount="";
		String salePrice="";
		String preMaxCount="";
		long minCount_l=0;
		priceHtml="<table id=\"pricetable\" >"; 
		priceHtml = priceHtml+"<tr><th class=\"p_first_th\">Qty</th><th class=\"p_second_th\">Price</th></tr>";
		
		for(int i = 0;i<priceListMap.size();i++){
			tmpMap = priceListMap.get(i);
			//minCount = (String)tmpMap.get("min_count");
			maxCount = (String)tmpMap.get("min_count");
			salePrice = (String)tmpMap.get("sale_price");
			priceHtml = priceHtml+"<tr>";
			if(i==0){minCount = "1";preMaxCount=maxCount;}
			else{
				//maxCount=preMaxCount;
				minCount_l = Long.parseLong(preMaxCount)+1;
				minCount=String.valueOf(minCount_l);
				preMaxCount = maxCount;
			}
			priceHtml = priceHtml+"<td>"+minCount+" - "+maxCount+"</td><td>US $"+Double.parseDouble(salePrice)+"</td>";
			priceHtml = priceHtml+"</tr>";
		}
		priceHtml = priceHtml +"</table>";
		proHtmlAttrValue.put("priceHtml", priceHtml);		
	
		String attrHtml ="";
		int tmpAttrType= 0;
		String tmpAttrlabelEn="";
		String tmpDefaultValue="";
		String curSection="";
		String preSection="";
		
		if( null!=proAttrListMap && null!=proAttrListMap && !proAttrListMap.isEmpty() && !proAttrListMap.isEmpty()){
			 for(int i =0 ;i<proAttrListMap.size();i++){
				 tmpMap = (Map<String,Object>)proAttrListMap.get(i);
				 tmpAttrType = Integer.parseInt((String)tmpMap.get("attr_type"));
				 //tmpAttrlabel = (String)tmpMap.get("attr_label");
				 tmpAttrlabelEn = (String)tmpMap.get("attr_label_en");
				 tmpDefaultValue = (String)tmpMap.get("attr_value");
				 curSection = (String)tmpMap.get("section_code");
				 
				//checkBox
				if(tmpAttrType==1){
					 if(tmpDefaultValue.equals("on")){
						 attrHtml = attrHtml+tmpAttrlabelEn+"&nbsp;&nbsp;";
					 }
				}
				//text
				if(tmpAttrType==2){
					if(!"null".equals(tmpDefaultValue)){
						attrHtml = attrHtml+tmpAttrlabelEn +"&nbsp;&nbsp;:&nbsp;&nbsp;"+tmpDefaultValue+"&nbsp;&nbsp;";
					}
				}
				//textarea
				if(tmpAttrType==3){
					if(!"null".equals(tmpDefaultValue)){
						attrHtml = attrHtml+tmpAttrlabelEn +"&nbsp;&nbsp;:&nbsp;&nbsp;"+tmpDefaultValue+"&nbsp;&nbsp;";
					}
				}
				if(!curSection.equals(preSection)){
					preSection = curSection;
					attrHtml = attrHtml +"<br>"; 
				}
			 }
		}
		proHtmlAttrValue.put("attrHtmlValue", attrHtml);
		
		//meta ,footer数据
		AdminSrvImpl adminsrv = new AdminSrvImpl();
		List<Map<String,Object>> metalist = null;
		List<Map<String,Object>> footerlist = null;
		Map<String,Object> tmpMap1 = null;
		metalist = adminsrv.metaContentList("");
		footerlist = adminsrv.shopCfg();
		String footermsg = "";
		String tmpmeta="";
		for(int i = 0 ;i<metalist.size();i++){
			tmpMap1 = metalist.get(i);
			tmpmeta = "<meta "+tmpMap1.get("attr_name")+"=\""+tmpMap1.get("attr_value")+"\"" +"  content=\""+tmpMap1.get("meta_content")+"\"/>";
			tmpmeta = tmpmeta+"\r\n";
		}
		proHtmlAttrValue.put("meta_list", tmpmeta);
		for(int i =0;i<footerlist.size();i++){
			tmpMap1 = footerlist.get(i);
			footermsg = (String)tmpMap1.get("shop_footer");
		}
		proHtmlAttrValue.put("footermsg", footermsg);
		//4、生成html
		generateHtml();
		
	
		
		return 0;
	}
	
	/**
	 * 
	 * @Method:	ProductBean::generateHtml
	 * @param 
	 * @return void
	 * @date: 2014年6月20日下午3:12:30
	 * @throws 
	 * @author wanglz
	 * @Description: 默认生成html文件:模板,路径,文件名称均有系统默认
	 */
	public void generateHtml(){
		/**
		 * 默认路径组成
		 * 1、根路径 : rootBase 在config.properties文件中配置
		 * 2、用户ID: userId
		 * filePath = rootBase+userId;
		 */
		/**默认文件名称
		 * fileNmae:proKind+userId+proNo,在hash一下，取hash值
		 * 
		 */
		//this.setProId(this.getProId());
		//this.setUserId("1001");
		String filePath=ConfigUtil.getConfig("HTML_PATH")+File.separator+this.userId;
		String fileName=getUserId()+"-"+getProId()+ConfigUtil.getConfig("HTML_EXTENSION");
		String templatefile="product_show_model.ftl";
		generateHtml(templatefile,filePath, fileName);
	}
	/**
	 * 
	 * @Method:	ProductBean::generateHtml
	 * @param @param templatefile
	 * @return void
	 * @date: 2014年6月20日下午3:13:04
	 * @throws 
	 * @author wanglz
	 * @Description: 生成html文件:模板(指定);路径,文件名称均有系统默认 
	 */
	public void generateHtml(String templatefile){
		String desPath="";
		String desFile="";
		generateHtml(templatefile,desPath,desFile);
	}
	/**
	 * 
	 * @Method:	ProductBean::generateHtml
	 * @param @param templatefile
	 * @param @param desPath
	 * @param @param desFile
	 * @return void
	 * @date: 2014年6月20日下午3:14:04
	 * @throws 
	 * @author wanglz
	 * @Description: 生成html文件:模板(指定);路径(制定),文件名称(指定) 
	 */
	public void generateHtml(String templatefile,String desPath,String desFile){
		FreeMarkerToHtml toHtml = new FreeMarkerToHtml();
		String tplPath = ConfigUtil.getConfig("WEB_PATH");
		tplPath = tplPath +"\\ftl\\"+"s003";
		toHtml.setTemplate_path(tplPath);
		//*meta ,footer 信息*//
		
		
		
		
		
		toHtml.geneHtmlFile(templatefile, proHtmlAttrValue, desPath, desFile);
		/*更新状态为审核通过*/
		String sql="update dproductothermsg set html_full_name='"+desFile+"' where product_id='"+getProId()+"'";
		String sql1="update dproductmsg set product_stat='2',update_date=sysdate() where   product_id='"+getProId()+"'";
		Dao dao = Config.getDao();
		dao.addBatch(sql);
		dao.addBatch(sql1);
		dao.execBatch();
	}
	
	public String getProKind() {
		return proKind;
	}
	public void setProKind(String proKind) {
		this.proKind = proKind;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProBrand() {
		return proBrand;
	}
	public void setProBrand(String proBrand) {
		this.proBrand = proBrand;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProUnit() {
		return proUnit;
	}
	public void setProUnit(String proUnit) {
		this.proUnit = proUnit;
	}
	public String getSelType() {
		return selType;
	}
	public void setSelType(String selType) {
		this.selType = selType;
	}
	public String getProStat() {
		return proStat;
	}
	public void setProStat(String proStat) {
		this.proStat = proStat;
	}
	public String getProPrepareCount() {
		return proPrepareCount;
	}
	public void setProPrepareCount(String proPrepareCount) {
		this.proPrepareCount = proPrepareCount;
	}
	public String getProPrepareDay() {
		return proPrepareDay;
	}
	public void setProPrepareDay(String proPrepareDay) {
		this.proPrepareDay = proPrepareDay;
	}
	public String getSinglePicUrl1() {
		return singlePicUrl1;
	}
	public void setSinglePicUrl1(String singlePicUrl1) {
		this.singlePicUrl1 = singlePicUrl1;
	}
	public String getSinglePicUrl2() {
		return singlePicUrl2;
	}
	public void setSinglePicUrl2(String singlePicUrl2) {
		this.singlePicUrl2 = singlePicUrl2;
	}
	public String getSinglePicUrl3() {
		return singlePicUrl3;
	}
	public void setSinglePicUrl3(String singlePicUrl3) {
		this.singlePicUrl3 = singlePicUrl3;
	}
	public String getSinglePicUrl4() {
		return singlePicUrl4;
	}
	public void setSinglePicUrl4(String singlePicUrl4) {
		this.singlePicUrl4 = singlePicUrl4;
	}
	public String getSinglePicUrl5() {
		return singlePicUrl5;
	}
	public void setSinglePicUrl5(String singlePicUrl5) {
		this.singlePicUrl5 = singlePicUrl5;
	}
	public String getSinglePicUrl6() {
		return singlePicUrl6;
	}
	public void setSinglePicUrl6(String singlePicUrl6) {
		this.singlePicUrl6 = singlePicUrl6;
	}
	public String getProSimpleDescript() {
		return proSimpleDescript;
	}
	public void setProSimpleDescript(String proSimpleDescript) {
		this.proSimpleDescript = proSimpleDescript;
	}
	public String getProDetailDescript() {
		return proDetailDescript;
	}
	public void setProDetailDescript(String proDetailDescript) {
		this.proDetailDescript = proDetailDescript;
	}
	public String getProPackageLength() {
		return proPackageLength;
	}
	public void setProPackageLength(String proPackageLength) {
		this.proPackageLength = proPackageLength;
	}
	public String getProPackageWidth() {
		return proPackageWidth;
	}
	public void setProPackageWidth(String proPackageWidth) {
		this.proPackageWidth = proPackageWidth;
	}
	public String getProPackageHeight() {
		return proPackageHeight;
	}
	public void setProPackageHeight(String proPackageHeight) {
		this.proPackageHeight = proPackageHeight;
	}
	public String getProPackageWeight() {
		return proPackageWeight;
	}
	public void setProPackageWeight(String proPackageWeight) {
		this.proPackageWeight = proPackageWeight;
	}
	public String getProDeliveryKey() {
		return proDeliveryKey;
	}
	public void setProDeliveryKey(String proDeliveryKey) {
		this.proDeliveryKey = proDeliveryKey;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}
	public String getHtml_full_name() {
		return html_full_name;
	}
	public void setHtml_full_name(String html_full_name) {
		this.html_full_name = html_full_name;
	}
	public String getProUri() {
		return proUri;
	}
	public void setProUri(String proUri) {
		this.proUri = proUri;
	}
	public User getUsr() {
		return usr;
	}
	public void setUsr(User usr) {
		this.usr = usr;
	}
}
