package com.vansuita.sqliteparser;

import java.util.Date;


/**
 * Created by jrvansuita on 05/09/15.
 * This class handle the values reading from {@link android.database.Cursor}.
 */

public class Cursor {

    private android.database.Cursor cursor;

    public Cursor(android.database.Cursor cursor) {
        this.cursor = cursor;
    }

    /**
     * @return false if the cursor is null
     */
    public boolean binded() {
        return cursor != null;
    }

    /**
     * @return an int representing the index of the column
     */
    private int ind(String column) {
        return cursor.getColumnIndex(column);
    }

    /**
     * @return a String value of this index position
     */
    public String str(int index) {
        String result = cursor.getString(index);
        return result == null ? "" : result;
    }

    /**
     * @return Get the String value of the given column name
     */
    public String str(String column) {
        return str(ind(column));
    }

    /**
     * @return Get the float value of the given column name
     */
    public float flo(String column) {
        return cursor.getFloat(ind(column));
    }

    /**
     * @return Get the int value of the given column name
     */
    public int num(String column) {
        return cursor.getInt(ind(column));
    }

    /**
     * @return Get the long value of the given column name
     */
    public long lon(String column) {
        return cursor.getLong(ind(column));
    }


    /**
     * @return Get the long value of the given column index
     */
    public long lon(int index) {
        return cursor.getLong(index);
    }

    /**
     * @return Get the boolean value of the given column name
     */
    public boolean bool(String column) {
        return num(column) != 0;
    }

    /**
     * @return Get the Date value of the given column name
     */
    public Date date(String column) {
        return toDate(lon(column));
    }

    /**
     * @return Get the Date value of the given column index
     */
    public Date date(int index) {
        return toDate(lon(index));
    }


    private Date toDate(long time) {
        return (time > 0) ? new Date(time) : null;
    }


}
