package com.example.demo.data_tables.mapping;

import lombok.Data;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DataTablesInput {

    @Min(0)
    private int draw = 1;

    @Min(0)
    private int start = 0;

    @Min(-1)
    private int length = 10;

    private Search search = null;

    private List<Order> order = new ArrayList<Order>();

    private List<Column> columns = new ArrayList<Column>();

    //Connects to Search class and returns a list
    private List<Search> criteriaList = new ArrayList<Search>();

    private List<Column> orColumns = new ArrayList<Column>();

    public DataTablesInput() {
    }

    public DataTablesInput(@Min(0) int draw, @Min(0) int start, @Min(-1) int length, Search search, List<Order> order, List<Column> columns, List<Search> criteriaList, List<Column> orColumns) {
        this.draw = draw;
        this.start = start;
        this.length = length;
        this.search = search;
        this.order = order;
        this.columns = columns;
        this.criteriaList = criteriaList;
        this.orColumns = orColumns;
    }

    public List<Column> getOrColumns() {
        return orColumns;
    }

    public void setOrColumns(List<Column> orColumns) {
        this.orColumns = orColumns;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Search> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<Search> criteriaList) {
        this.criteriaList = criteriaList;
    }

    /**
     *
     * @return a {@link Map} of {@link Column} indexed by name
     */
    public Map<String, Column> getColumnsAsMap() {
        Map<String, Column> map = new HashMap<String, Column>();
        for (Column column : columns) {
            map.put(column.getData(), column);
        }
        return map;
    }


    /**
     * Find a column by its name
     *
     * @param columnName the name of the column
     * @return the given Column, or <code>null</code> if not found
     */
    public Column getColumn(String columnName) {
        if (columnName == null) {
            return null;
        }
        for (Column column : columns) {
            if (columnName.equals(column.getData())) {
                return column;
            }
        }
        return null;
    }


}