package dev.thewlabs.schoolar.domain.attendances.dtos;

import dev.thewlabs.schoolar.domain.events.Event;
import dev.thewlabs.schoolar.domain.events.validators.EventExists;
import dev.thewlabs.schoolar.domain.users.User;
import dev.thewlabs.schoolar.domain.users.validators.UserExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

public record BulkUpdateAttendanceDto(
        @NotNull
        @UUID
        @EventExists
        Event event,

        @NotNull
        @UUID
        @UserExists
        User attendee,

        @NotNull
        boolean isPresent,

        @Size(max = 1000)
        String observation
) {
}
