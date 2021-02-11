package ru.sapteh.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_role")
public class EntityUsersRoles {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "registration_date")
    private Date reagistrationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", nullable = false)
    private EntityUser user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roles_id",nullable = false)
    private EntityRole role;


    @Override
    public String toString() {
        return "EntityUsersRoles{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
