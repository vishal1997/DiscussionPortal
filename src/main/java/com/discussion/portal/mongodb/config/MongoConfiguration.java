package com.discussion.portal.mongodb.config;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.discussion.portal.common.Constants.Database;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * MongoDB Configuration
 * @author Vishal
 *
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.discussion.portal.mongodb.repository"})
public class MongoConfiguration extends AbstractMongoConfiguration {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDatabaseName() {
		return Database.DATABASE;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mongo mongo() throws Exception {
		
        ServerAddress serverAddress = new ServerAddress(Database.ADDRESS, Database.PORT);
        MongoCredential credential = MongoCredential.createCredential(Database.USER, Database.DATABASE, Database.PWD.toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(4).socketKeepAlive(true).build();
        
        Mongo mongo = new MongoClient(serverAddress, Arrays.asList(credential),options);

        return mongo;
	}
	
    @Bean(name="MongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), Database.DATABASE);
    }
}
