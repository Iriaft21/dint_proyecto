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
        try {
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_crear);

            ProyectosController proyectosController = new ProyectosController().showEstaPantalla(stage);
            proyectosController.setProyectoFromMain(proyecto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Proyecto extraerDatos(){
        String nombre = txt_nombre.getText();
        int alto = validarCampos(txt_alto);
        int largo = validarCampos(txt_largo);
        int puntadasTotales = validarCampos(txt_puntadasTotales);
                //Integer.parseInt(txt_ancho.getText());
        String estado = combo_estado.getValue();
        String fechaInicio = dp_fechaInicio.getValue() !=  null? String.valueOf(dp_fechaInicio.getValue()) : "Desconocida";
        String fechaFin = dp_fechaFin.getValue() != null? String.valueOf(dp_fechaFin.getValue()) : "Desconocida/Sin terminar";
        String descripcion = txt_descripcion.getText().isEmpty()? txt_descripcion.getText() : "Sin descripción";
        String diseniador = txt_diseniador.getText().isEmpty()? txt_diseniador.getText() : "Desconocido";
        Proyecto proyecto = new Proyecto(nombre, descripcion, diseniador, alto, largo, estado, puntadasTotales, fechaInicio, fechaFin, img.getImage());

        AlertaUtils.showAlertInformativa(Constantes.TITULO_PROYECTO_CREADO.getDescripcion(), Constantes.AVISO_PROYECTO_CREADO.getDescripcion());

        return proyecto;
    }

    private int validarCampos(TextField textField) throws IllegalArgumentException{
        String texto = textField.getText().trim();
        if(texto.isEmpty()){
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
            throw new IllegalArgumentException("El campo no puede estar vacío");
        }
        try {
            int valor = Integer.parseInt(texto);
            if (valor < 0) {
                AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                throw  new IllegalArgumentException("El número no puede ser negativo");
            }
            return valor;
        } catch (NumberFormatException e) {
            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            throw new IllegalArgumentException("El campo debe contener solo números");
        }
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
