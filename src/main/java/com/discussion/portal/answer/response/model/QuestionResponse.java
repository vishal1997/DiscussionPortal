package com.discussion.portal.answer.response.model;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestionResponse {
	
	String questionId;
	String question;
	String owner;
	Date creationDate;
	List<String> tags;
	List<Integer> year;
	List<AnswerResponse> answerResponse;
}
