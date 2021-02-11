package ru.sapteh.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "role")
public class EntityRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name", nullable = false)
    private String role_name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<EntityUsersRoles> users;

    @Override
    public String toString() {
        return role_name;
    }
}
