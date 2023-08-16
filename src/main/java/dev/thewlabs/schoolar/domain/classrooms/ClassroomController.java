package dev.thewlabs.schoolar.domain.classrooms;

import dev.thewlabs.schoolar.core.interfaces.CrudController;
import dev.thewlabs.schoolar.domain.classrooms.dtos.ClassroomDetailsDTO;
import dev.thewlabs.schoolar.domain.classrooms.dtos.CreateClassroomDTO;
import dev.thewlabs.schoolar.domain.classrooms.dtos.UpdateClassroomDTO;
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
@RequestMapping("/api/v1/classrooms")
@Tag(name = "Classrooms", description = "Classrooms endpoints")
public class ClassroomController implements CrudController<ClassroomDetailsDTO, CreateClassroomDTO, UpdateClassroomDTO> {
    private final ClassroomService service;

    @Autowired
    public ClassroomController(ClassroomService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List all classrooms")
    public ResponseEntity<Page<ClassroomDetailsDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<ClassroomDetailsDTO> classrooms = this.service.findAll(page, size, sort);

        return HttpResponse.ok(classrooms);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show a classroom info")
    public ResponseEntity<ClassroomDetailsDTO> show(@PathVariable UUID id) {
        ClassroomDetailsDTO classroom = this.service.findById(id);

        return HttpResponse.ok(classroom);
    }

    @PostMapping
    @Operation(summary = "Create a new classroom")
    public ResponseEntity<ClassroomDetailsDTO> create(@RequestBody @Validated CreateClassroomDTO dto) {
        ClassroomDetailsDTO classroom = this.service.create(dto);

        return HttpResponse.created(classroom);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update classroom data")
    public ResponseEntity<ClassroomDetailsDTO> update(@PathVariable UUID id, @RequestBody @Validated UpdateClassroomDTO dto) {
        ClassroomDetailsDTO classroom = this.service.update(id, dto);

        return HttpResponse.ok(classroom);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a classroom")
    public void delete(@PathVariable UUID id) {
        this.service.delete(id);
    }
}
