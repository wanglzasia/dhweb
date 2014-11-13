package com.dh.ora.s004.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.dh.ora.s004.service.AdminSrv;
import com.dh.ora.s004.service.DefaultPageSrv;
import com.dh.ora.tool.FreeMarkerToHtml;
import com.x.orange.Action;
import com.x.orange.util.ConfigUtil;

public class DefaultPageAction extends Action{
	/** 
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 1L;
	private String title ="";
	private String resourcepath="";
	private String dataxml="";
	private File upload;
	private String uploadContentType;


	public String index() throws Exception{
		this.setTitle("China Direct Sale");
		this.setResourcepath("../");
		this.setDataxml("testdata.xml");
		//一级产品类型
		List<Map<String,Object>> topKindList = null;
		//二级产品类型
		List<Map<String,Object>> subKindList = null;
		//页脚信息
		List<Map<String,Object>> footerlist = null;
		//广告轮播列表
		List<Map<String,Object>> advList = null;
		//二级菜单列表
		List<Map<String,Object>> subMenuList = null;
		
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		footerlist = adminSrv.shopCfg();
		DefaultPageSrv	defaultPageSrv = (DefaultPageSrv)getBean("defaultPageSrv");
		topKindList = defaultPageSrv.productKindList("1000", "0");
		subKindList = defaultPageSrv.productKindList("1000", "1");
		advList = defaultPageSrv.advList();
		List<Map<String,Object>> ftlattrlist = null;
		ftlattrlist = defaultPageSrv.ftlAtrrList("6","");
		String tmpLabel="";
		String tmpValue="";
		
		subMenuList = adminSrv.subMenusList("");
		
		Map<String,Object> map = null;

		for(int i = 0;i<ftlattrlist.size() ;i++){
			map = ftlattrlist.get(i);
			tmpLabel = (String)map.get("attr_label");
			tmpValue = (String)map.get("attr_value");
			assgin(tmpLabel,tmpValue);
		}
		assgin("footermsg", footerlist.get(0).get("shop_footer"));
		assgin("main_kind_list",topKindList);
		assgin("sub_kind_list",subKindList);
		assgin("advlist",advList);
		assgin("subMenuList",subMenuList);
	
		return "index";
	}
	
	public String indexToHtml() throws Exception{
		this.setTitle("China Direct Sale");
		this.setResourcepath("/");
		this.setDataxml("data.xml");
		/*一级产品类型菜单*/
		List<Map<String,Object>> topKindList = null;
		/*二级产品类型菜单*/
		List<Map<String,Object>> subKindList = null;
		/*脚本信息*/
		List<Map<String,Object>> footerlist = null;
		//轮播广告列表 
		List<Map<String,Object>> advList = null;
		//二级菜单列表
		List<Map<String,Object>> subMenuList = null;
		
		AdminSrv adminSrv = (AdminSrv)getBean("adminSrv");
		DefaultPageSrv	defaultPageSrv = (DefaultPageSrv)getBean("defaultPageSrv");
		topKindList = defaultPageSrv.productKindList("1000", "0");
		subKindList = defaultPageSrv.productKindList("1000", "1");
		footerlist = adminSrv.shopCfg();
		advList = defaultPageSrv.advList();
		subMenuList = adminSrv.subMenusList("");
		
		Map<String,Object> root = new HashMap<String,Object>();
		/*生成轮播广告*/
		String xmlDataPath = ServletActionContext.getServletContext().getRealPath("/public/flashdata/default/");
		defaultPageSrv.setRealPath(xmlDataPath);
		defaultPageSrv.genXmlData(1);
		List<Map<String,Object>> ftlattrlist = null;
		ftlattrlist = defaultPageSrv.ftlAtrrList("6","");
		String tmpLabel="";
		String tmpValue="";
		Map<String,Object> map = null;
		for(int i = 0;i<ftlattrlist.size() ;i++){
			map = ftlattrlist.get(i);
			tmpLabel = (String)map.get("attr_label");
			tmpValue = (String)map.get("attr_value");
			root.put(tmpLabel,tmpValue);
		}
		
		root.put("footermsg", footerlist.get(0).get("shop_footer"));
		root.put("main_kind_list",topKindList);
		root.put("sub_kind_list",subKindList);
		root.put("title", this.getTitle());
		root.put("resourcepath", this.getResourcepath());
		root.put("dataxml", this.getDataxml());
		root.put("advlist",advList);
		root.put("subMenuList",subMenuList);
		
		
		
		FreeMarkerToHtml toHtml = new FreeMarkerToHtml();
		
		String htmlFilePath="";
		String templateFileName="index.ftl";
		String tplPath = ConfigUtil.getConfig("WEB_PATH");
		tplPath = tplPath +"\\ftl";
		toHtml.setTemplate_path(tplPath);
		htmlFilePath = ConfigUtil.getConfig("WEB_PATH");
		boolean ret = false;
		ret = toHtml.geneHtmlFile(templateFileName, root, htmlFilePath, "index.htm");
		/*根据head.ftl生成head.html供其他页面调用*/
		Map<String,Object> root1 = new HashMap<String,Object>();
		
		templateFileName = "\\common\\head.ftl";
		htmlFilePath = htmlFilePath+"\\ftl\\common";
		root1.put("main_kind_list", topKindList);
		root1.put("sub_kind_list", subKindList);
		root1.put("subMenuList",subMenuList);
		ret = toHtml.geneHtmlFile(templateFileName, root1, htmlFilePath, "head.htm");

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
	
	
	
	public String toAdvList() throws Exception{
		List<Map<String, Object>> list = null;
		DefaultPageSrv	defaultPageSrv = (DefaultPageSrv)getBean("defaultPageSrv");
		list = defaultPageSrv.list();
		assgin("list",list);
		return "adv_list";
	}
	
	public String managerftl(){
		
		return "null";
	}
	
	public String opftlatrmsg() throws Exception{
		String ftlId = null==(getRequest().getParameter("ftl_id"))? "":getRequest().getParameter("ftl_id");
		String act = getRequest().getParameter("act");
		String msg="";
		String atrName= null==(getRequest().getParameter("attr_name"))? "":getRequest().getParameter("attr_name");
		String atrLabel= null==(getRequest().getParameter("attr_label"))? "":getRequest().getParameter("attr_label");
		String atrValue= null==(getRequest().getParameter("attr_value"))? "":getRequest().getParameter("attr_value");
		String atrNode= null==(getRequest().getParameter("note"))? "":getRequest().getParameter("note");
		String atrId= null==(getRequest().getParameter("attr_id"))? "":getRequest().getParameter("attr_id");
		DefaultPageSrv	defaultPageSrv = (DefaultPageSrv)getBean("defaultPageSrv");
		List<Map<String,Object>> list = null;
		Map<String,Object> map = null;
		int ret = 0;
		if("".equals(ftlId)){
			msg = "请选择模板";
			assgin("msg",msg);
			return "gl_error";
		}
		if(act.equals("add") || act.equals("mod") || act.equals("del")){
			//Add Mod Del
			ret = defaultPageSrv.opftlAttr(getRequest());
			if(ret==1){
				msg="操作成功";
				assgin("msg",msg);
				return "gl_msg";
			}else{
				msg="操作失败";
				assgin("msg",msg);
				return "gl_error";
			}
		}
		
		if(act.equals("toadd")){
			act="add";
		}
		
		if(act.equals("tomod") || act.equals("todel")){
			act=act.substring(2,5);
			list = defaultPageSrv.ftlAtrrList(ftlId,atrId);
			for(int i = 0 ;i<list.size(); i++){
				map = list.get(i);
				atrName = (String)map.get("attr_name");
				atrLabel= (String)map.get("attr_label");
				atrValue= (String)map.get("attr_value");
				atrNode = (String)map.get("note");
				atrId   = (String)map.get("attr_id");
			}
		}
		
		assgin("act",act);
		assgin("ftlid",ftlId);
		assgin("attr_name",atrName);
		assgin("attr_label",atrLabel);
		assgin("attr_value",atrValue);
		assgin("attr_note",atrNode);
		assgin("attr_id",atrId);
		
		return "atr_msg";
	}
	
	public String ftlatrlist() throws Exception{
		List<Map<String,Object>> ftllist = null;
		List<Map<String,Object>> ftlattrlist = null;
		DefaultPageSrv	defaultPageSrv = (DefaultPageSrv)getBean("defaultPageSrv");
		ftllist = defaultPageSrv.ftlList(getRequest());
		String ftlId = null==(getRequest().getParameter("ftl_id"))? "":getRequest().getParameter("ftl_id");
		assgin("ftl_id",ftlId);
		assgin("ftl_list",ftllist);
		if(!"".equals(ftlId)){
			ftlattrlist = defaultPageSrv.ftlAtrrList(ftlId,"");
		}
		assgin("ftl_attr_list", ftlattrlist);
		return "atr_list";
	}
	
	public String toAdvMsg() throws Exception{
		
		String act = getRequest().getParameter("act");
		String id = "";
		String fileName = "";
		String imgUrl = "";
		String imgText = "";
		String imgSort = "";
		id = getRequest().getParameter("id");
		fileName = getRequest().getParameter("img_src") != null ? getRequest().getParameter("img_src") : "";
		imgUrl = getRequest().getParameter("img_url") != null ? getRequest().getParameter("img_url") : "";
		imgText = getRequest().getParameter("img_text") != null ? getRequest().getParameter("img_text") : "";
		imgSort = getRequest().getParameter("img_sort") != null ? getRequest().getParameter("img_sort") : "0";
		String expandedName = "";
		String uploadPath = "";
		String xmlDataPath = "";
		int ret = 0;
		uploadPath = ServletActionContext.getServletContext().getRealPath("/public/flashdata/img/");
		xmlDataPath = ServletActionContext.getServletContext().getRealPath("/public/flashdata/default/");
		DefaultPageSrv defaultPageSrv = (DefaultPageSrv)getBean("defaultPageSrv");
		if (act.equals("add") || act.equals("mod"))
		{
			if (upload != null)
			{
				if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg") || uploadContentType.equals("image/jpg"))
					expandedName = ".jpg";
				else
				if (uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png"))
					expandedName = ".png";
				else
				if (uploadContentType.equals("image/gif"))
					expandedName = ".gif";
				else
				if (uploadContentType.equals("image/bmp"))
				{
					expandedName = ".bmp";
				} else
				{
					assgin("msg", "文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）");
					return "gl_error";
				}
				InputStream is = new FileInputStream(upload);
				fileName = UUID.randomUUID().toString();
				fileName = (new StringBuilder(String.valueOf(fileName))).append(expandedName).toString();
				File toFile = new File(uploadPath, fileName);
				OutputStream os = new FileOutputStream(toFile);
				byte buffer[] = new byte[1024];
				for (int length = 0; (length = is.read(buffer)) > 0;)
					os.write(buffer, 0, length);
	
				is.close();
				os.close();
			}
			if (act.equals("add"))
			{
				defaultPageSrv.setRealPath(xmlDataPath);
				ret = defaultPageSrv.add(getRequest(), (new StringBuilder("/public/flashdata/img/")).append(fileName).toString());
				if (ret != 1)
				{
					assgin("msg", "操作失败!");
					return "error";
				}
			}
			if (act.equals("mod"))
			{
				defaultPageSrv.setRealPath(xmlDataPath);
				ret = defaultPageSrv.modify(getRequest());
				if (ret != 1)
				{
					assgin("msg", "操作失败!");
					return "error";
				}
			}
		}
		if (act.equals("del"))
		{
			ret = defaultPageSrv.remove(getRequest());
			if (ret != 1)
			{
				assgin("msg", "操作失败!");
				return "error";
			}
		}
		if (act.equals("toadd"))
			act = "add";
		if (act.equals("todel"))
			act = "del";
		if (act.equals("tomod"))
			act = "mod";
		assgin("img_src", fileName);
		assgin("img_url", imgUrl);
		assgin("img_text", imgText);
		assgin("img_sort", imgSort);
		assgin("act", act);
		assgin("id", id);
		if (ret == 1)
		{
			assgin("msg", "操作成功!");
			return "gl_msg";
		} else
		{
			return "adv_msg";
		}
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getResourcepath()
	{
		return resourcepath;
	}

	public void setResourcepath(String resourcepath)
	{
		this.resourcepath = resourcepath;
	}

	public String getDataxml()
	{
		return dataxml;
	}

	public void setDataxml(String dataxml)
	{
		this.dataxml = dataxml;
	}

	public File getUpload()
	{
		return upload;
	}

	public void setUpload(File upload)
	{
		this.upload = upload;
	}

	public String getUploadContentType()
	{
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}
}
		