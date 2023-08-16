package dev.thewlabs.schoolar.domain.classrooms;

import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassroomRepository extends CrudRepository<Classroom> {
    boolean existsByName(String name);

    default @NotNull Classroom findByClassroomId(@NotNull UUID id) throws NotFoundException {
        return this.findById(id).orElseThrow(() -> new NotFoundException("Classroom with id " + id + " not found."));
    }
}
