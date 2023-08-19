package dev.thewlabs.schoolar.domain.sessions.dtos;

import dev.thewlabs.schoolar.domain.events.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    UUID id;

    String title;

    String description;

    ZonedDateTime start;

    ZonedDateTime end;

    EventStatus status;

    String url;

    boolean allDay;

    String color;
}
