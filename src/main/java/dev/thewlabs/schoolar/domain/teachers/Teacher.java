package dev.thewlabs.schoolar.domain.teachers;

import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.users.User;
import dev.thewlabs.schoolar.domain.users.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PostLoad;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Teacher extends User {
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "teachers")
    private List<Course> courses;

    @PostLoad
    void updateType() {
        setType(UserType.TEACHER);
    }
}
