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
            MenuController controller = fxmlLoader.getController();
            return controller;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onClickCalculadora(ActionEvent event) {
        try {
            // Obtén el Stage (ventana principal)
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_calculadora);
            CalculadoraController controller = new CalculadoraController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickInventario(ActionEvent event) {
        try {
            // Obtén el Stage (ventana principal)
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_inventario);
            InventarioController controller = new InventarioController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickProyectos(ActionEvent event) {
        try {
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_proyectos);
            ProyectosController controller = new ProyectosController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonActionProyectos(){

    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

}