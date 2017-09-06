package com.discussion.portal.model;

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
public class User {
	
	private String name;
	private String username;
	private String admissionYear;
	private String branch;
	private List<String> questionId;
	private Map<String, String> userAnswerMap;
	private String password;
	private String sec;
	private String phone;
	private String emailId;
	private String state;
	private String city;
	private String dob;
	private String gender;
}
