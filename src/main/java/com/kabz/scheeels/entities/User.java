package com.kabz.scheeels.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;

    @Email
    @Column(nullable = false)
    private String email;

    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public String getFullName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
