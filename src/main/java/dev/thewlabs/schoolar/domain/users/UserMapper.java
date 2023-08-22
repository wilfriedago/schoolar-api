package dev.thewlabs.schoolar.domain.users;

import dev.thewlabs.schoolar.domain.students.dtos.StudentDetailsDTO;
import dev.thewlabs.schoolar.domain.teachers.dtos.TeacherDetailsDto;
import dev.thewlabs.schoolar.domain.users.dtos.UserDetailsDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    protected final ModelMapper modelMapper;

    protected UserMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        this.modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.modelMapper.getConfiguration().setMethodAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public UserDetailsDto entityToDto(User entity) {
        return modelMapper.map(entity, UserDetailsDto.class);
    }

    public StudentDetailsDTO entityToStudentDto(User entity) {
        return modelMapper.map(entity, StudentDetailsDTO.class);
    }

    public TeacherDetailsDto entityToTeacherDto (User entity) {
        return modelMapper.map(entity, TeacherDetailsDto.class);
    }

    public UserDetailsDto entityToAdminDto (User entity) {
        return modelMapper.map(entity, UserDetailsDto.class);
    }
}
