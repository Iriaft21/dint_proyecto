package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FormularioProyectoController {

    @FXML
    private Button btn_crear;

    @FXML
    private Button btn_imagen;

    @FXML
    private Button btn_limpiarCampos;

    @FXML
    private Button btn_salir;

    @FXML
    private ComboBox<String> combo_estado;

    @FXML
    private DatePicker dp_fechaFin;

    @FXML
    private DatePicker dp_fechaInicio;

    @FXML
    private ImageView img;

    @FXML
    private TextField txt_alto;

    @FXML
    private TextField txt_ancho;

    @FXML
    private TextField txt_descripcion;

    @FXML
    private TextField txt_diseniador;

    @FXML
    private TextField txt_nombre;

    private ObservableList<String> estados;

    @FXML
    void handleSeleccionarImagen(ActionEvent event) {
        //coger imagen del ordenador y pasarla al objeto
    }

    public void initialize(){
        estados = FXCollections.observableArrayList("Materiales reunidos", "En proceso", "Completado");
        combo_estado.setItems(estados);
        combo_estado.setValue("En proceso");
    }

    @FXML
    void onClickCrear(ActionEvent event) {
        //extraer datos y crear Proyecto.
        //Hay que pasarlo a ProyectoController

    }

    @FXML
    void onClickLimpiarCampos(ActionEvent event) {
        //limpiar campos y poner el default del comboBox

    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
