package com.banking.opb.utilities;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
}
