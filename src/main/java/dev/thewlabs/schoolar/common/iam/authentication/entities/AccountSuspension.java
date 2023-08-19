package dev.thewlabs.schoolar.common.iam.authentication.entities;

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
@Table(name = "account_suspensions", schema = "v1")
public class AccountSuspension extends ExpireableAndTraceable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SuspensionReason reason;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SuspensionStatus status;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn
    private Account account;
}
