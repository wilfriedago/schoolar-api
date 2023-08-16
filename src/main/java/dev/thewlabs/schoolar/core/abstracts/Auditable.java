package dev.thewlabs.schoolar.core.abstracts;

import dev.thewlabs.schoolar.core.enums.AuditAction;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@MappedSuperclass
public abstract class Auditable {
    @Column(name = "action", nullable = false)
    private AuditAction action;

    @Column(name = "description")
    private String description;
}
