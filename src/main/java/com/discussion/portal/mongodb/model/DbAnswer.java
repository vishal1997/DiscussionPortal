package com.discussion.portal.mongodb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author AkashKG
 *
 */
@Setter
@Getter
@Document(collection="answers")
public class DbAnswer extends AgreeDisagree{
	
	@Id
	private String answerId;
	
	private String userId;
	private String questionId;
	
	private String answer;
	private Date date;
	


	private List<String> commentId;
	
	public void addCommentId(String commentId) {
		
		if(this.commentId == null) {
			this.commentId = new ArrayList<String>();
		}
		this.commentId.add(commentId);
	}
	
}
