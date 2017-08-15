package com.discussion.portal.mongodb.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 * @author AkashKG
 *
 */
@Builder
@Getter
@Document(collection="answers")
public class DbAnswer {
	
	@Id
	private String answerId;
	
	private String userId;
	private String questionId;
	
	private String answer;
	private Date date;
	
	private Set<String> agree;
	private Set<String> disAgree;
	
	public void addAgree(String userId) {
		
		if(!this.disAgree.contains(userId)) {
			if(this.agree == null) {
				this.agree = new HashSet<String>();
			}
			
			if(this.agree.contains(userId)) {
				this.agree.remove(userId);
			} else {
				this.agree.add(userId);
			}
		}
	}
	
	public void addDisAgree(String userId) {
		
		if(!this.agree.contains(userId)) {
			if(this.disAgree == null) {
				this.disAgree =new HashSet<String>(); 
			}
			
			if(this.disAgree.contains(userId)) {
				this.disAgree.remove(userId);
			} else {
				this.disAgree.add(userId);
			}
		}
	}
	
	public Set<String> getAgree() {
		return this.agree;
	}
	
	public Set<String> getDisAgree() {
		return this.disAgree;
	}
	
	public int getNoOfAgree() {
		return this.agree == null ? 0 : this.agree.size();
	}
	
	public int getNoOfDisagree() {
		return this.disAgree == null ? 0 :this.disAgree.size();
	}
}
