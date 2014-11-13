package com.dh.ora.s004.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.x.orange.Config;
import com.x.orange.dao.Dao;

public class CourierSrvImpl implements CourierSrv {

	@Override
	public int opCourier(HttpServletRequest request) throws Exception {
		int retValue = 0 ;
		Dao dao = Config.getDao();
		String act = (String)request.getParameter("act");
		String courier_id =(String)request.getParameter("courier_id");
		String courier_en_name =(String)request.getParameter("courier_en_name");
		String courier_cn_name =(String)request.getParameter("courier_cn_name");;
		String freight =(String)request.getParameter("freight");
		String deliery_day =(String)request.getParameter("deliery_day");
		String note =(String)request.getParameter("note");
		String sql="";
		if(act.equals("add"))
		{
			sql=" insert into dcouriermsg (courier_en_name,courier_cn_name,freight,deliery_day,note,status ) values(?,?,?,?,?,?)";
			dao.addParam(new Object[]{courier_en_name,courier_cn_name,freight,deliery_day,note,1});
		}
		if(act.equals("mod"))
		{
			sql="update dcouriermsg set courier_en_name=? ,courier_cn_name=? ,freight=? ,deliery_day=?,note=? where courier_id=?";
			dao.addParam(new Object[]{courier_en_name,courier_cn_name,freight,deliery_day,note,courier_id});
		}
		if(act.equals("del"))
		{
			sql="delete from  dcouriermsg  where courier_id=?";
			dao.addParam(new Object[]{courier_id});			
		}
		retValue = dao.execute(sql);
		return retValue;
	}

	@Override
	public List<Map<String, Object>> courierList(HttpServletRequest request)
			throws Exception {
		Dao dao = Config.getDao();
		String id = null == request.getParameter("courier_id")?"":request.getParameter("courier_id");
		String sql="select * from dcouriermsg where status=1 ";
		if(!id.equals("")){
			sql+=" and courier_id=?";
			dao.addParam(new Object[]{id});
		}
		sql+=" order by courier_id";
		List<Map<String,Object>> list = null;
		list = dao.query(sql);
		return list;
	}
	@Override
	public List<Map<String,Object>> courierzoneList(HttpServletRequest request) 
			throws Exception{
		Dao dao = Config.getDao();
		String courierId = null == request.getParameter("courier_id")?"":request.getParameter("courier_id");
		String zoneid = null == request.getParameter("zone_id")?"":request.getParameter("zone_id");
		String sql="select * from dcourier_freight where 1 = 1";//"where courier_id='?'";
		List<Object> list = new ArrayList<Object>();
		List<Map<String,Object>> courList = null;
		if(!courierId.equals("")){
			sql+=" and courier_id=?";
			list.add(courierId);
		}
		if(!zoneid.equals("")){
			sql+=" and zone_id=?";
			list.add(zoneid);
		}
		sql+=" order by zone_id";
		int paramSize = 0;
		
		Object[] params = null;
		if(list.size()>0){
			paramSize = list.size();
			params = new Object[paramSize];
			for(int i = 0 ;i<list.size() ;i++){
				params[i] = list.get(i);
			}
			dao.addParam(params);			
		}
		courList = dao.query(sql);
		return courList;
	}
	
	public int opCourierZone(HttpServletRequest request) throws Exception{
		int retValue = 0;
		Dao dao = Config.getDao();
		String act =  (String)request.getParameter("act");
		String sql = "";
		
		String zone_id           =  null == request.getParameter("zone_id")?"":request.getParameter("zone_id");
		String courier_id		 =  null == request.getParameter("courier_id")?"":request.getParameter("courier_id");
		String zone_name         =  null == request.getParameter("zone_name")?"":request.getParameter("zone_name"); 
		String zone_name_en      =  null == request.getParameter("zone_name_en")?"":request.getParameter("zone_name_en");
		String basic_weight      =  null == request.getParameter("basic_weight")?"":request.getParameter("basic_weight");
		String basic_freight_USD =  null == request.getParameter("basic_freight_USD")?"":request.getParameter("basic_freight_USD");
		String basic_freight_RMB =  null == request.getParameter("basic_freight_RMB")?"":request.getParameter("basic_freight_RMB");
		String step_weight		 =  null == request.getParameter("step_weight")?"":request.getParameter("step_weight");
		String step_freight_USD	 =  null == request.getParameter("step_freight_USD")?"":request.getParameter("step_freight_USD");
		String step_freight_RMB  =  null == request.getParameter("step_freight_RMB")?"":request.getParameter("step_freight_RMB");
		String min_length        =  null == request.getParameter("min_length")?"":request.getParameter("min_length");
		String bulb_weight_op    =  null == request.getParameter("bulb_weight_op")?"":request.getParameter("bulb_weight_op");
		String bulb_weight_param =  null == request.getParameter("bulb_weight_param")?"":request.getParameter("bulb_weight_param");
		String country           =  null == request.getParameter("country")?"":request.getParameter("country");
		String countryname       =  null == request.getParameter("country_name")?"":request.getParameter("country_name");

		if(act.equals("add")){
			sql = "insert into dcourier_freight ( courier_id,zone_name,zone_name_en,basic_weight,basic_freight_USD,";
			sql+=" basic_freight_RMB,step_weight,step_freight_USD,step_freight_RMB,min_length,bulb_weight_op,bulb_weight_param,country,country_name) values ";
			sql+="(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dao.addParam(new Object[]{courier_id,zone_name,zone_name_en,basic_weight,basic_freight_USD,basic_freight_RMB,step_weight,
					step_freight_USD,step_freight_RMB,min_length,bulb_weight_op,bulb_weight_param,country,countryname});
		}
		if(act.equals("mod")){
			sql="update dcourier_freight set courier_id=?,zone_name=?,zone_name_en=?,basic_weight=?,basic_freight_USD=?,";
			sql+="basic_freight_RMB=?,step_weight=?,step_freight_USD=?,step_freight_RMB=?,min_length=?,bulb_weight_op=?,";
			sql+="bulb_weight_param=?,country=?,country_name=? where zone_id=?";
			dao.addParam(new Object[]{courier_id,zone_name,zone_name_en,basic_weight,basic_freight_USD,basic_freight_RMB,
					step_weight,step_freight_USD,step_freight_RMB,min_length,bulb_weight_op,bulb_weight_param,country,countryname,zone_id});			
		}
		if(act.equals("del")){
			sql="update dcourier_freight set status=0 where zone_id=?";
			dao.addParam(new Object[]{zone_id});
		}
		
		retValue = dao.execute(sql);
		
		return retValue;
	}
	
	public List<Map<String,Object>>  countrylist(HttpServletRequest request) throws Exception{
		List<Map<String,Object>> list = null;
		String sql="select * From dcountrymsg order by short_name";
		Dao dao = Config.getDao();
		list = dao.query(sql);
		return list;
	}
	
	
	public String zoneListToJson(List<Map<String,Object>> zonelist) throws Exception{
		String zonename = "";
		String courierid="";
		String zoneid="";
		String shortName="";
		String countrynames="";
		String countries="";
		String cnName = "";
		String courierenname="";
		StringTokenizer stc = null;
		StringTokenizer stcn = null;
		Map<String,Object> tmpMap = null;
		String json="[";
		int countryCount = 0;
		for(int i = 0 ;i<zonelist.size() ; i++){
			tmpMap = zonelist.get(i);
			zonename = (String)tmpMap.get("zone_name");
			courierid = (String)tmpMap.get("courier_id");
			zoneid = (String)tmpMap.get("zone_id");
			courierid = (String)tmpMap.get("courier_id");
			countries = (String)tmpMap.get("country");
			countrynames = (String)tmpMap.get("country_name");
			courierenname =(String)tmpMap.get("courier_en_name");
			stc = new StringTokenizer(countries,",");
			stcn = new StringTokenizer(countrynames,",");
			countryCount=0;
			json+="{\"countries\": [";
			while(stc.hasMoreTokens()){
				shortName = stc.nextToken();
				cnName =  stcn.nextToken();
				json+="{";
				json+= "\"counrier_id\":"+"\""+courierid+"\",";
				json+= "\"zone_id\":"+"\""+zoneid+"\",";
				json+= "\"zone_name\":"+"\""+zonename+"\",";
				json+="\"short_name\":"+"\""+shortName+"\",";
				json+="\"cn_name\":"+"\""+cnName+"\",";
				json+="\"isselected\":"+"0,";
				json+="\"seltypeid\":"+"\"\",";//选择的类型(a免运费 ,b标准运费,c自定义运费,d不送货)
				json+="\"seltypesection\":"+"0,";//分段
				json+="\"en_name\":"+"\"\"";
				json+="},";
				countryCount++;
			}
			/*截取掉最后一个,*/
			json = json.substring(0, json.length()-1);
			json+="],";
			json+="\"counrier_id\":\""+courierid+"\",";
			json+="\"cou_en_name\":\""+courierenname+"\",";
			json+="\"countryCount\":"+countryCount+",";
			json+="\"zone_id\":"+"\""+zoneid+"\",";
			json+="\"zone_name\":"+"\""+zonename+"\"";
			json+="},";
		}
		/*截取掉最后一个,*/
		json = json.substring(0, json.length()-1);
		json+="]";
		return json;
	}
	
	
	public int saveCourierTpl(HttpServletRequest request) throws Exception
	{
		int retValue = 1;
		
		String[] courier_id = request.getParameterValues("courier_id");
		
		String free_country="";
		String free_country_name="";
		String noship_country="";
		String noship_country_name="";
		String self_country="";
		String self_country_basicfee="";
		String self_country_basicweight="";
		String self_country_name="";
		String self_country_stepfee="";
		String self_country_stepweight="";
		String stanard_country="";
		String stanard_country_name="";
		String sql="";
		Dao dao = Config.getDao();
		String tplId=dao.find("select nextval('shiptpl_id')");
		String tplName = null == request.getParameter("tpl_name")?"":request.getParameter("tpl_name");
		String userId="";
		
		for(int i = 0 ;i<courier_id.length ;i++){
			free_country = null == request.getParameter("free_country_"+courier_id[i])?"":request.getParameter("free_country_"+courier_id[i]);
			free_country_name = null == request.getParameter("free_country_name_"+courier_id[i])?"":request.getParameter("free_country_name_"+courier_id[i]);	
			noship_country = null == request.getParameter("noship_country_"+courier_id[i])?"":request.getParameter("noship_country_"+courier_id[i]);
			noship_country_name = null == request.getParameter("noship_country_name_"+courier_id[i])?"":request.getParameter("noship_country_name_"+courier_id[i]);
			stanard_country = null == request.getParameter("stanard_country_"+courier_id[i])?"":request.getParameter("stanard_country_"+courier_id[i]);
			stanard_country_name = null == request.getParameter("stanard_country_name_"+courier_id[i])?"":request.getParameter("stanard_country_name_"+courier_id[i]);
		
			self_country = null == request.getParameter("self_country_"+courier_id[i])?"":request.getParameter("self_country_"+courier_id[i]);
			self_country_name = null == request.getParameter("self_country_name_"+courier_id[i])?"":request.getParameter("self_country_name_"+courier_id[i]);

			self_country_basicfee = null == request.getParameter("self_country_basicfee_"+courier_id[i])? "0.00":request.getParameter("self_country_basicfee_"+courier_id[i]);
			if("".equals(self_country_basicfee)){
				self_country_basicfee="0.00";
			}
			self_country_basicweight = null == request.getParameter("self_country_basicweight_"+courier_id[i])? "0.00":request.getParameter("self_country_basicweight_"+courier_id[i]);
			if("".equals(self_country_basicweight)){
				self_country_basicweight="0.00";
			}
			self_country_stepfee = null == request.getParameter("self_country_stepfee_"+courier_id[i])? "0.00":request.getParameter("self_country_stepfee_"+courier_id[i]);
			if("".equals(self_country_stepfee)){
				self_country_stepfee="0.00";
			}
			self_country_stepweight = null == request.getParameter("self_country_stepweight_"+courier_id[i])? "0.00":request.getParameter("self_country_stepweight_"+courier_id[i]);
			if("".equals(self_country_stepweight)){
				self_country_stepweight="0.00";
			}
			if(!free_country.equals("") || !noship_country.equals("") || !stanard_country.equals("") || !self_country.equals("")){
				
				sql = "insert into dcourier_tpl_new (tpl_id,tpl_name,user_id,courier_id,free_ship_country,no_ship_country,stanard_ship_country,ud_ship_country,stanard_ship_rate,";
				sql+="ship_ud_start_piece,ship_ud_start_freight,ship_ud_step_piece,ship_ud_step_freight,free_ship_country_n,no_ship_country_n,stanard_ship_country_n,ud_ship_country_n,status) values (";
				sql+="'"+tplId+"' ,'"+tplName+"' ,'"+userId+"' ,'"+courier_id[i]+"' ,'"+free_country+"' ,'"+noship_country+"' ,'"+stanard_country+"','"+self_country+"','100',";
				sql+="'"+self_country_basicweight+"','"+self_country_basicfee+"','"+self_country_stepweight+"','"+self_country_stepfee+"',";
				sql+="'"+free_country_name+"','"+noship_country_name+"','"+stanard_country_name+"','"+self_country_name+"','1' )";
				dao.addBatch(sql);					
			}
			
			

		}
		
		int retVal[] = dao.execBatch();
		for(int i = 0 ;i<retVal.length;i++){
			if (retVal[i]!=1){
				retValue = 0;
				break;
			}
		}
		return retValue;
	}
	
	
	public List<Map<String,Object>> ComputFreight(HttpServletRequest request){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		Map<String,String> productInfo = new HashMap<String,String>();

		String dest_short_name = null == request.getParameter("ship_country")?"":request.getParameter("ship_country");
		String in_productid = null == request.getParameter("product_id")?"":request.getParameter("product_id");
		String buycounts = null == request.getParameter("buy_count")?"0":request.getParameter("buy_count");
		//String carts = null ==  request.getParameter("carts")?"":request.getParameter("carts");

		
		StringTokenizer st_proid = new StringTokenizer(in_productid,",");
		StringTokenizer st_buycount = new StringTokenizer(buycounts,",");
		//StringTokenizer st_carts = new StringTokenizer(carts,",");
		String productIds="";
		String tmpProductid="";
		while(st_proid.hasMoreTokens()){
			tmpProductid = st_proid.nextToken();
			productIds = productIds +"'"+tmpProductid+"',"; 
			productInfo.put(tmpProductid, st_buycount.nextToken());
			//productInfo.put(tmpProductid+"_cart", st_carts.nextToken());
		}
		productIds = productIds.substring(0, productIds.length()-1);
		
				
		String shipCountrys="";
		double shipfee=0.00;
		Dao dao = Config.getDao();
		
		double p_width=0.00;
		double p_length=0.00;
		double p_height=0.00;
		double p_weight=0.00;
		double sum_p_weight = 0.00;
		
		/*标准运费的临界值*/
		double stanard_start_weight=0.00;
		double stanatd_start_fee =0.00;
		double stanard_step_weight=0.00;
		double stanard_step_fee =0.00;
		double stanard_sum_fee = 0.00;
		int stanard_step_count = 0;
		double stanard_leave_value=0.00;

		
		/*泡重临界值*/
		double stanard_pao_minlenth=0.00;
		double stanard_pao_param=0.00;
		String stanard_pao_op="";
		double pao_weight=0.00;
		double pao_vl = 0.00;//
		
		
		
		/*自定义*/
		double tpl_min_piece=0.00;
		double tpl_min_fee=0.00;
		double tpl_step_piece=0.00;
		double tpl_step_fee=0.00;
		double tpl_sum_fee = 0.00;
		int tpl_step_count = 0;
		double tpl_step_level = 0.00;
		
		int buycount  = 0;
		
		String tmpProid="";

		String sql="";
		sql=sql+"SELECT ifnull(a.package_width,0)  package_width,             ";
		sql=sql+"       ifnull(a.package_length,0) package_length,            ";
		sql=sql+"       ifnull(a.package_height,0) package_height,            ";
		sql=sql+"       ifnull(a.package_weight,0) package_weight,            ";
		sql=sql+"       b.delivery_key,                                       ";
		sql=sql+"       a.product_id,                                         ";
		sql+="          ifnull(c.free_ship_country,'') free_ship_country,     "; 
		sql+="          ifnull(c.no_ship_country,'') no_ship_country,         ";
		sql+="          ifnull(c.stanard_ship_country,'') stanard_ship_country,   ";
		sql+="          ifnull(c.ud_ship_country,'')  ud_ship_country,        ";
		sql+="          ifnull(c.stanard_ship_rate,'0') stanard_ship_rate,    ";
		sql+="          ifnull(c.ship_ud_start_piece,'0.00')  ship_ud_start_piece,   ";
		sql+="          ifnull(c.ship_ud_start_freight,'0.00') ship_ud_start_freight, ";
		sql+="          ifnull(c.ship_ud_step_piece,'0.00') ship_ud_step_piece,       ";
		sql+="          ifnull(c.ship_ud_step_freight,'0.00') ship_ud_step_freight,    ";
		sql+="          c.courier_id                                                   ";
		sql=sql+"  FROM dproductmsg a, dproductothermsg b, dcourier_tpl_new c ";
		sql=sql+" WHERE     a.product_id = b.product_id                       ";
		sql=sql+"       AND b.delivery_key = c.tpl_id                         ";
		sql=sql+"       AND a.product_id in ("+productIds+")                  ";
		
		List<Map<String,Object>> shipList = null;
		Map<String,Object> retmap = null;
		shipList = dao.query(sql);
		
		List<Map<String,Object>> stdlist = null;
		Map<String,Object> stdMap = null;
		for(int i = 0 ;i<shipList.size() ;i++){
			map = shipList.get(i);
			retmap = new HashMap<String,Object>();
			tmpProid = (String)map.get("product_id");
			buycount = Integer.parseInt(productInfo.get(tmpProid));
			retmap.put("product_id",tmpProid);
			retmap.put("ship_country", dest_short_name);
			retmap.put("cart_id", (String)productInfo.get(tmpProid+"_cart"));

			retmap.put("courier_id", (String)map.get("courier_id"));
			shipCountrys = (String)map.get("free_ship_country");
			if(shipCountrys.contains(dest_short_name)){
				//该国家免运费
				retmap.put("ship_type", "free_ship");
			}
			
			shipCountrys = (String)map.get("no_ship_country");
			if(shipCountrys.contains(dest_short_name)){
				//该国家不发货
				retmap.put("ship_type", "no_ship");
			}
			//标准运费
			shipCountrys = (String)map.get("stanard_ship_country");
			if(shipCountrys.contains(dest_short_name)){
				retmap.put("ship_type", "std_ship");
			}
			sql="select * from dcourier_freight where courier_id='1' and country like '%"+dest_short_name+"%'";
			stdlist = dao.query(sql);
			
			p_width=Double.parseDouble((String)map.get("package_width"));
			p_length=Double.parseDouble((String)map.get("package_length"));
			p_height=Double.parseDouble((String)map.get("package_height"));
			p_weight=Double.parseDouble((String)map.get("package_weight"));
			shipfee=0.00;
			if(stdlist.size()>0){
				stdMap = stdlist.get(0);
				stanard_start_weight = Double.parseDouble((String)stdMap.get("basic_weight"));
				stanatd_start_fee = Double.parseDouble((String)stdMap.get("basic_freight_USD"));
				stanard_step_weight = Double.parseDouble((String)stdMap.get("step_weight"));
				stanard_step_fee = Double.parseDouble((String)stdMap.get("step_freight_USD"));
				stanard_pao_minlenth = Double.parseDouble((String)stdMap.get("min_length"));
				stanard_pao_op = (String)stdMap.get("bulb_weight_op");
				stanard_pao_param = Double.parseDouble((String)stdMap.get("bulb_weight_param"));
			}
			
			/*计算标准运费*/
			//1、根据起泡长度判断，是否进行泡重			
			if( stanard_pao_minlenth<p_length || stanard_pao_minlenth<p_height || stanard_pao_minlenth<p_width ){
				//进行泡重计算
				pao_vl = (p_length * p_height)*p_weight;
				if(stanard_pao_op.equals("乘")){
					pao_weight = pao_vl * stanard_pao_param;
				}
				if(stanard_pao_op.equals("除")){
					if(stanard_pao_param==0){stanard_pao_param=1;}
					pao_weight = pao_vl / stanard_pao_param;
				}
				if(p_weight<pao_weight){
					p_weight = pao_weight;
				}
			}
			/*总重量*/
			sum_p_weight = p_weight * buycount;
			if(sum_p_weight>stanard_start_weight){
				//取余数
				if(stanard_step_weight==0){stanard_step_weight=1;}
				stanard_step_count  = (int) ((sum_p_weight-stanard_start_weight) / stanard_step_weight);
				stanard_leave_value = ((sum_p_weight-stanard_start_weight) % stanard_step_weight);
				if(stanard_leave_value>0){
					stanard_step_count = stanard_step_count + 1;
				}
				shipfee = stanatd_start_fee + stanard_step_count * stanard_step_fee;
			}else{
				shipfee = stanatd_start_fee;
			}
			
			//按照标准运费设置计算的标准运费
			stanard_sum_fee = shipfee;
			
			shipCountrys = (String)map.get("ud_ship_country");
			if(shipCountrys.contains(dest_short_name)){
				//该国家采用自定义运费
				//根据每件以内多少钱，多一件加多少钱，计算自定义运费
				retmap.put("ship_type", "ud_ship");				
				if(tpl_min_piece>buycount){
					tpl_sum_fee = tpl_min_fee;
				}else{
					if(tpl_step_piece==0){tpl_step_piece=1;}
					tpl_step_count  = (int)(buycount / tpl_step_piece);
					tpl_step_level =  (buycount-tpl_min_piece) % tpl_step_piece ;
					if(tpl_step_level>0){
						tpl_step_count = tpl_step_count + 1;
					}
					tpl_sum_fee = tpl_min_fee + tpl_step_count*tpl_step_fee;
				}
				/*标准运费与自定义运费进行比较，取运费最小的*/
				if(tpl_sum_fee>stanard_sum_fee){
					shipfee =  stanard_sum_fee;
				}else{
					shipfee = tpl_sum_fee;
				}
		 }
		retmap.put("shipfee", shipfee);
			
		list.add(retmap);
	}
		
	return list;
}
	
	public String freightJson(List<Map<String,Object>> list){
	
		String json="";
		Map<String,Object> map = null;
		String product_id="";
		String ship_type="";
		String ship_fee="";
		json="[{";
		json+="\"shiplist\":[";
		for(int i =0 ;i<list.size();i++){
			map = list.get(i);
			product_id = (String)map.get("product_id");
			ship_type = (String)map.get("ship_type");
			ship_fee = String.valueOf(map.get("shipfee"));
			json+="{";
			json+="\"product_id\":\""+product_id+"\",";
			json+="\"ship_type\":\""+ship_type+"\",";
			json+="\"ship_country\":\""+(String)map.get("ship_country")+"\",";
			json+="\"courier_id\":\""+(String)map.get("courier_id")+"\",";
			json+="\"cart_id\":\""+(String)map.get("cart_id")+"\",";
			json+="\"ship_fee\":\""+ship_fee+"\"";
			json+="},";
		}		
		json = json.substring(0,json.length()-1);
		json+="]";
		json+="}]";
		return json;
	}
}





















