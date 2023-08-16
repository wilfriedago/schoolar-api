package dev.thewlabs.schoolar.common.iam.authentication.entities;

import dev.thewlabs.schoolar.core.abstracts.Expirable;
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
@Table(name = "refresh_tokens", schema = "v1")
public class RefreshToken extends Expirable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID token;

    @Column(name = "is_revoked")
    private Boolean isRevoked;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
