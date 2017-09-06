package com.discussion.portal.model;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Vishal
 *
 */
@Getter
@Setter
@Component
public class Answer extends AgreeDisagree{
	
	private String answerId;
	private String questionId;
	private String answer;
	private String question;
	private Date date;
	private String answeredBy;
	private List<String>commentId;
	private int noOfComment;	
}
