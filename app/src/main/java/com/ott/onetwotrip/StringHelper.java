package com.ott.onetwotrip;

import android.support.annotation.NonNull;


public class StringHelper {
    @NonNull
    public static String foldArrayToString(Object[] array) {
        StringBuilder sb = new StringBuilder();
        for (Object s : array) {
            sb.append(s);
        }
        return sb.toString();
    }

    @NonNull
    public static String foldCharArrayToString(char[] array) {
        StringBuilder sb = new StringBuilder();
        for (Object s : array) {
            sb.append(s);
        }
        return sb.toString();
    }
}
