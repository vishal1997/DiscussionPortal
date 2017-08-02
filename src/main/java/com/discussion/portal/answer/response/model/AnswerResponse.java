package com.discussion.portal.answer.response.model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnswerResponse {
	
	String answerId;
	String answerBy;
	Date date;
	String answer;

}
