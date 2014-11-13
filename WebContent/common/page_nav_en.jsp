<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
		<div style="padding-top:5px;padding-bottom:5px; text-align:right;">		
		<a href="${pageBean.urlFirstPage}">First</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${pageBean.urlLastPage}">Previous</a> 
		&nbsp;&nbsp;&nbsp;&nbsp;
		CurPage: ${pageBean.page}/${pageBean.totalPage}
		&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href="${pageBean.urlNextPage}">Next</a>  
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${pageBean.urlTailPage}">Last</a> 
		&nbsp;&nbsp;&nbsp;&nbsp;
		Total:${pageBean.totalCount}
		</div>
