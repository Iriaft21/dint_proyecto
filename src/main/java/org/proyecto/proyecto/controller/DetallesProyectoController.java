package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class DetallesProyectoController {

    @FXML
    private Button btn_addFoto;

    @FXML
    private Button btn_atras;

    @FXML
    private Button btn_guardarCambios;

    @FXML
    private Button btn_modificable;

    @FXML
    private Button btn_nuevoProgreso;

    @FXML
    private ImageView img_proyecto;

    @FXML
    private TextField txt_alto;

    @FXML
    private TextField txt_descripcion;

    @FXML
    private TextField txt_diseniador;

    @FXML
    private TextField txt_estado;

    @FXML
    private DatePicker txt_fechaFin;

    @FXML
    private DatePicker txt_fechaInicio;

    @FXML
    private TextField txt_largo;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_progreso;

    @FXML
    private TextField txt_puntadasTotales;

    private ObservableList<String> estados;

    @FXML
    void handlerSeleccionarFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            img_proyecto.setImage(image);
        }
    }

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
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_nuevoProgreso);
            ActualizarProgresoController actualizarProgresoController = new ActualizarProgresoController().showEstaPantalla(stage);
            actualizarProgresoController.setProyecto(this.proyecto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickModificable(ActionEvent event) {
        txt_nombre.setEditable(true);
        txt_diseniador.setEditable(true);
        txt_alto.setEditable(true);
        txt_largo.setEditable(true);
        txt_estado.setEditable(true);
        txt_fechaInicio.setEditable(true);
        txt_fechaFin.setEditable(true);
        txt_puntadasTotales.setEditable(true);
        txt_descripcion.setEditable(true);

        btn_addFoto.setVisible(true);
        btn_guardarCambios.setVisible(true);
    }

    @FXML
    void onClickGuardarCambios(ActionEvent event) {
        String diseniador = !txt_diseniador.getText().isEmpty()? txt_diseniador.getText() : "No especificado";
        String fechaInicio = txt_fechaInicio.getValue() !=  null? String.valueOf(txt_fechaInicio.getValue()) : "Desconocida";
        String fechaFin = txt_fechaFin.getValue() != null? String.valueOf(txt_fechaFin.getValue()) : "Desconocida/Sin terminar";
        String descripcion = !txt_descripcion.getText().isEmpty()? txt_descripcion.getText() : "Sin descripción";
        if(validarCampos()){
            proyecto.setNombre(txt_nombre.getText());
            proyecto.setDiseniador(diseniador);
            proyecto.setAlto(Integer.parseInt(txt_alto.getText()));
            proyecto.setLargo(Integer.parseInt(txt_largo.getText()));
            //TODO mirar de poner lo de estado como spinner
//        proyecto.setEstado();
            proyecto.setFechaInicio(fechaInicio);
            proyecto.setFechaFin(fechaFin);
            proyecto.setPuntadasTotales(Integer.parseInt(txt_puntadasTotales.getText()));
            proyecto.setDescripcion(descripcion);
            proyecto.setImagen(img_proyecto.getImage());
            AlertaUtils.showAlertInformativa(Constantes.TITULO_PROYECTO_MODIFICADO.getDescripcion(), Constantes.AVISO_PROYECTO_MODIFICADO.getDescripcion());
        }
        //TODO mismo problema que con el progreso
        System.out.println(proyecto.toString());
    }

    private boolean validarCampos() {
        try {
            // Validar que no estén vacíos
            if (txt_nombre.getText().isEmpty() ||txt_alto.getText().isEmpty() || txt_largo.getText().isEmpty() ||
                    txt_puntadasTotales.getText().isEmpty()) {
                AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
                return false;
            }

            // Validar números (que sean enteros y positivos)
            int alto = Integer.parseInt(txt_alto.getText());
            int largo = Integer.parseInt(txt_largo.getText());
            int puntadasTotales = Integer.parseInt(txt_puntadasTotales.getText());

            if (alto < 0 || largo < 0 || puntadasTotales < 0) {
                AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(),Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                return false;
            }

            if(puntadasTotales > alto * largo){
                AlertaUtils.showAlertError(Constantes.TITULO_AVISO_PUNTADAS.getDescripcion(), Constantes.AVISO_PUNTADAS.getDescripcion());
            }

        } catch (NumberFormatException e) {
            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            return false;
        }

        // Si pasa todas las validaciones, retorna true
        return true;
    }

    public void initialize(){
        File f = new File("src/main/resources/imagenesProyecto/candado.png");
        ImageView imageView = new ImageView(new Image("file:///" + f.getAbsolutePath().replace("\\", "/")));
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        btn_modificable.setGraphic(imageView);

        btn_addFoto.setVisible(false);
        btn_guardarCambios.setVisible(false);
        estados = FXCollections.observableArrayList("Reuniendo materiales", "Materiales reunidos", "En proceso", "Completado");
    }

    public void cargaDatos(){
        txt_nombre.setText(this.proyecto.getNombre());
        txt_diseniador.setText(this.proyecto.getDiseniador());
        txt_alto.setText(String.valueOf(this.proyecto.getAlto()));
        txt_largo.setText(String.valueOf(this.proyecto.getLargo()));
        txt_estado.setText(this.proyecto.getEstado());
//        txt_fechaInicio.setValue(LocalDate.parse(this.proyecto.getFechaInicio()));
//        txt_fechaFin.setValue(LocalDate.parse(this.proyecto.getFechaFin()));
        txt_puntadasTotales.setText(String.valueOf(this.proyecto.getPuntadasTotales()));
        txt_progreso.setText(String.valueOf(this.proyecto.getProgreso()));
        txt_descripcion.setText(this.proyecto.getDescripcion());
        img_proyecto.setImage(this.proyecto.getImagen());
    }

    public DetallesProyectoController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_DETALLES_PROYECTO.getDescripcion(),Constantes.TITULO_PANTALLA_DETALLES_PROYECTO.getDescripcion(),600,650);
        DetallesProyectoController controller = fxmlLoader.getController();
        return controller;
    }
}