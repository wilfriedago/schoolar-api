package dev.thewlabs.schoolar.domain.students;

import dev.thewlabs.schoolar.domain.groups.Group;
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
public class Student extends User {
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "students")
    private List<Group> groups;

    @PostLoad
    void updateType() {
        setType(UserType.STUDENT);
    }
}
