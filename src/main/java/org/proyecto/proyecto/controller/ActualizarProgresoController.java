package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Strings;

import java.io.IOException;

public class ActualizarProgresoController {

    @FXML
    private Button btn_actualizar;

    @FXML
    private Button btn_salir;

    @FXML
    private Label lbl_respuesta;

    @FXML
    private Label lbl_titulo;

    @FXML
    private TextField txt_puntadasNuevas;

    private Proyecto proyecto;

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @FXML
    void onClickActualizar(ActionEvent event) {
        int puntadasNuevas = 0;
        if(txt_puntadasNuevas != null){
            puntadasNuevas = Integer.parseInt(txt_puntadasNuevas.getText());
            float redondeado = Math.round(proyecto.calcularProgreso(puntadasNuevas) * 100)/100f;
            proyecto.setProgreso(redondeado);
        }
        //TODO en el lisrView no se refleja el cambio
        try {
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_actualizar);
            DetallesProyectoController detallesProyectoController = new DetallesProyectoController().showEstaPantalla(stage);
            detallesProyectoController.setProyecto(this.proyecto);
            detallesProyectoController.cargaDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        lbl_respuesta.setText(Strings.LBL_RESPUESTA.getDescripcion());
        lbl_titulo.setText(Strings.TITULO_ACTUALIZAR_PROGRESO.getDescripcion());
        btn_actualizar.setText(Strings.BOTON_ACTUALIZAR.getDescripcion());
        btn_salir.setText(Strings.BOTON_SALIR.getDescripcion());
    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
    }

    public ActualizarProgresoController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_ACTUALIZAR_PROGRESO.getDescripcion(),Constantes.TITULO_PANTALLA_ACTUALIZAR_PROGRESO.getDescripcion(),500,300);
        ActualizarProgresoController controller = fxmlLoader.getController();
        return controller;
    }
}
