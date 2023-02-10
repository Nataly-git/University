package com.thesoftwarepartner.thesoftwarepartnertesttask.service.Impl;

import com.thesoftwarepartner.thesoftwarepartnertesttask.entity.Course;
import com.thesoftwarepartner.thesoftwarepartnertesttask.entity.Student;
import com.thesoftwarepartner.thesoftwarepartnertesttask.errorHandler.exceptions.ResourceNotFoundException;
import com.thesoftwarepartner.thesoftwarepartnertesttask.repository.StudentRepository;
import com.thesoftwarepartner.thesoftwarepartnertesttask.service.CourseService;
import com.thesoftwarepartner.thesoftwarepartnertesttask.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Override
    public Page<Student> getAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getById(String id) {
        return studentRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        String.format("Student with id [%s] doesn't exist", id)));
    }

    @Override
    public Student save(Student student) {
        if(student.getCourses() != null) {
            bindCoursesToDb(student);
        }
        return studentRepository.save(student);
    }

    @Override
    public Student update(String id, Student student) {
        if (!existById(id)) {
            throw new ResourceNotFoundException(String.format("Student with id [%s] doesn't exist", id));
        }
        student.setId(id);
        return save(student);
    }

    @Override
    public void delete(String id) {
        studentRepository.deleteById(id);
    }

    private void bindCoursesToDb(Student student) {
        student.setCourses(courseService.getAllById(mapCoursesToCourseIds(student)));
    }

    private List<String> mapCoursesToCourseIds(Student student) {
        return student.getCourses().stream()
                      .map(Course::getId)
                      .collect(Collectors.toList());
    }

    private boolean existById(String id) {
        return studentRepository.existsById(id);
    }
}
