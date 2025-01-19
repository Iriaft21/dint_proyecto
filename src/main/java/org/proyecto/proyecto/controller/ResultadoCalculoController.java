package org.proyecto.proyecto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.CalculoTela;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.IOException;

public class ResultadoCalculoController {

    @FXML
    private Button btnAtras;

    @FXML
    private TextField txt_altoBordado;

    @FXML
    private TextField txt_altoTotal;

    @FXML
    private TextField txt_largoBordado;

    @FXML
    private TextField txt_largoTotal;

    @FXML
    void onClickAtras(ActionEvent event) {
        try {
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btnAtras);
            CalculadoraController calculadoraController = new CalculadoraController().showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCalculoFromMain(CalculoTela calculoTela){
        if (calculoTela != null) {
            // Redondear a dos decimales
            String alto = String.format("%.2f", calculoTela.tamanhoBordadoAlto());
            String largo = String.format("%.2f", calculoTela.tamanhoBordadoLargo());
            String largoTotal = String.format("%.2f", calculoTela.tamanhoTelaLargo());
            String altoTotal = String.format("%.2f", calculoTela.tamanhoTelaAlto());

            // Actualizar los campos de texto
            txt_altoBordado.setText(alto);
            txt_largoBordado.setText(largo);
            txt_largoTotal.setText(largoTotal);
            txt_altoTotal.setText(altoTotal);
        }
    }

    public ResultadoCalculoController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_RESULTADO_CALCULADORA.getDescripcion(),Constantes.TITULO_PANTALLA_RESULTADO_CALCULADORA.getDescripcion(),600,350);
        return fxmlLoader.getController();
    }
}
