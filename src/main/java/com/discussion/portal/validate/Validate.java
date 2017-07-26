package com.discussion.portal.validate;

import org.springframework.stereotype.Component;

@Component
public class Validate {

	public static void validateQuestion(String question) {
		if(question == null) {
			throw new RuntimeException("The question cannot be null");
		}
	}
}
