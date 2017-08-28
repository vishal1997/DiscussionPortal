package com.discussion.portal.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.discussion.portal.mongodb.model.DbUser;

@Repository
public interface UserRepository extends MongoRepository<DbUser, String> {

	Optional <DbUser> findByUsername(String username);
}
