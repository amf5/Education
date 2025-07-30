package com.com.Courses.constraintAnnotation;

import com.com.Courses.annotation.Code;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CodeValidator implements ConstraintValidator<Code, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value != null && value.matches("\\d{4}");
	}

}
