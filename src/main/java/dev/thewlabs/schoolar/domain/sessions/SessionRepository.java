package dev.thewlabs.schoolar.domain.sessions;

import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepository extends CrudRepository<Session> {
    default Session findSessionById(@NotNull UUID id) throws NotFoundException {
        return this.findById(id).orElseThrow(() -> new NotFoundException("Session with id " + id + " not found"));
    }
}
