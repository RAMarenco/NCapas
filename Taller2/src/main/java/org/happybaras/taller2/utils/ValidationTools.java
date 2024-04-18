package org.happybaras.taller2.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidationTools {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z]{4,16}$");

    public Boolean isValidUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public Boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public Boolean isValidIdentifier(String identifier) {
        return isValidEmail(identifier) || isValidUsername(identifier);
    }
}
