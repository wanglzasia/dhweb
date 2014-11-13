package com.dh.ora.s001.service;

import java.util.Date;
import java.util.Properties;
import java.security.Security;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class MailTest {
/**
 * 通过代理发送邮件
 */
    /**
      * @param args
     */
    public static void main(String[] args) throws Exception {
     MailTest test = new MailTest();
     //发送邮件
     //test.sendmail();
     //通过代理发送邮件
     test.sendMailByProxy();
     }

    private void sendMailByProxy()throws Exception{
      Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
     //设置代理服务器
      Properties props = System.getProperties();
      props.setProperty("proxySet", "true");
      props.setProperty("socksProxyHost", "10.109.222.127");
      props.setProperty("socksProxyPort", "1080");
      props.setProperty("mail.smtp.host", "smtp.163.com");
     
      props.setProperty("mail.smtp.socketFactory.fallback", "false");
      props.setProperty("mail.smtp.port", "25");
      props.setProperty("mail.smtp.socketFactory.port", "25");
      props.put("mail.smtp.auth", "true");
      props.put("mail.debug", "true");
      props.put("mail.store.protocol", "pop3");
      props.put("mail.transport.protocol", "smtp");
      final String username = "allshi01";
      final String password = "abc123";
     
     //使用验证
      Session session = Session.getDefaultInstance(props,
             new Authenticator() {
                 protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                     return new javax.mail.PasswordAuthentication(username,
                              password);
                  }
              });
      MimeMessage message = new MimeMessage(session);
      Address address = new InternetAddress("allshi01@163.com");
      Address toAaddress = new InternetAddress("wanglz@si-tech.com.cn");
     
      message.setFrom(address);
      message.setRecipient(MimeMessage.RecipientType.TO, toAaddress);
      message.setSubject("测试1");
      message.setText("test1");
      message.setSentDate(new Date());
      Transport.send(message);
      System.out.println("邮件发送！");
    }
    
 public void sendmail() {
  try {
   String host = "smtp.163.com";
   String from = "allshi01@163.com";
   String to = "allshi01@163.com";
   String username = "allshi01";
   String password = "abc123";
   String body = "This is a test";
   String subject = "Email Test";
   SmtpAuth sa = new SmtpAuth();
   sa.getuserinfo(username, password);
   Session session;
   MimeMessage message;
   Properties props = System.getProperties();
   props.put("mail.smtp.auth", "true");
   props.put("mail.smtp.host", host);
   session = Session.getInstance(props, sa);
   session.setDebug(true);
   message = new MimeMessage(session);
   message.setFrom(new InternetAddress(from));
   message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
   message.setSubject(subject);
   MimeBodyPart mbp = new MimeBodyPart();
   mbp.setText(body);
   Multipart mp = new MimeMultipart();
   mp.addBodyPart(mbp);
   message.setContent(mp);
   message.setSentDate(new java.util.Date());
   Transport transport = session.getTransport("smtp");
   transport.connect(host, username, password);
   transport.send(message);
  } catch (MessagingException e) {
	  System.out.println("error" + e.getMessage());
  }
  
 }

 class SmtpAuth extends javax.mail.Authenticator {
  private String user, password;

  public void getuserinfo(String getuser, String getpassword) {
   user = getuser;
   password = getpassword;
  }

  protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
   return new javax.mail.PasswordAuthentication(user, password);
  }
 }
 
 
}

