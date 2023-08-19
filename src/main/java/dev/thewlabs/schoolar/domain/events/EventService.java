package dev.thewlabs.schoolar.domain.events;

import dev.thewlabs.schoolar.domain.events.dtos.EventDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventMapper mapper;
    private final EventRepository repository;

    @Autowired
    public EventService(EventMapper mapper, EventRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<EventDetailsDto> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable).map(mapper::entityToDto);
    }
}
