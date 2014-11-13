package com.dh.ora.s003.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dh.ora.s001.bean.User;

public interface ProductSrv {

	/**
	 * 
	 * @Method:	ProductSrv::Save
	 * @param @param request
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月20日上午9:43:35
	 * @throws Exception
	 * @author wanglz
	 * @Description: 保存
	 */
	public int Save(HttpServletRequest request) throws Exception;
	
	/**
	 * 
	 * @Method:	ProductSrv::Modify
	 * @param @param request
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月20日上午9:43:50
	 * @throws Exception
	 * @author wanglz
	 * @Description: 修改
	 */
	public int Modify(HttpServletRequest request) throws Exception;
	/**
	 * 
	 * @Method:	ProductSrv::Remove
	 * @param @param proNo
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月20日上午9:44:04
	 * @throws Exception
	 * @author wanglz
	 * @Description: 删除产品
	 */
	public int Remove(String proId) throws Exception;
	/**
	 * 
	 * @Method:	ProductSrv::Show
	 * @param @param proNo
	 * @param @return
	 * @param @throws Exception
	 * @return Object
	 * @date: 2014年6月20日上午9:44:29
	 * @throws Exception
	 * @author wanglz
	 * @Description: 查询单个产品
	 */
	public Object Show(String proId) throws Exception;
	/**
	 * 
	 * @Method:	ProductSrv::auditPass
	 * @param @param proNo
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月20日上午9:44:54
	 * @author wanglz
	 * @Description:  审核通过
	 */
	public int auditPass(String proId) throws Exception;
	/**
	 * 
	 * @Method:	ProductSrv::auditRefuse
	 * @param @param proNo
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月20日上午9:45:18
	 * @author wanglz
	 * @Description: 审核拒绝
	 */
	public int auditRefuse(String proId) throws Exception;
	/**
	 * 
	 * @Method:	ProductSrv::putOnShelves
	 * @param @param proNo
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月20日上午9:45:32
	 * @author wanglz
	 * @Description: 产品上架,生成HTML文件
	 */
	public int putOnShelves(String proId) throws Exception;
	/**
	 * 
	 * @Method:	ProductSrv::pullOffShelves
	 * @param @param proNo
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @date: 2014年6月20日上午9:45:45
	 * @author wanglz
	 * @Description:产品下架
	 */
	public int pullOffShelves(String proId) throws Exception;

	//public void generateHtml() throws Exception;
	public List<Map<String,Object>> queryList(HttpServletRequest request) throws Exception;
	
	public List<Map<String,Object>> queryProduct(String proId) throws Exception;
	
	public void setUser(User usr);
	
	public double price(String pid,int count) throws Exception;
	
	public String kindTree(String kindcode) throws Exception;
}
