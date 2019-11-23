package com.sda.tasklist.validator;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final String regex = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+";


    public static boolean isValid(String email) {
        return email.matches(regex);
    }


}
