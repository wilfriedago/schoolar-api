package dev.thewlabs.schoolar.domain.events.dtos;

import dev.thewlabs.schoolar.domain.classrooms.dtos.ClassroomDTO;
import dev.thewlabs.schoolar.domain.events.enums.EventColor;
import dev.thewlabs.schoolar.domain.events.enums.EventStatus;
import dev.thewlabs.schoolar.domain.events.enums.EventType;
import dev.thewlabs.schoolar.domain.users.dtos.UserDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "EventDetailsDTO", description = "Event details data transfer object.")
public class EventDetailsDTO {
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

    @Schema(description = "The start of the event.", example = "2023-08-09T15:30:45.123+02:00")
    ZonedDateTime startTime;

    @Schema(description = "The end of the event.", example = "2023-08-09T18:30:45.123+02:00")
    ZonedDateTime endTime;

    @Schema(description = "The type of the event.", example = "SESSION")
    EventType type;

    @Schema(description = "The status of the event.", example = "CANCELLED")
    EventStatus status;

    @Schema(description = "The color of the event.", example = "BLUE")
    EventColor color;

    @Schema(description = "The classroom where the event is taking place.", implementation = ClassroomDTO.class)
    ClassroomDTO classroom;

    @Schema(description = "The organizer of the event.", implementation = UserDTO.class)
    UserDTO organizer;

    @ArraySchema(schema = @Schema(description = "List of all attendee of the events."), contains = @Schema(implementation = UserDTO.class))
    List<UserDTO> attendees;

    @Schema(description = "The date and time the event was created. Cannot be null.", example = "2021-01-01T12:00:00")
    ZonedDateTime createdAt;
}
