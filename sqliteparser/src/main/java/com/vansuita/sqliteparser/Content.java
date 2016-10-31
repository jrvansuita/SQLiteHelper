package com.vansuita.sqliteparser;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jrvansuita on 03/08/16.
 * This class creates a {@link ContentValues} object to build an insert or update for {@link SQLiteDatabase}
 */

public class Content {

    ContentValues cv;

    public Content() {
        cv = new ContentValues();
    }

    /**
     * Add an int value on ContentValues.
     * Use this method for the primary key field.
     *
     * @param tag The name of the field
     * @param val The value of the field
     * @return the same instance of {@link Content} class.
     */
    public Content id(String tag, int val) {
        cv.put(tag, val);
        return this;
    }


    /**
     * Add an int value on ContentValues.
     *
     * @param tag The name of the field
     * @param val The value of the field
     * @return the same instance of {@link Content} class.
     */
    public Content add(String tag, int val) {
        cv.put(tag, val);
        return this;
    }

    /**
     * Add a String value on ContentValues.
     *
     * @param tag The name of the field
     * @param val The value of the field
     * @return the same instance of {@link Content} class.
     */
    public Content add(String tag, String val) {
        cv.put(tag, val);
        return this;
    }

    /**
     * Add a boolean value on ContentValues.
     *
     * @param tag The name of the field
     * @param val The value of the field
     * @return the same instance of {@link Content} class.
     */
    public Content add(String tag, boolean val) {
        cv.put(tag, val);
        return this;
    }

    /**
     * Add a float value on ContentValues.
     *
     * @param tag The name of the field
     * @param val The value of the field
     * @return the same instance of {@link Content} class.
     */
    public Content add(String tag, float val) {
        cv.put(tag, val);
        return this;
    }

    /**
     * Add a long value on ContentValues.
     *
     * @param tag The name of the field
     * @param val The value of the field
     * @return the same instance of {@link Content} class.
     */
    public Content add(String tag, long val) {
        cv.put(tag, val);
        return this;
    }

    /**
     * @return the instance of {@link ContentValues} class.
     */
    public ContentValues get() {
        return cv;
    }
}
