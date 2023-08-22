package dev.thewlabs.schoolar.domain.teachers;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.teachers.dtos.CreateTeacherDTO;
import dev.thewlabs.schoolar.domain.teachers.dtos.TeacherDetailsDto;
import dev.thewlabs.schoolar.domain.teachers.dtos.UpdateTeacherDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper extends Mapper<Teacher, CreateTeacherDTO, UpdateTeacherDTO, TeacherDetailsDto> {
    @Override
    public Teacher dtoToEntity(CreateTeacherDTO dto) {
        return modelMapper.map(dto, Teacher.class);
    }

    @Override
    public TeacherDetailsDto entityToDto(Teacher entity) {
        return modelMapper.map(entity, TeacherDetailsDto.class);
    }

    @Override
    public void updateEntityFromDto(UpdateTeacherDTO dto, Teacher entity) {
        modelMapper.map(dto, entity);
    }
}
