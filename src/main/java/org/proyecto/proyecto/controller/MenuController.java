package org.proyecto.proyecto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Utils;

import java.io.File;
import java.io.IOException;

/**
 * Controlador de la pantalla del menú
 *
 * Contiene métodos para derivar en otras pantallas o salir directamente, asi como un metodo para cargar la pantalla.
 */
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

    /**
     * Muestra el menú principal en una ventana
     *
     * @param stage La ventana principal donde se mostrará el menú
     * @return El controlador del menú principal
     */
    public MenuController showMenu(Stage stage){
        FXMLLoader fxmlLoader = null;
        try {
            // Carga la pantalla inicial utilizando PantallaUtils
            fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_INICIAL.getDescripcion(), Constantes.TITULO_PAGINA_INICIAL.getDescripcion(), 450, 400);
            // Devuelve el controlador del menú
            return fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inicializa el controlador configurando unas imágenes de la interfaz
     */
    public void initialize(){
        // Crea un objeto File para la imagen de introducción
        File f = new File("src/main/resources/imagenesProyecto/punto-de-cruz.png");
        // Establece la imagen
        img_intro1.setImage(new Image("file:///" + f.getAbsolutePath().replace("\\", "/")));
        // Establece la imagen
        img_intro2.setImage(new Image("file:///" + f.getAbsolutePath().replace("\\", "/")));
        // Ajusta el alto de las imagenes
        img_intro1.setFitHeight(50);
        img_intro2.setFitHeight(50);
        // Ajusta el ancho de las imagenes
        img_intro1.setFitWidth(65);
        img_intro2.setFitWidth(65);
    }

    /**
     * Cambia de pantalla a la de la calculadora
     *
     * @param event El evento de acción
     */
    @FXML
    private void onClickCalculadora(ActionEvent event) {
        //Llamamos al método que nos lleva a la pantalla de la calculadora
        Utils.irPantallaCalculdora(btn_calculadora);
    }

    /**
     * Cambia de pantalla a la del inventario
     *
     * @param event El evento de acción
     */
    @FXML
    private void onClickInventario(ActionEvent event) {
        try {
            // Cerramos la pantalla actual y obtenemos la ventana principal
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_inventario);
            // Mostramos la pantalla del inventario
            InventarioController inventarioController = new InventarioController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            // En caso de error, imprimimos las causa
            e.printStackTrace();
        }
    }

    /**
     * Cambia de pantalla a la del seguimiento de proyectos
     *
     * @param event El evento de acción
     */
    @FXML
    private void onClickProyectos(ActionEvent event) {
        try {
            // Cerramos la pantalla actual y obtenemos la ventana principal
            Stage primaryStage = new PantallaUtils().cerrarEstaPantalla(btn_proyectos);
            // Mostramos la pantalla del seguimiento de proyectos
            ProyectosController proyectosController = new ProyectosController().showEstaPantalla(primaryStage);
        } catch (Exception e) {
            // En caso de error, imprimimos las causa
            e.printStackTrace();
        }
    }

    /**
     * Acción para salir de la pantalla actual
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickSalir(ActionEvent event) {
        //LLamamosal correspondiente método de Utils para salir del programa
        Utils.botonSalir();
    }

}