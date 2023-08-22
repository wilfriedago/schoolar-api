package dev.thewlabs.schoolar.domain.attendances;

import dev.thewlabs.schoolar.domain.attendances.dtos.AttendanceDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AttendanceService {
    private final AttendanceMapper mapper;
    private final AttendanceRepository repository;

    @Autowired
    public AttendanceService(AttendanceMapper mapper, AttendanceRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<AttendanceDetailsDto> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    public Page<AttendanceDetailsDto> findAllByAttendeeId(int page, int size, Sort sort, UUID userId) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAllByAttendeeId(userId, pageable).map(mapper::entityToDto);
    }

    public AttendanceDetailsDto findById(UUID id) {
        return repository.findById(id).map(mapper::entityToDto).orElse(null);
    }

    public Page<AttendanceDetailsDto> findAllByEventId(int page, int size, Sort sort, UUID eventId) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAllByEventId(eventId, pageable).map(mapper::entityToDto);
    }

    public Page<AttendanceDetailsDto> findAllByAttendeeIdAndEventId(int page, int size, Sort sort, UUID userId, UUID eventId) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAllByAttendeeIdAndEventId(userId, eventId, pageable).map(mapper::entityToDto);
    }
}
