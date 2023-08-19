package dev.thewlabs.schoolar.domain.students;

import dev.thewlabs.schoolar.core.interfaces.CrudController;
import dev.thewlabs.schoolar.domain.students.dtos.CreateStudentDTO;
import dev.thewlabs.schoolar.domain.students.dtos.StudentDetailsDTO;
import dev.thewlabs.schoolar.domain.students.dtos.UpdateStudentDTO;
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
@RequestMapping("/api/v1/students")
@Tag(name = "Students", description = "Students endpoints")
public class StudentController implements CrudController<StudentDetailsDTO, CreateStudentDTO, UpdateStudentDTO> {
    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    @Operation(summary = "List all students")
    public ResponseEntity<Page<StudentDetailsDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<StudentDetailsDTO> students = this.service.findAll(page, size, sort);

        return HttpResponse.ok(students);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Show a student info")
    public ResponseEntity<StudentDetailsDTO> show(
            @UUID
            @Validated
            @PathVariable
            java.util.UUID id
    ) {
        StudentDetailsDTO student = this.service.findById(id);

        return HttpResponse.ok(student);
    }

    @Override
    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<StudentDetailsDTO> create(@RequestBody @Validated CreateStudentDTO createDto) {
        StudentDetailsDTO student = this.service.create(createDto);

        return HttpResponse.created(student);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update student data")
    public ResponseEntity<StudentDetailsDTO> update(
            @UUID
            @Validated
            @PathVariable
            java.util.UUID id,

            @RequestBody
            @Validated
            UpdateStudentDTO updateDto
    ) {
        StudentDetailsDTO student = this.service.update(id, updateDto);

        return HttpResponse.ok(student);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a student")
    public void delete(
            @UUID
            @Validated
            @PathVariable
            java.util.UUID id
    ) {
        this.service.delete(id);
    }
}
