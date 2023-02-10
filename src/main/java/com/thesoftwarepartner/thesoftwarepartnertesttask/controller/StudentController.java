package com.thesoftwarepartner.thesoftwarepartnertesttask.controller;

import com.thesoftwarepartner.thesoftwarepartnertesttask.dto.StudentDto;
import com.thesoftwarepartner.thesoftwarepartnertesttask.entity.Student;
import com.thesoftwarepartner.thesoftwarepartnertesttask.mapper.StudentMapper;
import com.thesoftwarepartner.thesoftwarepartnertesttask.service.StudentService;
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
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public Page<StudentDto> getAll(Pageable pageable) {
        return studentService
                .getAll(pageable)
                .map(studentMapper::toDto);
    }

    @PostMapping
    public StudentDto create(@RequestBody @Valid StudentDto studentDto) {
        Student studentToSave = studentMapper.toEntity(studentDto);
        return studentMapper.toDto(studentService.save(studentToSave));
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable("id") String id, @RequestBody @Valid StudentDto studentDto) {
        Student studentToUpdate = studentMapper.toEntity(studentDto);
        return studentMapper.toDto(studentService.update(id, studentToUpdate));
    }

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable String id) {
        return studentMapper.toDto(studentService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        studentService.delete(id);
    }
}
