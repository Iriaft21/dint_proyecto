package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.IOException;

public class ProyectosController {
    @FXML
    private Button btn_aniadir;

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

    @FXML
    private void onClickAniadir(ActionEvent event){
        try {
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_aniadir);
            FormularioProyectoController formularioProyectoController = new FormularioProyectoController().showEstaPantalla(stage);
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
                stage.close();

                DetallesProyectoController detallesProyectoController = new DetallesProyectoController().showEstaPantalla(stage);
                detallesProyectoController.setProyecto(proyectoDetalle);
                detallesProyectoController.cargaDatos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize(){
        proyectos = FXCollections.observableArrayList();
        Proyecto p1 = new Proyecto("Persephone",null, "StitchyNova", 120, 240, "En proceso", 650, null, null, null );
        Proyecto p2 = new Proyecto("Marcapaginas medieval", "Regalo", null, 60, 30, "Completado", 120, null, null, null);
        Proyecto p3 = new Proyecto("Final Fantasy", "Proyecyo muy colorido con referencias a la saga de juegos Final Fantasy.", "BogDragon", 153, 151, "Reuniendo materiales", 1721, null, null,  null);
        proyectos.addAll(p1, p2, p3);
        listView.setItems(proyectos);

//      TODO mirar que pase bien la imagen

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
                            String imagenURL = getClass().getResource(proyecto.getImagen().toString()).toExternalForm();
                            Image imagen = new Image(imagenURL);
                            imageView.setImage(imagen);
                        } catch (Exception e) {
                            System.out.println("Invalid URL: " + e.getMessage());
                        }
                    }

                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    imageView.setPreserveRatio(true);

                    HBox hBox = new HBox(vBox, imageView);
                    hBox.setSpacing(10); // Espaciado entre el texto y la imagen
                    hBox.setAlignment(Pos.CENTER_LEFT);

                    setGraphic(vBox);
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

    public void setProyectoFromMain(Proyecto proyecto){
        if(proyecto!= null){
            proyectos.add(proyecto);
            listView.refresh();
        }
    }

    public ProyectosController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_PROYECTOS.getDescripcion(),Constantes.TITULO_PANTALLA_PROYECTOS.getDescripcion(),600,600);
        ProyectosController controller = fxmlLoader.getController();
        return controller;
    }
}
