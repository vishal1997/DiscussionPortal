package com.discussion.portal.mongodb.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="profiles")
public class DbUser {
	
	@Id
	private String username;
	
	private int password;
	
	private List<String> questionId;
	
	public void addQuestionId(String questionId) {
		
		if(this.questionId == null) {
			this.questionId = new ArrayList<String>();
		}
	
		this.questionId.add(questionId);
	}
	
	public List<String> getQuestionId() {
		return this.questionId;
	}
}
