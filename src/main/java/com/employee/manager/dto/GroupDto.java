package com.employee.manager.dto;

import com.employee.manager.pdo.Event;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class GroupDto implements Serializable {

    private Long id;
    @NotNull
    private String name;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private UserRoleDto userRole;
    private List<Event> events = new ArrayList<>();

    @Builder
    public GroupDto(
            Long id,
            String name,
            String address,
            String city,
            String country,
            String postalCode,
            UserRoleDto userRole,
            List<Event> events) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.userRole = userRole;
        this.events = events == null ? new ArrayList<>() : new ArrayList<>(events);
    }

    public enum UserRoleDto {
        ADMIN,
        BUSINESS,
        READ_ONLY;
    }
}
