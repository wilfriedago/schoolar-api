package dev.thewlabs.schoolar.domain.groups;

import dev.thewlabs.schoolar.core.abstracts.Mapper;
import dev.thewlabs.schoolar.domain.groups.dtos.CreateGroupDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.GroupDetailsDTO;
import dev.thewlabs.schoolar.domain.groups.dtos.UpdateGroupDTO;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper extends Mapper<Group, CreateGroupDTO, UpdateGroupDTO, GroupDetailsDTO> {
    @Override
    public Group dtoToEntity(CreateGroupDTO dto) {
        return modelMapper.map(dto, Group.class);
    }

    @Override
    public GroupDetailsDTO entityToDto(Group entity) {
        return modelMapper.map(entity, GroupDetailsDTO.class);
    }

    @Override
    public void updateEntityFromDto(UpdateGroupDTO dto, Group entity) {
        modelMapper.map(dto, entity);
    }
}
