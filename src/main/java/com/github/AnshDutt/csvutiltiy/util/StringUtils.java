package com.github.AnshDutt.csvutiltiy.util;

import java.util.List;

public class StringUtils {
    public static String join(List<String> property){
        String delimiter = ";";
        return String.join(delimiter,property).replace(",","");
    }
}
