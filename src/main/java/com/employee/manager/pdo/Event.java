package com.employee.manager.pdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Event {

    @Id
    private Long id;
    private Instant date;
    private String title;
    private String description;

    @ManyToMany
    @JoinTable(name = "event_customer",
            joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "event_customer_attendees_fk")),
            inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "event_customer_event_fk")))
    private Set<Customer> attendees;
}
