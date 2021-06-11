package com.employee.manager.web;

import com.employee.manager.dto.GroupDto;
import com.employee.manager.service.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GroupController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService service;

    @Test
    public void testGetAllGroups() throws Exception {
        GroupDto group1 = prepareGroupDto(1L, "jug-tour", "Address", "Lviv", "Ukraine", "1234");

        GroupDto group2 = prepareGroupDto(2L, "jug-tour1", "Address1", "Lviv2", "GH", "1235");

        when(service.getAllGroups()).thenReturn(Arrays.asList(group1, group2));

        this.mockMvc.perform(get("/api/groups")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getResourceAsString("groups")));
    }

    @Test
    public void testGetGroupById() throws Exception {
        GroupDto groupDto = prepareGroupDto(1L, "jug-tour", "Address", "Lviv", "Ukraine", "1234");

        when(service.getGroup(1L)).thenReturn(Optional.of(groupDto));

        this.mockMvc.perform(get("/api/groups/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getResourceAsString("group")));
    }

    @Test
    public void testCreateNewGroup() throws Exception {
        GroupDto groupDto = prepareGroupDto(1L, "jug-tour1", "Address1", "Lviv1", "Ukraine", "1237");

        doReturn(groupDto).when(service).createGroup(any());

        this.mockMvc.perform(post("/api/groups")
                .content(asJsonString(groupDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(groupDto)));
    }

    @Test
    public void testUpdateGroup() throws Exception {
        GroupDto groupDto = prepareGroupDto(1L, "jug-tour5", "Address5", "Lviv5", "Ukraine", "1237");

        doReturn(groupDto).when(service).updateGroup(any(GroupDto.class));

        this.mockMvc.perform(put("/api/groups/1")
                .content(asJsonString(groupDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(groupDto)));
    }

    @Test
    public void testDeleteGroup() throws Exception {
        this.mockMvc.perform(delete("/api/groups/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).deleteGroup(eq(1L));
        verifyNoMoreInteractions(service);
    }

    private GroupDto prepareGroupDto(long id, String name, String address, String city, String country, String postalCode) {
        return GroupDto.builder()
                .id(id)
                .name(name)
                .address(address)
                .city(city)
                .country(country)
                .postalCode(postalCode)
                .userRole(GroupDto.UserRoleDto.BUSINESS)
                .build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getResourceAsString(String fileName) throws IOException {
        InputStream file = this.getClass().getResourceAsStream(fileName + ".json");
        Objects.requireNonNull(file);

        return IOUtils.toString(file, StandardCharsets.UTF_8);
    }
}
