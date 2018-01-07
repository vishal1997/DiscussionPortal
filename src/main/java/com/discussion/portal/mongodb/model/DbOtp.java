package com.discussion.portal.mongodb.model;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DbOtp {

	@Id
	private String userId;
	private String otp;
	private Date expiryDate;
	
	public void setExpiryDate(Date expiryDate) {
	    this.expiryDate = expiryDate;
	}
	
	public void setExpiryDate(int minutes){
	    Calendar now = Calendar.getInstance();
	    now.add(Calendar.MINUTE, minutes);
	    this.expiryDate = now.getTime();
	}
	
	public boolean isExpired() {
	    return new Date().after(this.expiryDate);
	}
}
