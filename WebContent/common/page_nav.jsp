<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div style="padding-top:5px;padding-bottom:5px; text-align:right;">		
<a href="${pageBean.urlFirstPage}">首页</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="${pageBean.urlLastPage}">上一页</a> 
&nbsp;&nbsp;&nbsp;&nbsp;
CurPage: ${pageBean.page}/${pageBean.totalPage}
&nbsp;&nbsp;&nbsp;&nbsp; 
<a href="${pageBean.urlNextPage}">下一页</a>  
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="${pageBean.urlTailPage}">尾页</a> 
&nbsp;&nbsp;&nbsp;&nbsp;
总记录:${pageBean.totalCount}
</div>
