package dev.thewlabs.schoolar.domain.sessions;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.classrooms.Classroom;
import dev.thewlabs.schoolar.domain.classrooms.ClassroomRepository;
import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.courses.CourseRepository;
import dev.thewlabs.schoolar.domain.sessions.dtos.CreateSessionDto;
import dev.thewlabs.schoolar.domain.sessions.dtos.SessionDetailsDto;
import dev.thewlabs.schoolar.domain.sessions.dtos.UpdateSessionDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SessionMapper extends Mapper<Session, CreateSessionDto, UpdateSessionDto, SessionDetailsDto> {
    private final CourseRepository courseRepository;
    private final ClassroomRepository classroomRepository;

    public SessionMapper(CourseRepository courseRepository, ClassroomRepository classroomRepository) {
        super();
        this.courseRepository = courseRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Session dtoToEntity(CreateSessionDto dto) {
        Session session = modelMapper.map(dto, Session.class);

        // Load the course and classroom entities based on courseId and classroomId
        Course course = courseRepository.findCourseById(UUID.fromString(dto.courseId()));
        Classroom classroom = classroomRepository.findClassroomById(UUID.fromString(dto.classroomId()));

        // Set the loaded course and classroom entities to the session
        session.setCourse(course);
        session.setClassroom(classroom);

        return session;
    }

    @Override
    public SessionDetailsDto entityToDto(Session entity) {
        return modelMapper.map(entity, SessionDetailsDto.class);
    }

    @Override
    public void updateEntityFromDto(UpdateSessionDto dto, Session entity) {
        modelMapper.map(dto, entity);
    }
}
