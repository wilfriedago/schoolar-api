package dev.thewlabs.schoolar.core.interfaces;

import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface CrudService<T, C, U> {
    Page<T> findAll(int page, int size, Sort sort);

    T findById(@NotNull UUID id) throws NotFoundException;

    T create(@NotNull C dto);

    T update(@NotNull UUID id, @NotNull U dto) throws NotFoundException;

    void delete(@NotNull UUID id) throws NotFoundException;
}

