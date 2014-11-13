package com.dh.ora.servlet;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.x.orange.util.ConfigUtil;

public class SendMail {
	
    public static void sendMailByProxy(String to,String Subject,String content)throws Exception{
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
       //设置代理服务器
        Properties props = System.getProperties();
        props.setProperty("proxySet", "true");
        props.setProperty("socksProxyHost", ConfigUtil.getConfig("MAIL_PROXY"));
        props.setProperty("socksProxyPort", ConfigUtil.getConfig("MAIL_PROXY_PORT"));
        props.setProperty("mail.smtp.host", ConfigUtil.getConfig("MAIL_HOST"));
       
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.socketFactory.port", "25");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        
        final String username = ConfigUtil.getConfig("MAIL_USER");
        final String password = ConfigUtil.getConfig("MAIL_PWD");
       
       //使用验证
        Session session = Session.getDefaultInstance(props,
               new Authenticator() {
                   protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                       return new javax.mail.PasswordAuthentication(username,
                                password);
                    }
                });
        MimeMessage message = new MimeMessage(session);
        Address address = new InternetAddress(ConfigUtil.getConfig("MAIL_FROM"));
        Address toAaddress = new InternetAddress(to);
        message.setFrom(address);
        message.setRecipient(MimeMessage.RecipientType.TO, toAaddress);
        message.setSubject(Subject);
        message.setContent(content, "text/html;charset=utf-8");
        message.setSentDate(new Date());
        Transport.send(message);
      }
    
    
    public static void sendmail(String to,String Subject,String content) {
	
    	   String host = ConfigUtil.getConfig("MAIL_HOST");
		   String from = ConfigUtil.getConfig("MAIL_FROM");
		   String username = ConfigUtil.getConfig("MAIL_USER");
		   String password = ConfigUtil.getConfig("MAIL_PWD");
		   
		   Properties props = new Properties();
	       props.put("mail.smtp.host", host); // 指定SMTP服务器
	       props.put("mail.smtp.auth", "true"); // 指定是否需要SMTP验证
	       props.put("mail.debug", "true");

	        try {
	            Session mailSession = Session.getDefaultInstance(props);
	            Message message = new MimeMessage(mailSession);
	            message.setFrom(new InternetAddress(from)); // 发件人
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 收件人
	            message.setSubject(Subject); // 邮件主题
	            //指定邮箱内容及ContentType和编码方式
	            message.setContent(content, "text/html;charset=utf-8");
	            //指定邮件发送日期
	            message.setSentDate(new Date());
	            message.saveChanges();
	            
	            Transport transport = mailSession.getTransport("smtp");
	            transport.connect(host, username, password);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();

	        } catch(Exception e) {

	            System.out.println(e);
	        }
}

    /*
    class SmtpAuth extends javax.mail.Authenticator {
    	  private String user, password;

    	  public void getuserinfo(String getuser, String getpassword) {
    	   user = getuser;
    	   password = getpassword;
    	  }

    	  protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
    	   return new javax.mail.PasswordAuthentication(user, password);
    	  }
    }*/
    public static void main(String[] args) {
    	SendMail sendMail  = new SendMail();
    	try{
    	sendMail.sendMailByProxy("wanglz@si-tech.com.cn", "WelCome Regist China Direct Sale", "<h1>Welcome</h1>");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
}
