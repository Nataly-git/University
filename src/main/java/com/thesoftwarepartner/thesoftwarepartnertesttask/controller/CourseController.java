package com.thesoftwarepartner.thesoftwarepartnertesttask.controller;

import com.thesoftwarepartner.thesoftwarepartnertesttask.dto.CourseDto;
import com.thesoftwarepartner.thesoftwarepartnertesttask.entity.Course;
import com.thesoftwarepartner.thesoftwarepartnertesttask.mapper.CourseMapper;
import com.thesoftwarepartner.thesoftwarepartnertesttask.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @GetMapping
    public Page<CourseDto> getAll(Pageable pageable) {
        return courseService
                .getAll(pageable)
                .map(courseMapper::toDto);
    }

    @PostMapping
    public CourseDto create(@RequestBody @Valid CourseDto courseDto) {
        Course courseToSave = courseMapper.toEntity(courseDto);
        return courseMapper.toDto(courseService.save(courseToSave));
    }

    @PutMapping("/{id}")
    public CourseDto update(@PathVariable("id") String id, @RequestBody @Valid CourseDto courseDto) {
        Course courseToUpdate = courseMapper.toEntity(courseDto);
        return courseMapper.toDto(courseService.update(id, courseToUpdate));
    }

    @GetMapping("/{id}")
    public CourseDto getById(@PathVariable String id) {
        return courseMapper.toDto(courseService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        courseService.delete(id);
    }


}
