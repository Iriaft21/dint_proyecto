package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Hilo;
import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Utils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InventarioController {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_salir;

    @FXML
    private Button btn_atras;

    @FXML
    private TableColumn<Hilo, String> colCant;

    @FXML
    private TableColumn<Hilo, String> colMarca;

    @FXML
    private TableColumn<Hilo, String> colNombre;

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
    void onClickAtras(ActionEvent event) {
        Utils.irAtrasMenu(btn_atras);
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
        colMarca.setPrefWidth(200);

        colNombre.setCellValueFactory(new PropertyValueFactory<Hilo, String>("nombre"));
        colNombre.setText("Nombre");
        colNombre.setPrefWidth(200);

        colCant.setCellValueFactory(new PropertyValueFactory<Hilo, String>("cantidad"));
        colCant.setText("Cantidad");
        colCant.setPrefWidth(200);
    }

    @FXML
    void onClickAdd(ActionEvent event) {
        String nombre = txt_num.getText();
        String marca = txt_marca.getValue();
        String cantStr = txt_cant.getText();

        Hilo hilo = new Hilo(nombre, marca, cantStr);
        validarDatos(hilo);
        limpiarCampos();
    }

    public void validarDatos(Hilo hilo) {
        Hilo.TipoError tipoError = hilo.validarCantidad();
        if (!hilo.datosVacios()) {
            switch (tipoError) {
                case FORMATO:
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
                    break;
                case NUMEXCESIVO:
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_DEMASIADAS_UNIDADES.getDescripcion(), Constantes.AVISO_DEMASIADAS_UNIDADES.getDescripcion());
                    break;
                case NEGATIVO:
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                    break;
                case SIN_ERROR:
                    table_hilos.getItems().add(hilo);
            }
        }else{
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
        }
    }

    public void modificarDatos(){
        Hyperlink hyperlink = new Hyperlink("Comprar hilos");
        hyperlink.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.casacenina.es/hilos-y-hilados.html"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        modificarMarca();
        modificarNombre();
        modificarCantidad(hyperlink);
    }

    public void modificarMarca(){
        colMarca.setCellFactory(ComboBoxTableCell.<Hilo, String>forTableColumn(marcasHilos));
        colMarca.setOnEditCommit(
                (TableColumn.CellEditEvent<Hilo, String> t) -> {
                    ((Hilo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setMarca(t.getNewValue());
                });
    }

    public void modificarNombre(){
        colNombre.setCellFactory(TextFieldTableCell.<Hilo>forTableColumn());
        colNombre.setOnEditCommit(
                (TableColumn.CellEditEvent<Hilo, String> t) -> {
                    ((Hilo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNombre(t.getNewValue());
                });
    }

    public void modificarCantidad(Hyperlink hyperlink){
        colCant.setCellFactory(TextFieldTableCell.<Hilo>forTableColumn());
        colCant.setOnEditCommit((TableColumn.CellEditEvent<Hilo, String> t) ->{
            Hilo hilo=  t.getTableView().getItems().get(
                    t.getTablePosition().getRow());
            String nuevoValor =t.getNewValue();
            hilo.setCantidad(nuevoValor);

            if(nuevoValor.trim().equals("0")){
                AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_SIN_HILO.getDescripcion(), Constantes.AVISO_SIN_HILO.getDescripcion(), hyperlink);
            }
        });
    }

    public void limpiarCampos(){
        txt_num.clear();
        txt_marca.setValue("DMC");
        txt_cant.clear();
    }

    public InventarioController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_INVENTARIO.getDescripcion(), Constantes.TITULO_PANTALLA_INVENTARIO.getDescripcion(), 600, 600);
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
