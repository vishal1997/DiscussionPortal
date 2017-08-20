package com.discussion.portal.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class Question {

	private String questionId;
	private String question;
	private String owner;
	private Date creationDate;
	private List<String> tags;
	private List<Integer> year;
	private Map<String, String> answersMap;
	
}
