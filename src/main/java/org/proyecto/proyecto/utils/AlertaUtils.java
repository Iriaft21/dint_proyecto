package org.proyecto.proyecto.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AlertaUtils {

    public static void showAlertInformativa(String title, String sms){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(sms);
        alert.showAndWait();
    }

    public static void showAlertInformativa(String title, String sms, Hyperlink hyperlink) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title); alert.setHeaderText(null);
        VBox content = new VBox();
        Label mensaje = new Label(sms);
        mensaje.setWrapText(true);
        mensaje.setMaxWidth(250);
        content.getChildren().addAll(mensaje, hyperlink);
        alert.getDialogPane().setContent(content);
        alert.getDialogPane().setPrefSize(300, 150);
        alert.showAndWait();
    }

    public static void showAlertError(String title, String sms){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(sms);
        alert.showAndWait();
    }
}
