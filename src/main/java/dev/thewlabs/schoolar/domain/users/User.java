package dev.thewlabs.schoolar.domain.users;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.events.Event;
import dev.thewlabs.schoolar.domain.users.enums.UserGender;
import dev.thewlabs.schoolar.domain.users.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public abstract class User extends Traceable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private String middlename;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Transient
    private UserType type;

    @Check(name = "ck_birth_date", constraints = "birth_date < CURRENT_DATE")
    private LocalDate birthDate;

    private String address;

    @OneToOne(optional = false)
    @JoinColumn
    private Account account;

    @OneToMany(mappedBy = "organizer")
    private List<Event> organizedEvents;

    @ManyToMany(mappedBy = "attendees")
    private List<Event> attendedEvents;
}
