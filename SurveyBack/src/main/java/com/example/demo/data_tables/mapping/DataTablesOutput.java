package com.example.demo.data_tables.mapping;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class DataTablesOutput<T> {

    @JsonView(View.class)
    private int draw;

    @JsonView(View.class)
    private long recordsTotal = 0L;

    @JsonView(View.class)
    private long recordsFiltered = 0L;

    @JsonView(View.class)
    private List<T> content = Collections.emptyList();

    @JsonView(View.class)
    private String error;

    public interface View {
    }

    public DataTablesOutput() {
    }

    public DataTablesOutput(int draw, long recordsTotal, long recordsFiltered, List<T> content, String error) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.content = content;
        this.error = error;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return content;
    }

    public void setData(List<T> data) {
        this.content = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
