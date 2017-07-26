package com.discussion.portal.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.discussion.portal.mongodb.model.DbQuestion;

@Repository
public interface QuestionsRepository extends MongoRepository<DbQuestion, String>{

}
