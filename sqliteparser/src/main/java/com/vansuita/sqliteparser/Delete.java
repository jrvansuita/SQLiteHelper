package com.vansuita.sqliteparser;

import java.util.LinkedList;

public class Delete extends SqlParser {

    private String table;
    private LinkedList<String> where;

    Delete(String name) {
        table = name;
    }

    /**
     * Override method to clear class attributes.
     *
     * @return the same instance of {@link Delete} class.
     */
    @Override
    protected SqlParser clear() {
        table = "";
        where = new LinkedList<>();

        return this;
    }

    /**
     * Sets the name of the table
     *
     * @param name the table name
     * @return the same instance of {@link Delete} class.
     */
    public Delete table(String name) {
        table = name;
        return this;
    }

    /**
     * Sets a where clause to delete command
     *
     * @param column the name of the column
     * @param op     the operation of the clause
     * @param value  the value of the clause
     * @return the same instance of {@link Delete} class.
     */
    private Delete where(String column, String op, Object value) {
        return where(column + " " + op + " " + String.valueOf(value));
    }

    /**
     * Sets a where clause to delete command
     *
     * @param value full value of the where clause
     * @return the same instance of {@link Delete} class.
     */
    public Delete where(String value) {
        if (!value.isEmpty()) {
            where.add(value);
        }
        return this;
    }

    /**
     * Sets an and as a divider of the where clauses
     *
     * @return the same instance of {@link Delete} class.
     */
    public Delete and() {
        if (!where.isEmpty()) {
            where("", SqlParser.AND, "");
        }

        return this;
    }

    /**
     * Sets an or as a divider of the where clauses
     *
     * @return the same instance of {@link Delete} class.
     */
    public Delete or() {
        if (!where.isEmpty()) {
            where("", SqlParser.OR, "");
        }

        return this;
    }

    /**
     * Sets a where clause to delete command
     *
     * @param alias  the nick for the column
     * @param column the name of the column
     * @param op     the operation of the clause
     * @param value  the value of the clause
     * @return the same instance of {@link Delete} class.
     */
    private Delete where(String alias, String column, String op, String value) {
        return where(colAlias(alias, column), op, value);
    }

    /**
     * Sets a equal clause to delete command
     *
     * @param cAlias  the nick for the column
     * @param column the name of the column
     * @param vAlias the nick for the value
     * @param value the value of the clause
     * @return the same instance of {@link Delete} class.
     */
    public Delete equal(String cAlias, String column, String vAlias, Object value) {
        return where(cAlias, column, EQUAL, colAlias(vAlias, String.valueOf(value)));
    }

    /**
     * Sets a equal clause to delete command
     *
     * @param cAlias  the nick for the column
     * @param column the name of the column
     * @param value the value of the clause
     * @return the same instance of {@link Delete} class.
     */
    public Delete equal(String cAlias, String column, Object value) {
        return where(cAlias, column, "=", String.valueOf(value));
    }

    /**
     * Sets a equal clause to delete command
     *
     * @param column the name of the column
     * @param value the int value of the clause
     * @return the same instance of {@link Delete} class.
     */
    public Delete equal(String column, int value) {
        return where(column, EQUAL, value);
    }

    /**
     * Sets a equal clause to delete command
     *
     * @param column the name of the column
     * @param value the int value of the clause
     * @return the same instance of {@link Delete} class.
     */
    public Delete equal(String column, String value) {
        return where(column, EQUAL, "'" + value + "'");
    }

    public Delete greaterEqual(String column, Object value) {
        return where(column, GREATER_EQUAL, value);
    }

    public Delete smallerEqual(String column, Object value) {
        return where(column, SMALLER_EQUAL, value);
    }

    public Delete in(String column, String subSelect) {
        return where(column + IN + " (" + subSelect + ")");
    }

    public Delete dif(String column, Object value) {
        return where(column, DIFFERENT, String.valueOf(value));
    }

    // --- Exists or Not --- //

    public Delete exists(String value) {
        where.add(EXISTS + " (" + value + ")");
        return this;
    }

    public Delete notExists(String value) {
        where.add(" " + NOT + " " + EXISTS + " (" + value + ")");
        return this;
    }

    // --- Utilities --- //

    private String colAlias(String alias, String column) {
        return alias.isEmpty() ? column : alias + "." + column;
    }

    @Override
    protected String getSql() {
        String sql = DELETE + " " + FROM + " " + table;

        if (!where.isEmpty())
            sql += " " + WHERE + " " + Utils.breakList("", where);

        return sql;
    }


}
