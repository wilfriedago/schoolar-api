package dev.thewlabs.schoolar.domain.timetables.repositories;

import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import dev.thewlabs.schoolar.domain.timetables.entities.GroupTimetable;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupTimetableRepository extends CrudRepository<GroupTimetable> {

    @Query("SELECT gt FROM GroupTimetable gt JOIN gt.group g WHERE g.id = :groupId AND gt.startDate = :startDate AND gt.endDate = :endDate")
    Optional<GroupTimetable> findGroupTimetableByGroupIdAndStartDateAndEndDate(UUID groupId, LocalDate startDate, LocalDate endDate);

    default GroupTimetable findGroupTimetableById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Group timetable not found"));
    }
}
