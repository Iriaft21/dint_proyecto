package org.proyecto.proyecto.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.proyecto.proyecto.MenuApplication;

import java.io.File;
import java.io.IOException;

/**
 * Clase con métodos estáticos relacionados con las pantallas del programa
 */
public class PantallaUtils {

    /**
     * Metodo genérico para abrir una pantalla, dependiendo de la ruta de la vista pasada
     * por parámetro
     *
     * @param stage le pasamos un stage
     * @param vista le pasamos la vista que se va a cargar
     * @param titulo titulo de la pantalla
     * @param ancho ancho de la pantalla
     * @param alto alto de la pantalla
     * @return El cargador del FXML
     * @thows IOException En caso de error de entrada o salida
     */
    public FXMLLoader showEstaPantalla(Stage stage, String vista, String titulo, int ancho, int alto) throws IOException {
        //Se cre un FXMLLoader para cargar el archivo FXML de la vista
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource(vista));
        //Se crea una nueva escena con el ancho y alto especificados y cargar el contenido del archivo FXML
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);

        // Por algún motivo esto no funciona de ninguna forma:
        // scene.getStylesheets().add(MenuApplication.class.getResource("css/application.css").toExternalForm());
        // Workaround para que funcione, forzandolo con el path absoluto:
        // Creamos un objeto File al que le pasamos la direccion del archivo css
        File f = new File("src/main/java/css/application.css");
        // Agregamos la hoja de estilos CSS a la escena utilizando el path absoluto
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        //Le asignamos un titulo a la ventana
        stage.setTitle(titulo);
        // Establecemos la escena en la ventana
        stage.setScene(scene);
        // Mostramos la ventana
        stage.show();
        // Devolvemos el FXMLLoader utilizado para cargar la vista
        return fxmlLoader;
    }

    /**
     * Este métodoo cierra la pantalla actual, a partir del stage actual.
     * El stage se obtiene del botón que se accionó en el momento.
     * Queda genérico para cualquier botón de esta pantalla.
     *
     * @param botonDelAction el pasamos el botón que accionó
     * @return El stage de la pantalla a cerrar
     */
    public Stage cerrarEstaPantalla(Button botonDelAction){
        // Se obtiene el Stage actual desde la escena donde se encuentra botón
        Stage stageAhora = (Stage) botonDelAction.getScene().getWindow();
        //Cerramos la ventana actual
        stageAhora.close();
        //Devolvemos el objeto Stage que se cerró
        return stageAhora;
    }
}
