package com.dh.ora.s004.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dh.ora.s004.service.CourierSrv;
import com.x.orange.Action;

public class CourierAction extends Action
{

	private static final long serialVersionUID = 1L;

	public CourierAction()
	{
	}

	public String view()
		throws Exception
	{
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		String act = getRequest().getParameter("act");
		List<Map<String,Object>>  list = null;
		Map<String,Object> tmpMap = null;
		String courierId = request.getParameter("courier_id") != null ? request.getParameter("courier_id") : "";
		String courierEnName = request.getParameter("courier_en_name") != null ? request.getParameter("courier_en_name") : "";
		String courierCnName = request.getParameter("courier_cn_name") != null ? request.getParameter("courier_cn_name") : "";
		String freight = request.getParameter("freight") != null ? request.getParameter("freight") : "0";
		String delieryDay = request.getParameter("deliery_day") != null ? request.getParameter("deliery_day") : "0";
		String note = request.getParameter("note") != null ? request.getParameter("note") : "";
		if (act.equals("toadd"))
		{
			assgin("courier_id", "");
			assgin("courier_en_name", "");
			assgin("courier_cn_name", "");
			assgin("freight", "");
			assgin("deliery_day", "");
			assgin("note", "");
		}
		if (act.equals("tomod") || act.equals("todel"))
		{
			list = courierSrv.courierList(getRequest());
			for (int i = 0; i < list.size(); i++)
			{
				tmpMap = (Map<String,Object>)list.get(i);
				courierId = (String)tmpMap.get("courier_id");
				courierEnName = (String)tmpMap.get("courier_en_name");
				courierCnName = (String)tmpMap.get("courier_cn_name");
				freight = (String)tmpMap.get("freight");
				delieryDay = (String)tmpMap.get("deliery_day");
				note = (String)tmpMap.get("note");
			}

			assgin("courier_id", courierId);
			assgin("courier_en_name", courierEnName);
			assgin("courier_cn_name", courierCnName);
			assgin("freight", freight);
			assgin("deliery_day", delieryDay);
			assgin("note", note);
		}
		act = act.substring(2, 5);
		assgin("act", act);
		assgin("nav_msg", "运费模板");
		return "msg";
	}

	public String opmsg()
		throws Exception
	{
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		String act = getRequest().getParameter("act");
		int retValue = 0;
		String msg = "";
		if (act.equals("add") || act.equals("mod") || act.equals("del"))
			retValue = courierSrv.opCourier(getRequest());
		if (retValue == 1)
		{
			msg = "操作成功";
			assgin("msg", msg);
			return "gl_msg";
		} else
		{
			msg = "操作失败";
			assgin("msg", msg);
			return "gl_error";
		}
	}

	public String list()
		throws Exception
	{
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		List<Map<String,Object>>  list = null;
		list = courierSrv.courierList(getRequest());
		assgin("list", list);
		return "list";
	}

	public String zonelist()
		throws Exception
	{
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		List<Map<String,Object>>  list = null;
		List<Map<String,Object>>  courier = null;
		Map<String,Object> tmpMap = null;
		courier = courierSrv.courierList(getRequest());
		String courierId = "";
		String courierEnName = "";
		String courierCnName = "";
		String freight = "";
		String delieryDay = "";
		String note = "";
		for (int i = 0; i < courier.size(); i++)
		{
			tmpMap = (Map<String,Object>)courier.get(i);
			courierId = (String)tmpMap.get("courier_id");
			courierEnName = (String)tmpMap.get("courier_en_name");
			courierCnName = (String)tmpMap.get("courier_cn_name");
			freight = (String)tmpMap.get("freight");
			delieryDay = (String)tmpMap.get("deliery_day");
			note = (String)tmpMap.get("note");
		}

		assgin("courier_id", courierId);
		assgin("courier_en_name", courierEnName);
		assgin("courier_cn_name", courierCnName);
		assgin("freight", freight);
		assgin("deliery_day", delieryDay);
		assgin("note", note);
		list = courierSrv.courierzoneList(getRequest());
		assgin("list", list);
		return "zonelist";
	}

	public String viewzone()
		throws Exception
	{
		String act = getRequest().getParameter("act");
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		List<Map<String,Object>>  list = null;
		List<Map<String,Object>>  courier = null;
		Map<String,Object>  tmpMap = null;
		courier = courierSrv.courierList(getRequest());
		String courierId = "";
		String courierEnName = "";
		String courierCnName = "";
		String freight = "";
		String delieryDay = "";
		String note = "";
		for (int i = 0; i < courier.size(); i++)
		{
			tmpMap = (Map<String,Object>)courier.get(i);
			courierId = (String)tmpMap.get("courier_id");
			courierEnName = (String)tmpMap.get("courier_en_name");
			courierCnName = (String)tmpMap.get("courier_cn_name");
			freight = (String)tmpMap.get("freight");
			delieryDay = (String)tmpMap.get("deliery_day");
			note = (String)tmpMap.get("note");
		}

		if (act.equals("toadd"))
		{

			list = new ArrayList<Map<String,Object>>() ;
			list.add(tmpMap);
		}
		if (act.equals("tomod") || act.equals("todel"))
			list = courierSrv.courierzoneList(getRequest());
		act = act.substring(2, 5);
		assgin("act", act);
		assgin("courier_id", courierId);
		assgin("courier_en_name", courierEnName);
		assgin("courier_cn_name", courierCnName);
		assgin("freight", freight);
		assgin("deliery_day", delieryDay);
		assgin("note", note);
		assgin("list", list);
		return "zone";
	}

	public String opzone()
		throws Exception
	{
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		int retValue = 0;
		retValue = courierSrv.opCourierZone(getRequest());
		String msg = "";
		if (retValue == 1)
		{
			msg = "操作成功";
			assgin("msg", msg);
			return "gl_msg";
		} else
		{
			msg = "操作失败";
			assgin("msg", msg);
			return "gl_error";
		}
	}

	public String countrylist()
		throws Exception
	{
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		List<Map<String,Object>> list = null;
		list = courierSrv.countrylist(getRequest());
		String code = request.getParameter("code") != null ? request.getParameter("code") : "";
		String value = request.getParameter("value") != null ? request.getParameter("value") : "";
		assgin("list", list);
		assgin("code", code);
		assgin("value", value);
		return "countrylist";
	}

	public String shiptpl()
		throws Exception
	{
		String uri = "ship_tpl";
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		List<Map<String,Object>> courier = null;
		courier = courierSrv.courierList(getRequest());
		assgin("courierlist", courier);
		assgin("uri", uri);
		assgin("nav_msg", "运费模板");
		return "ok";
	}

	public String zoneJson()
		throws Exception
	{
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		List<Map<String,Object>> list = null;
		list = courierSrv.courierzoneList(getRequest());
		String json = courierSrv.zoneListToJson(list);
		getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.flush();
		out.print(json);
		out.close();
		System.out.println(json);
		return null;
	}

	public String saveshipmodel()
		throws Exception
	{
		int retValue = 0;
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		retValue = courierSrv.saveCourierTpl(getRequest());
		String msg = "";
		if (retValue == 1)
		{
			msg = "操作成功";
			assgin("msg", msg);
			return "gl_msg";
		} else
		{
			msg = "操作失败";
			assgin("msg", msg);
			return "gl_error";
		}
	}

	public String shipFeeJson()
		throws Exception
	{
		CourierSrv courierSrv = (CourierSrv)getBean("courierSrv");
		List<Map<String,Object>> list = null;
		list = courierSrv.ComputFreight(getRequest());
		String json = courierSrv.freightJson(list);
		getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.flush();
		out.print(json);
		out.close();
		System.out.println(json);
		return null;
	}
}
