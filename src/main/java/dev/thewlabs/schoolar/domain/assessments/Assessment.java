package dev.thewlabs.schoolar.domain.assessments;

import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.events.Event;
import dev.thewlabs.schoolar.domain.events.enums.EventType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Assessment extends Event {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "assessment_courses",
            joinColumns = @JoinColumn(name = "assessment_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    @Override
    public EventType getType() {
        return EventType.ASSESSMENT;
    }
}
