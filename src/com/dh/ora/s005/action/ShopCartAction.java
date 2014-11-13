package com.dh.ora.s005.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.dh.ora.s001.bean.User;
import com.dh.ora.s005.service.ShopCartSrv;
import com.x.orange.Action;

public class ShopCartAction extends Action {

	/** 
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 1L;

	public String goodlist() throws Exception{
		User usr = (User) this.getSessionUser();
		String userid="";
		ShopCartSrv shopCartSrv = (ShopCartSrv)getBean("shopCartSrv");
		List<Map<String,Object>> list = null;
		Map<String,Object> tmpMap = null;
		String json="{";
		String tmpproid="";
		String tmpproname="";
		String tmpprouri="";
		String tmpprocount="";
		String tmpprovalue="";
		userid = null == getRequest().getParameter("userid")?"":getRequest().getParameter("userid");
		if(null==usr){
			json = json +"\"status\":[{\"status\":\"0\"}] ";
		}else{
			//userid = usr.getUserId();
			list = shopCartSrv.goodList(userid);
			if(list.size()>0){
				json = json +"\"status\":[{\"status\":\"1\"}]";
			}else{
				json = json +"\"status\":[{\"status\":\"2\"}]";
			}
			if(list.size()>0){
				json = json +",";
				json = json +"\"prodcuts\":[";
			}
			for(int i=0 ;i<list.size();i++){
				tmpMap = list.get(i);
				tmpproid=(String)tmpMap.get("product_id");
				tmpproname=(String)tmpMap.get("product_name");
				tmpprouri=(String)tmpMap.get("pic_name_1");
				tmpprocount=(String)tmpMap.get("product_count");
				tmpprovalue=(String)tmpMap.get("product_value");
				json = json +"{\"pid\":\""+tmpproid+"\",\"pname\":\""+tmpproname+"\",\"pic\":\""+tmpprouri+"\",\"pct\":\""+tmpprocount+"\",\"pvalue\":\""+tmpprovalue+"\"}";
				if( (i<list.size()-1) && list.size()>1 ){
					json = json +",";
				}
			}
			
			if(list.size()>0){
				json = json +"]";
			}
		}
		json = json +"}";
		this.getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.flush();
		out.print(json);
		out.close();
		System.out.println(json);
		return null;
	}
}
