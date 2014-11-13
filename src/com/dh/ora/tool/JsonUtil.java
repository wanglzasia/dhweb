package com.dh.ora.tool;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

public class JsonUtil
{

	public JsonUtil()
	{
	}

	public static String listToJson(List<Map<String,Object>> list)
		throws Exception
	{
		String json = "{";
		Map<String,Object> map = null;
		String tmpValue = "";
		json = (new StringBuilder(String.valueOf(json))).append(" \"kindlist\": [").toString();
		int columnSize = 0;
		int j = 0;
		for (int i = 0; i < list.size(); i++)
		{
			json = (new StringBuilder(String.valueOf(json))).append("{").toString();
			map = (Map<String,Object>)list.get(i);
			columnSize = map.size();
			j = 0;
			for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();)
			{
				String key = (String)iterator.next();
				tmpValue = (String)map.get(key);
				json = (new StringBuilder(String.valueOf(json))).append("\"").append(key).append("\":").append("\"").append(tmpValue).append("\"").toString();
				if (j < columnSize - 1)
					json = (new StringBuilder(String.valueOf(json))).append(",").toString();
				j++;
			}

			json = (new StringBuilder(String.valueOf(json))).append(" }").toString();
			if (i < list.size() - 1)
				json = (new StringBuilder(String.valueOf(json))).append(",").toString();
		}

		json = (new StringBuilder(String.valueOf(json))).append("]}").toString();
		return json;
	}

	public static void outprint(HttpServletResponse response, String value)
		throws Exception
	{
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(value);
		out.flush();
		out.close();
	}
}
