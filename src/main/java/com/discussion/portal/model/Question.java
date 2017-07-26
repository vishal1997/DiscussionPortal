package com.discussion.portal.model;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Builder;
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
public class Question {

	private String questionId;
	private String question;
	private List<Answer> answers;
	private String owner;
	private Date creationDate;
	private List<String> tags;
	private List<Integer> year;
	
}
