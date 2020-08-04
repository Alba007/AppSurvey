package com.example.demo.data_tables.mapping;

import lombok.Data;

@Data
public class Search {

    public String name;
    public String value;


    private boolean regex = false;

    public Search() {
    }

    public Search(String name, String value, boolean regex) {
        this.name = name;
        this.value = value;
        this.regex = regex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRegex() {
        return regex;
    }

}
