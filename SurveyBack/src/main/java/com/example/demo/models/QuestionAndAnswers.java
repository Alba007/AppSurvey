package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Array;

@Document
public class QuestionAndAnswers {
    @Id
    private int id;
    private String question;
    private String[] options;
    private int[] answers;
    private String imageFile;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getIdQ() {
        return id;
    }

    public void setIdQ(int idQ) {
        this.id = idQ;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public QuestionAndAnswers(int id, String question, String[] options, int[] answers, String imageFile) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.answers = answers;
        this.imageFile = imageFile;
    }

    public QuestionAndAnswers() {
    }

    public int[] getAnswers() {
        return answers;
    }

    public void setAnswers(int[] answers) {
        this.answers = answers;
    }
}
