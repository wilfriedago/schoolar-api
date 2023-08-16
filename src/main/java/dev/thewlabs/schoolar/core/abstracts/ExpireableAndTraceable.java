package dev.thewlabs.schoolar.core.abstracts;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class ExpireableAndTraceable {
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Check(name = "ck_updated_at", constraints = "updated_at <= created_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Check(name = "ck_deleted_at", constraints = "deleted_at <= created_at")
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Check(name = "ck_expired_at", constraints = "expired_at <= created_at")
    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expireAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PreRemove
    public void preRemove() {
        this.deletedAt = LocalDateTime.now();
    }
}
