package dev.thewlabs.schoolar.core.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CrudController<R, C, U> {
    ResponseEntity<Page<R>> list(int page, int size, String sortBy, boolean sortDesc);

    ResponseEntity<R> show(UUID id);

    ResponseEntity<R> create(C createDto);

    ResponseEntity<R> update(UUID id, U updateDto);

    void delete(UUID id);
}