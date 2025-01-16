package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.CalculoTela;
import org.proyecto.proyecto.modelo.Hilo;
import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Strings;

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
    private Label lbl_dimensiones;

    @FXML
    private Label lbl_form1;

    @FXML
    private Label lbl_form2;

    @FXML
    private Label lbl_form3;

    @FXML
    private Label lbl_form4;

    @FXML
    private Label lbl_form5;

    @FXML
    private Label lbl_titulo;

    @FXML
    private Menu menu;

    @FXML
    private MenuItem menuItem_inventario;

    @FXML
    private MenuItem menuItem_proyectos;

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

    @FXML
    private void  onClickCalcular(ActionEvent event) {
        int alto = validar(txt_alto);
        int largo = validar(txt_largo);
        float hilosTela = extraerHilos();
        int telaAcabado = validar(txt_telaAcabado);
        int telaBorde = validar(txt_telaBorde);
        CalculoTela calculoTela = new CalculoTela(alto, largo, hilosTela, telaBorde, telaAcabado);

        CalculoTela.TipoError tipoError = calculoTela.validar();
            switch (tipoError) {
                case NEGATIVO:
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                    break;
                case SIN_ERROR:
                    try {
                        Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_calcular);

                        ResultadoCalculoController resultadoCalculoController = new ResultadoCalculoController().showEstaPantalla(stage);
                        resultadoCalculoController.setCalculoFromMain(calculoTela);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
    }

    private int validar(TextField textField){
        String texto = textField.getText().trim();
        if (texto.isEmpty()) {
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
            throw new IllegalArgumentException("El campo no puede estar vacío");
        }else if(!texto.matches("-?\\d+")){
            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            throw new IllegalArgumentException("El campo debe contener solo números");
        }
        return Integer.parseInt(texto);
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
            return Float.parseFloat(hilosStr);
        }
        return 0;
    }

    public void initialize(){
        tiposTelas = FXCollections.observableArrayList("11 ct (4,2 hilos/cm)", "12 ct (4,6 hilos/cm)", "14 ct (5,4 hilos/cm)", "16ct (6,2 hilos/cm)",
                "18 ct (7 hilos/cm)", "20 ct (7,8 hilos/cm)", "22 ct (8,5 hilos/cm)", "24 ct (9,3 hilos/cm)");
        spinner_ct.setItems(tiposTelas);
        spinner_ct.setValue("14 ct (5,4 hilos/cm)");
        asignarStrings();
    }

    private void asignarStrings(){
        menu.setText(Strings.MENU.getDescripcion());
        menuItem_inventario.setText(Strings.TITULO_INVENTARIO.getDescripcion());
        menuItem_proyectos.setText(Strings.TITULO_PROYECTOS.getDescripcion());
        lbl_titulo.setText(Strings.TITULO_CALCULADORA2.getDescripcion());
        lbl_dimensiones.setText(Strings.SUBTITULO_DIMENSIONES.getDescripcion());
        lbl_form1.setText(Strings.LABEL_FORMULARIO1.getDescripcion());
        lbl_form2.setText(Strings.LABEL_FORMULARIO2.getDescripcion());
        lbl_form3.setText(Strings.LABEL_FORMULARIO3.getDescripcion());
        lbl_form4.setText(Strings.LABEL_FORMULARIO4.getDescripcion());
        lbl_form5.setText(Strings.LABEL_FORMULARIO5.getDescripcion());
        btn_salir.setText(Strings.BOTON_SALIR.getDescripcion());
        btn_calcular.setText(Strings.BOTON_CALCULAR.getDescripcion());
        btn_limpiarCampos.setText(Strings.BOTON_LIMPIAR_CAMPOS.getDescripcion());
    }


    @FXML
    void onClickMenuItemInventario(ActionEvent event) {
        try {
            Stage stage = (Stage) menuItem_inventario.getParentPopup().getOwnerWindow().getScene().getWindow();
            stage.close();
            InventarioController inventarioController = new InventarioController().showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickMenuItemProyectos(ActionEvent event) {
        try {
            Stage stage = (Stage) menuItem_proyectos.getParentPopup().getOwnerWindow().getScene().getWindow();
            stage.close();
            ProyectosController proyectosController = new ProyectosController().showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
