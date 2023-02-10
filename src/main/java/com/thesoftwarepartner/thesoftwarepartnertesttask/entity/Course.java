package com.thesoftwarepartner.thesoftwarepartnertesttask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Course extends BaseClass {

    private String name;
    private String specialisation;
    private String facultyName;

    @ToString.Exclude
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
}
