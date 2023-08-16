package dev.thewlabs.schoolar.core.abstracts;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

public abstract class Mapper<T, C, U, R> {
    protected final ModelMapper modelMapper;

    protected Mapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        this.modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.modelMapper.getConfiguration().setMethodAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public abstract T dtoToEntity(C dto);

    public abstract R entityToDto(T entity);

    public abstract void updateEntityFromDto(U dto, T entity);
}
