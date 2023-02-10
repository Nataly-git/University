package com.thesoftwarepartner.thesoftwarepartnertesttask.service;

import com.thesoftwarepartner.thesoftwarepartnertesttask.entity.Course;

import java.util.List;

public interface CourseService extends BaseCrudService<Course> {
    List<Course> getAllById(List<String> ids);
}
