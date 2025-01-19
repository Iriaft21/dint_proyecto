package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.*;

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

    private ObservableList<Proyecto> proyectos;

    public void setObservableList(ObservableList<Proyecto> proyectos){
        this.proyectos = proyectos;
    }

    @FXML
    void handleSeleccionarImagen(ActionEvent event) {
        Utils.seleccionarImagen(img);
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
                proyectos.add(proyecto);

                // proyectosController.setProyectoFromMain(proyecto);
                proyectosController.setObservableList(proyectos);
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