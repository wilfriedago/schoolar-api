package dev.thewlabs.schoolar.domain.sessions;

import dev.thewlabs.schoolar.core.interfaces.CrudController;
import dev.thewlabs.schoolar.domain.sessions.dtos.CreateSessionDTO;
import dev.thewlabs.schoolar.domain.sessions.dtos.SessionDetailsDTO;
import dev.thewlabs.schoolar.domain.sessions.dtos.UpdateSessionDTO;
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
@RequestMapping("/api/v1/sessions")
@Tag(name = "Sessions", description = "Sessions endpoints")
public class SessionController implements CrudController<SessionDetailsDTO, CreateSessionDTO, UpdateSessionDTO> {
    private final SessionService service;

    @Autowired
    public SessionController(SessionService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    @Operation(summary = "List all sessions")
    public ResponseEntity<Page<SessionDetailsDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<SessionDetailsDTO> sessions = this.service.findAll(page, size, sort);

        return HttpResponse.ok(sessions);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Show a session info")
    public ResponseEntity<SessionDetailsDTO> show(
            @UUID
            @Validated
            @PathVariable
            java.util.UUID id
    ) {
        SessionDetailsDTO session = this.service.findById(id);

        return HttpResponse.ok(session);
    }

    @Override
    @PostMapping
    @Operation(summary = "Create a new session")
    public ResponseEntity<SessionDetailsDTO> create(@RequestBody @Validated CreateSessionDTO createDto) {
        SessionDetailsDTO session = this.service.create(createDto);

        return HttpResponse.created(session);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update session data")
    public ResponseEntity<SessionDetailsDTO> update(
            @UUID
            @Validated
            @PathVariable
            java.util.UUID id,

            @RequestBody
            @Validated
            UpdateSessionDTO updateDto
    ) {
        SessionDetailsDTO session = this.service.update(id, updateDto);

        return HttpResponse.ok(session);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a session")
    public void delete(
            @UUID
            @Validated
            @PathVariable
            java.util.UUID id
    ) {
        this.service.delete(id);
    }
}
