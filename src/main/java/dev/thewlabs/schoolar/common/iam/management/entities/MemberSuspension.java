package dev.thewlabs.schoolar.common.iam.management.entities;

import dev.thewlabs.schoolar.common.iam.management.enums.BanReason;
import dev.thewlabs.schoolar.core.abstracts.ExpireableAndTraceable;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@NoArgsConstructor
@Setter
@Table(name = "member_suspensions", schema = "v1")
public class MemberSuspension extends ExpireableAndTraceable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "reason", nullable = false)
    private BanReason reason;
}
