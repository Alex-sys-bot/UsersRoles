package ru.sapteh.dao;

import java.util.List;

public interface Dao<Entity, Key> {
    void save(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
    Entity read(Key key);
    List<Entity> readAll();
}
