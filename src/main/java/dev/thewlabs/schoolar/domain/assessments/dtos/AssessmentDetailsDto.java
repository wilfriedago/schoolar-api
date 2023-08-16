package dev.thewlabs.schoolar.domain.assessments.dtos;

import dev.thewlabs.schoolar.domain.classrooms.dtos.ClassroomDTO;
import dev.thewlabs.schoolar.domain.courses.dtos.CourseDetailsDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(name = "Assessment", description = "Represents an Assessment entity in the application.")
public record AssessmentDetailsDto(
        @Schema(description = "The assessment id.", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
        UUID id,
        @Schema(description = "The title of the assessment.", example = "Maths & Physics assessment")
        String name,
        @Schema(description = "A description of the assessment, limited to a maximum length of 1000 characters.", example = "This is a maths & physics assessment")
        String description,
        @Schema(description = "The date of the assessment.", example = "2021-01-01")
        String date,
        @Schema(description = "The start time of the assessment.", example = "2021-01-01T10:00:00")
        String startTime,
        @Schema(description = "The end time of the assessment.", example = "2021-01-01T12:00:00")
        String endTime,
        @Schema(description = "The classroom of the event.", implementation = ClassroomDTO.class)
        ClassroomDTO classroom,
        @Schema(description = "The status of the assessment.", example = "CANCELLED")
        String status,
        @Schema(description = "The courses associated with the assessment.", implementation = CourseDetailsDTO.class)
        List<CourseDetailsDTO> courses,

        @Schema(description = "The date and time the assessment was created.", example = "2021-01-01T12:00:00")
        String createdAt
) {
}
