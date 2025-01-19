package org.proyecto.proyecto.utils;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.proyecto.proyecto.controller.MenuController;

import java.io.File;

/**
 * Clase que contiene métodos estáticos que se usan varias veces en el programa
 */
public class Utils {

    /**
     * Abre un cuadro de diálogo para seleccionar una imagen y establece la imagen seleccionada en el ImageView que le pasamos.
     *
     * @param img El ImageView donde se mostrará la imagen seleccionada.
     */
    public static void seleccionarImagen(ImageView img){
        // Crear un FileChooser para seleccionar archivos
        FileChooser fileChooser = new FileChooser();
        // Establecer el título del cuadro de diálogo
        fileChooser.setTitle("Seleccionar imagen");
        // Agregar filtros de extensión para permitir solo archivos de imagen
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
        );
        // Mostrar el cuadro de diálogo para seleccionar un archivo
        File selectedFile = fileChooser.showOpenDialog(null);

        // Si se selecciona un archivo, cargarlo y establecerlo en el ImageView
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            img.setImage(image);
        }
    }

    /**
     * Cierra la pantalla actual para volver al menú principal.
     *
     * @param button El botón que activa esta acción.
     */
    public static void irAtrasMenu(Button button){
        try {
            //Cerramos la pantalla actual
            Stage stage = (Stage) new PantallaUtils().cerrarEstaPantalla(button);
            stage.close();

            //Mostramos el menú principal
            MenuController menuController = new MenuController().showMenu(stage);
        } catch (Exception e) {
            //En caso de error, imprimimos el motivo
            e.printStackTrace();
        }
    }
}
