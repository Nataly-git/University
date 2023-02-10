package com.thesoftwarepartner.thesoftwarepartnertesttask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDto {

    private String id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String specialisation;

    @NotBlank
    @Size(max = 100)
    private String facultyName;
}
