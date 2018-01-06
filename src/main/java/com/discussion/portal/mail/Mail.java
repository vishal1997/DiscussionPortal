package com.discussion.portal.mail;

import org.springframework.stereotype.Component;

@Component
public class Mail {

	private String host;
	private String emailId;
	private String password;
	private String sender;
	private String subject;
	private String message;
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getHost() {
		return this.host;
	}
	
	public String getSender() {
		return this.sender;
	}
	
	public String getSubject() {
		return this.subject;
	}

	public String getMessage() {
		return this.message;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public String getPassword() {
		return this.password;
	}
}
