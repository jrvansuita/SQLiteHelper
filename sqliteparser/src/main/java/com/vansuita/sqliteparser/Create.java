package com.vansuita.sqliteparser;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jrvansuita on 05/09/15.
 * This class builds an insert command to run on {@link SQLiteDatabase}.
 */

public class Create extends SqlParser {

    private LinkedHashMap<String, String> fields;
    private List<String> indexes;
    private String tableName;

    public Create(String tableName) {
        this.tableName = tableName;
    }


    /**
     * Override method to clear class attributes.
     *
     * @return the same instance of {@link Create} class.
     */
    @Override
    protected Create clear() {
        fields = new LinkedHashMap();
        indexes = new ArrayList();
        tableName = "";
        return this;
    }

    /**
     * Sets the primary key field name.
     *
     * @param fieldName The name nof the field
     * @return the same instance of {@link Create} class.
     */
    public Create pk(String fieldName) {
        fields.put(fieldName, PK);
        return this;
    }

    /**
     * Sets the primary key autoincrement field name.
     *
     * @param fieldName The name of the field
     * @return the same instance of {@link Create} class.
     */
    public Create pkai(String fieldName) {
        fields.put(fieldName, AUTOINCREMENT);
        return this;
    }

    /**
     * Sets a String field name.
     *
     * @param fieldName The name nof the field
     * @return the same instance of {@link Create} class.
     */
    public Create str(String fieldName) {
        fields.put(fieldName, TEXT);
        return this;
    }

    /**
     * Sets a boolean field name.
     *
     * @param fieldName The name nof the field
     * @return the same instance of {@link Create} class.
     */
    public Create bool(String fieldName) {
        fields.put(fieldName, INTEGER);
        return this;
    }

    /**
     * Sets a int field name.
     *
     * @param fieldName The name nof the field
     * @return the same instance of {@link Create} class.
     */
    public Create num(String fieldName) {
        fields.put(fieldName, INTEGER);
        return this;
    }

    /**
     * Sets a Date field name. (Date is stored as int on {@link SQLiteDatabase}.)
     *
     * @param fieldName The name nof the field
     * @return the same instance of {@link Create} class.
     */
    public Create date(String fieldName) {
        fields.put(fieldName, INTEGER);
        return this;
    }

    /**
     * Sets a float field name.
     *
     * @param fieldName The name nof the field
     * @return the same instance of {@link Create} class.
     */
    public Create flo(String fieldName) {
        fields.put(fieldName, FLOAT);
        return this;
    }

    /**
     * Creates an index on the table.
     *
     * @param name The index name
     * @param fields an array of the fields names to compouse the index
     * @return the same instance of {@link Create} class.
     */
    public Create index(String name, String... fields) {
        String ind = String.format(CREATE_INDEX, name, tableName, Utils.breakArray(",", fields));
        indexes.add(ind);
        return this;
    }


    /**
     * Iterates over the fields map to build a string with field name and types.
     *
     * @return The String builded
     */
    private String buildFields() {
        String result = "";

        Iterator<Map.Entry<String, String>> it = fields.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, String> field = it.next();
            result += field.getKey() + " " + field.getValue() + (it.hasNext() ? "," : "");
        }

        return result;
    }


    /**
     * Override method that returns the sql generated
     *
     * @return The String builded
     */
    @Override
    protected String getSql() {
        String result = String.format(CREATE_TABLE, tableName, buildFields()) + ";";
        result += Utils.breakList("", indexes);

        return result;
    }


}
