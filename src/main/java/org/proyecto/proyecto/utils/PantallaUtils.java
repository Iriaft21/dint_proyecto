package org.proyecto.proyecto.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.proyecto.proyecto.MenuApplication;

import java.io.File;
import java.io.IOException;

public class PantallaUtils {

    /**
     * Metodo genérico para abrir una pantalla, dependiendo de la ruta de la vista pasada
     * por parámetro
     *
     * @param stage le pasamos un stage
     * @param vista le pasamos la vista que se va a cargar
     */
    public FXMLLoader showEstaPantalla(Stage stage, String vista, String titulo, int ancho, int alto) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource(vista));
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);

        // Por algun motivo esto no funciona de ninguna forma:
        // scene.getStylesheets().add(MenuApplication.class.getResource("css/application.css").toExternalForm());
        // Workaround para que funcione, forzandolo con el path absoluto:
        File f = new File("src/main/java/css/application.css");
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
        return fxmlLoader;
    }

    /**
     * Este métodoo cierra la pantalla actual, a partir del stage actual.
     * El stage se obtiene del botón que se accionó en el momento.
     * Queda genérico para cualquier botón de esta pantalla.
     *
     * @param botonDelAction el pasamos el botón que accionó
     */
    public Stage cerrarEstaPantalla(Button botonDelAction){
        Stage stageAhora = (Stage) botonDelAction.getScene().getWindow();
        stageAhora.close();
        return stageAhora;
    }
}
