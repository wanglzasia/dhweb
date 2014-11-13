package com.dh.ora.s001.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Lession1 {

    String host = "";

    String user = "";

    String password = "";

    public void setHost(String host) {

        this.host = host;

    }

    public void setAccount(String user, String password) {

        this.user = user;

        this.password = password;

    }

    public void send(String from, String to, String subject, String content) {

        Properties props = new Properties();
        props.put("mail.smtp.host", host); // 指定SMTP服务器
        props.put("mail.smtp.auth", "true"); // 指定是否需要SMTP验证
        props.put("mail.debug", "true");

        try {
            Session mailSession = Session.getDefaultInstance(props);
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(from)); // 发件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 收件人
            message.setSubject(subject); // 邮件主题
            //指定邮箱内容及ContentType和编码方式
            message.setContent(content, "text/html;charset=utf-8");
            //指定邮件发送日期
            message.setSentDate(new Date());
            message.saveChanges();
            
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();


        } catch(Exception e) {

            System.out.println(e);

        }

    }

    public static void main(String[] args) {
        Lession1 sm = new Lession1();
        sm.setHost("smtp.163.com"); // 指定要使用的邮件服务器
        sm.setAccount("allshi01@163.com", "abc123");//指定帐号和密码
		/*
		* @param String 发件人的地址
		* @param String 收件人地址
		* @param String 邮件标
		* @param String 邮件正文
		* */        
        sm.send("allshi01@163.com", "wanglz@si-tech.com.cn", "标题", "<br>Hellor,World!<hr>");

    }
}