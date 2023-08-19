package dev.thewlabs.schoolar.domain.courses;

import dev.thewlabs.schoolar.core.interfaces.CrudController;
import dev.thewlabs.schoolar.domain.courses.dtos.CourseDetailsDto;
import dev.thewlabs.schoolar.domain.courses.dtos.CreateCourseDto;
import dev.thewlabs.schoolar.domain.courses.dtos.UpdateCourseDto;
import dev.thewlabs.schoolar.shared.http.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name = "Courses", description = "Courses endpoints")
public class CourseController implements CrudController<CourseDetailsDto, CreateCourseDto, UpdateCourseDto> {
    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    @Operation(summary = "List all courses")
    public ResponseEntity<Page<CourseDetailsDto>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<CourseDetailsDto> courses = this.service.findAll(page, size, sort);

        return HttpResponse.ok(courses);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Show a course info")
    public ResponseEntity<CourseDetailsDto> show(@PathVariable UUID id) {
        CourseDetailsDto course = this.service.findById(id);

        return HttpResponse.ok(course);
    }

    @Override
    @PostMapping
    @Operation(summary = "Create a new course")
    public ResponseEntity<CourseDetailsDto> create(@RequestBody @Validated CreateCourseDto dto) {
        CourseDetailsDto course = this.service.create(dto);

        return HttpResponse.created(course);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update a course")
    public ResponseEntity<CourseDetailsDto> update(@PathVariable UUID id, @RequestBody @Validated UpdateCourseDto dto) {
        CourseDetailsDto course = this.service.update(id, dto);

        return HttpResponse.ok(course);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a course")
    public void delete(@PathVariable UUID id) {
        this.service.delete(id);
    }
}
