package org.proyecto.proyecto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.IOException;

public class DetallesProyectoController {

    @FXML
    private Button btn_atras;

    @FXML
    private Button btn_modificarDatos;

    @FXML
    private Button btn_nuevoProgreso;

    @FXML
    private ImageView img_proyecto;

    @FXML
    private Label lbl_alto;

    @FXML
    private Label lbl_largo;

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
    private Label lbl_puntadasTotales;

    @FXML
    private Label lbl_progreso;

    @FXML
    private TextField txt_descripcion;

    private Proyecto proyecto;

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @FXML
    void onClickAtras(ActionEvent event) {
        try {
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_atras);
            ProyectosController proyectosController = new ProyectosController().showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickProgreso(ActionEvent event) {
        try {
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_modificarDatos);
            ActualizarProgresoController actualizarProgresoController = new ActualizarProgresoController().showEstaPantalla(stage);
            actualizarProgresoController.setProyecto(this.proyecto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickModificar(ActionEvent event) {
        //TODO modificar los campos. Se podría volver a llamar al formulario pero pasandole el proyecto
    }


    public void cargaDatos(){
        lbl_nombre.setText(this.proyecto.getNombre());
        lbl_diseniador.setText(this.proyecto.getDiseniador());
        lbl_alto.setText(String.valueOf(this.proyecto.getAlto()));
        lbl_largo.setText(String.valueOf(this.proyecto.getLargo()));
        lbl_estado.setText(this.proyecto.getEstado());
        lbl_fechaInicio.setText(this.proyecto.getFechaInicio());
        lbl_fechaFin.setText(this.proyecto.getFechaFin());
        lbl_puntadasTotales.setText(String.valueOf(this.proyecto.getPuntadasTotales()));
        lbl_progreso.setText(String.valueOf(this.proyecto.getProgreso()));
        txt_descripcion.setText(this.proyecto.getDescripcion());
        img_proyecto.setImage(this.proyecto.getImagen());
    }

    public DetallesProyectoController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_DETALLES_PROYECTO.getDescripcion(),Constantes.TITULO_PANTALLA_DETALLES_PROYECTO.getDescripcion(),500,500);
        DetallesProyectoController controller = fxmlLoader.getController();
        return controller;
    }
}
