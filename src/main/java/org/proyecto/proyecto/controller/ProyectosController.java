package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Utils;

import java.io.IOException;

public class ProyectosController {

    @FXML
    private Button btn_aniadir;

    @FXML
    private Button btn_atras;

    @FXML
    private Button btn_eliminar;

    @FXML
    private Button btn_salir;

    @FXML
    private ListView<Proyecto> listView;

    @FXML
    private MenuItem menuItem_calculadora;

    @FXML
    private MenuItem menuItem_inventario;

    private ObservableList<Proyecto> proyectos;

    public void setObservableList(ObservableList<Proyecto> proyectos){
        this.proyectos = proyectos;
        this.listView.setItems(this.proyectos);
        this.listView.refresh();
    }

    @FXML
    void onClickAtras(ActionEvent event) {
        Utils.irAtrasMenu(btn_atras);
    }

    @FXML
    private void onClickAniadir(ActionEvent event){
        try {
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_aniadir);
            FormularioProyectoController formularioProyectoController = new FormularioProyectoController().showEstaPantalla(stage);
            formularioProyectoController.setObservableList(proyectos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickEliminar(ActionEvent event) {
        Proyecto proyectoBorrar = listView.getSelectionModel().getSelectedItem();
        if(proyectoBorrar != null){
            listView.getItems().remove(proyectoBorrar);
        }
    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void  onMouseClickedListView(MouseEvent click) {
        if(click.getClickCount() == 2){
            Proyecto proyectoDetalle = listView.getSelectionModel().getSelectedItem();

            try {
                Stage stage = (Stage)listView.getScene().getWindow();
                stage.show();

                DetallesProyectoController detallesProyectoController = new DetallesProyectoController().showEstaPantalla(stage);
                detallesProyectoController.setProyecto(proyectoDetalle);
                detallesProyectoController.setObservableList(proyectos);
                detallesProyectoController.cargaDatos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize(){
        proyectos = FXCollections.observableArrayList();
        listView.setItems(proyectos);
        listViewPersonalizado();
        listView.refresh();
    }

    public void listViewPersonalizado(){
        listView.setCellFactory(param -> new ListCell<Proyecto>() {
            @Override
            protected void updateItem(Proyecto proyecto, boolean empty) {
                super.updateItem(proyecto, empty);
                if (empty || proyecto == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label nombreLabel = new Label("Nombre: " + proyecto.getNombre());
                    Label estadoLabel = new Label("Estado: " + proyecto.getEstado());
                    Label progresoLabel = new Label("Progreso: " + proyecto.getProgreso() + "%");
                    VBox vBox = new VBox(nombreLabel, estadoLabel, progresoLabel);
                    vBox.setSpacing(5);
                    ImageView imageView = new ImageView();
                    if (proyecto.getImagen() != null) {
                        try {
                            imageView.setImage(proyecto.getImagen());
                        } catch (Exception e) {
                            System.out.println("Invalid URL: " + e.getMessage());
                        }
                    }
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    imageView.setPreserveRatio(true);

                    HBox hBox = new HBox(vBox, imageView);
                    hBox.setSpacing(340); // Espaciado entre el texto y la imagen
                    hBox.setAlignment(Pos.CENTER_LEFT);

                    setGraphic(hBox);
                    setText(null);
                }
            }
        });
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
    void onClickMenuItemInventario(ActionEvent event) {
        try {
            Stage stage = (Stage) menuItem_inventario.getParentPopup().getOwnerWindow().getScene().getWindow();
            stage.close();
            InventarioController inventarioController = new InventarioController().showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProyectosController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_PROYECTOS.getDescripcion(),Constantes.TITULO_PANTALLA_PROYECTOS.getDescripcion(),600,600);
        ProyectosController controller = fxmlLoader.getController();
        return controller;
    }
}
