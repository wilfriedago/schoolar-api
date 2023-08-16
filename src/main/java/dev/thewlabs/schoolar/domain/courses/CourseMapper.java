package dev.thewlabs.schoolar.domain.courses;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.courses.dtos.CourseDetailsDTO;
import dev.thewlabs.schoolar.domain.courses.dtos.CreateCourseDTO;
import dev.thewlabs.schoolar.domain.courses.dtos.UpdateCourseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper extends Mapper<Course, CreateCourseDTO, UpdateCourseDTO, CourseDetailsDTO> {
    @Override
    public Course dtoToEntity(CreateCourseDTO dto) {
        return this.modelMapper.map(dto, Course.class);
    }

    @Override
    public CourseDetailsDTO entityToDto(Course entity) {
        return this.modelMapper.map(entity, CourseDetailsDTO.class);
    }

    @Override
    public void updateEntityFromDto(UpdateCourseDTO dto, Course entity) {
        this.modelMapper.map(dto, entity);
    }
}
