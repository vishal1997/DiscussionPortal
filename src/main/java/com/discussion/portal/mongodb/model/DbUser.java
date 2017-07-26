package com.discussion.portal.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="profiles")
public class DbUser {
	
	@Id
	private String username;
	
	private int password;
	
	private String dummyData;
	
}
