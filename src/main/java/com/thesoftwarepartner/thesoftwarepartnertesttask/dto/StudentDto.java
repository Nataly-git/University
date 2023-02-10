package com.thesoftwarepartner.thesoftwarepartnertesttask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.thesoftwarepartner.thesoftwarepartnertesttask.validation.ValidCourses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@ValidCourses
public class StudentDto {

    @JsonProperty(access = Access.READ_ONLY)
    private String id;

    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    private String lastName;

    @Past
    private LocalDate birthday;

    @Pattern(regexp = "^(\\+\\d{3}( )?)[- ]?((\\(\\d{2}\\))|\\d{2})[- ]?\\d{3}[- ]?\\d{2}[- ]?\\d{2}$",
            message = "Phone should be in format '+XXX (XX) XXX-XX-XX'")
    private String phone;

    private List<CourseDto> courses;
}
