package dev.thewlabs.schoolar.domain.timetables.entities;

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.timetables.enums.TimetableType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Check;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Timetable extends Traceable {
    @Id
    @GeneratedValue
    private UUID id;

    private Date startDate;

    @Check(name = "ck_end_date", constraints = "end_date > start_date")
    private Date endDate;

    @Transient
    private TimetableType type;
}
