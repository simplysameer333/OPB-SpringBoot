package com.banking.opb.Utilities;

import java.util.Random;

public class BasicUtilities {
    public static boolean isEmptyOrNullString(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isEmptyOrNullCharaterArray(char[] chars) {
        return chars == null || chars.length == 0;
    }

    public static int getRandomNumberInRange() {
        int min = 100000;
        int max = 999999;
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
