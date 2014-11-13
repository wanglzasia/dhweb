package com.dh.ora.s003;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

public static void main(String[] args){
		
		//String regex = "\\$\\{(.+?)\\}";
		//String str = "<a onclick=\"showUserName('${session.user.username}','${session.user.password}');\" >linkme</a>" ;
		                       
		//String regex ="\\wd\\we\\ws\\wP\\wa\\wg\\we\\wN\\wo\\w=";
	    String regex ="(\\??desPageNo=\\s*\\w*\\w&)";
		String str="querypro.html?desPageNo=1&a=s";
		Pattern pattern = Pattern.compile(regex);
		System.out.println(pattern);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			System.out.println(">>>>> replace sequence : " + matcher.group(0));
			System.out.println(">>>>> index range : (" + matcher.start() + ", " + matcher.end() + ")");
			System.out.println(">>>>> sub : " + str.substring(matcher.start(), matcher.end()));
			matcher.appendReplacement(sb, "");
			System.out.println("-----------------");
		}
		matcher.appendTail(sb);
		System.out.println(">>>> sb : " + sb.toString());

	}

//输出：
//
//\$\{(.+?)\}
//>>>>> replace sequence : ${session.user.username}
//>>>>> index range : (26, 50)
//>>>>> sub : ${session.user.username}
//-----------------
//>>>>> replace sequence : ${session.user.password}
//>>>>> index range : (53, 77)
//>>>>> sub : ${session.user.password}
//-----------------
//>>>> sb : <a onclick="showUserName('123','123');" >linkme</a>


}
