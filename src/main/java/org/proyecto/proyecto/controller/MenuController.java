package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

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

    public MenuController showMenu(Stage stage){
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_INICIAL.getDescripcion(),Constantes.TITULO_PAGINA_INICIAL.getDescripcion(),300,200);
            return fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onClickCalculadora(ActionEvent event) {
        try {
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_calculadora);
            CalculadoraController calculadoraController = new CalculadoraController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickInventario(ActionEvent event) {
        try {
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_inventario);
            InventarioController inventarioController = new InventarioController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickProyectos(ActionEvent event) {
        try {
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_proyectos);
            ProyectosController proyectosController = new ProyectosController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

}