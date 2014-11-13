<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/OptionListTag.tld" prefix="OptionList" %>
<%@ taglib uri="/WEB-INF/tld/ProductAttrTag.tld" prefix="ProAttr" %>
<script language="javascript">
<!--
$(document).ready(function(){
	CKEDITOR.replace('pro_detail_descript');
});
//-->
</script>

<form action="/seller/save.do" method="post">

	<div class="add_pro_title">基本信息</div>
		<div class="pro_item"><label>商品分类:</label><p>${kindtree}</p>
			<input type="hidden" name="pro_kind" value="${kindcode}"/></div>
		<div class="pro_item"><label>产品名称:</label>
			<div class="multi_opr_area">
				<input type="text" name="pro_name" id="pro_name_id" value=""/>
			</div>
		</div>
		<div class="pro_item"><label>品牌信息:</label>
			<div class="multi_opr_area">
				<input type="text" name="pro_brand" value=""/>
			</div>
		</div>
		<div class="pro_item"><label>商品编码:</label>
			<div class="multi_opr_area">
				<input type="text" name="pro_no" value=""/>&nbsp;&nbsp;如果为空,系统自动给出商品编码
			</div>
		</div>
		
		<div class="pro_item" ><label>计量单位:</label>
			<div class="multi_opr_area">
			 <OptionList:OptionList name="pro_unit" id="testa" cssclass="jj" style="width2:100px;" code="code" key="concat(value,'/',value_en) as value" table="s_cfg_unit" keyAlis="value"/>
			</div>
		</div>
		
		<div class="pro_item" style="min-height:40px;"><label>产品属性:</label>
			<div class="multi_opr_area">
				<div class="price_box">
					<ProAttr:ProAttr type="0" usr="1" kindCode="${kindcode}" ></ProAttr:ProAttr>
				</div>
			</div>
		</div>		
		
 
	<div class="add_pro_title">价格信息</div>
	<div class="pro_item"><label>销售方式:</label>
		<div class="multi_opr_area">
			 <input type="radio" value="0" name="sel_type" id="sel_type0" checked/>按件卖(单位：件)
		</div>
		<div class="multi_opr_area">
			 <input  type="radio" value="1" name="sel_type" id="sel_type1"/>按包卖
		</div>
	</div>
	
	<div class="pro_item"><label>备货状态:</label>
			<div class="multi_opr_area">
			 	<input  type="radio" value="0" name="pro_stat" id="pro_stat0" checked/>有备货
			</div>
			<div class="multi_opr_area">
			 	<input  type="radio" value="1" name="pro_stat" id="pro_stat1"/>待备货
		 	</div>
	</div>
	<div class="pro_item"><label>备货数量:</label>
		<div class="multi_opr_area">
			<input type="text" style="width:50px;" name="pro_prepare_count" value=""/>
		</div>
	</div>
	<div class="pro_item"><label>备货周期:</label>
		<div class="multi_opr_area">
		  	<input type="text" style="width:50px;" name="pro_prepare_day"  value=""/>&nbsp;&nbsp;天
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
								<input type="text" name="probprice_c" value="0" id="probprice_id"/>
						购买数量:<input type="text" style="width:100px;" value="999999" name="pro_buy_count0"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						购买价格:<input type="text"  style="width:80px;" value="" name="pro_buy_price0"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $(美元)
					</div>
				</div>
				
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
				<div class="pic_box" ondblclick="upload('1',400,200)" id="single_pic1">双击,上传图片</div>
				<div class="pic_ablum"><a href="javascript:load_ablum('1')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('2',400,200)" id="single_pic2">双击,上传图片</div>
				<div class="pic_ablum"><a href="javascript:load_ablum('2')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('3',400,200)" id="single_pic3">双击,上传图片</div>
				<div class="pic_ablum"><a href="javascript:load_ablum('3')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('4',400,200)" id="single_pic4">双击,上传图片</div>
				<div class="pic_ablum"><a href="javascript:load_ablum('4')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('5',400,200)" id="single_pic5">双击,上传图片</div>
				<div class="pic_ablum"><a href="javascript:load_ablum('5')">相册中选择</a></div>
			</div>
			
			<div class="pic_frame">
				<div class="pic_box" ondblclick="upload('6',400,200)" id="single_pic6">双击,上传图片</div>
				<div class="pic_ablum"><a href="javascript:load_ablum('6')">相册中选择</a></div>
			</div>
			
			<input type="hidden" name="single_pic_url_1" id="single_pic_url_1"/>
			<input type="hidden" name="single_pic_url_2" id="single_pic_url_2"/>
			<input type="hidden" name="single_pic_url_3" id="single_pic_url_3"/>
			<input type="hidden" name="single_pic_url_4" id="single_pic_url_4"/>
			<input type="hidden" name="single_pic_url_5" id="single_pic_url_5"/>
			<input type="hidden" name="single_pic_url_6" id="single_pic_url_6"/>
			
			<div class="clear" ></div>
		</div>
	</div>

	<div class="pro_item"><label>产品简述:</label>
	    <div style="padding-top:5px;padding-bottom:10px;">
			输入产品的简要描述,如：颜色、尺寸、交易方式等等。
		</div>
		<div class="multi_opr_area">
				<textarea cols='50' rows='6' name="pro_simple_descript"></textarea>
		</div>
	</div>
	
	<div class="pro_item"><label>详细描述:</label>
	    <div style="padding-top:5px;padding-bottom:10px;">
			产品功能属性、产品细节、产品图片、物流支付、售后服务等。
		</div>
		<div class="multi_opr_area">
			<textarea cols='50' rows='6' name='pro_detail_descript' id='pro_detail_descript'></textarea>
		</div>
	</div>	 
	 
	 
	<div class="add_pro_title">包装信息</div>
	<div class="pro_item"><label>包装尺寸:</label>
		<div class="multi_opr_area">
			长<input type="text" style="width:30px" name="pro_package_length" value=""/> 厘米
			&nbsp;&nbsp;&nbsp;&nbsp;宽<input type="text" style="width:30px" name="pro_package_width" value=""/> 厘米
			&nbsp;&nbsp;&nbsp;&nbsp;高<input type="text" style="width:30px" name="pro_package_height" value=""/> 厘米 
		</div>
	</div>
		<div class="pro_item"><label>包装重量:</label>
		<div class="multi_opr_area">
			<input type="text" style="width:60px" name="pro_package_weight" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;公斤(KG)/件
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