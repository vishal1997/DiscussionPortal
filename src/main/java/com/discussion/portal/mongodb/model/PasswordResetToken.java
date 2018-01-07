package com.discussion.portal.mongodb.model;
/*
 * Currently not used.
 * Used this collection to generate reset token.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Calendar;
import java.util.Date;

@Document(collection="tokens")
public class PasswordResetToken {
		
	@Id
	private String token;
	private DbUser user;
	private Date expiryDate;
	
	public String getToken() {
	    return token;
	}
	
	public void setToken(String token) {
	    this.token = token;
	}
	
	public DbUser getUser() {
	    return user;
	}
	
	public void setUser(DbUser user) {
	    this.user = user;
	}
	
	public Date getExpiryDate() {
	    return expiryDate;
	}
	
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
