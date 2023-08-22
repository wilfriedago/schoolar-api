package dev.thewlabs.schoolar.domain.students;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.students.dtos.CreateStudentDTO;
import dev.thewlabs.schoolar.domain.students.dtos.StudentDetailsDTO;
import dev.thewlabs.schoolar.domain.students.dtos.UpdateStudentDTO;
import dev.thewlabs.schoolar.domain.users.dtos.UserDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper extends Mapper<Student, CreateStudentDTO, UpdateStudentDTO, StudentDetailsDTO> {
    @Override
    public Student dtoToEntity(CreateStudentDTO dto) {
        return modelMapper.map(dto, Student.class);
    }

    @Override
    public StudentDetailsDTO entityToDto(Student entity) {
        return modelMapper.map(entity, StudentDetailsDTO.class);
    }

    public UserDetailsDto entityToUserDto(Student entity) {
        return modelMapper.map(entity, UserDetailsDto.class);
    }

    @Override
    public void updateEntityFromDto(UpdateStudentDTO dto, Student entity) {
        modelMapper.map(dto, entity);
    }
}
