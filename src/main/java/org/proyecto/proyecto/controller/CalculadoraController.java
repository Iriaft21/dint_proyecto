package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.IOException;

public class CalculadoraController {

    @FXML
    private Button btn_calcular;

    @FXML
    private Button btn_limpiarCampos;

    @FXML
    private Button btn_salir;

    @FXML
    private TextField txt_alto;

    @FXML
    private TextField txt_largo;

    @FXML
    void onClickCalcular(ActionEvent event) {
        //Mostrar los datos en otra pantalla
    }

    @FXML
    void onClickLimpiarCampos(ActionEvent event) {

    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

    public CalculadoraController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_CALCULADORA.getDescripcion(),Constantes.TITULO_PANTALLA_CALCULADORA.getDescripcion(),600,600);
        CalculadoraController controller = fxmlLoader.getController();

        //PASAMOS EL CONTENIDO QUE HA INTRODUCIDO EL USUARIO EN EL TEXTFIELD DE ESTA PANTALLA
        //A LA SEGUNDA PANTALLA
        //controller.setTextFromMain(textField.getText());
        return controller;
    }
}
