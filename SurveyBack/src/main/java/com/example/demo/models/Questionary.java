package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Questionary {
    @Id
    private String id;
    private String name;
    private String description;
    private QuestionAndAnswers[] questions;
    private String userId;
    private Integer voters;


    public Questionary() {
    }

    public Questionary(String id, String name, String description, QuestionAndAnswers[] questions, String userId, Integer voters) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.userId = userId;
        this.voters = voters;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionAndAnswers[] getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionAndAnswers[] questions) {
        this.questions = questions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getVoters() {
        return voters;
    }

    public void setVoters(Integer voters) {
        this.voters = voters;
    }
}
