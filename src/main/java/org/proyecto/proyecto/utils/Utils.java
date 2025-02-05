package org.proyecto.proyecto.utils;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.proyecto.proyecto.controller.CalculadoraController;
import org.proyecto.proyecto.controller.InventarioController;
import org.proyecto.proyecto.controller.MenuController;
import org.proyecto.proyecto.controller.ProyectosController;
import org.proyecto.proyecto.modelo.Proyecto;

import java.io.File;

/**
 * Clase que contiene métodos estáticos que se usan varias veces en el programa
 */
public class Utils {

    /**
     * Abre un cuadro de diálogo para seleccionar una imagen y establece la imagen seleccionada en el ImageView que le pasamos
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

    /**
     * Método para salir del programa
     */
    public static void botonSalir(){
        Platform.exit();
    }

    /**
     * Método que nos lleva de una pantalla a la de la calculadora
     * @param menuItem_calculadora MenuItem desde el que nos movemos
     */
    public static void menuCalculadora(MenuItem menuItem_calculadora){
        try {
            // Obtenemos la ventana principal desde el menú de la calculadora y la cerramos
            Stage stage = (Stage) menuItem_calculadora.getParentPopup().getOwnerWindow().getScene().getWindow();
            stage.close();
            // Mostramos la pantalla de la calculadora
            CalculadoraController calculadoraController = new CalculadoraController().showEstaPantalla(stage);
        } catch (Exception e) {
            //En caso de errror, imprimimos la causa
            e.printStackTrace();
        }
    }

    /**
     * Método que nos lleva de una pantalla a la del inventario
     * @param menuItem_inventario MenuItem desde el que nos movemos
     */
    public static void menuInventario(MenuItem menuItem_inventario){
        try {
            // Obtenemos la ventana principal desde el menú del inventario y la cerramos
            Stage stage = (Stage) menuItem_inventario.getParentPopup().getOwnerWindow().getScene().getWindow();
            stage.close();
            // Mostramos la pantalla del inventario
            InventarioController inventarioController = new InventarioController().showEstaPantalla(stage);
        } catch (Exception e) {
            //En caso de errror, imprimimos la causa
            e.printStackTrace();
        }
    }

    /**
     * Método que nos lleva de una pantalla a la de seguimiento de proyectos
     * @param menuItem_proyectos MenuItem desde el que nos movemos
     */
    public static void menuProyectos(MenuItem menuItem_proyectos){
        try {
            // Obtenemos la ventana principal desde el menú de seguimiento de proyectos y la cerramos
            Stage stage = (Stage) menuItem_proyectos.getParentPopup().getOwnerWindow().getScene().getWindow();
            stage.close();
            // Mostramos la pantalla de seguimiento de proyectos
            ProyectosController proyectosController = new ProyectosController().showEstaPantalla(stage);
        } catch (Exception e) {
            //En caso de errror, imprimimos la causa
            e.printStackTrace();
        }
    }

    /**
     * Método para ir a la pantalla de la calculadora
     *
     * @param btn El botón que llama a la acción
     */
    public static void irPantallaCalculdora(Button btn){
        try {
            // Obtenemos la ventana actual y la cerramos
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn);
            // Mostramos la pantalla de la calculadora
            CalculadoraController calculadoraController = new CalculadoraController().showEstaPantalla(stage);
        } catch (Exception e) {
            //En caso de error, mostramos la causa
            e.printStackTrace();
        }
    }

    /**
     * Método para ir a la pantalla de seguimiento de proyectos a la vez que se le pasa al controller una lista
     *
     * @param btn El botón que llama a la acción
     * @params proyectos Lista que contiene los proyectos
     */
    public static void irPantallaProyectosPasandoLista(Button btn, ObservableList<Proyecto> proyectos){
        try {
            // Obtenemos la ventana actual y la cerramos
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn);
            // Mostramos la pantalla de seguimiento de los proyectos
            ProyectosController proyectosController = new ProyectosController().showEstaPantalla(stage);
            //Le pasamos la lista con los proyectos
            proyectosController.setObservableList(proyectos);
        } catch (Exception e) {
            //En caso de error, imprimimos la causa
            e.printStackTrace();
        }
    }

    /**
     * Método para comprobar si los TextFields van vacíos o si cumplen cierto formato
     *
     * @param textField Los TextFields de la pantalla que necesitamos comprobar
     * @return El valor del TextField ya extraído y pasado a int
     * @throws IllegalArgumentException Si el valor pasado está vacío o tiene el formato incorrecto
     */
    public static int validarTextFields(TextField textField){
        String texto = textField.getText().trim();
        //Comprobamos que el texto no vaya vacío
        if (texto.isEmpty()) {
            // Mostrar alerta informativa si los datos del hilo están vacíos
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
            //De paso lanzamos una excepcion para avisar del error
            throw new IllegalArgumentException("El campo no puede estar vacío");
            //Se verifica que la String sea un número entero, tanto positivo como negativo
        }else if(!texto.matches("-?\\d+")){
            // Mostramos alerta de error de formato
            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            //Se lanza una excepcion para visar del error
            throw new IllegalArgumentException("El campo debe contener solo números");
        }
        //Se devuelve el valor del TextField ya extraído y como un Int
        return Integer.parseInt(texto);
    }
}