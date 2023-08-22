package dev.thewlabs.schoolar.domain.groups;

import dev.thewlabs.schoolar.core.interfaces.CrudController;
import dev.thewlabs.schoolar.core.records.BulkActionResultDto;
import dev.thewlabs.schoolar.domain.courses.dtos.CourseDetailsDto;
import dev.thewlabs.schoolar.domain.events.dtos.EventDetailsDto;
import dev.thewlabs.schoolar.domain.groups.dtos.CreateGroupDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.GroupDetailsDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.UpdateGroupDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.courses.AddCoursesDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.courses.RemoveCoursesDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.students.AddStudentsDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.students.RemoveStudentsDTO;
import dev.thewlabs.schoolar.domain.students.dtos.StudentDetailsDTO;
import dev.thewlabs.schoolar.domain.timetables.services.TimetableService;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
@Tag(name = "Groups", description = "Groups endpoints")
public class GroupController implements CrudController<GroupDetailsDTO, CreateGroupDTO, UpdateGroupDTO> {
    private final GroupService service;
    private final TimetableService timetableService;

    @Autowired
    public GroupController(GroupService service, TimetableService timetableService) {
        this.service = service;
        this.timetableService = timetableService;
    }

    @GetMapping
    @Operation(summary = "List all groups")
    public ResponseEntity<Page<GroupDetailsDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<GroupDetailsDTO> groups = this.service.findAll(page, size, sort);

        return HttpResponse.ok(groups);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show a group info")
    public ResponseEntity<GroupDetailsDTO> show(@PathVariable UUID id) {
        GroupDetailsDTO group = this.service.findById(id);

        return HttpResponse.ok(group);
    }

    @PostMapping
    @Operation(summary = "Create a new group")
    public ResponseEntity<GroupDetailsDTO> create(@RequestBody @Validated CreateGroupDTO dto) {
        GroupDetailsDTO group = this.service.create(dto);

        return HttpResponse.created(group);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update group data")
    public ResponseEntity<GroupDetailsDTO> update(@PathVariable UUID id, @RequestBody @Validated UpdateGroupDTO dto) {
        GroupDetailsDTO group = this.service.update(id, dto);

        return HttpResponse.ok(group);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a group")
    public void delete(@PathVariable UUID id) {
        this.service.delete(id);
    }

    @GetMapping("/{id}/students")
    @Operation(summary = "List all students of a group")
    public ResponseEntity<Page<StudentDetailsDTO>> listStudents(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<StudentDetailsDTO> students = this.service.findStudents(id, page, size, sort);

        return HttpResponse.ok(students);
    }

    @PostMapping("/{id}/students")
    @Operation(summary = "Add students to a group")
    public ResponseEntity<BulkActionResultDto> addStudents(@PathVariable UUID id, @RequestBody @Validated AddStudentsDTO dto) {
        BulkActionResultDto result = this.service.addStudents(id, dto);

        return HttpResponse.ok(result);
    }

    @DeleteMapping("/{id}/students")
    @Operation(summary = "Remove students from a group")
    public ResponseEntity<BulkActionResultDto> removeStudents(@PathVariable UUID id, @RequestBody @Validated RemoveStudentsDTO dto) {
        BulkActionResultDto result = this.service.removeStudents(id, dto);

        return HttpResponse.ok(result);
    }

    @GetMapping("/{id}/courses")
    @Operation(summary = "List all courses of a group")
    public ResponseEntity<Page<CourseDetailsDto>> listCourses(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<CourseDetailsDto> courses = this.service.findCourses(id, page, size, sort);

        return HttpResponse.ok(courses);
    }

    @PostMapping("/{id}/courses")
    @Operation(summary = "Add courses to a group")
    public ResponseEntity<BulkActionResultDto> addCourses(@PathVariable UUID id, @RequestBody @Validated AddCoursesDTO dto) {
        BulkActionResultDto result = this.service.addCourses(id, dto);

        return HttpResponse.ok(result);
    }

    @DeleteMapping("/{id}/courses")
    @Operation(summary = "Remove courses from a group")
    public ResponseEntity<BulkActionResultDto> removeCourses(@PathVariable UUID id, @RequestBody @Validated RemoveCoursesDTO dto) {
        BulkActionResultDto result = this.service.removeCourses(id, dto);

        return HttpResponse.ok(result);
    }

    @GetMapping("/{id}/events")
    public ResponseEntity<List<EventDetailsDto>> getGroupTimetableEvents(
            @PathVariable UUID id,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        List<EventDetailsDto> events = timetableService.getGroupTimetableEvents(id, start, end);

        return HttpResponse.ok(events);
    }
}
