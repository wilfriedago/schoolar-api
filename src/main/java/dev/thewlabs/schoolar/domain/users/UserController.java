package dev.thewlabs.schoolar.domain.users;

import dev.thewlabs.schoolar.domain.users.dtos.UserDetailsDTO;
import dev.thewlabs.schoolar.shared.http.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Users endpoints")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List all users")
    public ResponseEntity<Page<UserDetailsDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean sortDesc
    ) {
        Sort sort = Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        Page<UserDetailsDTO> users = this.service.findAll(page, size, sort);

        return HttpResponse.ok(users);
    }
}