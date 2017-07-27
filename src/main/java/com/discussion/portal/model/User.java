package com.discussion.portal.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class User {
	
	private String firstName;
	private String lastName;
	private String username;
	private int year;
	private String branch;
	private List<String> questionId;
	private Map<String, String> userAnswerMap;
	
}
