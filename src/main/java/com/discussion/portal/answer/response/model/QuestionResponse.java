package com.discussion.portal.answer.response.model;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 * @author Vishal
 *
 */
@Builder
@Getter
public class QuestionResponse {
	
	private String questionId;
	private String question;
	private String owner;
	private String ownerName;
	private Date creationDate;
	private List<String> tags;
	private List<Integer> year;
	private List<AnswerResponse> answerResponse;
}
