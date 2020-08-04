package com.example.demo.data_tables.mapping;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class Order {

    @Min(0)
    private Integer column;

    private String data;

    @NotNull
    @Pattern(regexp = "(desc|asc)")
    private String dir;

    public Order() {
    }

    public Order(@Min(0) Integer column, String data, @NotNull @Pattern(regexp = "(desc|asc)") String dir) {
        this.column = column;
        this.data = data;
        this.dir = dir;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }


}
