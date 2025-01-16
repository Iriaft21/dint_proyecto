package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Hilo;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.*;
import org.w3c.dom.Text;

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
    private Label lbl_alto;

    @FXML
    private Label lbl_descripcion;

    @FXML
    private Label lbl_diseniador;

    @FXML
    private Label lbl_estado;

    @FXML
    private Label lbl_fechaFin;

    @FXML
    private Label lbl_fechaInicio;

    @FXML
    private Label lbl_largo;

    @FXML
    private Label lbl_necesarios;

    @FXML
    private Label lbl_nombre;

    @FXML
    private Label lbl_puntadas_totales;

    @FXML
    private Label lbl_titulo;


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
        ImagenesUtils.seleccionarImagen(img);
    }

    public void initialize(){
        estados = FXCollections.observableArrayList("Reuniendo materiales", "Materiales reunidos", "En proceso", "Completado");
        combo_estado.setItems(estados);
        combo_estado.setValue("En proceso");

        asignarStrings();
    }

    private void asignarStrings(){
        lbl_titulo.setText(Strings.TITULO_FORMULARIO_PROYECTO.getDescripcion());
        lbl_necesarios.setText(Strings.LABEL_NECECESARIOS.getDescripcion());
        btn_crear.setText(Strings.BOTON_CREAR.getDescripcion());
        btn_imagen.setText(Strings.BOTON_SELECCIONAR_IMAGEN.getDescripcion());
        btn_salir.setText(Strings.BOTON_SALIR.getDescripcion());
        btn_limpiarCampos.setText(Strings.BOTON_LIMPIAR_CAMPOS.getDescripcion());
        lbl_alto.setText(Strings.LABEL_ALTO.getDescripcion());
        lbl_descripcion.setText(Strings.LABEL_DESCRIPCION.getDescripcion());
        lbl_diseniador.setText(Strings.LABEL_DISENIADOR.getDescripcion());
        lbl_estado.setText(Strings.LABEL_ESTADO.getDescripcion());
        lbl_fechaFin.setText(Strings.LABEL_FECHA_FIN.getDescripcion());
        lbl_fechaInicio.setText(Strings.LABEL_FECHA_INICIO.getDescripcion());
        lbl_largo.setText(Strings.LABEL_LARGO.getDescripcion());
        lbl_nombre.setText(Strings.LABEL_NOMBRE.getDescripcion());
        lbl_puntadas_totales.setText(Strings.LABEL_PUNTADAS_TOTALES.getDescripcion());
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
        int alto = validar(txt_alto);
        int largo = validar(txt_largo);
        int puntadasTotales = validar(txt_puntadasTotales);

        proyecto = new Proyecto(nombre, descripcion, diseniador, alto, largo, estado, puntadasTotales, fechaInicio, fechaFin, img.getImage());
        return avisosValidar(proyecto);
    }

    private int validar(TextField textField){
        String texto = textField.getText().trim();
        if(!texto.matches("-?\\d+")){
            System.out.println(textField.getText());
            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            throw new IllegalArgumentException("El campo debe contener solo números");
        }
        return Integer.parseInt(texto);
    }

    private Proyecto avisosValidar(Proyecto proyecto){
        Proyecto.TipoError tipoError = proyecto.validar();
        if (!proyecto.datosVacios()) {
            switch (tipoError) {
                case ERRORBORDADO:
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_PUNTADAS.getDescripcion(), Constantes.AVISO_PUNTADAS.getDescripcion());
                    break;
                case NEGATIVO:
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                    break;
                case SIN_ERROR:
                    AlertaUtils.showAlertInformativa(Constantes.TITULO_PROYECTO_CREADO.getDescripcion(), Constantes.AVISO_PROYECTO_CREADO.getDescripcion());
                    return proyecto;
            }
        }else{
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
        }
        return null;
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