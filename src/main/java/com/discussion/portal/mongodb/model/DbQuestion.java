package com.discussion.portal.mongodb.model;

import java.util.Date;
import java.util.List;

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
	
	private List<Answer> answers;
	private String owner;
	private Date creationDate;
	private List<String> tags;
	private List<Integer> year;
	
}
