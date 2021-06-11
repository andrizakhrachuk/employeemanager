package com.employee.manager.service;

import com.employee.manager.dto.GroupDto;
import com.employee.manager.pdo.Group;
import com.employee.manager.repository.GroupRepository;
import com.employee.manager.mapper.GroupMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(groupMapper::groupToGroupDto)
                .collect(Collectors.toList());
    }

    public Optional<GroupDto> getGroup(Long id) {
        return groupRepository.findById(id).map(groupMapper::groupToGroupDto);
    }

    public GroupDto createGroup(@NotNull GroupDto groupDto) {
        Group group = groupMapper.groupDtoToGroup(groupDto);
        Group createdGroup = groupRepository.save(group);

        return groupMapper.groupToGroupDto(createdGroup);
    }

    public GroupDto updateGroup(@NotNull GroupDto groupDto) {
//        Objects.requireNonNull(groupDto.getId(), "Group id is required");

//        Group group = groupRepository.findById(groupDto.getId()).orElseThrow(IllegalArgumentException::new);
        Group group = groupMapper.groupDtoToGroup(groupDto);
        Group updatedGroup = groupRepository.save(group);

        return groupMapper.groupToGroupDto(updatedGroup);
    }

    public void deleteGroup(@NotNull Long id) {
        groupRepository.deleteById(id);
    }
}
