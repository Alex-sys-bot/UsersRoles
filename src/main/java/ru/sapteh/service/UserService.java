package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.EntityUser;

import java.util.List;

public class UserService implements Dao<EntityUser, Integer> {

    private final SessionFactory factory;
    public UserService(SessionFactory factory){
        this.factory = factory;
    }


    @Override
    public void save(EntityUser user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(EntityUser user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(EntityUser user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public EntityUser read(Integer integer) {
        try (Session session = factory.openSession()){
            return session.get(EntityUser.class, integer);
        }
    }

    @Override
    public List<EntityUser> readAll() {
        try (Session session = factory.openSession()) {
            Query<EntityUser> result = session.createQuery("FROM EntityUser");
            return result.list();
        }
    }
}
