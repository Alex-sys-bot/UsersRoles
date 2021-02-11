package ru.sapteh.controls;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.EntityRole;
import ru.sapteh.model.EntityUser;
import ru.sapteh.service.RoleService;
import ru.sapteh.service.UserService;

import java.io.IOException;


public class ControllerMainWindow {
    private final SessionFactory factory;

    public ControllerMainWindow(){
        factory = new Configuration().configure().buildSessionFactory();
    }
    private final ObservableList<EntityUser> usersList = FXCollections.observableArrayList();
    private final ObservableList<EntityRole> rolesList = FXCollections.observableArrayList();;

//    tables;

//    tableUsers;
    @FXML
    private TableView<EntityUser> tableUsers;
    @FXML
    private TableColumn<EntityUser,String> columnFirstName;
    @FXML
    private TableColumn<EntityUser,String> columnLastName;

//    tableRoles;
    @FXML
    private TableView<EntityRole> tableRoles;
    @FXML
    private TableColumn<EntityRole, Integer> columnID;
    @FXML
    private TableColumn<EntityRole, String> columnNameRoles;

//    labels;
    @FXML
    private Label labelSize;
    @FXML
    private Label labelID;
    @FXML
    private Label labelFirstName;
    @FXML
    private Label labelLastName;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelRegDate;


//    TextField
    @FXML
    private TextField txtRoleName;

//    comboBox;
    @FXML
    private ComboBox<String> comboRoles;

    public void initialize(){
//        intiUsers;
        initUserList();

        tableUsers.setItems(usersList);
        columnFirstName.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getFirstName()));
        columnLastName.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getLastName()));

        tableRoles.setItems(rolesList);
        columnID.setCellValueFactory(r -> new SimpleObjectProperty<>(r.getValue().getId()));
        columnNameRoles.setCellValueFactory(r -> new SimpleObjectProperty<>(r.getValue().getRole_name()));


//      quantityRowsUsers;
        labelSize.setText(String.valueOf("Rows: " + usersList.size()));

        tableUsers.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> showPersonDet(newValue));

    }

    public void showPersonDet(EntityUser takeValue) {
        labelID.setText(String.valueOf(takeValue.getId()));
        labelFirstName.setText(takeValue.getFirstName());
        labelLastName.setText(takeValue.getLastName());
        labelEmail.setText(takeValue.getEmail());
        labelRegDate.setText(String.valueOf(takeValue.getRoles().iterator().next().getRole().getRole_name()));
        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 0; i < takeValue.getRoles().size(); i++) {
               list.add(takeValue.getRoles().iterator().next().getRole().getRole_name());
        }
        comboRoles.setItems(list);
    }

    public void initUserList(){
        Dao<EntityUser,Integer> daoUsers = new UserService(factory);
        Dao<EntityRole, Integer> daoRoles = new RoleService(factory);
        usersList.addAll(daoUsers.readAll());
        rolesList.addAll(daoRoles.readAll());
    }

    public void buttonAddRole(){
        Dao<EntityRole, Integer> daoRoles = new RoleService(factory);
        EntityRole role = new EntityRole();
        role.setRole_name(txtRoleName.getText());
        daoRoles.save(role);
    }



//    buttons;
    public void butAddNewUser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addUserWindow.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add user");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @SneakyThrows
    public void butSetRole(){
        Parent root = FXMLLoader.load(getClass().getResource("/view/setRoleWindow.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add user");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
