package com.dh.ora.s003;

import org.springframework.web.util.HtmlUtils;

public class Test {
    public static void main(String[] args) { 
        String specialStr = "<div id=\"testDiv\">test1;test2</div>"; 
        String str1 = HtmlUtils.htmlEscape(specialStr); //①转换为HTML转义字符表示 
        System.out.println(str1); 
       
        String str2 = HtmlUtils.htmlEscapeDecimal(specialStr); //②转换为数据转义表示 
        System.out.println(str2); 
       
        String str3 = HtmlUtils.htmlEscapeHex(specialStr); //③转换为十六进制数据转义表示 
        System.out.println(str3); 
       
        //④下面对转义后字符串进行反向操作 
        System.out.println(HtmlUtils.htmlUnescape(str1)); 
        System.out.println(HtmlUtils.htmlUnescape(str2)); 
        System.out.println(HtmlUtils.htmlUnescape(str3)); 
    } 

}