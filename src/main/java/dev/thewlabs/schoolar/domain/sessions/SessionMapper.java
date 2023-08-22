package dev.thewlabs.schoolar.domain.sessions;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.classrooms.Classroom;
import dev.thewlabs.schoolar.domain.classrooms.ClassroomRepository;
import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.courses.CourseRepository;
import dev.thewlabs.schoolar.domain.sessions.dtos.CreateSessionDto;
import dev.thewlabs.schoolar.domain.sessions.dtos.SessionDetailsDto;
import dev.thewlabs.schoolar.domain.sessions.dtos.UpdateSessionDto;
import dev.thewlabs.schoolar.domain.timetables.entities.ClassroomTimetable;
import dev.thewlabs.schoolar.domain.timetables.entities.GroupTimetable;
import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import dev.thewlabs.schoolar.domain.timetables.repositories.ClassroomTimetableRepository;
import dev.thewlabs.schoolar.domain.timetables.repositories.GroupTimetableRepository;
import dev.thewlabs.schoolar.domain.timetables.repositories.TimeslotRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class SessionMapper extends Mapper<Session, CreateSessionDto, UpdateSessionDto, SessionDetailsDto> {
    private final CourseRepository courseRepository;
    private final TimeslotRepository timeslotRepository;
    private final ClassroomRepository classroomRepository;
    private final GroupTimetableRepository groupTimetableRepository;
    private final ClassroomTimetableRepository classroomTimetableRepository;

    public SessionMapper(
            CourseRepository courseRepository,
            TimeslotRepository timeslotRepository,
            ClassroomRepository classroomRepository,
            GroupTimetableRepository groupTimetableRepository,
            ClassroomTimetableRepository classroomTimetableRepository
    ) {
        super();
        this.courseRepository = courseRepository;
        this.timeslotRepository = timeslotRepository;
        this.classroomRepository = classroomRepository;
        this.groupTimetableRepository = groupTimetableRepository;
        this.classroomTimetableRepository = classroomTimetableRepository;
    }

    @Override
    @Transactional
    public Session dtoToEntity(@NotNull CreateSessionDto dto) {
        Classroom classroom = classroomRepository.findClassroomById(UUID.fromString(dto.classroomId()));
        Course course = courseRepository.findCourseById(UUID.fromString(dto.courseId()));

        GroupTimetable groupTimetable = course.getGroup().findTimeslotTimetable(dto.timeslot()).orElse(new GroupTimetable(dto.timeslot()));
        ClassroomTimetable classroomTimetable = classroom.findTimeslotTimetable(dto.timeslot()).orElse(new ClassroomTimetable(dto.timeslot()));

        groupTimetable.setGroup(course.getGroup());
        classroomTimetable.setClassroom(classroom);

        groupTimetableRepository.save(groupTimetable);
        classroomTimetableRepository.save(classroomTimetable);

        Timeslot sessionTimeSlot = dto.timeslot();

        sessionTimeSlot.setGroupTimetable(groupTimetable);
        sessionTimeSlot.setClassroomTimetable(classroomTimetable);

        timeslotRepository.save(sessionTimeSlot);

        Session session = new Session();

        session.setTitle(dto.title());
        session.setDescription(dto.description());
        session.setCourse(course);
        session.setClassroom(classroom);
        session.setTimeslot(sessionTimeSlot);

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
