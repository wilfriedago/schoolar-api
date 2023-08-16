package dev.thewlabs.schoolar.domain.teachers;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.common.iam.authentication.services.AuthService;
import dev.thewlabs.schoolar.core.interfaces.CrudService;
import dev.thewlabs.schoolar.domain.teachers.dtos.CreateTeacherDTO;
import dev.thewlabs.schoolar.domain.teachers.dtos.TeacherDetailsDTO;
import dev.thewlabs.schoolar.domain.teachers.dtos.UpdateTeacherDTO;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeacherService implements CrudService<TeacherDetailsDTO, CreateTeacherDTO, UpdateTeacherDTO> {
    private final TeacherMapper mapper;
    private final TeacherRepository repository;
    private final AuthService authService;

    public TeacherService(TeacherMapper mapper, TeacherRepository repository, AuthService authService) {
        this.mapper = mapper;
        this.repository = repository;
        this.authService = authService;
    }

    @Override
    public Page<TeacherDetailsDTO> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    public TeacherDetailsDTO findById(@NotNull UUID id) throws NotFoundException {
        Teacher teacher = repository.findTeacherById(id);

        return mapper.entityToDto(teacher);
    }

    @Override
    public TeacherDetailsDTO create(@NotNull CreateTeacherDTO dto) {
        Account account = authService.createAccount(dto.email());

        Teacher teacher = mapper.dtoToEntity(dto);

        teacher.setAccount(account);

        repository.save(teacher);

        return mapper.entityToDto(teacher);
    }

    @Override
    public TeacherDetailsDTO update(@NotNull UUID id, @NotNull UpdateTeacherDTO dto) throws NotFoundException {
        Teacher teacher = repository.findTeacherById(id);

        mapper.updateEntityFromDto(dto, teacher);

        repository.save(teacher);

        return mapper.entityToDto(teacher);
    }

    @Override
    public void delete(@NotNull UUID id) throws NotFoundException {
        Teacher teacher = repository.findTeacherById(id);

        repository.delete(teacher);
    }
}
