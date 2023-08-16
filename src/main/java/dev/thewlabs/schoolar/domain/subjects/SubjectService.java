package dev.thewlabs.schoolar.domain.subjects;

import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import dev.thewlabs.schoolar.shared.exceptions.UnprocessableEntityException;
import dev.thewlabs.schoolar.core.interfaces.CrudService;
import dev.thewlabs.schoolar.domain.subjects.dtos.CreateSubjectDTO;
import dev.thewlabs.schoolar.domain.subjects.dtos.SubjectDetailsDTO;
import dev.thewlabs.schoolar.domain.subjects.dtos.UpdateSubjectDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubjectService implements CrudService<SubjectDetailsDTO, CreateSubjectDTO, UpdateSubjectDTO> {
    private final SubjectMapper mapper;
    private final SubjectRepository repository;

    @Autowired
    public SubjectService(SubjectMapper mapper, SubjectRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<SubjectDetailsDTO> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    public SubjectDetailsDTO findById(@NotNull UUID id) throws NotFoundException {
        Subject subject = repository.findSubjectById(id);

        return mapper.entityToDto(subject);
    }

    public SubjectDetailsDTO create(@NotNull CreateSubjectDTO dto) throws UnprocessableEntityException {
        if (repository.existsByName(dto.name()))
            throw new UnprocessableEntityException("Subject with name " + dto.name() + " already exists.");

        Subject subject = mapper.dtoToEntity(dto);

        repository.save(subject);

        return mapper.entityToDto(subject);
    }

    public SubjectDetailsDTO update(@NotNull UUID id, @NotNull UpdateSubjectDTO dto) throws NotFoundException {
        Subject subject = repository.findSubjectById(id);

        mapper.updateEntityFromDto(dto, subject);

        repository.save(subject);

        return mapper.entityToDto(subject);
    }

    public void delete(@NotNull UUID id) throws NotFoundException {
        Subject subject = repository.findSubjectById(id);

        repository.delete(subject);
    }
}
