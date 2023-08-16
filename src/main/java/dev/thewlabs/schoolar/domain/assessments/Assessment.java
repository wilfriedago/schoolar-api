package dev.thewlabs.schoolar.domain.assessments;

import dev.thewlabs.schoolar.core.interfaces.Serializable;
import dev.thewlabs.schoolar.domain.assessments.dtos.AssessmentDetailsDto;
import dev.thewlabs.schoolar.domain.assessments.dtos.AssessmentDto;
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
public class Assessment extends Event implements Serializable<AssessmentDto, AssessmentDetailsDto> {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "assessment_courses",
            joinColumns = @JoinColumn(name = "assessment_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    @PostLoad
    void updateType() {
        setType(EventType.ASSESSMENT);
    }

    @Override
    public AssessmentDto serialize() {
        return null;
    }

    @Override
    public AssessmentDetailsDto serializeWithDetails() {
        return null;
    }
}
