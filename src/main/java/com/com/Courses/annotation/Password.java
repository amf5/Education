package com.com.Courses.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.com.Courses.constraintAnnotation.PasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {
	 String message() default "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one number or special character.";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}
