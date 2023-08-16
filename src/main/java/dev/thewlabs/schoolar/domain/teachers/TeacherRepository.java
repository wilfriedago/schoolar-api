package dev.thewlabs.schoolar.domain.teachers;

import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher> {

    default Teacher findTeacherById(@NotNull UUID id) throws NotFoundException {
        return findById(id).orElseThrow(() -> new NotFoundException("Teacher with id " + id + " not found"));
    }
}
