package com.example.demo;

import com.example.demo.data_tables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
public class SurveyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurveyBackendApplication.class, args);
    }

}
