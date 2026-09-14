package com.template.main;

import com.template.controller.PassarinhoController;

import com.template.model.dao.InterfacePassarinhoDAO;
import com.template.model.dao.PassarinhoDAO;

import com.template.service.InterfacePassarinhoService;
import com.template.service.PassarinhoService;

import com.template.util.PassarinhoValidator;

import com.template.validator.InterfacePassarinhoValidador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage)
            throws IOException {


        // ==============================
        // DAO
        // ==============================

        InterfacePassarinhoDAO dao =
                new PassarinhoDAO();


        // ==============================
        // VALIDATOR
        // ==============================

        InterfacePassarinhoValidador validador =
                new PassarinhoValidator();


        // ==============================
        // SERVICE
        // ==============================

        InterfacePassarinhoService service =
                new PassarinhoService(
                        dao,
                        validador
                );


        // ==============================
        // FXML
        // ==============================

        FXMLLoader loader =
                new FXMLLoader(
                        Main.class.getResource(
                                "/com/template/template.fxml"
                        )
                );


        // ==============================
        // INJEÇÃO DO CONTROLLER
        // ==============================

        loader.setControllerFactory(
                tipo -> {

                    if (tipo ==
                            PassarinhoController.class) {

                        return new PassarinhoController(
                                service
                        );
                    }

                    try {

                        return tipo
                                .getDeclaredConstructor()
                                .newInstance();

                    } catch (Exception e) {

                        throw new RuntimeException(e);
                    }
                }
        );


        Parent root = loader.load();


        Scene scene =
                new Scene(root);


        stage.setTitle(
                "Cadastro de Passarinhos"
        );

        stage.setScene(scene);

        stage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}