package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Hilo;
import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.IOException;

public class InventarioController {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_salir;

    @FXML
    private TableColumn<Hilo, String> colCant;

    @FXML
    private TableColumn<Hilo, String> colMarca;

    @FXML
    private TableColumn<Hilo, String> colNumero;

    @FXML
    private Label lbl_titulo;

    @FXML
    private TableView<Hilo> table_hilos;

    @FXML
    private TextField txt_cant;

    @FXML
    private ComboBox<String> txt_marca;

    @FXML
    private TextField txt_num;

    @FXML
    private Button btn_eliminar;

    @FXML
    private MenuItem menuItem_calculadora;

    @FXML
    private MenuItem menuItem_proyectos;

    private ObservableList<String> marcasHilos;

    @FXML
    void onClickEliminar(ActionEvent event) {
        Hilo hiloSeleccionado = table_hilos.getSelectionModel().getSelectedItem();
        table_hilos.getItems().remove(hiloSeleccionado);
    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

    public void initialize(){
        crearTabla();

        ObservableList<Hilo> datosHilos = FXCollections.observableArrayList();
        table_hilos.setItems(datosHilos);

        modificarDatos();
    }

    void crearTabla(){
        marcasHilos = FXCollections.observableArrayList("DMC", "Anchor", "Kreinik", "Madeira", "Otros");
        table_hilos.setEditable(true);
        txt_marca.setItems(marcasHilos);
        txt_marca.setValue("DMC");

        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colMarca.setText("Marca");
        colMarca.setPrefWidth(175);

        colNumero.setCellValueFactory(new PropertyValueFactory<Hilo, String>("numero"));
        colNumero.setText("Numero");
        colNumero.setPrefWidth(175);

        colCant.setCellValueFactory(new PropertyValueFactory<Hilo, String>("cantidad"));
        colCant.setText("Cantidad");
        colCant.setPrefWidth(175);
    }

    @FXML
    void onClickAdd(ActionEvent event) {
        String numero = txt_num.getText();
        String marca = txt_marca.getValue();
        String cantStr = txt_cant.getText();

        //TODO revisar lo de validar
        if (numero == null || numero.trim().isEmpty() ||
                marca == null || marca.trim().isEmpty() ||
                cantStr == null || cantStr.trim().isEmpty()) {
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
        } else {
            try {
                int cantidad = Integer.parseInt(cantStr);
                if(cantidad >= 0){
                    Hilo hilo = new Hilo(numero, marca, cantStr);
                    table_hilos.getItems().add(hilo);
                }else{
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                }
            } catch (NumberFormatException e) {
                AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            }
        }
        limpiarCampos();
    }

    public void modificarDatos(){
        colMarca.setCellFactory(ComboBoxTableCell.<Hilo, String>forTableColumn(marcasHilos));
        colMarca.setOnEditCommit(
                (TableColumn.CellEditEvent<Hilo, String> t) -> {
                    ((Hilo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setMarca(t.getNewValue());
                });

        colNumero.setCellFactory(TextFieldTableCell.<Hilo>forTableColumn());
        colNumero.setOnEditCommit(
                (TableColumn.CellEditEvent<Hilo, String> t) -> {
                    ((Hilo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNumero(t.getNewValue());
                });

        colCant.setCellFactory(TextFieldTableCell.<Hilo>forTableColumn());
        colCant.setOnEditCommit((TableColumn.CellEditEvent<Hilo, String> t) ->{
            Hilo hilo=  t.getTableView().getItems().get(
                    t.getTablePosition().getRow());
            String nuevoValor =t.getNewValue();
            hilo.setCantidad(nuevoValor);

            if(nuevoValor.trim().equals("0")){
                AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_SIN_HILO.getDescripcion(), Constantes.AVISO_SIN_HILO.getDescripcion());
            }
        });
    }

    public void limpiarCampos(){
        txt_num.clear();
        txt_marca.setValue("DMC");
        txt_cant.clear();
    }

    public InventarioController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_INVENTARIO.getDescripcion(),Constantes.TITULO_PANTALLA_INVENTARIO.getDescripcion(),600,600);
        InventarioController controller = fxmlLoader.getController();
        return controller;
    }

    @FXML
    void onClickMenuItemCalculadora(ActionEvent event) {
        try {
            Stage stage = (Stage) menuItem_calculadora.getParentPopup().getOwnerWindow().getScene().getWindow();
            stage.close();
            CalculadoraController calculadoraController = new CalculadoraController().showEstaPantalla(stage);
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
}
