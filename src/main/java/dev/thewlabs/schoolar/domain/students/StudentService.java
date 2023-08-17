package dev.thewlabs.schoolar.domain.students;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.common.iam.authentication.services.AuthService;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import dev.thewlabs.schoolar.core.interfaces.CrudService;
import dev.thewlabs.schoolar.domain.groups.Group;
import dev.thewlabs.schoolar.domain.students.dtos.CreateStudentDTO;
import dev.thewlabs.schoolar.domain.students.dtos.StudentDetailsDTO;
import dev.thewlabs.schoolar.domain.students.dtos.UpdateStudentDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements CrudService<StudentDetailsDTO, CreateStudentDTO, UpdateStudentDTO> {
    private final StudentMapper mapper;
    private final StudentRepository repository;
    private final AuthService authService;

    @Autowired
    public StudentService(StudentMapper mapper, StudentRepository repository, AuthService authService) {
        this.mapper = mapper;
        this.repository = repository;
        this.authService = authService;
    }

    @Override
    public Page<StudentDetailsDTO> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    public StudentDetailsDTO findById(@NotNull UUID id) throws NotFoundException {
        Student student = repository.findStudentById(id);

        return mapper.entityToDto(student);
    }

    @Override
    @Transactional
    public StudentDetailsDTO create(@NotNull CreateStudentDTO dto) {
        Account account = authService.createAccount(dto.email());

        Student student = mapper.dtoToEntity(dto);

        student.setAccount(account);

        repository.save(student);

        return mapper.entityToDto(student);
    }

    @Override
    public StudentDetailsDTO update(@NotNull UUID id, @NotNull UpdateStudentDTO dto) throws NotFoundException {
        Student student = repository.findStudentById(id);

        mapper.updateEntityFromDto(dto, student);

        repository.save(student);

        return mapper.entityToDto(student);
    }

    @Override
    public void delete(@NotNull UUID id) throws NotFoundException {
        Student student = repository.findStudentById(id);

        repository.delete(student);
    }

    public Page<StudentDetailsDTO> findAllByGroup(Group group, Pageable pageable) {
        return repository.findAllByGroup(group, pageable).map(mapper::entityToDto);
    }

    public List<Student> findAllById(List<UUID> ids) {
        return repository.findAllById(ids);
    }
}
