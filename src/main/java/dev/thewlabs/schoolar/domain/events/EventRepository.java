package dev.thewlabs.schoolar.domain.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE e.timeslot.endTime BETWEEN CURRENT_TIMESTAMP AND :fiveMinutesFromNow")
    List<Event> findAllEventsEndingIn5Minutes();

    default LocalDateTime getFiveMinutesFromNow() {
        return LocalDateTime.now().plusMinutes(5);
    }
}
