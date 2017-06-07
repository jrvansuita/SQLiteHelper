package com.vansuita.sqliteparser;

import android.util.Log;

public abstract class SqlParser {


    public static Create create(String tableName) {
        return new Create(tableName);
    }

    public static Content content() {
        return new Content();
    }

    public static Query query() {
        return new Query();
    }

    public static Cursor cursor(android.database.Cursor c) {
        return new Cursor(c);
    }

    public static Insert insert(String tableName) {
        return new Insert(tableName);
    }

    public static Delete delete(String tableName) {
        return new Delete(tableName);
    }

    SqlParser() {
        clear();
    }

    protected abstract String getSql();

    protected abstract SqlParser clear();


    public String build() {
        String sql = getSql();

        Log.v("SQL", sql);

        return sql;
    }





    protected static final String AND = "AND";
    protected static final String OR = "OR";
    protected static final String EQUAL = "=";
    protected static final String GREATER_EQUAL = ">=";
    protected static final String SMALLER_EQUAL = "<=";
    protected static final String IN = "IN";
    protected static final String DIFFERENT = "<>";
    protected static final String EXISTS = "EXISTS";
    protected static final String NOT = "NOT";

    protected static final String CREATE_INDEX = "CREATE INDEX %s ON %s (%s);";
    protected static final String CREATE_TABLE = "CREATE TABLE %s (%s)";
    protected static final String INTEGER = "INTEGER";
    protected static final String PK = INTEGER + " PRIMARY KEY";
    protected static final String AUTOINCREMENT = PK + " AUTOINCREMENT";
    protected static final String TEXT = "TEXT";
    protected static final String FLOAT = "FLOAT";

    protected static final String DELETE = "DELETE";
    protected static final String FROM = "FROM";
    protected static final String WHERE = "WHERE";


}
