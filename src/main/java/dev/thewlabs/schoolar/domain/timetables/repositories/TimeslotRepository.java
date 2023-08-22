package dev.thewlabs.schoolar.domain.timetables.repositories;

import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, UUID> {
}
