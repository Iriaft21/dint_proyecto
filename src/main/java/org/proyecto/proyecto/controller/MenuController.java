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
            //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
            return fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    CalculadoraController onClickCalculadora(ActionEvent event) {
        try {
            // Obtén el Stage (ventana principal)
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_calculadora);
            return new CalculadoraController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    InventarioController onClickInventario(ActionEvent event) {
        try {
            // Obtén el Stage (ventana principal)
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_inventario);
            return new InventarioController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    ProyectosController onClickProyectos(ActionEvent event) {
        try {
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_proyectos);
            return new ProyectosController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void buttonActionProyectos(){

    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

}