package dev.thewlabs.schoolar.common.iam.management.entities;

import dev.thewlabs.schoolar.core.abstracts.ExpireableAndTraceable;
import dev.thewlabs.schoolar.core.enums.SuspensionReason;
import dev.thewlabs.schoolar.core.enums.SuspensionStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Setter
@Table(name = "organization_suspensions", schema = "v1")
public class OrganizationSuspension extends ExpireableAndTraceable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "reason", nullable = false)
    private SuspensionReason reason;

    @Column(name = "status", nullable = false)
    private SuspensionStatus status;

    @Column(name = "description", length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
}
