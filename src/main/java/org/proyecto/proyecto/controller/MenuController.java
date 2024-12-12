package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button btn_calculadora;

    @FXML
    private Button btn_inventario;

    @FXML
    private Button btn_proyectos;

    @FXML
    private Button btn_salir;

    @FXML
    void onClickCalculadora(ActionEvent event) {
        try {
            // Obtén el Stage (ventana principal)
            Stage primaryStage = (Stage) btn_calculadora.getScene().getWindow();

            new CalculadoraController().showSecondFrame(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickInventario(ActionEvent event) {
        try {
            // Obtén el Stage (ventana principal)
            Stage primaryStage = (Stage) btn_inventario.getScene().getWindow();

            new InventarioController().showSecondFrame(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickProyectos(ActionEvent event) {
        try {
            // Obtén el Stage (ventana principal)
            Stage primaryStage = (Stage) btn_proyectos.getScene().getWindow();

            new ProyectosController().showSecondFrame(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }


    public void startPrimeFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/proyecto/proyecto/menu-view.fxml"));
        GridPane root = loader.load();

        Scene scene = new Scene(root, 300, 240);
        Stage primaryStage = new Stage();

        //scene.getStylesheets().add(getClass().getResource("/org/proyecto/proyecto/css/application.css").toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.getIcons().add(new Image("images/punto-de-cruz.png"));
        primaryStage.setTitle("Menú");

        primaryStage.show();
    }
}