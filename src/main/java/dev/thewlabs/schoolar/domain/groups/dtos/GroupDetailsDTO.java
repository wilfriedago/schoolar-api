package dev.thewlabs.schoolar.domain.groups.dtos;

import dev.thewlabs.schoolar.domain.courses.dtos.CourseDto;
import dev.thewlabs.schoolar.domain.students.dtos.StudentDTO;
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
@Schema(name = "GroupDetailsDto", description = "Group details data transfer object.")
public class GroupDetailsDTO {
    @Schema(example = "9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2a")
    UUID id;

    @Schema(example = "Wonderful group")
    String name;

    @Schema(example = "Wonderful group located in Cotonou")
    String description;

    @ArraySchema(contains = @Schema(implementation = CourseDto.class))
    List<CourseDto> courses;

    @ArraySchema(contains = @Schema(implementation = StudentDTO.class))
    List<StudentDTO> students;

    @Schema(example = "2021-08-01T00:00:00.000Z")
    ZonedDateTime createdAt;
}
