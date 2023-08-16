package dev.thewlabs.schoolar.domain.sessions;

import dev.thewlabs.schoolar.core.interfaces.CrudService;
import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.courses.CourseRepository;
import dev.thewlabs.schoolar.domain.groups.GroupRepository;
import dev.thewlabs.schoolar.domain.sessions.dtos.CreateSessionDTO;
import dev.thewlabs.schoolar.domain.sessions.dtos.SessionDetailsDTO;
import dev.thewlabs.schoolar.domain.sessions.dtos.UpdateSessionDTO;
import dev.thewlabs.schoolar.domain.students.Student;
import dev.thewlabs.schoolar.infra.constants.ApplicationConstants;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import dev.thewlabs.schoolar.shared.exceptions.UnprocessableEntityException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class SessionService implements CrudService<SessionDetailsDTO, CreateSessionDTO, UpdateSessionDTO> {
    private final SessionMapper mapper;
    private final SessionRepository repository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public SessionService(
            SessionMapper mapper,
            SessionRepository repository,
            GroupRepository groupRepository,
            CourseRepository courseRepository
    ) {
        this.mapper = mapper;
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<SessionDetailsDTO> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    public SessionDetailsDTO findById(@NotNull UUID id) throws NotFoundException {
        Session session = repository.findSessionById(id);

        return mapper.entityToDto(session);
    }

    @Override
    public SessionDetailsDTO create(@NotNull CreateSessionDTO dto) throws UnprocessableEntityException {
        ZonedDateTime startTime = dto.startTime();
        ZonedDateTime endTime = dto.endTime();
        long sessionDuration = ChronoUnit.MINUTES.between(startTime, endTime);

        if (endTime.isBefore(startTime))
            throw new UnprocessableEntityException("Session end time can be before start time.");
        if (endTime.equals(startTime))
            throw new UnprocessableEntityException("Session start time can be the same as session end time.");
        if (sessionDuration >= ApplicationConstants.EVENT_MIN_DURATION)
            throw new UnprocessableEntityException("Session duration must be at least " + ApplicationConstants.EVENT_MIN_DURATION + " minutes long.");

        Course course = courseRepository.findCourseById(UUID.fromString(dto.courseId()));

        // course.setHoursDone(course.getHoursDone() += sessionDuration);

        List<Student> attendees = course.getGroup().getStudents();

        Session session = mapper.dtoToEntity(dto);

        if (session.getClassroom().getCapacity() < attendees.size())
            throw new UnprocessableEntityException("The classroom chosen cannot host all the attendees for this session");


        session.setAttendees(attendees);

        repository.save(session);

        return mapper.entityToDto(session);
    }

    @Override
    public SessionDetailsDTO update(@NotNull UUID id, @NotNull UpdateSessionDTO dto) throws NotFoundException {
        Session session = repository.findSessionById(id);

        mapper.updateEntityFromDto(dto, session);

        repository.save(session);

        return mapper.entityToDto(session);
    }

    @Override
    public void delete(@NotNull UUID id) throws NotFoundException {
        Session session = repository.findSessionById(id);

        repository.delete(session);
    }
}
