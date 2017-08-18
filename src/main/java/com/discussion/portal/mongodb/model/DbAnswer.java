package com.discussion.portal.mongodb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	private Set<String> disagree;

	private List<DbComment> comments;
	
	/**
	 * 
	 * @param comment
	 */
	public void updateComment(DbComment comment) {
		
		if(this.comments == null) {
			this.comments= new ArrayList<DbComment>();
		}
		
		this.comments.add(comment);
	}
	
	/**
	 * Update Agree
	 * @param userId
	 */
	public void updateAgree(String userId) {

		if(this.getNoOfDisagree()!=0) {
			if(this.disagree.contains(userId)) {
				return ;
			}
		}
		
		if(this.agree == null) {
			this.agree = new HashSet<String>();
		}
		
		if(this.agree.contains(userId)) {
			this.agree.remove(userId);
		} else {
			this.agree.add(userId);
		}
	}
	
	/**
	 * Update Disagree
	 * @param userId
	 */
	public void updateDisagree(String userId) {
		
		if(this.getNoOfAgree()!=0) {
			if(this.agree.contains(userId)) {
				return ;
			}
		}
		
		if(this.disagree == null) {
			this.disagree =new HashSet<String>(); 
		}
		
		if(this.disagree.contains(userId)) {
			this.disagree.remove(userId);
		} else {
			this.disagree.add(userId);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<String> getAgree() {
		return this.agree;
	}
	
	public Set<String> getDisagree() {
		return this.disagree;
	}
	
	public int getNoOfAgree() {
		return this.agree == null ? 0 : this.agree.size();
	}
	
	public int getNoOfDisagree() {
		return this.disagree == null ? 0 :this.disagree.size();
	}
}
