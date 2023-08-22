package dev.thewlabs.schoolar.domain.sessions;

import dev.thewlabs.schoolar.core.interfaces.CrudService;
import dev.thewlabs.schoolar.domain.sessions.dtos.CreateSessionDto;
import dev.thewlabs.schoolar.domain.sessions.dtos.SessionDetailsDto;
import dev.thewlabs.schoolar.domain.sessions.dtos.UpdateSessionDto;
import dev.thewlabs.schoolar.domain.users.User;
import dev.thewlabs.schoolar.shared.exceptions.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SessionService implements CrudService<SessionDetailsDto, CreateSessionDto, UpdateSessionDto> {
    private final SessionMapper mapper;
    private final SessionRepository repository;

    @Autowired
    public SessionService(SessionMapper mapper, SessionRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Page<SessionDetailsDto> findAll(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    public SessionDetailsDto findById(@NotNull UUID id) throws NotFoundException {
        Session session = repository.findSessionById(id);

        return mapper.entityToDto(session);
    }

    @Override
    @Transactional
    public SessionDetailsDto create(@NotNull CreateSessionDto dto) {
        Session session = mapper.dtoToEntity(dto);

        List<User> attendees = session.getCourse().getAttendees();

        session.setAttendees(attendees);

        repository.save(session);

        return mapper.entityToDto(session);
    }

    @Override
    public SessionDetailsDto update(@NotNull UUID id, @NotNull UpdateSessionDto dto) throws NotFoundException {
        Session session = repository.findSessionById(id);

        mapper.updateEntityFromDto(dto, session);

        repository.save(session);

        return mapper.entityToDto(session);
    }

    @Override
    public void delete(@NotNull UUID id) throws NotFoundException {
        Session session = repository.findSessionById(id);

        repository.delete(session);
    }
}
