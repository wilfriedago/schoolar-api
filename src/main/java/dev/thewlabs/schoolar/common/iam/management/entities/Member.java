package dev.thewlabs.schoolar.common.iam.management.entities;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumns({
        @PrimaryKeyJoinColumn(name = "organization_id", referencedColumnName = "id"),
        @PrimaryKeyJoinColumn(name = "member_id", referencedColumnName = "id")
})
@Setter
@Table(name = "members", schema = "v1")
public class Member extends Account {
    @Column(name = "description", length = 1000)
    private String description;

    // TODO: Traduire parain en anglais
    @ManyToOne
    @JoinColumn(name = "inviter_id", referencedColumnName = "id")
    private Member inviter;

    // TODO: Trasuire filleules en anglais
    @OneToMany(mappedBy = "inviter")
    private Set<Member> invitees;
}
