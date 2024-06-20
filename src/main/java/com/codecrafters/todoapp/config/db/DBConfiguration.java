package com.codecrafters.todoapp.config.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DBConfiguration {

  @Bean
  MongoClient mongoClient() {
    return MongoClients.create(DBCredentials.DB_URL);
  }
}
