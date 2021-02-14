package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.EntityUsersRoles;

import java.util.List;

public class UserRoleService implements Dao<EntityUsersRoles, Integer> {

    private final SessionFactory factory;
    public UserRoleService(SessionFactory factory){
        this.factory = factory;
    }


    @Override
    public void save(EntityUsersRoles user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(EntityUsersRoles user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(EntityUsersRoles user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public EntityUsersRoles read(Integer integer) {
        try (Session session = factory.openSession()){
            return session.get(EntityUsersRoles.class, integer);
        }
    }

    @Override
    public List<EntityUsersRoles> readAll() {
        try (Session session = factory.openSession()) {
            Query<EntityUsersRoles> result = session.createQuery("FROM EntityUsersRoles ");
            return result.list();
        }
    }
}
