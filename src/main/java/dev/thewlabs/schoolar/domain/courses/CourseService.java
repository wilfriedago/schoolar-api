package dev.thewlabs.schoolar.domain.courses;

import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import dev.thewlabs.schoolar.core.interfaces.CrudService;
import dev.thewlabs.schoolar.domain.courses.dtos.CourseDetailsDTO;
import dev.thewlabs.schoolar.domain.courses.dtos.CreateCourseDTO;
import dev.thewlabs.schoolar.domain.courses.dtos.UpdateCourseDTO;
import dev.thewlabs.schoolar.domain.groups.Group;
import dev.thewlabs.schoolar.domain.groups.GroupRepository;
import dev.thewlabs.schoolar.domain.subjects.Subject;
import dev.thewlabs.schoolar.domain.subjects.SubjectRepository;
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
public class CourseService implements CrudService<CourseDetailsDTO, CreateCourseDTO, UpdateCourseDTO> {
    private final CourseMapper mapper;
    private final CourseRepository repository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public CourseService(
            CourseMapper mapper,
            CourseRepository repository,
            GroupRepository groupRepository,
            SubjectRepository subjectRepository
    ) {
        this.mapper = mapper;
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Page<CourseDetailsDTO> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Course> courses = repository.findAll(pageable);

        return courses.map(mapper::entityToDto);
    }

    @Override
    public CourseDetailsDTO findById(@NotNull UUID id) throws NotFoundException {
        Course course = repository.findCourseById(id);

        return mapper.entityToDto(course);
    }

    @Override
    public CourseDetailsDTO create(@NotNull CreateCourseDTO dto) throws NotFoundException {
        Course course = mapper.dtoToEntity(dto);
        Group group = groupRepository.findGroupById(UUID.fromString(dto.groupId()));
        Subject subject = subjectRepository.findSubjectById(UUID.fromString(dto.subjectId()));

        course.setGroup(group);
        course.setSubject(subject);

        repository.save(course);

        return mapper.entityToDto(course);
    }

    @Override
    public CourseDetailsDTO update(@NotNull UUID id, @NotNull UpdateCourseDTO dto) throws NotFoundException {
        Course course = this.repository.findCourseById(id);

        mapper.updateEntityFromDto(dto, course);

        repository.save(course);

        return mapper.entityToDto(course);
    }

    @Override
    public void delete(@NotNull UUID id) throws NotFoundException {
        Course course = repository.findCourseById(id);

        repository.delete(course);
    }

    public Page<CourseDetailsDTO> findAllByGroup(Group group, Pageable pageable) {
        return repository.findAllByGroup(group, pageable).map(mapper::entityToDto);
    }

    public List<Course> findAllById(List<UUID> ids) {
        return repository.findAllById(ids);
    }
}
