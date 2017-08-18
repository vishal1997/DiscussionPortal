package com.discussion.portal.answer.response.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnswerResponse {
	
	private String answerId;
	private String answerBy;
	private Date date;
	private String answer;
	private Set<String> agree;
	private Set<String> disagree;
	private Map<String, String> comments;
	private int noOfAgree;
	private int noOfDisagree;
}
