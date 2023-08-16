package dev.thewlabs.schoolar.domain.subjects;

import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubjectRepository extends CrudRepository<Subject> {
    boolean existsByName(String name);

    default Subject findSubjectById(UUID id) throws NotFoundException {
        return this.findById(id).orElseThrow(() -> new NotFoundException("Entity with id " + id + " not found."));
    }
}
