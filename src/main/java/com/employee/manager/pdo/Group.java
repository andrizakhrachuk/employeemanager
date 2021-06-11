package com.employee.manager.pdo;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "customer_group")
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private UserRole userRole;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "customer_group_customer_fk"))
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "customer_group_event_fk"))
    private Set<Event> events;

    public enum UserRole {
        ADMIN,
        BUSINESS,
        READ_ONLY
    }
}
