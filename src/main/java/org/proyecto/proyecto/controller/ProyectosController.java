package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    void onClickAniadir(ActionEvent event) {
        //llamar al formulario
    }

    @FXML
    void onClickEliminar(ActionEvent event) {
        //al clicar en el objeto eliminarlo del arraylist del listView
    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onMouseClickedListView(MouseEvent event) {
        //al hacer click en uno de los elementos, pasa a otra pantalla que muestra el proyecto en mas detalle
        //https://stackoverflow.com/questions/22542015/how-to-add-a-mouse-doubleclick-event-listener-to-the-cells-of-a-listview-in-java  //evento clicks listview
    }

    public void initialize(){
        //mostrar nombre, progreso porcentaje, imagen(?)
        //https://jenkov.com/tutorials/javafx/listview.html  listview
    }

    public ProyectosController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_PROYECTOS.getDescripcion(),Constantes.TITULO_PANTALLA_PROYECTOS.getDescripcion(),600,600);
        ProyectosController controller = fxmlLoader.getController();
        return controller;
    }
}
