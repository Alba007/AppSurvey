package com.example.demo.data_tables.mapping;

import lombok.Data;

@Data
public class Filter {
    private String gt;
    private String gte;
    private String lt;
    private String lte;
    private String eq;
    private String ne;
    private String in;
    private String nin;
    private String regex;
    private Boolean exists;
    private Boolean isNull;
    private Boolean isEmpty;
    private String all;

    public Filter() {
    }

    public Filter(String gt, String gte, String lt, String lte, String eq, String ne, String in, String nin, String regex, Boolean exists, Boolean isNull, Boolean isEmpty, String all) {
        this.gt = gt;
        this.gte = gte;
        this.lt = lt;
        this.lte = lte;
        this.eq = eq;
        this.ne = ne;
        this.in = in;
        this.nin = nin;
        this.regex = regex;
        this.exists = exists;
        this.isNull = isNull;
        this.isEmpty = isEmpty;
        this.all = all;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getGte() {
        return gte;
    }

    public void setGte(String gte) {
        this.gte = gte;
    }

    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
    }

    public String getLte() {
        return lte;
    }

    public void setLte(String lte) {
        this.lte = lte;
    }

    public String getEq() {
        return eq;
    }

    public void setEq(String eq) {
        this.eq = eq;
    }

    public String getNe() {
        return ne;
    }

    public void setNe(String ne) {
        this.ne = ne;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public Boolean getNull() {
        return isNull;
    }

    public void setNull(Boolean aNull) {
        isNull = aNull;
    }

    public Boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
