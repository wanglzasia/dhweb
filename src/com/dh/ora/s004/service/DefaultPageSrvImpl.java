package com.dh.ora.s004.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.HtmlUtils;

import com.x.orange.Service;
import com.x.orange.dao.Dao;

public class DefaultPageSrvImpl extends Service implements DefaultPageSrv {
	
	private String realPath="";
	
	public void setRealPath(String realPath){
		this.realPath = realPath;
	}
	public int add(HttpServletRequest request,String picAddr) throws Exception{
		int  ret = 0;
		String sql="insert into d_adv_msg (pic_addr,pic_link,pic_note,pic_order) VALUES (?,?,?,?)";
		String picLink = "",picNote ="",picOrder ="";
		picLink = (String)request.getParameter("img_url");
		picLink = HtmlUtils.htmlEscape(picLink);		
		picNote = (String)request.getParameter("img_text");
		picOrder = (String)request.getParameter("img_sort");
		Dao dao = getDao();
		Object[] params = new Object[]{picAddr,picLink,picNote,picOrder};
		dao.addParam(params);
		ret = dao.execute(sql);
		genXmlData(0);
		return ret;
	}
	
	public int modify(HttpServletRequest request) throws Exception{
		int  ret = 0;
		String sql="update d_adv_msg set pic_addr=?,pic_link=?,pic_note=?,pic_order=? where adv_id=?";
		String picAdr ="",picLink = "",picNote ="",picOrder ="",advId="";
		picAdr = (String)request.getParameter("img_src");
		picLink = (String)request.getParameter("img_url");
		picNote = (String)request.getParameter("img_text");
		picOrder = (String)request.getParameter("img_sort");
		advId = (String)request.getParameter("id");
		Dao dao = getDao();
		picLink = HtmlUtils.htmlEscape(picLink);
		Object[] params = new Object[]{picAdr,picLink,picNote,picOrder,advId};
		dao.addParam(params);
		ret = dao.execute(sql);
		genXmlData(0);
		return ret;
	}
	
	public int remove(HttpServletRequest request) throws Exception{
		int  ret = 0;
		String sql="delete from  d_adv_msg where adv_id=?";
		String advId="";
		advId = (String)request.getParameter("id");
		Dao dao = getDao();
		Object[] params = new Object[]{advId};
		dao.addParam(params);
		ret = dao.execute(sql);
		return ret;
	}
	public List<Map<String,Object>> list() throws Exception{
		List<Map<String,Object>> list = null;
		Dao dao = getDao();
		String sql="select adv_id,pic_addr,pic_link,pic_note,pic_order from d_adv_msg order by pic_order";
		list = dao.query(sql);
		return list;
	}
	
	public List<Map<String,Object>> productKindList(String parentcode,String level) throws Exception{
		String sql="";
		sql = sql +"SELECT a.kind_code, a.kind_name_en, b.parent_kind_code,c.kind_name_en parent_kind_name_en  ";
		sql = sql +"  FROM s_cfg_productkind a, s_cfg_productinfo b,  s_cfg_productkind c  ";
		sql = sql +" WHERE a.kind_code = b.kind_code                                       ";
		sql = sql +"       AND b.parent_kind_code IN                                       ";
		sql = sql +"              (SELECT kind_code                                        ";
		sql = sql +"                 FROM s_cfg_productinfo                                ";
		sql = sql +"                WHERE parent_kind_code = ? AND denorm_level = ?)	   ";
		sql = sql +"       AND b.denorm_level = '1'                                        ";
		sql = sql +"       AND b.parent_kind_code = c.kind_code                            ";
		sql = sql +"       AND a.status = '1'  order by a.kind_code                        ";
		Dao dao = getDao();
		dao.addParam(new Object[]{parentcode,level});
		List<Map<String,Object>> list = dao.query(sql);
		return list;
	}
	
	public List<Map<String,Object>> ftlList(HttpServletRequest request) throws Exception{
		String sql="select ftl_id,ftl_name,file_name,file_path,note from s_Cfg_ftl where status=1 order by ftl_id";
		Dao dao = getDao();
		List<Map<String,Object>> list = null;
		list = dao.query(sql);
		return list;
	}
	
	public List<Map<String,Object>> ftlAtrrList(String ftlId,String atrId) throws Exception{
		//String ftlId = (String)request.getParameter("ftl_id");
		//String atrId= null==(request.getParameter("attr_id"))? "":request.getParameter("attr_id");
		String sql="";
		sql = sql +"SELECT a.attr_id,a.ftl_id,a.attr_name,a.attr_label,a.attr_value,a.note FROM s_cfg_ftl_attr a,";
		sql = sql +" s_cfg_ftl b WHERE a.ftl_id = b.ftl_id AND a.status = 1 AND b.status = 1 and b.ftl_id = ? ";
		Dao dao = getDao();
		if(!atrId.equals("")){
			sql = sql +" and a.attr_id=?";
			dao.addParam(new Object[]{ftlId,atrId});
		}else{
			dao.addParam(new Object[]{ftlId});
		}
		List<Map<String,Object>> list = null;
		list = dao.query(sql);
		return list;
	}
	
	public int opftlAttr(HttpServletRequest request) throws Exception{
		int ret = 0;
		String act = (String)request.getParameter("act");
		String sql="";
		String atrName= null==(request.getParameter("attr_name"))? "":request.getParameter("attr_name");
		String atrLabel= null==(request.getParameter("attr_label"))? "":request.getParameter("attr_label");
		String atrValue= null==(request.getParameter("attr_value"))? "":request.getParameter("attr_value");
		String atrNode= null==(request.getParameter("note"))? "":request.getParameter("note");
		String atrId= null==(request.getParameter("attr_id"))? "":request.getParameter("attr_id");
		String ftlId = null==(request.getParameter("ftl_id"))? "":request.getParameter("ftl_id");
		Dao dao = getDao();
		atrNode = HtmlUtils.htmlEscape(atrNode);
		if(act.equals("add")){
			sql="insert into s_cfg_ftl_attr (ftl_id,attr_name,attr_label,attr_value,note,status) VALUES (?,?,?,?,?,?)";
			dao.addParam(new Object[]{ftlId,atrName,atrLabel,atrValue,atrNode,"1"});
			ret = dao.execute(sql);
		}
		
		if(act.equals("del")){
			sql="update s_cfg_ftl_attr set status=0 where attr_id=? and  ftl_id=?";
			dao.addParam(new Object[]{atrId,ftlId});
			ret = dao.execute(sql);
		}
		if(act.equals("mod")){
			sql="update s_cfg_ftl_attr set attr_name=? ,attr_label=?,attr_value=?,note=? where ftl_id=? and attr_id=?";
			dao.addParam(new Object[]{atrName,atrLabel,atrValue,atrNode,ftlId,atrId});
			ret = dao.execute(sql);
		}
		return ret;
	}
	
	
	public List<Map<String,Object>> advList() throws Exception{
		return list();
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @Method:	DefaultPageSrvImpl::genXmlData
	 * @param @param type(0 测试;1发布)
	 * @param @throws Exception
	 * @return void
	 * @date: 2014年8月21日上午9:40:37
	 * @author wanglz
	 * @Description: 生成xml文件
	 */
	public void genXmlData(int type) throws Exception{
		List<Map<String,Object>> list = null;
		list = list();
		String xmldata="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xmldata = xmldata +"\r\n";
		xmldata = xmldata +"<bcaster>\r\n";
		String picAddr="";
		String picLink="";
		String xmlfilename="";
		if(type==0){
			xmlfilename="testdata.xml";
		}else{
			xmlfilename="data.xml";
		}
		Map<String,Object> map = null;
		for(int i =0 ;i<list.size() ;i++){
			map = list.get(i);
			picAddr = (String)map.get("pic_addr");
			picLink = (String)map.get("pic_link");
			xmldata = xmldata +"<item item_url=\""+picAddr+"\" link=\""+ picLink+"\" />\r\n";
		}
		xmldata = xmldata +"</bcaster>\r\n";

		File file = new File(realPath+File.separator+xmlfilename);

		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		int b=xmldata.length();
		FileOutputStream fos=new FileOutputStream(file);
		
		for(int i = 0 ;i<b;i++){
			char c = xmldata.charAt(i);
			fos.write(c);
		}
		fos.close();
	}
}
