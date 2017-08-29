package com.discussion.portal.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.discussion.portal.mongodb.model.DbAnswer;

@Repository
public interface AnswerRepository extends MongoRepository<DbAnswer, String> {
	
	List<DbAnswer> findByUserId(String userId);
}
