package com.github.AnshDutt.csvutiltiy.util;



import com.github.AnshDutt.csvutiltiy.model.Atms;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HeaderUtils {
    public static String atmHeaders() {
        List<String> atmHeaders = new ArrayList<>();
        Field[] fields = Atms.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (!(fields[i].getType().isMemberClass()))
                atmHeaders.add(fields[i].getName());
        }
        Field[] field2 = Atms.Location.class.getDeclaredFields();
        for (int i = 0; i < field2.length; i++) {
            if (!(field2[i].getType().isMemberClass()))
                atmHeaders.add(field2[i].getName());
        }
        Field[] field3 = Atms.PostalAddress.class.getDeclaredFields();
        for (int i = 0; i < field3.length; i++) {
            if (!(field3[i].getType().isMemberClass()))
                atmHeaders.add(field3[i].getName());
        }

        Field[] field4 = Atms.GeographicCo_ordinates.class.getDeclaredFields();
        for (int i = 0; i < field4.length; i++) {
            if (!(field4[i].getType().isMemberClass()))
                atmHeaders.add(field4[i].getName());
        }
        return String.join(",",atmHeaders) + "\n";
    }
    public static String branchesHeaders() {
        List<String> branchesHeaders = new ArrayList<>();
        /**
         * ADD CODE HERE
         */
        return String.join(",",branchesHeaders) + "\n";
    }

}
