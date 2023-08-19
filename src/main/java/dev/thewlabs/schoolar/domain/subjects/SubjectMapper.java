package dev.thewlabs.schoolar.domain.subjects;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.subjects.dtos.CreateSubjectDto;
import dev.thewlabs.schoolar.domain.subjects.dtos.SubjectDetailsDto;
import dev.thewlabs.schoolar.domain.subjects.dtos.UpdateSubjectDto;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper extends Mapper<Subject, CreateSubjectDto, UpdateSubjectDto, SubjectDetailsDto> {
    @Override
    public Subject dtoToEntity(CreateSubjectDto dto) {
        return modelMapper.map(dto, Subject.class);
    }

    @Override
    public SubjectDetailsDto entityToDto(Subject entity) {
        return modelMapper.map(entity, SubjectDetailsDto.class);
    }

    @Override
    public void updateEntityFromDto(UpdateSubjectDto dto, Subject entity) {
        modelMapper.map(dto, entity);
    }
}
