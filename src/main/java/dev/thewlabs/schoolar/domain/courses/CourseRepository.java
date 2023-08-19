package dev.thewlabs.schoolar.domain.courses;

import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import dev.thewlabs.schoolar.domain.groups.Group;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends CrudRepository<Course> {
    default Page<Course> findAllByGroup(Group group, Pageable pageable) {
        Course course = new Course();

        course.setGroup(group);
        Example<Course> example = Example.of(course);

        return this.findAll(example, pageable);
    }

    default Course findCourseById(@NotNull UUID id) throws NotFoundException {
        return this.findById(id).orElseThrow(() -> new NotFoundException("Course with id " + id + " not found."));
    }
}
