package com.example.demo.dto;


import com.example.demo.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private String id;
    private String username;
    private String chart_id;
    private User.Role role;
    private List<String> survey = new ArrayList<>();;
    public UserDto() {
    }

    public UserDto(String id, String username, String chart_id, User.Role role, List<String> surveysSubmited) {
        this.id = id;
        this.username = username;
        this.chart_id = chart_id;
        this.role = role;
        this.survey = surveysSubmited;
    }

    public String getId() {
        return id;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChart_id() {
        return chart_id;
    }

    public void setChart_id(String chart_id) {
        this.chart_id = chart_id;
    }

    public List<String> getSurveysSubmited() {
        return survey;
    }

    public void setSurveysSubmited(List<String>surveysSubmited) {
        this.survey = surveysSubmited;
    }
}