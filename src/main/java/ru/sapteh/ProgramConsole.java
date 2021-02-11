package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.EntityUser;
import ru.sapteh.service.UserService;

public class ProgramConsole {
    public static void main(String[] args){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Dao<EntityUser, Integer> daoUsers = new UserService(factory);
//        DaoUsersRoles<EntityRole, Integer> daoRole = new RoleService(factory);
//        DaoUsersRoles<EntityUsersRoles, Integer> daoUserRole = new UserRoleService(factory);

        System.out.println(daoUsers.read(1));
        factory.close();
    }
}
