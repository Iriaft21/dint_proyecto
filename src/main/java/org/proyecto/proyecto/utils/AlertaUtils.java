package org.proyecto.proyecto.utils;

import javafx.scene.control.Alert;

public class AlertaUtils {

    public static void showAlertInformativa(String title, String sms){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(sms);
        alert.showAndWait();
    }

    public static void showAlertError(String title, String sms){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(sms);
        alert.showAndWait();
    }
}
