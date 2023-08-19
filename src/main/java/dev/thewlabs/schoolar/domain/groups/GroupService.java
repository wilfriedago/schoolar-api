package dev.thewlabs.schoolar.domain.groups;

import dev.thewlabs.schoolar.core.interfaces.CrudService;
import dev.thewlabs.schoolar.core.records.BulkActionResultDto;
import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.courses.CourseService;
import dev.thewlabs.schoolar.domain.courses.dtos.CourseDetailsDto;
import dev.thewlabs.schoolar.domain.groups.dtos.CreateGroupDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.GroupDetailsDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.UpdateGroupDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.courses.AddCoursesDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.courses.RemoveCoursesDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.students.AddStudentsDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.students.RemoveStudentsDTO;
import dev.thewlabs.schoolar.domain.students.Student;
import dev.thewlabs.schoolar.domain.students.StudentService;
import dev.thewlabs.schoolar.domain.students.dtos.StudentDetailsDTO;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import dev.thewlabs.schoolar.shared.exceptions.UnprocessableEntityException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupService implements CrudService<GroupDetailsDTO, CreateGroupDTO, UpdateGroupDTO> {
    private final GroupMapper mapper;
    private final GroupRepository repository;
    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public GroupService(
            GroupMapper mapper,
            GroupRepository repository,
            CourseService courseService,
            StudentService studentService
    ) {
        this.mapper = mapper;
        this.repository = repository;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    public Page<GroupDetailsDTO> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    public GroupDetailsDTO findById(@NotNull UUID id) throws NotFoundException {
        Group group = repository.findGroupById(id);

        return mapper.entityToDto(group);
    }

    public GroupDetailsDTO create(@NotNull CreateGroupDTO dto) throws UnprocessableEntityException {
        if (this.repository.existsByName(dto.name()))
            throw new UnprocessableEntityException("This name " + dto.name() + " is already used by another group");

        Group group = mapper.dtoToEntity(dto);

        repository.save(group);

        return mapper.entityToDto(group);
    }

    public GroupDetailsDTO update(@NotNull UUID id, @NotNull UpdateGroupDTO dto) throws NotFoundException {
        Group group = repository.findGroupById(id);

        mapper.updateEntityFromDto(dto, group);

        repository.save(group);

        return mapper.entityToDto(group);
    }

    public void delete(@NotNull UUID id) throws NotFoundException {
        Group group = repository.findGroupById(id);

        repository.delete(group);
    }

    public Page<StudentDetailsDTO> findStudents(@NotNull UUID id, int page, int size, Sort sort) throws NotFoundException {
        Group group = repository.findGroupById(id);
        Pageable pageable = PageRequest.of(page, size, sort);

        return studentService.findAllByGroup(group, pageable);
    }

    public BulkActionResultDto addStudents(@NotNull UUID id, @NotNull AddStudentsDTO dto) throws NotFoundException {
        Group group = repository.findGroupById(id);

        List<Student> newStudents = studentService.findAllById(dto.studentIds());

        group.getStudents().addAll(newStudents);

        repository.save(group);

        return BulkActionResultDto.getResult(dto.studentIds().size(), newStudents.size());
    }

    public BulkActionResultDto removeStudents(@NotNull UUID id, @NotNull RemoveStudentsDTO dto) throws NotFoundException {
        Group group = repository.findGroupById(id);

        List<Student> studentsToRemove = studentService.findAllById(dto.studentIds());

        group.getStudents().removeAll(studentsToRemove);

        repository.save(group);

        return BulkActionResultDto.getResult(dto.studentIds().size(), studentsToRemove.size());
    }

    public Page<CourseDetailsDto> findCourses(@NotNull UUID id, int page, int size, Sort sort) {
        Group group = repository.findGroupById(id);
        Pageable pageable = PageRequest.of(page, size, sort);

        return courseService.findAllByGroup(group, pageable);
    }

    public BulkActionResultDto addCourses(@NotNull UUID id, @NotNull AddCoursesDTO dto) throws NotFoundException {
        Group group = repository.findGroupById(id);

        List<Course> newCourses = courseService.findAllById(dto.courseIds());

        group.getCourses().addAll(newCourses);

        repository.save(group);

        return BulkActionResultDto.getResult(dto.courseIds().size(), newCourses.size());
    }

    public BulkActionResultDto removeCourses(@NotNull UUID id, @NotNull RemoveCoursesDTO dto) throws NotFoundException {
        Group group = repository.findGroupById(id);

        List<Course> coursesToRemove = courseService.findAllById(dto.courseIds());

        group.getCourses().removeAll(coursesToRemove);

        repository.save(group);

        return BulkActionResultDto.getResult(dto.courseIds().size(), coursesToRemove.size());
    }
}
