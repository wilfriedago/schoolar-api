package dev.thewlabs.schoolar.domain.timetables.entities;

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.timetables.enums.TimetableType;
import dev.thewlabs.schoolar.shared.utils.DatetimeUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Check;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Timetable extends Traceable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Check(name = "ck_end_date", constraints = "end_date > start_date")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Transient
    private TimetableType type;

    protected Timetable() {
        this.startDate = DatetimeUtils.getFirstDayOfWeek();
        this.endDate = DatetimeUtils.getFirstDayOfWeek();
    }

    protected Timetable(@NotNull Timeslot timeslot) {
        this.startDate = DatetimeUtils.getFirstDayOfWeek(timeslot.getStartTime().toLocalDate());
        this.endDate = DatetimeUtils.getLastDayOfWeek(timeslot.getEndTime().toLocalDate());
    }
}
