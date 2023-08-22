package dev.thewlabs.schoolar.domain.courses.dtos;

import dev.thewlabs.schoolar.domain.assessments.dtos.AssessmentDto;
import dev.thewlabs.schoolar.domain.groups.dtos.GroupDTO;
import dev.thewlabs.schoolar.domain.sessions.dtos.SessionDto;
import dev.thewlabs.schoolar.domain.subjects.dtos.SubjectDto;
import dev.thewlabs.schoolar.domain.teachers.dtos.TeacherDto;
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
@Schema(name = "CourseDetailsDto", description = "Course details data transfer object")
public class CourseDetailsDto {
    @Schema(example = "9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2a")
    UUID id;

    @Schema(example = "Mathematics course for group 1")
    String name;

    @Schema(example = "A mathematics course schedule for a group")
    String description;

    @Schema(example = "30")
    Integer hours;

    @Schema(implementation = SubjectDto.class)
    SubjectDto subject;

    @Schema(implementation = GroupDTO.class)
    GroupDTO group;

    @ArraySchema(schema = @Schema(implementation = SessionDto.class))
    List<SessionDto> sessions;

    @ArraySchema(contains = @Schema(implementation = TeacherDto.class))
    List<TeacherDto> teachers;

    @ArraySchema(schema = @Schema(implementation = AssessmentDto.class))
    List<AssessmentDto> assessments;

    @Schema(example = "2021-08-01T00:00:00.000Z")
    ZonedDateTime createdAt;
}
