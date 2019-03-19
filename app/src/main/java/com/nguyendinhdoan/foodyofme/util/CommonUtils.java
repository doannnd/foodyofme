package com.nguyendinhdoan.foodyofme.util;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("");
    private static final int PASSWORD_MIN_LENGTH = 6;

    public static boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validatePassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= PASSWORD_MIN_LENGTH;
    }
}
