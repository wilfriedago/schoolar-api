package dev.thewlabs.schoolar.domain.sessions;

import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.events.Event;
import dev.thewlabs.schoolar.domain.events.enums.EventType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Session extends Event {
    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @Override
    public EventType getType() {
        return EventType.SESSION;
    }
}
