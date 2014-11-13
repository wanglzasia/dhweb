package com.dh.ora.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FormateMoney
{

	public FormateMoney()
	{
	}

	public static String formatMoney(String s, int len)
	{
		if (s == null || s.length() < 1)
			return "";
		NumberFormat formater = null;
		double num = Double.parseDouble(s);
		if (len == 0)
		{
			formater = new DecimalFormat("###,###");
		} else
		{
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for (int i = 0; i < len; i++)
				buff.append("#");

			formater = new DecimalFormat(buff.toString());
		}
		String result = formater.format(num);
		if (result.indexOf(".") == -1)
			result = (new StringBuilder(String.valueOf(result))).append(".00").toString();
		return result;
	}

	public static void main(String args[])
	{
		System.out.println(formatMoney("1234342", 2));
	}
}
