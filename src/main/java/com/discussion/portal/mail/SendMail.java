package com.discussion.portal.mail;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.Mailer;
import com.discussion.portal.common.Constants.StatusCode;

@Component
public class SendMail {

	@Autowired
	private Mail mail;
	
	public String sendMail() {
		
		mail.setPassword(Mailer.PA);
		mail.setHost(Mailer.HOST);
		mail.setSender(Mailer.SENDER);
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", mail.getHost());
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.port", "465");    
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");     
        properties.put("mail.smtp.port", "465");    
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAurhentication() {
						return new PasswordAuthentication(mail.getSender(), mail.getPassword());
					}
				});
		
		try {
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getSender()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getEmailId()));
			message.setSubject(mail.getSubject());
			message.setText(mail.getMessage());
			Transport.send(message, mail.getSender(),mail.getPassword());
			System.out.println("Mail Sent");
			return StatusCode.SUCCESS;
		} catch(Exception ex) {
			throw new RuntimeException("Error while sending mail" + ex);
		}
	}
}
