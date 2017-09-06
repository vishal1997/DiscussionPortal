package com.discussion.portal.mongodb.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.discussion.portal.model.Answer;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 * @author Vishal
 *
 */
@Builder
@Getter
@Document(collection="questions")
public class DbQuestion {

	@Id
	private String questionId;
	
	private String question;
	
	private Map<String, String> answersMap;
	
	private String owner;
	private Date creationDate;
	private List<String> tags;
	private List<Integer> year;
	
	public void addAnswerMap(Answer answer) {
		if(answersMap == null) {
			answersMap = new HashMap<String, String>();
		}
		answersMap.put(answer.getAnsweredBy(), answer.getAnswerId());
	}
	
	public void removeAnswerMap(DbAnswer dbAnswer) {
		if(this.answersMap.containsValue(dbAnswer.getAnswerId())) {
			this.answersMap.remove(dbAnswer.getUserId(), dbAnswer.getAnswerId());
		}
	}
}
