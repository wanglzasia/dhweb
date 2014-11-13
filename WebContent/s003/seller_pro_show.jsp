<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/OptionListTag.tld" prefix="OptionList" %>
<%@ taglib uri="/WEB-INF/tld/ProductAttrTag.tld" prefix="ProAttr" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<script language="javascript">
<!--
$(document).ready(function(){
	CKEDITOR.replace('pro_detail_descript');
});
//-->
</script>

<form action="/seller/mod.do" method="post">

	<div class="add_pro_title">基本信息</div>
		<div class="pro_item"><label>商品分类:</label><p>${kindtree}</p>
			<input type="hidden" name="pro_kind" value="${kindcode}"/></div>
		<div class="pro_item"><label>产品名称:</label>
			<div class="multi_opr_area">
				<input type="text" name="pro_name" id="pro_name_id" value="${product.proName}"/>
			</div>
		</div>
		<div class="pro_item"><label>品牌信息:</label>
			<div class="multi_opr_area">
				<input type="text" name="pro_brand" value="${product.proBrand}"/>
			</div>
		</div>
		<div class="pro_item"><label>商品编码:</label>
			<div class="multi_opr_area">
				<input type="text" name="pro_no" value="${product.proNo}"/>&nbsp;&nbsp;如果为空,系统自动给出商品编码
				<input type="hidden" name="pro_id" value="${product.proId}"/>
			</div>
		</div>
		
		<div class="pro_item" ><label>计量单位:</label>
			<div class="multi_opr_area">
			 	<OptionList:OptionList name="pro_unit" id="testa" cssclass="jj" style="width2:100px;" code="code" key="concat(value,'/',value_en) as value" table="s_cfg_unit" keyAlis="value"/>
			</div>
		</div>
		
		<div class="pro_item" style="min-height:40px;"><label>产品属性:</label>
			<p></p>
			<p>
			<div class="pro_item_p">
				<ProAttr:ProAttr type="1" usr="1" kindCode="${kindcode}" productId="${product.proId}" ></ProAttr:ProAttr>
			</div>
			</p>
		</div>		
		
 
	<div class="add_pro_title">价格信息</div>
	
	<div class="pro_item"><label>销售方式:</label>
		<div class="multi_opr_area">
			 <input type="radio" value="0"  <s:if test="#request.product.selType==0">checked</s:if> name="sel_type" id="sel_type0" />按件卖(单位：件)
		</div>
		<div class="multi_opr_area">
			 <input  type="radio" value="1" <s:if test="#request.product.selType==1">checked</s:if> name="sel_type" id="sel_type1"/>按包卖
		</div>
	</div>
	
	<div class="pro_item"><label>备货状态:</label>
			<div class="multi_opr_area">
			 	<input  type="radio" value="0" <s:if test="#request.product.proStat==0">checked</s:if> name="pro_stat" id="pro_stat0" checked/>有备货
			</div>
			<div class="multi_opr_area">
			 	<input  type="radio" value="1" <s:if test="#request.product.proStat==0">checked</s:if> name="pro_stat" id="pro_stat1"/>待备货
		 	</div>
	</div>
	<div class="pro_item"><label>备货数量:</label>
		<div class="multi_opr_area">
			<input type="text" style="width:50px;" name="pro_prepare_count" value="${product.proPrepareCount}"/>
		</div>
	</div>
	<div class="pro_item"><label>备货周期:</label>
		<div class="multi_opr_area">
		  	<input type="text" style="width:50px;" name="pro_prepare_day"  value="${product.proPrepareDay}"/>&nbsp;&nbsp;天
		 </div>
	</div>
	
	<div class="pro_item">
	
		<label style="padding-top:10px;padding-left:0px;">产品价格:</label>
		
			<div class="price_box">
			
				<div style="padding-bottom:10px;padding-top:10px;">
					可以设置多个价格区间
				</div>
				
			 	<div id="price_cell_box">
					
					<div class="multi_opr_area" style="padding-bottom:5px;">
					</div>
					
					<s:iterator value="#request.product.priceListMap" status="st">
						<DIV id="price_cell_add<s:property value="#st.index"/>" class="multi_opr_area" style="padding-bottom:5px;">
							购买数量:<input type="text" style="width:100px;" value="<s:property value="min_count"/>" name="pro_buy_count<s:property value="#st.index"/>"/>
							购买价格:<input type="text"  style="width:80px;" value="<s:property value="min_count"/>" name="pro_buy_price<s:property value="#st.index"/>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $(美元)
							<input type="text"  style="width:80px;" value="<s:property value="#st.index"/>" name="pro_buy_price0"/>
							<a href="javascript:removeSegment(<s:property value="#st.index"/>)">删除区间</a>
						</DIV>
					</s:iterator>

				</div>
				
				<input type="hidden" name="probprice_c" value="<s:property value="#request.product.priceListMap.size()"/>" id="probprice_id"/>
				
				<div class="multi_opr_area" style="padding-bottom:5px;">
					<a href="javascript:add_pricedetail();">[+]&nbsp;添加区间</a>
				</div>
				
		 	</div>
	</div>
	
	
	<div class="add_pro_title">详细描述</div>
	<div class="pro_item"><label>产品图片:</label>
		<div style="padding-top:5px;padding-bottom:10px;">
			请选择产品图片,用于展示产品信息。建议jpg,jpeg,png格式图片,大小最好不超过40KB。
		</div>
	</div>
	
	<div class="pro_item"><label></label>
		<div class="multi_opr_area">
		
		
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('1',400,200)" id="single_pic1"><img src="${product.singlePicUrl1}" width="80px" height="80px"/></div>
				<div class="pic_ablum"><a href="javascript:load_ablum('1')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('2',400,200)" id="single_pic2"><img src="${product.singlePicUrl2}" width="80px" height="80px"/></div>
				<div class="pic_ablum"><a href="javascript:load_ablum('2')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('3',400,200)" id="single_pic3"><img src="${product.singlePicUrl3}" width="80px" height="80px"/></div>
				<div class="pic_ablum"><a href="javascript:load_ablum('3')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('4',400,200)" id="single_pic4"><img src="${product.singlePicUrl4}" width="80px" height="80px"/></div>
				<div class="pic_ablum"><a href="javascript:load_ablum('4')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('5',400,200)" id="single_pic5"><img src="${product.singlePicUrl5}" width="80px" height="80px"/></div>
				<div class="pic_ablum"><a href="javascript:load_ablum('5')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('6',400,200)" id="single_pic6"><img src="${product.singlePicUrl6}" width="80px" height="80px"/></div>
				<div class="pic_ablum"><a href="javascript:load_ablum('6')">相册中选择</a></div>
			</div>
			
			<input type="hidden" name="single_pic_url_1" id="single_pic_url_1" value="${product.singlePicUrl1}"/>
			<input type="hidden" name="single_pic_url_2" id="single_pic_url_2" value="${product.singlePicUrl2}"/>
			<input type="hidden" name="single_pic_url_3" id="single_pic_url_3" value="${product.singlePicUrl3}"/>
			<input type="hidden" name="single_pic_url_4" id="single_pic_url_4" value="${product.singlePicUrl4}"/>
			<input type="hidden" name="single_pic_url_5" id="single_pic_url_5" value="${product.singlePicUrl5}"/>
			<input type="hidden" name="single_pic_url_6" id="single_pic_url_6" value="${product.singlePicUrl6}"/>
			
			<div class="clear" ></div>
		</div>
	</div>

	<div class="pro_item"><label>产品简述:</label>
	    <div style="padding-top:5px;padding-bottom:10px;">
			输入产品的简要描述,如：颜色、尺寸、交易方式等等。
		</div>
		<div class="multi_opr_area">
				<textarea cols='50' rows='6' name="pro_simple_descript">${product.proSimpleDescript}</textarea>
		</div>
	</div>
	
	<div class="pro_item"><label>详细描述:</label>
	    <div style="padding-top:5px;padding-bottom:10px;">
			产品功能属性、产品细节、产品图片、物流支付、售后服务等。
		</div>
		<div class="multi_opr_area">
			<textarea cols='50' rows='6' name='pro_detail_descript' id='pro_detail_descript'>${product.proDetailDescript}</textarea>
		</div>
	</div>	 
	 
	 
	<div class="add_pro_title">包装信息</div>
	<div class="pro_item"><label>包装尺寸:</label>
		<div class="multi_opr_area">
								       长<input type="text" style="width:30px" name="pro_package_length" value="${product.proPackageLength}"/> 厘米
			&nbsp;&nbsp;&nbsp;&nbsp;宽<input type="text" style="width:30px" name="pro_package_width" value="${product.proPackageWidth}"/> 厘米
			&nbsp;&nbsp;&nbsp;&nbsp;高<input type="text" style="width:30px" name="pro_package_height" value="${product.proPackageHeight}"/> 厘米 
		</div>
	</div>
		<div class="pro_item"><label>包装重量:</label>
		<div class="multi_opr_area">
			<input type="text" style="width:60px" name="pro_package_weight" value="${product.proPackageWeight}"/>&nbsp;&nbsp;&nbsp;&nbsp;公斤(KG)/件
		</div>
	</div>
	<div class="add_pro_title">运费信息</div>
	<div class="pro_item">
		<div class="multi_opr_area">
		运费模板&nbsp;&nbsp;&nbsp;
		<select name="pro_delivery_key">
			<option value="1">默认运费</option>
			<option value="2">新的运费</option>
			<option value="3">3</option>
			<option value="4">4</option>
		</select>
			<br>
			<table border="1">
				<tr><td>物流公司</td><td>运达国家</td><td>运费</td></tr>
	 			<tr><td>韵达快递</td><td>韩国，日本，美国，加拿大，俄罗斯，保加利亚，厄瓜多尔</td><td>100元/公斤</td></tr>
	 			<tr><td>申通快递</td><td>韩国，日本，美国，加拿大，俄罗斯，保加利亚，厄瓜多尔</td><td>80元/公斤</td></tr>
			</table>
		</div>
	</div>
	<br><br><br>
	<input type="checkbox" checked />同意接受<a href="#">《产品发布规则》</a><br><br>
	<div style="text-align:center;"><input type="submit" value="同意《产品发布规则》,提交产品" /></div>
	
</form>