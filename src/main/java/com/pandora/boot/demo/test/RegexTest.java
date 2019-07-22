package com.pandora.boot.demo.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        String PHONE_REGEX_PATTERN = "^1[0-9]{10}$";
        String num = "13454123243";

        Pattern pattern = Pattern.compile(PHONE_REGEX_PATTERN);

        Matcher matcher = pattern.matcher(num);

        //System.out.println(matcher.matches());
        System.out.println(num.matches(PHONE_REGEX_PATTERN));
    }
}
