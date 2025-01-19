package org.proyecto.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.proyecto.proyecto.controller.MenuController;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.IOException;

/**
 * Clase principal de la aplicación de menú.
 * Extiende Application y maneja la inicialización de la aplicación.
 */
public class MenuApplication extends Application {

    /**
     * Método start que se ejecuta al inicio de la aplicación.
     * Muestra el menú principal en la ventana dada.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el menú.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Crear una instancia del controlador del menú
        MenuController controller = new MenuController();
        //Se muestra la pantalla del menu
        controller.showMenu(stage);
    }

    /**
     * Método principal de la aplicación.
     * Llama al método launch() para iniciar la aplicación.
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}