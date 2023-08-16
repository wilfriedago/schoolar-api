package dev.thewlabs.schoolar.domain.events.dtos;

import dev.thewlabs.schoolar.domain.events.enums.EventColor;
import dev.thewlabs.schoolar.domain.events.enums.EventStatus;
import dev.thewlabs.schoolar.domain.events.enums.EventType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "EventDTO", description = "Event data transfer object.")
public class EventDTO {
    @Schema(description = "The unique identifier for the event.", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
    UUID id;

    @Schema(description = "The title of the event.", example = "Maths session")
    String title;

    @Schema(description = "A description of the event.", example = "This is a maths session")
    String description;

    @Schema(description = "A url related to the event.", example = "https://somemeeting.com/123456")
    String url;

    @Schema(description = "The event is all day long.", example = "true")
    Boolean allDay;

    ZonedDateTime startTime;

    ZonedDateTime endTime;

    @Schema(description = "The type of the event.", example = "SESSION")
    EventType type;

    @Schema(description = "The status of the event.", example = "CANCELLED")
    EventStatus status;

    @Schema(description = "The color of the event.", example = "BLUE")
    EventColor color;
}
