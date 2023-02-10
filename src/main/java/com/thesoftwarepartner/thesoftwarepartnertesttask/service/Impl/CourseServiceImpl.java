package com.thesoftwarepartner.thesoftwarepartnertesttask.service.Impl;

import com.thesoftwarepartner.thesoftwarepartnertesttask.entity.Course;
import com.thesoftwarepartner.thesoftwarepartnertesttask.errorHandler.exceptions.ResourceNotFoundException;
import com.thesoftwarepartner.thesoftwarepartnertesttask.repository.CourseRepository;
import com.thesoftwarepartner.thesoftwarepartnertesttask.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Course> getAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Course getById(String id) {
        return courseRepository.findById(id)
                               .orElseThrow(() -> new ResourceNotFoundException(
                                       String.format("Course with id [%s] doesn't exist", id)));
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(String id, Course course) {
        if (!existById(id)) {
            throw new ResourceNotFoundException(String.format("Course with id [%s] doesn't exist", id));
        }
        course.setId(id);
        return courseRepository.save(course);
    }

    @Override
    public void delete(String id) {
        courseRepository.deleteById(id);
    }

    public List<Course> getAllById(List<String> ids) {
        return courseRepository.findAllById(ids);
    }

    private boolean existById(String id) {
        return courseRepository.existsById(id);
    }
}
