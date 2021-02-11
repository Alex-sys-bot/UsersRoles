package ru.sapteh.controls;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.EntityRole;
import ru.sapteh.model.EntityUser;
import ru.sapteh.model.EntityUsersRoles;
import ru.sapteh.service.RoleService;
import ru.sapteh.service.UserRoleService;
import ru.sapteh.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ControllerSetRole {
    private final SessionFactory factory;

    private final ObservableList<EntityUser> usersList = FXCollections.observableArrayList();
    private final ObservableList<EntityRole> rolesList = FXCollections.observableArrayList();;

    public ControllerSetRole(){
        factory = new Configuration().configure().buildSessionFactory();
    }

//    comboBox;
    @FXML
    private ComboBox<EntityUser> cbUsers;
    @FXML
    private ComboBox<EntityRole> cbRoles;

//    date;
    @FXML
    private DatePicker dateReg;

    public void initialize(){
        initUsersRoles();
        cbUsers.setItems(usersList);
        cbRoles.setItems(rolesList);
    }

    public void initUsersRoles(){
        Dao<EntityRole, Integer> daoRole = new RoleService(factory);
        Dao<EntityUser, Integer> daoUser = new UserService(factory);
        usersList.addAll(daoUser.readAll());
        rolesList.addAll(daoRole.readAll());
    }

    public void butSetRole() throws ParseException {
        Dao<EntityUsersRoles, Integer> daoUsersRoles = new UserRoleService(factory);
        EntityUsersRoles usersRoles = new EntityUsersRoles();

        daoUsersRoles.save(usersRoles);

    }
}
