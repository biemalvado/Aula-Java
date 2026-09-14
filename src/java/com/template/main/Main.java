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

public class Main extends Application {

    @Override
    public void start(
            Stage primaryStage
    ) throws Exception {

        /*
         * ==================================
         * MONTAGEM DAS DEPENDÊNCIAS
         * ==================================
         */

        InterfacePassarinhoDAO dao =
                new PassarinhoDAO();


        InterfacePassarinhoValidador validador =
                new PassarinhoValidator();


        InterfacePassarinhoService service =
                new PassarinhoService(
                        dao,
                        validador
                );


        /*
         * ==================================
         * CARREGAMENTO DO FXML
         * ==================================
         */

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com.template/template.fxml"
                        )
                );


        /*
         * ==================================
         * INJEÇÃO NO CONTROLLER
         * ==================================
         */

        loader.setControllerFactory(
                controllerClass -> {

                    if (
                            controllerClass
                                    == PassarinhoController.class
                    ) {

                        return new PassarinhoController(
                                service
                        );
                    }

                    try {

                        return controllerClass
                                .getDeclaredConstructor()
                                .newInstance();

                    } catch (Exception e) {

                        throw new RuntimeException(
                                "Erro ao criar o Controller.",
                                e
                        );
                    }
                }
        );


        Parent root =
                loader.load();


        Scene scene =
                new Scene(root);


        primaryStage.setTitle(
                "CRUD de Passarinhos"
        );

        primaryStage.setScene(
                scene
        );

        primaryStage.show();
    }


    public static void main(
            String[] args
    ) {

        launch(args);
    }
}