package com.discussion.portal.mongodb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.discussion.portal.common.Constants.StatusCode;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Vishal
 *
 */
@Getter
@Setter
@Document(collection="profiles")
public class DbUser {
	
	@Id
	private String username;
	private String password;
	private List<String> questionId;
	private String name;
	private String Branch;
	private String admissionYear;
	private String gender;
	private String dob;
	private String emailId;
	private String sec;
	private String phone;
	private String state;
	private String city;
	
	
	/**
	 * Key: QuestionId
	 * Value: AnswerId
	 */
	private Map<String, String> userAnswerMap;
	
	public void addQuestionId(String questionId) {
		
		if(this.questionId == null) {
			this.questionId = new ArrayList<String>();
		}
	
		this.questionId.add(questionId);
	}
	
	public void addAnswerToMap(String questionId, String answerId) {
		if(this.userAnswerMap == null) {
			userAnswerMap = new HashMap<String, String>();
		}
		
		userAnswerMap.put(questionId, answerId);
	}
	
	
	public List<String> getQuestionId() {
		return this.questionId;
	}
	
	public void deleteQuestionId(String questionId) {
		this.questionId.remove(questionId);
	}
	
	public void deleteAnswerToMap(String questionId) {
		this.userAnswerMap.remove(questionId);
	}
}
