package dev.thewlabs.schoolar.domain.courses;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.courses.dtos.CourseDetailsDto;
import dev.thewlabs.schoolar.domain.courses.dtos.CreateCourseDto;
import dev.thewlabs.schoolar.domain.courses.dtos.UpdateCourseDto;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper extends Mapper<Course, CreateCourseDto, UpdateCourseDto, CourseDetailsDto> {
    @Override
    public Course dtoToEntity(CreateCourseDto dto) {
        return this.modelMapper.map(dto, Course.class);
    }

    @Override
    public CourseDetailsDto entityToDto(Course entity) {
        return this.modelMapper.map(entity, CourseDetailsDto.class);
    }

    @Override
    public void updateEntityFromDto(UpdateCourseDto dto, Course entity) {
        this.modelMapper.map(dto, entity);
    }
}
