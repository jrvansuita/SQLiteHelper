package com.vansuita.sqliteparser;

import java.util.Date;
import java.util.LinkedList;

public class Insert extends SqlParser {


    private static final String INSERT_FORMAT = "INSERT INTO %s(%s) VALUES(%s);";

    private String tableName;

    private LinkedList<String> columns;
    private LinkedList<String> values;

    Insert(){}

    public Insert(String tableName) {
        this.tableName = tableName;
    }

    private Insert add(String name, String value) {
        columns.add(name);
        values.add(value);
        return this;
    }

    public Insert col(String name, String value) {
        return add(name, "'" + value + "'");
    }

    public Insert col(String name, int value) {
        return add(name, String.valueOf(value));
    }

    public Insert col(String name, Boolean value) {
        return col(name, value ? 1 : 0);
    }

    public Insert col(String name, Date value) {
        return add(name, String.valueOf(value.getTime()));
    }

    public Insert col(String name, long value) {
        return add(name, String.valueOf(value));
    }

    @Override
    protected String getSql() {
        String cols = "";
        String vals = "";

        if (!columns.isEmpty()) {
            cols = Utils.breakList(",", columns);
            vals = Utils.breakList(",", values);
        }

        return String.format(INSERT_FORMAT, tableName, cols, vals);
    }

    @Override
    protected Insert clear() {
         tableName = "";
        columns = new LinkedList();
        values = new LinkedList();

        return this;
    }

}
