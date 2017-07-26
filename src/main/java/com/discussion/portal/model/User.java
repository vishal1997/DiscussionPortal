package com.discussion.portal.model;

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
	
}
