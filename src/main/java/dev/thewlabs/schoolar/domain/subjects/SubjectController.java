package dev.thewlabs.schoolar.domain.subjects;

import dev.thewlabs.schoolar.core.interfaces.CrudController;
import dev.thewlabs.schoolar.domain.subjects.dtos.CreateSubjectDto;
import dev.thewlabs.schoolar.domain.subjects.dtos.SubjectDetailsDto;
import dev.thewlabs.schoolar.domain.subjects.dtos.UpdateSubjectDto;
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
@RequestMapping("/api/v1/subjects")
@Tag(name = "Subjects", description = "Subjects endpoints")
public class SubjectController implements CrudController<SubjectDetailsDto, CreateSubjectDto, UpdateSubjectDto> {
    private final SubjectService service;

    @Autowired
    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List all subjects")
    public ResponseEntity<Page<SubjectDetailsDto>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<SubjectDetailsDto> subjects = this.service.findAll(page, size, sort);

        return HttpResponse.ok(subjects);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show a subject info")
    public ResponseEntity<SubjectDetailsDto> show(@PathVariable UUID id) {
        SubjectDetailsDto subject = this.service.findById(id);

        return HttpResponse.ok(subject);
    }

    @PostMapping
    @Operation(summary = "Create a new subject")
    public ResponseEntity<SubjectDetailsDto> create(@RequestBody @Validated CreateSubjectDto dto) {
        SubjectDetailsDto subject = this.service.create(dto);

        return HttpResponse.created(subject);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update subject data")
    public ResponseEntity<SubjectDetailsDto> update(@PathVariable UUID id, @RequestBody @Validated UpdateSubjectDto dto) {
        SubjectDetailsDto subject = this.service.update(id, dto);

        return HttpResponse.ok(subject);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a subject")
    public void delete(@PathVariable UUID id) {
        this.service.delete(id);
    }
}
