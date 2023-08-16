package dev.thewlabs.schoolar.domain.classrooms;

import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import dev.thewlabs.schoolar.shared.exceptions.UnprocessableEntityException;
import dev.thewlabs.schoolar.core.interfaces.CrudService;
import dev.thewlabs.schoolar.domain.classrooms.dtos.ClassroomDetailsDTO;
import dev.thewlabs.schoolar.domain.classrooms.dtos.CreateClassroomDTO;
import dev.thewlabs.schoolar.domain.classrooms.dtos.UpdateClassroomDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClassroomService implements CrudService<ClassroomDetailsDTO, CreateClassroomDTO, UpdateClassroomDTO> {
    private final ClassroomMapper mapper;
    private final ClassroomRepository repository;

    @Autowired
    public ClassroomService(ClassroomMapper mapper, ClassroomRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<ClassroomDetailsDTO> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    public ClassroomDetailsDTO findById(@NotNull UUID id) throws NotFoundException {
        Classroom classroom = repository.findByClassroomId(id);

        return mapper.entityToDto(classroom);
    }

    public ClassroomDetailsDTO create(@NotNull CreateClassroomDTO dto) {
        if (repository.existsByName(dto.name()))
            throw new UnprocessableEntityException("Classroom with name " + dto.name() + " already exists.");

        Classroom classroom = mapper.dtoToEntity(dto);

        repository.save(classroom);

        return mapper.entityToDto(classroom);
    }

    public ClassroomDetailsDTO update(@NotNull UUID id, @NotNull UpdateClassroomDTO dto) throws NotFoundException {
        Classroom classroom = repository.findByClassroomId(id);

        mapper.updateEntityFromDto(dto, classroom);

        repository.save(classroom);

        return mapper.entityToDto(classroom);
    }

    public void delete(@NotNull UUID id) throws NotFoundException {
        Classroom classroom = repository.findByClassroomId(id);

        repository.delete(classroom);
    }
}
