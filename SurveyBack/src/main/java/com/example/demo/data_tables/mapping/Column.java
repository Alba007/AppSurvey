package com.example.demo.data_tables.mapping;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

@Data
public class Column {

    @NotBlank
    private String data;

    private String name;
    private boolean searchable = false;
    private boolean orderable = true;
    private Search search;
    private String type ;
    private Filter filter = null;

    public boolean hasValidSearch() {
        boolean isSearchValid = false;
        if (this.searchable) {
            if (this.search != null) {
                if (StringUtils.hasLength(this.search.getValue())) {
                    isSearchValid = true;
                }
            }
        }
        return isSearchValid;
    }

    public Column() {
    }

    public Column(@NotBlank String data, String name, boolean searchable, boolean orderable, Search search, String type, Filter filter) {
        this.data = data;
        this.name = name;
        this.searchable = searchable;
        this.orderable = orderable;
        this.search = search;
        this.type = type;
        this.filter = filter;
    }

    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public Search getSearch() {
        return search;
    }

    public String getType() {
        return type;
    }

    public Filter getFilter() {
        return filter;
    }


}
