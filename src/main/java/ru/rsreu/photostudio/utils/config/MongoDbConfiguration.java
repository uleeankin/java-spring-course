package ru.rsreu.photostudio.utils.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDbConfiguration {
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://root:root@localhost:27017/");
    }
}
