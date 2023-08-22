package dev.thewlabs.schoolar.domain.attendances;

import dev.thewlabs.schoolar.domain.attendances.dtos.AttendanceDetailsDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {
    protected final ModelMapper modelMapper;

    protected AttendanceMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        this.modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.modelMapper.getConfiguration().setMethodAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public AttendanceDetailsDto entityToDto(Attendance entity) {
        return modelMapper.map(entity, AttendanceDetailsDto.class);
    }
}
