package dev.thewlabs.schoolar.domain.groups;

import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepository extends CrudRepository<Group> {
    default Group findGroupById(@NotNull UUID id) throws NotFoundException {
        return this.findById(id).orElseThrow(() -> new NotFoundException("Group with id " + id + " not found."));
    }

    boolean existsByName(String name);
}