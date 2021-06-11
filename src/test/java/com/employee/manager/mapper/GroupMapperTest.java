package com.employee.manager.mapper;

import com.employee.manager.dto.GroupDto;
import com.employee.manager.pdo.Group;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupMapperTest {

    private final GroupMapper mapper = Mappers.getMapper(GroupMapper.class);

    @Test
    public void testGroupToGroupDto() {
        Group group = Group.builder()
                .id(1L)
                .name("jug-group")
                .address("Address")
                .city("Lviv")
                .country("Ukraine")
                .postalCode("1234")
                .userRole(Group.UserRole.BUSINESS)
                .build();

        GroupDto groupDto = mapper.groupToGroupDto(group);

        assertThat(groupDto.getId()).isEqualTo(1L);
        assertThat(groupDto.getName()).isEqualTo("jug-group");
        assertThat(groupDto.getAddress()).isEqualTo("Address");
        assertThat(groupDto.getCity()).isEqualTo("Lviv");
        assertThat(groupDto.getCountry()).isEqualTo("Ukraine");
        assertThat(groupDto.getPostalCode()).isEqualTo("1234");
        assertThat(groupDto.getUserRole()).isEqualTo(GroupDto.UserRoleDto.BUSINESS);
    }

    @Test
    public void testGroupDtoToGroup() {
        GroupDto groupDto = GroupDto.builder()
                .id(2L)
                .name("jug-tour")
                .address("Address")
                .city("Lviv")
                .country("Ukraine")
                .postalCode("1234")
                .userRole(GroupDto.UserRoleDto.BUSINESS)
                .build();

        Group group = mapper.groupDtoToGroup(groupDto);

        assertThat(group.getId()).isEqualTo(2L);
        assertThat(group.getName()).isEqualTo("jug-tour");
        assertThat(group.getAddress()).isEqualTo("Address");
        assertThat(group.getCity()).isEqualTo("Lviv");
        assertThat(group.getCountry()).isEqualTo("Ukraine");
        assertThat(group.getPostalCode()).isEqualTo("1234");
        assertThat(group.getUserRole()).isEqualTo(Group.UserRole.BUSINESS);
    }
}
