package dev.thewlabs.schoolar.domain.attendances;

import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance> {
    Page<Attendance> findAllByAttendeeId(UUID userId, Pageable pageable);

    Page<Attendance> findAllByEventId(UUID eventId, Pageable pageable);

    Page<Attendance> findAllByAttendeeIdAndEventId(UUID userId, UUID eventId, Pageable pageable);
}
