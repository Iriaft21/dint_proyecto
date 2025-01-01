package org.proyecto.proyecto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProyectoController {

    @FXML
    private Button btn_cerrar;

    @FXML
    private ImageView img_proyecto;

    @FXML
    private Label lbl_alto;

    @FXML
    private Label lbl_ancho;

    @FXML
    private Label lbl_diseniador;

    @FXML
    private Label lbl_estado;

    @FXML
    private Label lbl_fechaFin;

    @FXML
    private Label lbl_fechaInicio;

    @FXML
    private Label lbl_nombre;

    @FXML
    private Label lbl_progreso;

    @FXML
    private TextField txt_descripcion;

    @FXML
    void onClickCerrar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
