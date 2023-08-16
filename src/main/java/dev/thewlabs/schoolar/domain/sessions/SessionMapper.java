package dev.thewlabs.schoolar.domain.sessions;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.sessions.dtos.CreateSessionDTO;
import dev.thewlabs.schoolar.domain.sessions.dtos.SessionDetailsDTO;
import dev.thewlabs.schoolar.domain.sessions.dtos.UpdateSessionDTO;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper extends Mapper<Session, CreateSessionDTO, UpdateSessionDTO, SessionDetailsDTO> {
    @Override
    public Session dtoToEntity(CreateSessionDTO dto) {
        return modelMapper.map(dto, Session.class);
    }

    @Override
    public SessionDetailsDTO entityToDto(Session entity) {
        return modelMapper.map(entity, SessionDetailsDTO.class);
    }

    @Override
    public void updateEntityFromDto(UpdateSessionDTO dto, Session entity) {
        modelMapper.map(dto, entity);
    }
}
