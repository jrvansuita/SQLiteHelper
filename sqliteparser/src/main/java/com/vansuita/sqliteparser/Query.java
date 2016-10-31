package com.vansuita.sqliteparser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Query extends SqlParser {

    private LinkedList<String> columns;
    private LinkedList<String> tables;
    private LinkedList<String> leftJoin;
    private LinkedList<String> where;
    private LinkedList<String> grouping;
    private LinkedList<String> order;

    private int limit = 0;

    Query() {

    }

    @Override
    public Query clear() {
        columns = new LinkedList();
        tables = new LinkedList();
        leftJoin = new LinkedList();
        where = new LinkedList();
        grouping = new LinkedList();
        order = new LinkedList();

        return this;
    }

    // --- Columns --- //


    public Query col(String name) {
        columns.add(name);
        return this;
    }


    public Query cols(String... names) {
        if (names != null) {
            columns.addAll(Arrays.asList((String[]) names));
        }
        return this;
    }


    public Query col(String alias, String name, String nick) {
        return col(colAlias(alias, asAlias(name, nick)));
    }


    public Query col(String name, String nick) {
        return col(asAlias(name, nick));
    }

    public Query count(String alias) {
        return col("COUNT(*)", alias);
    }

    public Query count() {
        return col("COUNT(*)");
    }


    public Query max(String name) {
        return col("MAX(" + name + ")");
    }

    public Query sum(String name) {
        return col("SUM(" + name + ")");
    }

    public Query sum(String name, String alias) {
        return col(asAlias("SUM(" + name + ")", alias));
    }

    // --- Tables --- //

    public Query table(String name) {
        tables.add(name);
        return this;
    }

    public Query table(String name, String alias) {
        return table(name + " " + alias);
    }

    // --- Left Join --- //

    public Query left(String name, String alias) {
        leftJoin.add(" LEFT JOIN " + name + " " + alias);
        return this;
    }

    public Query on(String cAlias, String column, String op, String vAlias, String value) {
        int last = leftJoin.size() - 1;
        if (last > -1) {
            leftJoin.set(last, leftJoin.get(last) + " ON (" + colAlias(cAlias, column) + " " + op + " " + colAlias(vAlias, value) + ")");
        }

        return this;
    }

    // --- Wheres --- //

    private Query where(String column, String op, Object value) {
        return where(column + " " + op + " " + String.valueOf(value));
    }

    public Query where(String value) {
        if (!value.isEmpty()) {
            where.add(value);
        }
        return this;
    }

    public Query and() {
        if (!where.isEmpty()) {
            where("", "AND", "");
        }

        return this;
    }

    public Query or() {
        if (!where.isEmpty()) {
            where("", "OR", "");
        }

        return this;
    }

    private Query where(String cAlias, String column, String op, String value) {
        return where(colAlias(cAlias, column), op, value);
    }

    public Query equal(String cAlias, String column, String vAlias, Object value) {
        return where(cAlias, column, "=", colAlias(vAlias, String.valueOf(value)));
    }

    public Query equal(String cAlias, String column, Object value) {
        return where(cAlias, column, "=", String.valueOf(value));
    }

    public Query equal(String column, Object value) {
        return where(column, "=", value);
    }

    public Query equalStr(String column, Object value) {
        return where(column, "=", "'" + value + "'");
    }

    public Query equalTrim(String column, Object value) {
        return where("TRIM(" + column + ")", "=", "'" + value.toString().trim() + "'");
    }

    public Query greater(String cAlias, String column, Object value) {
        return where(colAlias(cAlias, column), ">", value);
    }

    public Query greater(String column, Object value) {
        return where(column, ">", value);
    }

    public Query greaterEqual(String column, Object value) {
        return where(column, ">=", value);
    }

    public Query smallerEqual(String column, Object value) {
        return where(column, "<=", value);
    }

    public Query in(String column, String subSelect) {
        return where(column + " IN (" + subSelect + ")");
    }


    // --- Exists or Not --- //

    public Query exists(String value) {
        where.add(" EXISTS (" + value + ")");
        return this;
    }

    public Query notExists(String value) {
        where.add(" NOT EXISTS (" + value + ")");
        return this;
    }

    // --- Group by --- //

    public Query group(String column) {
        grouping.add(column);
        return this;
    }

    public Query group(String alias, String column) {
        grouping.add(colAlias(alias, column));
        return this;
    }

    // --- Order by --- //


    public Query desc(String column) {
        return order(column, "DESC");
    }

    public Query desc(String alias, String column) {
        return order(alias, column, "DESC");
    }

    public Query asc(String column) {
        return order(column, "ASC");
    }

    public Query asc(String alias, String column) {
        return order(alias, column, "ASC");
    }

    public Query order(String column, String modf) {
        order.add(column + " " + modf);
        return this;
    }

    private Query order(String alias, String column, String modf) {
        order.add(colAlias(alias, column) + " " + modf);
        return this;
    }


    // --- Generate Select --- //

    @Override
    protected String getSql() {
        String sql = "SELECT ";

        if (columns.isEmpty()) {
            sql += " * ";
        } else {
            sql += get(columns);
        }

        sql += " FROM  " + get(tables);

        if (!leftJoin.isEmpty())
            sql += Utils.breakList("", leftJoin);

        if (!where.isEmpty())
            sql += " WHERE " + Utils.breakList("", where);

        if (!grouping.isEmpty())
            sql += " GROUP BY " + get(grouping);

        if (!order.isEmpty())
            sql += " ORDER BY " + get(order);


        if (limit > 0) {
            sql += " LIMIT " + limit;
        }

        return sql;
    }



    private String get(List<String> list) {
        return Utils.breakList(",", list);
    }

    // --- Utilities --- //

    private String colAlias(String alias, String column) {
        return alias.isEmpty() ? column : alias + "." + column;
    }

    private String asAlias(String column, String alias) {
        return alias.isEmpty() ? column : column + " AS " + alias;
    }

    public Query limit(int limit) {
        this.limit = limit;
        return this;
    }

    public int getLimit() {
        return limit;
    }
}
