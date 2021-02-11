package ru.sapteh.controls;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.EntityUser;
import ru.sapteh.service.UserService;

public class ControllerAddUserWindow {

    private final SessionFactory factory;


    public ControllerAddUserWindow(){
        factory = new Configuration().configure().buildSessionFactory();
    }

//    textField;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;


//    buttons;
    public void butAddUser(){
        Dao<EntityUser, Integer> daoUsers = new UserService(factory);

        EntityUser user = new EntityUser();
        user.setFirstName(txtFirstName.getText());
        user.setLastName(txtLastName.getText());
        user.setEmail(txtEmail.getText());

        daoUsers.save(user);
    }
}
