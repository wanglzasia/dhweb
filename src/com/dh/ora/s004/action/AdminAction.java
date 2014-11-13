package com.dh.ora.s004.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.util.HtmlUtils;

import com.dh.ora.s004.service.AdminSrv;
import com.dh.ora.s004.service.DefaultPageSrv;
import com.dh.ora.s004.service.ProductKindSrv;
import com.dh.ora.tool.FreeMarkerToHtml;
import com.x.orange.Action;
import com.x.orange.Config;
import com.x.orange.dao.Dao;
import com.x.orange.util.ConfigUtil;

public class AdminAction extends Action {

	private static final long serialVersionUID = 88L;
	 //tologinpage
	public String index(){
		//return "login_init";
		return "login_init";
	}
	
	public String login() throws Exception{
		String username = null == getRequest().getParameter("username")?"":getRequest().getParameter("username");
		String password = null == getRequest().getParameter("password")?"":getRequest().getParameter("password");
		String validcode = null == getRequest().getParameter("validcode")?"":getRequest().getParameter("validcode");		
		String msg = "";
		if(username==""){
			msg ="用户名为空";
			getRequest().setAttribute("msg1", msg);
			return "login";
		}
		if(password==""){
			msg ="密码为空";
			getRequest().setAttribute("msg1", msg);
			return "login";
		}
		if(validcode==""){
			msg ="验证码为空";
			getRequest().setAttribute("msg1", msg);
			return "login";
		}
		String vcode = (String)getRequest().getSession().getAttribute("RANDOMVALIDATECODEKEY");
		vcode = vcode.toUpperCase();
		validcode = validcode.toUpperCase();
		if(!validcode.equals(vcode)){
			msg ="验证码错误";
			getRequest().setAttribute("msg1", msg);
			return "login";
		}
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		int ret = 0;
		ret = adminSrv.opuser("check","",username,password);
		if(ret==1){
			getRequest().getSession().setAttribute("adminusername", username);
			return "index";
		}else{
			msg="用户名或者密码错误";
			getRequest().setAttribute("msg1", msg);
			return "login";
		}
	}
	
	public String userop() throws Exception{
		String act =  null == getRequest().getParameter("act")?"":getRequest().getParameter("act");
		String uid =  null == getRequest().getParameter("uid")?"":getRequest().getParameter("uid");
		String uname =  null == getRequest().getParameter("uname")?"":getRequest().getParameter("uname");
		String pwd =  null == getRequest().getParameter("pwd")?"":getRequest().getParameter("pwd");
		String reg_date =  null == getRequest().getParameter("reg_date")?"":getRequest().getParameter("reg_date");
		String update_date =  null == getRequest().getParameter("update_date")?"":getRequest().getParameter("update_date");
		int ret =0 ;
		String msg="";
		List<Map<String,Object>> usrlist = null;
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		
		if(act.equals("")){act="list";}
		
		if(act.equals("list")){
			usrlist = adminSrv.userlist("");
			assgin("usrlist",usrlist);
			return "ok";
		}
		if(act.equals("add") || act.equals("mod") || act.equals("del")){
			ret = adminSrv.opuser(act, uid, uname, pwd);
			if(ret==-100){
				msg ="用户名已经存在";
				assgin("msg",msg);
				return "gl_error";				
			}
			if(ret!=1){
				msg ="操作失败";
				assgin("msg",msg);
				return "gl_error";
			}else{
				msg="操作成功";
				assgin("msg",msg);
				return "gl_msg";
			}
		}
		if(act.equals("toadd")){
			assgin("uid","");
			assgin("pwd","");
			assgin("uname","");
			assgin("reg_date","");
			assgin("update_date","");
			act="add";
		}
		if(act.equals("tomod")){
			
			assgin("uid",uid);
			assgin("pwd","");
			assgin("uname",uname);
			assgin("reg_date",reg_date);
			assgin("update_date",update_date);
			act="mod";
		}
		if(act.equals("todel")){
			assgin("uid",uid);
			assgin("pwd","");
			assgin("uname",uname);
			assgin("reg_date",reg_date);
			assgin("update_date",update_date);
			act="del";
		}
		assgin("act",act);
		return "ok";
	}
	
	public String shopCfg() throws Exception{
		String act = null == getRequest().getParameter("act")?"":getRequest().getParameter("act");
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		List<Map<String,Object>> list = null;
		Map<String,Object> map = null;
		int ret = 0;
		String msg="";
		if(act.equals("mod")){
			ret = adminSrv.update(getRequest());
			if(ret!=1){
				msg ="操作失败";
				assgin("msg",msg);
				return "gl_error";
			}
		}
		list = adminSrv.shopCfg();
		for(int i = 0;i<list.size();i++){
			map = list.get(i);
			assgin("shop_name",map.get("shop_name"));
			assgin("shop_home_url",map.get("shop_home_url"));
			assgin("shop_footer",map.get("shop_footer"));
			assgin("shop_footer_cn",map.get("shop_footer_cn"));
			if(act.equals("mod")){
				String webpath = ServletActionContext.getServletContext().getRealPath("/");
				adminSrv.footerToFile((String)map.get("shop_footer"),(String)map.get("shop_footer_cn"),webpath);
			}
		}
		return "shopcfg";
	}
	
	
	public String mailcfg() throws Exception{
		String act = null == getRequest().getParameter("act")?"":getRequest().getParameter("act");
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		List<Map<String,Object>> list = null;
		Map<String,Object> map = null;
		int ret = 0;
		String msg="";
		if(act.equals("mod")){
			ret = adminSrv.updateMail(getRequest());
			if(ret!=1){
				msg ="操作失败";
				assgin("msg",msg);
				return "gl_error";
			}
		}
		list = adminSrv.mailCfg();
		for(int i = 0;i<list.size();i++){
			map = list.get(i);
			assgin("mail_title",map.get("mail_title"));
			assgin("mail_body",map.get("mail_body"));
		}
		return "mailcfg";
	}
	
	
	public String meta_list() throws Exception{
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		List<Map<String,Object>> list = null;
		list = adminSrv.metaContentList("");
		assgin("list",list);
		return "ok";
	}
	public String meta_msg() throws Exception{
		String act = null == getRequest().getParameter("act")?"":getRequest().getParameter("act");
		String msg_id = null == getRequest().getParameter("msg_id")?"":getRequest().getParameter("msg_id");
		String attr_id = null == getRequest().getParameter("attrid")?"":getRequest().getParameter("attrid");
		String meta_content = null == getRequest().getParameter("meta_content")?"":getRequest().getParameter("meta_content");
		int ret = 0;
		String msg="";
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		List<Map<String,Object>> attrList = null;
		attrList = adminSrv.metaAtrList();
		if(act.equals("add") || act.equals("mod") || act.equals("del")){
			//增删改
			ret  = adminSrv.metaOp(act, msg_id, attr_id, meta_content);
			if(ret==1){
				msg="操作成功";
				assgin("msg",msg);
				return "gl_error";
			}else{
				msg="操作失败";
				assgin("msg",msg);
				return "gl_msg";
			}
		}
		if(act.equals("toadd")){
			act = "add";
			assgin("msg_id","");
			assgin("attrid","");
			assgin("meta_content","");
			assgin("attr_list",attrList);
		}
		if(act.equals("tomod")){
			act = "mod";
			assgin("msg_id",msg_id);
			assgin("attrid",attr_id);
			assgin("meta_content",meta_content);
			assgin("attr_list",attrList);
		}
		if(act.equals("todel")){
			act = "del";
			assgin("msg_id",msg_id);
			assgin("attrid",attr_id);
			assgin("meta_content",meta_content);
			assgin("attr_list",attrList);
		}
		assgin("act",act);
		return "ok";
	}
	
	public String view() throws Exception{
		String pid = null==(getRequest().getParameter("p"))? "":getRequest().getParameter("p");
		if(pid.equals("")){getResponse().sendError(404, "没有此产品");}
		return "ok";
	}
	
	public String protolist() throws Exception{
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		List<Map<String,Object>> list = null;
		list = adminSrv.protolList("0");
		assgin("list",list);
		return "list";
	}
	
	public String upProtolmsg() throws Exception{
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		int ret = 0;
		ret = adminSrv.updateProtol(getRequest());
		String msg="";
		if(ret==0){
			msg="操作失败";
			assgin("msg",msg);
			return "gl_error";
		}else{
			msg="操作成功";
			assgin("msg",msg);
			return "gl_msg";
		}
	}
	
	public String kindlist() throws Exception{
		ProductKindSrv productKindSrv = (ProductKindSrv)getBean("proKindSrv");
		List<Map<String,Object>> list = null;
		list = productKindSrv.kindList();
		assgin("kindlist",list);
		return "kindlist";
	}
	
	public String toupdatekm() throws Exception{
		String kindcode = (String)getRequest().getParameter("kind_code");
		String kindname = (String)getRequest().getParameter("kind_name");
		String tpl_id = null== (String)getRequest().getParameter("ftl")?"":(String)getRequest().getParameter("ftl");
		List<Map<String,Object>> tpllist = null;
		DefaultPageSrv	defaultPageSrv = (DefaultPageSrv)getBean("defaultPageSrv");
		tpllist = defaultPageSrv.ftlList(getRequest());
		assgin("tpllist",tpllist);
		assgin("kindname",kindname);
		assgin("kindcode",kindcode);
		assgin("tpl_id",tpl_id);
		return "tplmsg";
	}
	
	public String updatekm() throws Exception{
		String kindcode = (String)getRequest().getParameter("kind_code");
		String tpl_id = null== (String)getRequest().getParameter("ftl")?"":(String)getRequest().getParameter("ftl");
		ProductKindSrv productKindSrv = (ProductKindSrv)getBean("proKindSrv");
		int ret = 0 ;
		ret = productKindSrv.updateKindModel(kindcode, tpl_id);
		String msg="";
		if(ret!=1){
			msg="操作失败";
			assgin("msg",msg);
			return "gl_error";
		}else{
			msg="操作成功";
			assgin("msg",msg);
			return "gl_msg";
		}
	}
	
	public String preview() throws Exception{
		String kindCode = (String)getRequest().getParameter("kind_code");
		List<Map<String,Object>> ftlattrlist = null;
		DefaultPageSrv	defaultPageSrv = (DefaultPageSrv)getBean("defaultPageSrv");
		Dao dao = Config.getDao();
		String ftlId = dao.find("select ftl_id from s_cfg_kind_tpl where kind_code='"+kindCode+"'");
		
		if(null == ftlId){
			assgin("msg","为关联模板");
			return "gl_error";
			
		}
		//String modelftl = dao.find("select ftl_name,file_name from s_cfg_ftl where ftl_id='"+ftlId+"'");
		
		String tmpLabel="";
		String tmpValue="";
		ftlattrlist = defaultPageSrv.ftlAtrrList(ftlId,"");
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		/*一级产品类型菜单*/
		List<Map<String,Object>> topKindList = null;
		/*二级产品类型菜单*/
		List<Map<String,Object>> subKindList = null;
		//页脚信息
		List<Map<String,Object>> footerlist = null;
		//二级菜单列表
		List<Map<String,Object>> subMenuList = null;
		
		footerlist = adminSrv.shopCfg();
		
		subMenuList = adminSrv.subMenusList("");
		
		topKindList = defaultPageSrv.productKindList("1000", "0");
		subKindList = defaultPageSrv.productKindList("1000", "1");
		
		String kind_name_en =dao.find("select kind_name_en from s_cfg_productkind where kind_code='"+kindCode+"'");
		
		Map<String,Object> map = null;
		for(int i = 0;i<ftlattrlist.size() ;i++){
			map = ftlattrlist.get(i);
			tmpLabel = (String)map.get("attr_label");
			tmpValue = (String)map.get("attr_value");
			assgin(tmpLabel,tmpValue);
		}
		
		
		assgin("footermsg", footerlist.get(0).get("shop_footer"));
		assgin("title","title");
		assgin("main_kind_list",topKindList);
		assgin("sub_kind_list",subKindList);
		assgin("kind_name_en",kind_name_en);
		assgin("subMenuList",subMenuList);
		return "msg";
	}
	
	
	public String genHtml() throws Exception{
		String kindCode = (String)getRequest().getParameter("kind_code");
		List<Map<String,Object>> ftlattrlist = null;
		DefaultPageSrv	defaultPageSrv = (DefaultPageSrv)getBean("defaultPageSrv");
		Dao dao = Config.getDao();
		String ftlId = dao.find("select ftl_id from s_cfg_kind_tpl where kind_code='"+kindCode+"'");
		
		if(null == ftlId){
			assgin("msg","为关联模板");
			return "gl_error";
			
		}		
		String tmpLabel="";
		String tmpValue="";
		ftlattrlist = defaultPageSrv.ftlAtrrList(ftlId,"");
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		/*一级产品类型菜单*/
		List<Map<String,Object>> topKindList = null;
		/*二级产品类型菜单*/
		List<Map<String,Object>> subKindList = null;
		//页脚信息
		List<Map<String,Object>> footerlist = null;
		//二级菜单列表
		List<Map<String,Object>> subMenuList = null;
		
		footerlist = adminSrv.shopCfg();
		
		subMenuList = adminSrv.subMenusList("");

		topKindList = defaultPageSrv.productKindList("1000", "0");
		subKindList = defaultPageSrv.productKindList("1000", "1");
		
		String kind_name_en =dao.find("select kind_name_en from s_cfg_productkind where kind_code='"+kindCode+"'");
		Map<String,Object> root = new HashMap<String,Object>();

		Map<String,Object> map = null;
		for(int i = 0;i<ftlattrlist.size() ;i++){
			map = ftlattrlist.get(i);
			tmpLabel = (String)map.get("attr_label");
			tmpValue = (String)map.get("attr_value");
			root.put(tmpLabel,tmpValue);
		}
		root.put("footermsg", footerlist.get(0).get("shop_footer"));
		root.put("title",kind_name_en);
		root.put("main_kind_list",topKindList);
		root.put("sub_kind_list",subKindList);
		root.put("kind_name_en",kind_name_en);
		root.put("subMenuList",subMenuList);
		
		FreeMarkerToHtml toHtml = new FreeMarkerToHtml();
		String htmlFilePath="";
		String templateFileName="model_"+kindCode+".ftl";
		String tplPath = ConfigUtil.getConfig("WEB_PATH");
		tplPath = tplPath +"\\ftl\\model";
		toHtml.setTemplate_path(tplPath);
		htmlFilePath = ConfigUtil.getConfig("WEB_PATH");
		boolean ret = false;
		ret = toHtml.geneHtmlFile(templateFileName, root, htmlFilePath, kindCode+".htm");
		String msg="";
		if(ret){
			msg="操作成功";
			assgin("msg",msg);
			return "gl_msg";
		}else
		{
			msg="操作失败";
			assgin("msg",msg);
			return "gl_error";
		}
		
	}
	
	
	/**
	 * 
	 * @Method:	AdminAction::genPagefream
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2014年10月7日上午10:03:41
	 * @throws 
	 * @author wanglz
	 * @Description: 生成页面头部
	 */
	public String genPageHead() throws Exception{
		//模板
		
		return "";
	}
	
	/*
	 * 获取menu
	 */
	public String sb_menu() throws Exception{
		List<Map<String,Object>> list = null;
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		String menus_id =null==(String)getRequest().getParameter("menu_id")?"":(String)getRequest().getParameter("menu_id");
		list = adminSrv.subMenusList(menus_id);
		assgin("list",list);
		return "list";
	}
	/*
	 * 
	 */
	public String sb_menu_msg() throws Exception{
		List<Map<String,Object>> list = null;
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		String menus_id =null==(String)getRequest().getParameter("menu_id")?"":(String)getRequest().getParameter("menu_id");
		Map<String,Object> map = null;
		list = adminSrv.subMenusList(menus_id);
		String act =(String)getRequest().getParameter("act");
		act = act.substring(2,act.length());		
		String menu_name="";
		String menu_name_en="";
		String menu_id="";
		String m_index ="";
		String http_addr="";
		
		if(list.size()>0){
			map = list.get(0);
			menu_name = (String)map.get("menu_name");
			menu_name_en = (String)map.get("menu_name_en");
			menu_id = (String)map.get("menu_id");
			m_index = (String)map.get("m_index");
			http_addr = (String)map.get("http_addr");
			
		}
		 
		assgin("act",act);
		assgin("menu_name",HtmlUtils.htmlEscape(menu_name));//HtmlUtils.htmlEscape(menu_name)
		assgin("menu_name_en",HtmlUtils.htmlEscape(menu_name_en));
		assgin("menu_id",menu_id);
		assgin("m_index",m_index);
		assgin("http_addr",HtmlUtils.htmlEscape(http_addr));
		
		
		return "msg";		
	}
	
	/*
	 * 操作menu
	 */
	public String op_menu() throws Exception{
		int ret = 0;
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		ret = adminSrv.opSubMenu(getRequest());
		String msg="";
		if(ret!=1){
			msg="操作失败";
			assgin("msg",msg);
			return "gl_error";
			
		}else{
			msg="操作成功";
			assgin("msg",msg);
			return "gl_msg";
		}
		
	}
	/*
	 * 搜索界面设置
	 */
	public String spage() throws Exception{
		
		return "";
	}
		
}
