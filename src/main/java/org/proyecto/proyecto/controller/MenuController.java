package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Strings;

import java.io.File;
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
    private ImageView img_intro1;

    @FXML
    private ImageView img_intro2;

    @FXML
    private Label lbl_titulo;

    public MenuController showMenu(Stage stage){
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_INICIAL.getDescripcion(),Constantes.TITULO_PAGINA_INICIAL.getDescripcion(),450,400);
            return fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void initialize(){
        File f = new File("src/main/resources/imagenesProyecto/punto-de-cruz.png");
        img_intro1.setImage(new Image("file:///" + f.getAbsolutePath().replace("\\", "/")));
        img_intro2.setImage(new Image("file:///" + f.getAbsolutePath().replace("\\", "/")));
        img_intro1.setFitHeight(50);
        img_intro1.setFitWidth(65);
        img_intro2.setFitHeight(50);
        img_intro2.setFitWidth(65);

        btn_proyectos.setText(Strings.TITULO_PROYECTOS.getDescripcion());
        btn_calculadora.setText(Strings.TITULO_CALCULADORA.getDescripcion());
        btn_inventario.setText(Strings.TITULO_INVENTARIO.getDescripcion());
        btn_salir.setText(Strings.BOTON_SALIR.getDescripcion());
        lbl_titulo.setText(Strings.TITULO_PAGINA_INICIAL.getDescripcion());
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