package com.example.app.objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    private static final Pattern patternemail = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        Matcher matcher = patternemail.matcher(email);
        return matcher.matches();
    }

    private static final String SURNAME_REGEX = "^[A-Za-z]\\w{2,29}$";
    private static final Pattern patternsurname = Pattern.compile(SURNAME_REGEX);

    public static boolean isValidSurName(String name) {
        Matcher matcher = patternsurname.matcher(name);
        return matcher.matches();
    }

    private static final String PHONE_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3} ?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3} ?)(\\d{2} ?){2}\\d{2}$";
    private static final Pattern patternphone = Pattern.compile(PHONE_REGEX);

    public static boolean isValidPhone(String phone) {
        Matcher matcher = patternphone.matcher(phone);
        return matcher.matches();
    }

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*)(?=.*[@$!%*?&])[A-Za-z@$!%*?&]{8,}$";
    private static final Pattern patternpassword = Pattern.compile(PASSWORD_REGEX);

    public static boolean isValidPassword(String password) {
        Matcher matcher = patternpassword.matcher(password);
        return matcher.matches();
    }
}
