package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

public class FormularioProyectoController {

    @FXML
    private Button btn_crear;

    @FXML
    private Button btn_imagen;

    @FXML
    private Button btn_limpiarCampos;

    @FXML
    private Button btn_salir;

    @FXML
    private ComboBox<String> combo_estado;

    @FXML
    private DatePicker dp_fechaFin;

    @FXML
    private DatePicker dp_fechaInicio;

    @FXML
    private ImageView img;

    @FXML
    private TextField txt_alto;

    @FXML
    private TextField txt_largo;

    @FXML
    private TextField txt_descripcion;

    @FXML
    private TextField txt_diseniador;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_puntadasTotales;

    private ObservableList<String> estados;

    int alto = 0;
    int largo = 0;
    int puntadasTotales = 0;

    @FXML
    void handleSeleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            img.setImage(image);
        }
    }

    public void initialize(){
        estados = FXCollections.observableArrayList("Reuniendo materiales", "Materiales reunidos", "En proceso", "Completado");
        combo_estado.setItems(estados);
        combo_estado.setValue("En proceso");
    }

    @FXML
    private void onClickCrear(ActionEvent event){
        Proyecto proyecto = extraerDatos();
        System.out.println(proyecto);
        if (proyecto != null){
            try {
                Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_crear);

                ProyectosController proyectosController = new ProyectosController().showEstaPantalla(stage);
                proyectosController.setProyectoFromMain(proyecto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Proyecto extraerDatos(){
        Proyecto proyecto =  null;
        String nombre = txt_nombre.getText();
        String estado = combo_estado.getValue();
        String fechaInicio = String.valueOf(dp_fechaInicio.getValue());
        String fechaFin = String.valueOf(dp_fechaFin.getValue());
        String descripcion = !txt_descripcion.getText().isEmpty()? txt_descripcion.getText() : "Sin descripción";
        String diseniador = !txt_diseniador.getText().isEmpty()? txt_diseniador.getText() : "No especificado";
        if(validarCampos() == true){
            alto = Integer.parseInt(txt_alto.getText());
            largo = Integer.parseInt(txt_largo.getText());
            puntadasTotales = Integer.parseInt(txt_puntadasTotales.getText());

            proyecto = new Proyecto(nombre, descripcion, diseniador, alto, largo, estado, puntadasTotales, fechaInicio, fechaFin, img.getImage());
            AlertaUtils.showAlertInformativa(Constantes.TITULO_PROYECTO_CREADO.getDescripcion(), Constantes.AVISO_PROYECTO_CREADO.getDescripcion());
        }

        return proyecto;
    }

    private boolean validarCampos() {
        try {
            // Validar que no estén vacíos
            if (txt_nombre.getText().isEmpty() ||txt_alto.getText().isEmpty() || txt_largo.getText().isEmpty() ||
                    txt_puntadasTotales.getText().isEmpty() ) {
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
                return false;
            }

        } catch (NumberFormatException e) {
            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            return false;
        }

        return true;
    }

    @FXML
    void onClickLimpiarCampos(ActionEvent event) {
        combo_estado.setValue("En proceso");
        dp_fechaInicio.setValue(null);
        dp_fechaFin.setValue(null);
        txt_alto.clear();
        txt_largo.clear();
        txt_descripcion.clear();
        txt_nombre.clear();
        txt_diseniador.clear();
        img.setImage(null);
    }

    @FXML
    void onClickSalir(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public FormularioProyectoController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_FORMULARIO_PROYECTO.getDescripcion(),Constantes.TITULO_PANTALLA_FORMULARIO_PROYECTO.getDescripcion(),550,600);
        FormularioProyectoController controller = fxmlLoader.getController();

        return controller;
    }
}