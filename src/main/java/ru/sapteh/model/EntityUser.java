package ru.sapteh.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class EntityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email",nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<EntityUsersRoles> roles;


    @Override
    public String toString() {
        return "EntityUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' + '}';
    }
}
