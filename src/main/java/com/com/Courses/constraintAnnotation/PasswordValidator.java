package com.com.Courses.constraintAnnotation;

import com.com.Courses.annotation.Password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
	@Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) return false;
      
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d\\W]).{8,}$");
    }
}
