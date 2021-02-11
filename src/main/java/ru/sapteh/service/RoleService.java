package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.EntityRole;

import java.util.List;

public class RoleService implements Dao<EntityRole, Integer> {

    private final SessionFactory factory;
    public RoleService(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void save(EntityRole role) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(EntityRole role) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.update(role);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(EntityRole role) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(role);
            session.getTransaction().commit();
        }
    }


    @Override
    public EntityRole read(Integer integer) {
        try (Session session = factory.openSession()){
            return session.get(EntityRole.class, integer);
        }
    }

    @Override
    public List<EntityRole> readAll() {
        try (Session session = factory.openSession()) {
            Query<EntityRole> result = session.createQuery("FROM EntityRole");
            return result.list();
        }
    }
}
