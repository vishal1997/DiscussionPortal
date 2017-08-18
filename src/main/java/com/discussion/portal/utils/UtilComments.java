package com.discussion.portal.utils;

import org.springframework.stereotype.Component;

import com.discussion.portal.model.Comment;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbComment;

@Component
public class UtilComments {

	public DbComment convertCommentToDbComment(Comment commentObj) {
		
		if(commentObj == null) {
			return null;
		}
		DbComment dbComment = DbComment.builder()
							.answerId(commentObj.getAnswerId())
							.comment(commentObj.getComment())
							.date(commentObj.getDate())
							.userId(commentObj.getUserId())
							.build();
		return dbComment;
	}
}
