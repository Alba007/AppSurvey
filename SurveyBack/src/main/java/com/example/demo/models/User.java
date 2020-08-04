package com.example.demo.models;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    public enum Role {
        Voter, Creater
    }

    private Role role;
    @UniqueElements
    private String username;
    private String password;
    private List<String> survey = new ArrayList<>();


    public User() {
    }

    public String getId() {
        return id;
    }

    public User(String id, String username, String password, Role role, List<String> survey) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.survey = survey;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getSuveysSubmited() {
        return survey;
    }

    public void setSurvey(List<String> survey) {
        this.survey = survey;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}