package com.thesoftwarepartner.thesoftwarepartnertesttask.validation;

import com.thesoftwarepartner.thesoftwarepartnertesttask.dto.StudentDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseValidator implements ConstraintValidator<ValidCourses, StudentDto> {
    @Override
    public void initialize(ValidCourses constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(StudentDto studentDto, ConstraintValidatorContext context) {
        boolean isValid = studentDto.getCourses() == null || studentDto.getCourses().stream()
                                                                       .noneMatch(course -> course.getId() == null);
        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Listed courses id couldn't be null").addConstraintViolation();
        }
        return isValid;
    }
}
