package dev.thewlabs.schoolar.domain.classrooms;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.classrooms.dtos.ClassroomDetailsDTO;
import dev.thewlabs.schoolar.domain.classrooms.dtos.CreateClassroomDTO;
import dev.thewlabs.schoolar.domain.classrooms.dtos.UpdateClassroomDTO;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper extends Mapper<Classroom, CreateClassroomDTO, UpdateClassroomDTO, ClassroomDetailsDTO> {
    @Override
    public Classroom dtoToEntity(CreateClassroomDTO dto) {
        return modelMapper.map(dto, Classroom.class);
    }

    @Override
    public ClassroomDetailsDTO entityToDto(Classroom entity) {
        return modelMapper.map(entity, ClassroomDetailsDTO.class);
    }

    @Override
    public void updateEntityFromDto(UpdateClassroomDTO dto, Classroom entity) {
        modelMapper.map(dto, entity);
    }
}
