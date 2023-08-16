package dev.thewlabs.schoolar.domain.subjects;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.subjects.dtos.CreateSubjectDTO;
import dev.thewlabs.schoolar.domain.subjects.dtos.SubjectDetailsDTO;
import dev.thewlabs.schoolar.domain.subjects.dtos.UpdateSubjectDTO;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper extends Mapper<Subject, CreateSubjectDTO, UpdateSubjectDTO, SubjectDetailsDTO> {
    @Override
    public Subject dtoToEntity(CreateSubjectDTO dto) {
        return modelMapper.map(dto, Subject.class);
    }

    @Override
    public SubjectDetailsDTO entityToDto(Subject entity) {
        return modelMapper.map(entity, SubjectDetailsDTO.class);
    }

    @Override
    public void updateEntityFromDto(UpdateSubjectDTO dto, Subject entity) {
        modelMapper.map(dto, entity);
    }
}
