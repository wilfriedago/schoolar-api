package dev.thewlabs.schoolar.domain.timetables.repositories;

import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import dev.thewlabs.schoolar.domain.timetables.entities.ClassroomTimetable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassroomTimetableRepository extends CrudRepository<ClassroomTimetable> {
    Optional<ClassroomTimetable> findClassroomTimetableByClassroomIdAndStartDate(UUID classroomId, LocalDate startDate);

    default ClassroomTimetable findClassroomTimetableById(UUID id) {
        return findById(id).orElseThrow();
    }
}
