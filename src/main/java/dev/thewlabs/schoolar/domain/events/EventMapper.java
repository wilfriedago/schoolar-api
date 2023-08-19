package dev.thewlabs.schoolar.domain.events;

import dev.thewlabs.schoolar.domain.events.dtos.EventDetailsDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    protected final ModelMapper modelMapper;

    protected EventMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        this.modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.modelMapper.getConfiguration().setMethodAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public EventDetailsDto entityToDto(Event entity) {
        return modelMapper.map(entity, EventDetailsDto.class);
    }
}
