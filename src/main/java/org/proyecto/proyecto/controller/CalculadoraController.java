package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.CalculoTela;
import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculadoraController {

    @FXML
    private Button btn_calcular;

    @FXML
    private Button btn_limpiarCampos;

    @FXML
    private Button btn_salir;

    @FXML
    private ComboBox<String> spinner_ct;

    @FXML
    private TextField txt_alto;

    @FXML
    private TextField txt_largo;

    @FXML
    private TextField txt_telaAcabado;

    @FXML
    private TextField txt_telaBorde;

    private ObservableList<String> tiposTelas;

    private CalculoTela calculoTela;

    @FXML
    ResultadoCalculoController onClickCalcular(ActionEvent event) {
        int alto = validarCampoEntero(txt_alto);
        int largo = validarCampoEntero(txt_largo);
        float hilosTela = extraerHilos();
        int telaAcabado = validarCampoEntero(txt_telaAcabado);
        int telaBorde = validarCampoEntero(txt_telaBorde);
        calculoTela = new CalculoTela(alto, largo, hilosTela, telaBorde, telaAcabado);

        //enviar a la otra pantalla la clase
        try {
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(
                    primaryStage,
                    Constantes.PAGINA_PANTALLA_RESULTADO_CALCULADORA.getDescripcion(),
                    Constantes.TITULO_PANTALLA_RESULTADO_CALCULADORA.getDescripcion(),
                    500,
                    500
            );

            ResultadoCalculoController resultadoCalculoController = fxmlLoader.getController();
            resultadoCalculoController.setClassFromMain(calculoTela);
            resultadoCalculoController.actualizarDatos();
            primaryStage.show();
            return resultadoCalculoController;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private int validarCampoEntero(TextField textField) throws IllegalArgumentException {
        String texto = textField.getText().trim();
        if (texto.isEmpty()) {
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
            throw new IllegalArgumentException("El campo no puede estar vacío");
        }
        try {
            int valor = Integer.parseInt(texto);
            if (valor < 0) {
                AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                throw  new IllegalArgumentException("El número no puede ser negativo");
            }
            return valor;
        } catch (NumberFormatException e) {
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            throw new IllegalArgumentException("El campo debe contener solo números");
        }
    }

    @FXML
    void onClickLimpiarCampos(ActionEvent event) {
        txt_alto.clear();
        txt_largo.clear();
        spinner_ct.setValue("14 ct (5,4 hilos/cm)");
        txt_telaAcabado.clear();
        txt_telaBorde.clear();
    }

    float extraerHilos(){
        String tipoTela = spinner_ct.getValue();
        Pattern pattern = Pattern.compile("\\((\\d+,?\\d*) hilos/cm\\)");
        Matcher matcher = pattern.matcher(tipoTela);

        if(matcher.find()){
            String hilosStr = matcher.group(1).replace(",", ".");
            // Reemplazar coma por punto decimal
            return Float.parseFloat(hilosStr);
        }
        return 0;
    }

    public void initialize(){
        tiposTelas = FXCollections.observableArrayList("11 ct (4,2 hilos/cm)", "12 ct (4,6 hilos/cm)", "14 ct (5,4 hilos/cm)", "16ct (6,2 hilos/cm)",
                "18 ct (7 hilos/cm)", "20 ct (7,8 hilos/cm)", "22 ct (8,5 hilos/cm)", "24 ct (9,3 hilos/cm)");
        spinner_ct.setItems(tiposTelas);
        spinner_ct.setValue("14 ct (5,4 hilos/cm)");
    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

    public CalculadoraController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_CALCULADORA.getDescripcion(),Constantes.TITULO_PANTALLA_CALCULADORA.getDescripcion(),600,600);
        return fxmlLoader.getController();
    }
}
