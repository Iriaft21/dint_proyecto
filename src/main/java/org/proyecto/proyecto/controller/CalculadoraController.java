package org.proyecto.proyecto.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculadoraController {

    public void showSecondFrame(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/proyecto/proyecto/calculadora-view.fxml"));

        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(loader.load(),400,440));

        // Obtener el controlador de la segunda ventana y pasar el texto
        CalculadoraController calculadoraController = loader.getController();

        // Mostrar la segunda ventana
        secondStage.setTitle("Calculadora");
        secondStage.show();

        //CERRAR LA VENTANA ANTERIOR
        // Minimizar la ventana principal (bloquearla)
        primaryStage.setIconified(true); // Minimiza la ventana principal

        // Opción alternativa: deshabilitar la ventana principal completamente
        // primaryStage.setOpacity(0.5); // Desactiva la ventana principal (con opacidad reducida)

        // Cerrar la ventana principal cuando la segunda ventana se cierre
        secondStage.setOnCloseRequest(e -> primaryStage.close());
    }
}
