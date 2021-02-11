package ru.sapteh;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProgramStart extends Application {
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/mainWindow.fxml"));
        stage.setTitle("Users");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
