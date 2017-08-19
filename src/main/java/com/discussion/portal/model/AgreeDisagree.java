package com.discussion.portal.model;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AgreeDisagree {
	
	private int noOfAgree;
	private int noOfDisagree;
	private Set<String> agree;
	private Set<String> disagree;
}
