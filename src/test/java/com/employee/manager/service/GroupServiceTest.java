package com.employee.manager.service;

import com.employee.manager.dto.GroupDto;
import com.employee.manager.mapper.GroupMapper;
import com.employee.manager.pdo.Group;
import com.employee.manager.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Spy
    private GroupMapper groupMapper;

    @InjectMocks
    private GroupService groupService;

    @Test
    public void testGetGroupsSuccessfully() {
        Group group1 = Group.builder()
                .id(1L)
                .name("book")
                .address("Khreschatyk")
                .city("Kyiv")
                .country("Ukraine")
                .postalCode("1111")
                .build();

        Group group2 = Group.builder()
                .id(2L)
                .name("tree")
                .address("Svobody")
                .city("Lviv")
                .country("Ukraine")
                .postalCode("2222")
                .build();

        when(groupRepository.findAll()).thenReturn(List.of(group1, group2));

        List<GroupDto> employees = groupService.getAllGroups();

        assertThat(employees).hasSize(2);
    }
}
