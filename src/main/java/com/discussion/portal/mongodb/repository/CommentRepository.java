package com.discussion.portal.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.discussion.portal.mongodb.model.DbComment;


/**
 * 
 * @author Vishal
 *
 */
@Repository
public interface CommentRepository extends MongoRepository<DbComment, String> {

}
