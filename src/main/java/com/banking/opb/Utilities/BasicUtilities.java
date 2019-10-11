package com.banking.opb.Utilities;

public class BasicUtilities {
    public static boolean isEmptyOrNullString(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isEmptyOrNullCharaterArray(char[] chars) {
        return chars == null || chars.length == 0;
    }
}
