package dev.thewlabs.schoolar.core.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Expirable {
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @Check(name = "ck_expired_at", constraints = "expired_at > created_at")
    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expireAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
