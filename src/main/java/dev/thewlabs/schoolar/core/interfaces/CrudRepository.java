package dev.thewlabs.schoolar.core.interfaces;

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface CrudRepository<T extends Traceable> extends JpaRepository<T, UUID> {
    /*    String SOFT_DELETE_BY_ID = "UPDATE #{#entityName} SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?1";
    String SOFT_DELETE_ALL = "UPDATE #{#entityName} SET deleted_at = CURRENT_TIMESTAMP";

    @Override
    @Modifying
    @Transactional
    @Query(SOFT_DELETE_BY_ID)
    void deleteById(@NotNull UUID id);

    @Override
    @Transactional
    default void delete(@NotNull T entity) {
        deleteById(entity.getId());
    }

    @Override
    @Transactional
    default void deleteAll(@NotNull Iterable<? extends T> entities) {
        entities.forEach(entity -> deleteById(entity.getId()));
    }

    @Override
    @Modifying
    @Transactional
    @Query(SOFT_DELETE_ALL)
    void deleteAll();*/
}
