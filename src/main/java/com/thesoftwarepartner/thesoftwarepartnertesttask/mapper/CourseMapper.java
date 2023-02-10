package com.thesoftwarepartner.thesoftwarepartnertesttask.mapper;

import com.thesoftwarepartner.thesoftwarepartnertesttask.dto.CourseDto;
import com.thesoftwarepartner.thesoftwarepartnertesttask.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toEntity(CourseDto dto);

    CourseDto toDto(Course course);
}
