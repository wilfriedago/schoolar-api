package dev.thewlabs.schoolar.domain.students;

import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import dev.thewlabs.schoolar.domain.groups.Group;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<Student> {
    default Page<Student> findAllByGroup(Group group, Pageable pageable) {
        Student student = new Student();

        student.setGroups(List.of(group));
        Example<Student> example = Example.of(student);

        return this.findAll(example, pageable);
    }

    default Student findStudentById(@NotNull UUID id) throws NotFoundException {
        return this.findById(id).orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
    }
}
