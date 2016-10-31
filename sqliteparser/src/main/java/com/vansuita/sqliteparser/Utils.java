package com.vansuita.sqliteparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jrvansuita on 25/10/16.
 */

public class Utils {

    public static String breakArray(String divider, String... array) {
        return breakList(divider, Arrays.asList(array));
    }

    public static String breakList(String divider, List<String> list) {
        String result = "";

        for(int i = 0; i < list.size(); ++i) {
            String val = (String)list.get(i);
            if(!val.isEmpty()) {
                result = result + val + (i == list.size() - 1?"":divider);
            }
        }

        return result;
    }

    public static String breakingNulls(String divider, String... array) {
        ArrayList list = new ArrayList();

        for(int i = 0; i < array.length; ++i) {
            if(array[i] != null && !array[i].isEmpty()) {
                list.add(array[i]);
            }
        }

        return breakList(divider, list);
    }
}
