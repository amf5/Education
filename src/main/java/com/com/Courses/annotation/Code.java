package com.com.Courses.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.com.Courses.constraintAnnotation.CodeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CodeValidator.class)
@Documented
public @interface Code {
	 String message() default "in valid code , code must be 4 number";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}
