package com.employee.manager.mapper;

import com.employee.manager.dto.GroupDto;
import com.employee.manager.pdo.Group;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GroupMapper {

    Group groupDtoToGroup(GroupDto groupDto);

    GroupDto groupToGroupDto(Group group);
}
