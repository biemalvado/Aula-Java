package com.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Aponta para o seu ficheiro FXML correto
        Parent root = FXMLLoader.load(getClass().getResource("/com.template/template.fxml"));

        primaryStage.setTitle("CRUD de Passarinhos");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}