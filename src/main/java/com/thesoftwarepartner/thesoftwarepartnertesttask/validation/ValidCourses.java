package com.thesoftwarepartner.thesoftwarepartnertesttask.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCourses {
    String message() default "Chosen functions are not available to USER";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
