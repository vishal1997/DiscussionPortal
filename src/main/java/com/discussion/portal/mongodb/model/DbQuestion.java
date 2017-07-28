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

import com.discussion.portal.model.Answer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Document(collection="questions")
public class DbQuestion {

	@Id
	private String questionId;
	
	private String question;
	
	//private List<Answer> answers;
	
	private Map<String, String> answersMap;
	
	private String owner;
	private Date creationDate;
	private List<String> tags;
	private List<Integer> year;
	
/*	public void addAnswer(Answer answer) {
		if(this.answers == null) {
			this.answers = new ArrayList<Answer>();
		}
		this.answers.add(answer);
	}*/
	
	public void addAnswerMap(Answer answer) {
		if(answersMap == null) {
			answersMap = new HashMap<String, String>();
		}
		answersMap.put(answer.getAnsweredBy(), answer.getAnswerId());
	}
}
