package org.proyecto.proyecto.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Clase para mostrar alertas
 */
public class AlertaUtils {

    /**
     * Muestra una alerta informativa con el título y mensaje proporcionados
     *
     * @param title El título de la alerta
     * @param sms   El mensaje de la alerta
     */
    public static void showAlertInformativa(String title, String sms){
        // Crear una alerta de tipo informativa
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Establecer el título de la alerta
        alert.setTitle(title);
        // Establecer el mensaje de la alerta
        alert.setContentText(sms);
        // Muestra la alerta y espera a que el usuario la cierre
        alert.showAndWait();
    }

    /**
     * Muestra una alerta informativa personalizada con el título, mensaje y un enlace proporcionado
     *
     * @param title     El título de la alerta
     * @param sms       El mensaje de la alerta
     * @param hyperlink Un hipervínculo que se mostrará en la alerta.
     */
    public static void showAlertInformativa(String title, String sms, Hyperlink hyperlink) {
        // Crea una alerta de tipo informativa
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Establece el título de la alerta
        alert.setTitle(title);
        // Establece que no haya encabezado en la alerta
        alert.setHeaderText(null);
        // Crear un contenedor para el contenido de la alerta
        VBox content = new VBox();
        // Crear una etiqueta para el mensaje y establece sus propiedades
        Label mensaje = new Label(sms);
        mensaje.setWrapText(true);
        mensaje.setMaxWidth(250);
        //Se agrega el mensaje y el hipervínculo al contenedor
        content.getChildren().addAll(mensaje, hyperlink);
        // Se establece el contenido del cuadro de diálogo de la alerta
        alert.getDialogPane().setContent(content);
        //Se establece el tamaño preferido del cuadro de diálogo de la alerta
        alert.getDialogPane().setPrefSize(300, 150);
        //Muestra la alerta y espera a que el usuario la cierre
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de error con el título y mensaje proporcionados.
     *
     * @param title El título de la alerta.
     * @param sms   El mensaje de la alerta.
     */
    public static void showAlertError(String title, String sms){
        // Crea una alerta de tipo error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // Establece el título de la alerta
        alert.setTitle(title);
        // Establece el mensaje de la alerta
        alert.setContentText(sms);
        //Muestra la alerta y espera a que el usuario la cierre
        alert.showAndWait();
    }
}
