package com.alexbt.mongodb;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration {
	
	@Value("${one.spring.data.mongodb.uri}")
	private String dbOne;

	@Value("${two.spring.data.mongodb.uri}")
	private String dbTwo;
	
	@Bean
	@Qualifier("one")
	public ModelMongoRepository modelMongoRepositoryOne() throws DataAccessException, Exception {
		MongoRepositoryFactoryBean<ModelMongoRepository, Model, String> myFactory = new MongoRepositoryFactoryBean<ModelMongoRepository, Model, String>();
		myFactory.setRepositoryInterface(ModelMongoRepository.class);
		myFactory.setMongoOperations(createMongoOperations(dbOne));
		myFactory.afterPropertiesSet();
		return myFactory.getObject();
	}

	@Bean
	@Qualifier("two")
	public ModelMongoRepository modelMongoRepositoryTwo() throws DataAccessException, Exception {
		MongoRepositoryFactoryBean<ModelMongoRepository, Model, String> myFactory = new MongoRepositoryFactoryBean<ModelMongoRepository, Model, String>();
		myFactory.setRepositoryInterface(ModelMongoRepository.class);
		myFactory.setMongoOperations(createMongoOperations(dbTwo));
		myFactory.afterPropertiesSet();
		return myFactory.getObject();
	}
	
	@Bean
	@Qualifier("two-other")
	public OtherModelMongoRepository model2MongoRepositoryTwo() throws DataAccessException, Exception {
		MongoRepositoryFactoryBean<OtherModelMongoRepository, OtherModel, String> myFactory = new MongoRepositoryFactoryBean<OtherModelMongoRepository, OtherModel, String>();
		myFactory.setRepositoryInterface(OtherModelMongoRepository.class);
		myFactory.setMongoOperations(createMongoOperations(dbTwo));
		myFactory.afterPropertiesSet();
		return myFactory.getObject();
	}

	private MongoOperations createMongoOperations(String dbConnection) throws DataAccessException, Exception {
		MongoClientURI mongoClientURI = new MongoClientURI(dbConnection);
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		Mongo mongo = new SimpleMongoDbFactory(mongoClient, mongoClientURI.getDatabase()).getDb().getMongo();
		return new MongoTemplate(mongo, mongoClientURI.getDatabase());
	}
}
