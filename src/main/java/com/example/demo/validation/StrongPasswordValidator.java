package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:\";'<>?,./]).{8,}$";

    @Override
    public void initialize(StrongPassword constraintAnnotation) {
        // no initialization needed
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }
}
