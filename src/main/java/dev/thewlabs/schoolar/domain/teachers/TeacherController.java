package dev.thewlabs.schoolar.domain.teachers;

import dev.thewlabs.schoolar.core.interfaces.CrudController;
import dev.thewlabs.schoolar.domain.teachers.dtos.CreateTeacherDTO;
import dev.thewlabs.schoolar.domain.teachers.dtos.TeacherDetailsDto;
import dev.thewlabs.schoolar.domain.teachers.dtos.UpdateTeacherDTO;
import dev.thewlabs.schoolar.shared.http.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teachers")
@Tag(name = "Teachers", description = "Teachers endpoints")
public class TeacherController implements CrudController<TeacherDetailsDto, CreateTeacherDTO, UpdateTeacherDTO> {
    private final TeacherService service;

    @Autowired
    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    @Operation(summary = "List all teachers")
    public ResponseEntity<Page<TeacherDetailsDto>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<TeacherDetailsDto> teachers = this.service.findAll(page, size, sort);

        return HttpResponse.ok(teachers);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Show a teacher info")
    public ResponseEntity<TeacherDetailsDto> show(
            @UUID
            @Validated
            @PathVariable
            java.util.UUID id
    ) {
        TeacherDetailsDto teacher = this.service.findById(id);

        return HttpResponse.ok(teacher);
    }

    @Override
    @PostMapping
    @Operation(summary = "Create a new teacher")
    public ResponseEntity<TeacherDetailsDto> create(@RequestBody @Validated CreateTeacherDTO createDto) {
        TeacherDetailsDto teacher = this.service.create(createDto);

        return HttpResponse.created(teacher);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update teacher data")
    public ResponseEntity<TeacherDetailsDto> update(
            @UUID
            @Validated
            @PathVariable
            java.util.UUID id,

            @RequestBody
            @Validated
            UpdateTeacherDTO updateDto
    ) {
        TeacherDetailsDto teacher = this.service.update(id, updateDto);

        return HttpResponse.ok(teacher);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a teacher")
    public void delete(
            @UUID
            @Validated
            @PathVariable
            java.util.UUID id
    ) {
        this.service.delete(id);
    }
}
