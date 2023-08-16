package dev.thewlabs.schoolar.core.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Check;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * This class is intended to be extended by all entities that need to be traceable.
 * <p>
 * It contains the following fields: {@code createdAt}, {@code updatedAt} and {@code deletedAt}.
 * </p>
 */
@Getter
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Traceable {
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @JsonIgnore
    @Check(name = "ck_updated_at", constraints = "updated_at >= created_at")
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
    @JsonIgnore
    @Check(name = "ck_deleted_at", constraints = "deleted_at >= created_at")
    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @EqualsAndHashCode.Include
    public abstract UUID getId();

    @PrePersist
    public void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

    @PreRemove
    public void preRemove() {
        this.deletedAt = ZonedDateTime.now();
    }
}
