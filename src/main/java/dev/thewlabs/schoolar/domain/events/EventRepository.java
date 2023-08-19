package dev.thewlabs.schoolar.domain.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    default List<Event> findAllEventsEndingIn5Minutes() {
        // TODO: Implement this ASAP !
        return null;
    }
}
