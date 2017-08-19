package com.discussion.portal.mongodb.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AgreeDisagree {

	private Set<String> agree;
	private Set<String> disagree;
	

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
