package dev.thewlabs.schoolar.domain.assessments;

import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends CrudRepository<Assessment> {
}
