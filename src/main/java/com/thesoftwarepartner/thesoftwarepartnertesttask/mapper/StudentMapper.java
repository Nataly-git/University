package com.thesoftwarepartner.thesoftwarepartnertesttask.mapper;

import com.thesoftwarepartner.thesoftwarepartnertesttask.dto.StudentDto;
import com.thesoftwarepartner.thesoftwarepartnertesttask.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface StudentMapper {

    Student toEntity(StudentDto dto);

    StudentDto toDto(Student student);
}
