package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Hilo;

import java.io.IOException;

public class InventarioController {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_eliminar;

    @FXML
    private Button btn_modificar;

    @FXML
    private Button btn_salir;

    @FXML
    private Label lbl_titulo;

    @FXML
    private TableView<Hilo> table_hilos;

    @FXML
    private TableColumn<Hilo, Integer> colCant;

    @FXML
    private TableColumn<Hilo, String> colMarca;

    @FXML
    private TableColumn<Hilo, String> colNumero;

    @FXML
    void onClickAdd(ActionEvent event) {
//        Hilo hilo = new Hilo();
//        table_hilos.getItems().add(hilo);
    }

    @FXML
    void onClickEliminar(ActionEvent event) {

    }

    @FXML
    void onClickModificar(ActionEvent event) {

    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

    private void crearTabla(){
        ObservableList<String> marcasHilo = FXCollections.observableArrayList("DMC", "Anchor", "Kreinik", "Madeira", "Otros");
        table_hilos = new TableView<>();
        colMarca.setText("Marca");
        colNumero.setText("Numero");

        colMarca.setCellFactory(col -> {
            TableCell<Hilo, String> c = new TableCell<>();
            final ComboBox<String> comboBox = new ComboBox<>(marcasHilo);
            c.setGraphic(comboBox);
            c.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            c.itemProperty().addListener((observable, oldValue, newValue) -> {
                if (c.getItem() != null) {
                    comboBox.setValue(newValue);
                }
            });
            comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (c.getTableRow() != null && c.getTableRow().getItem() != null) {
                    c.getTableRow().getItem().setMarca(newValue);
                }
            });
            return c;
        });
        colCant.setText("Cantidad");
    }


    public void initialize(){
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colCant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        table_hilos.getColumns().addAll(colNumero, colMarca, colCant);
    }

    public void showSecondFrame(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/proyecto/proyecto/inventario_view.fxml"));

        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(loader.load(),400,440));

        // Obtener el controlador de la segunda ventana y pasar el texto
        InventarioController inventarioController = loader.getController();

        // Mostrar la segunda ventana
        secondStage.setTitle("Inventario");
        secondStage.show();

        //CERRAR LA VENTANA ANTERIOR
        // Minimizar la ventana principal (bloquearla)
        primaryStage.setIconified(true); // Minimiza la ventana principal

        // Opción alternativa: deshabilitar la ventana principal completamente
        // primaryStage.setOpacity(0.5); // Desactiva la ventana principal (con opacidad reducida)

        // Cerrar la ventana principal cuando la segunda ventana se cierre
        secondStage.setOnCloseRequest(e -> primaryStage.close());
    }

    @FXML
    void onEditCommitCant(ActionEvent event) {

    }

    @FXML
    void onEditCommitMarca(ActionEvent event) {

    }

    @FXML
    void onEditCommitNumero(ActionEvent event) {

    }
}
