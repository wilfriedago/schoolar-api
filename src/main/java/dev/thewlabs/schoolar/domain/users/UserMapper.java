package dev.thewlabs.schoolar.domain.users;

import dev.thewlabs.schoolar.domain.users.dtos.UserDetailsDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    protected final ModelMapper modelMapper;

    protected UserMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        this.modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.modelMapper.getConfiguration().setMethodAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public UserDetailsDTO entityToDto(User entity) {
        return modelMapper.map(entity, UserDetailsDTO.class);
    }
}
