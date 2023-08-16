package dev.thewlabs.schoolar.domain.sessions.dtos;

import dev.thewlabs.schoolar.domain.classrooms.dtos.ClassroomDTO;
import dev.thewlabs.schoolar.domain.courses.dtos.CourseDTO;
import dev.thewlabs.schoolar.domain.events.enums.EventColor;
import dev.thewlabs.schoolar.domain.events.enums.EventStatus;
import dev.thewlabs.schoolar.domain.students.dtos.StudentDTO;
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
@Schema(name = "Session", description = "Represents a Session entity in the application.")
public class SessionDetailsDTO {
    @Schema(description = "The unique identifier for the event.", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
    UUID id;

    @Schema(description = "The title of the event.", example = "Maths session")
    String title;

    @Schema(description = "A description of the event.", example = "This is a maths session")
    String description;

    @Schema(description = "The event is all day long.", example = "true")
    Boolean allDay;

    @Schema(description = "The start of the event.", example = "2023-08-09T15:30:45.123+02:00")
    ZonedDateTime startTime;

    @Schema(description = "The end of the event.", example = "2023-08-09T18:30:45.123+02:00")
    ZonedDateTime endTime;

    @Schema(description = "The status of the event.", example = "CANCELLED")
    EventStatus status;

    @Schema(description = "An url associated with the event.", example = "https://schoolar.dev/events/5151s15sd151")
    String url;

    @Schema(description = "The event color.", implementation = EventColor.class)
    EventColor color;

    @Schema(description = "The classroom of the event.", implementation = ClassroomDTO.class)
    ClassroomDTO classroom;

    @Schema(description = "The event organizer.", implementation = UserDTO.class)
    UserDTO organizer;

    @ArraySchema(schema = @Schema(description = "The list of attendees to the events"), contains = @Schema(implementation = UserDTO.class))
    List<StudentDTO> attendees;

    @Schema(description = "The course associated with the session.", implementation = CourseDTO.class)
    CourseDTO course;

    @Schema(description = "The date and time the event was created.", example = "2021-01-01T12:00:00:45.123+02:00")
    ZonedDateTime createdAt;

    @Schema(description = "The date and time the event was updated.", example = "2021-01-01T12:00:00:45.123+02:00")
    ZonedDateTime updatedAt;
}
