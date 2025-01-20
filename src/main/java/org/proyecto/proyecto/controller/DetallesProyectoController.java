package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.Utils;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controlador de la pantalla de detalles del proyecto
 *
 * Contiene metodos para obtener datos de TextField y limpiarlos, así como validar y extraer el valor de algunos de esos campos
 */
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
    private ComboBox<String> txt_estado;

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

    private boolean editable = false;

    private Proyecto proyecto;

    private ObservableList<Proyecto> proyectos;

    /**
     * Establece el objeto Proyecto
     *
     * @param proyecto El  objeto a establecer
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    /**
     * Establece la lista de proyectos y actualiza el ListView asociado
     *
     * @param proyectos La lista e de proyectos a establecer
     */
    public void setObservableList(ObservableList<Proyecto> proyectos) {
        // Asigna la lista de proyectos a la variable de instancia
        this.proyectos = proyectos;
    }

    /**
     * Método que llama a un Util para seleccionar una imagen y establecerla en el ImageView
     *
     * @param event El evento de la acción
     */
    @FXML
    void handleSeleccionarImagen(ActionEvent event) {
        //Se llama al método de Utils y le pasamos el ImageView necesario
        Utils.seleccionarImagen(img_proyecto);
    }

    /**
     * Método para volver a la pantalla anterior
     *
     * @param event El evento de acción
     */
    @FXML
    void onClickAtras(ActionEvent event) {
        try {
            // Obtenemos la ventana actual y la cerramos
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_atras);
            // Mostramos la pantalla de seguimiento de los proyectos
            ProyectosController proyectosController = new ProyectosController().showEstaPantalla(stage);
            //Le pasamos la lista con los proyectos
            proyectosController.setObservableList(proyectos);
        } catch (Exception e) {
            //En caso de error, imprimimos la causa
            e.printStackTrace();
        }
    }

    /**
     * Método para redirigir a la pantalla que aumenta el progreso del proyecto
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickProgreso(ActionEvent event) {
        try {
            // Obtenemos la ventana actual y la cerramos
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_nuevoProgreso);
            // Mostramos la pantalla de actualizacion de progreso
            ActualizarProgresoController actualizarProgresoController = new ActualizarProgresoController().showEstaPantalla(stage);
            //Le pasamos al controlador el objeto proyecto a modificar
            actualizarProgresoController.setProyecto(this.proyecto);
            // También le pasamos la lista con los proyectos
            actualizarProgresoController.setObservableList(this.proyectos);
        } catch (Exception e) {
            //En caso de error, imprimimos la causa
            e.printStackTrace();
        }
    }

    /**
     * Método para hacer modificables los datos
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickModificable(ActionEvent event) {
        //Si se puede modificar la información
        if(editable){
            //Llamamos al método para que no se pueda modificar
            noModificable();
        }else{
            //Sino llamamos al método para que si la pueda hacer modificable
            modificable();
        }
    }

    /**
     * Método que hace que los datos no se puedan modificar
     */
    private void noModificable(){
        //Todos los elementos del formulario ya no se pueden modificar
        editable= false;
        txt_nombre.setEditable(false);
        txt_diseniador.setEditable(false);
        txt_alto.setEditable(false);
        txt_largo.setEditable(false);
        txt_estado.setEditable(false);
        txt_fechaInicio.setEditable(false);
        txt_fechaFin.setEditable(false);
        txt_puntadasTotales.setEditable(false);
        txt_descripcion.setEditable(false);
        btn_addFoto.setVisible(false);
        btn_guardarCambios.setVisible(false);
    }

    /**
     * Método que hace que los datos sean modificables
     */
    private void modificable(){
        //Todos los elementos del formulario se vuelven modificables
        editable = true;
        txt_nombre.setEditable(true);
        txt_diseniador.setEditable(true);
        txt_alto.setEditable(true);
        txt_largo.setEditable(true);
        txt_estado.setEditable(true);
        //Al comboBox se le pasan los valores que debe tener
        ObservableList<String> estados = FXCollections.observableArrayList("Reuniendo materiales", "Materiales reunidos", "En proceso", "Completado");
        txt_estado.setItems(estados);
        txt_fechaInicio.setEditable(true);
        txt_fechaFin.setEditable(true);
        txt_puntadasTotales.setEditable(true);
        txt_descripcion.setEditable(true);
        btn_addFoto.setVisible(true);
        btn_guardarCambios.setVisible(true);
    }

    /**
     * Método para guardar los cambios realizados en el proyecto
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickGuardarCambios(ActionEvent event) {
        //Se extraen los valores de descripcion y de diseñador, si están vacío se les asigna un valor por defecto
            String diseniador = !txt_diseniador.getText().isEmpty() ? txt_diseniador.getText() : "No especificado";
            String descripcion = !txt_descripcion.getText().isEmpty() ? txt_descripcion.getText() : "Sin descripción";

            //Mediante los set se van modificando los valores del proyecto
            proyecto.setNombre(txt_nombre.getText());
            proyecto.setDescripcion(descripcion);
            proyecto.setDiseniador(diseniador);
            //Los valores numéricos se validan para asegurarse que no vayan vacíos o con formato inválido
            proyecto.setAlto(validar(txt_alto));
            proyecto.setLargo(validar(txt_largo));
            proyecto.setEstado(txt_estado.getValue());
            //Con las fechas primero se pasa el valor a String antes de hacer el set
            proyecto.setFechaInicio(String.valueOf(txt_fechaInicio.getValue()));
            proyecto.setFechaFin(String.valueOf(txt_fechaFin.getValue()));
            proyecto.setPuntadasTotales(validar(txt_puntadasTotales));
            proyecto.setImagen(img_proyecto.getImage());
            //Se valida que los cambios sean válidos
            revisarErrores();
    }

    /**
     *
     * @param textField
     * @return
     */
    private int validar(TextField textField){
        String texto = textField.getText().trim();
        if(!texto.matches("-?\\d+")){
            System.out.println(textField.getText());
            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            throw new IllegalArgumentException("El campo debe contener solo números");
        }
        return Integer.parseInt(texto);
    }

    private void revisarErrores(){
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
                    AlertaUtils.showAlertInformativa(Constantes.TITULO_PROYECTO_MODIFICADO.getDescripcion(), Constantes.AVISO_PROYECTO_MODIFICADO.getDescripcion());
                    try {
                        Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_atras);
                        ProyectosController proyectosController = new ProyectosController().showEstaPantalla(stage);
                        proyectosController.setObservableList(proyectos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }else {
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
        }
    }

    public void initialize(){
        File f = new File("src/main/resources/imagenesProyecto/candado.png");
        ImageView imageView = new ImageView(new Image("file:///" + f.getAbsolutePath().replace("\\", "/")));
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        btn_modificable.setGraphic(imageView);

        btn_addFoto.setVisible(false);
        btn_guardarCambios.setVisible(false);
    }

    public void cargaDatos(){
        txt_nombre.setText(this.proyecto.getNombre());
        txt_diseniador.setText(this.proyecto.getDiseniador());
        txt_alto.setText(String.valueOf(this.proyecto.getAlto()));
        txt_largo.setText(String.valueOf(this.proyecto.getLargo()));
        txt_estado.setValue(this.proyecto.getEstado());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String fechaInicio = this.proyecto.getFechaInicio();
        String fechaFin = this.proyecto.getFechaFin();
        if (fechaInicio != null && !fechaInicio.isEmpty() && !fechaInicio.equals("null")) {
            txt_fechaInicio.setValue(LocalDate.parse(fechaInicio, formatter));
        }

        if (fechaFin != null && !fechaFin.isEmpty() && !fechaFin.equals("null")) {
            txt_fechaFin.setValue(LocalDate.parse(fechaFin, formatter));
        }
        txt_puntadasTotales.setText(String.valueOf(this.proyecto.getPuntadasTotales()));
        txt_progreso.setText(String.valueOf(this.proyecto.getProgreso()));
        txt_descripcion.setText(this.proyecto.getDescripcion());
        img_proyecto.setImage(this.proyecto.getImagen());
    }

    /**
     * Método para mostrar la pantalla de detalles de un proyecto
     *
     * @param stage La ventana principal donde se mostrará la pantalla
     * @return El controlador de la pantalla
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    public DetallesProyectoController showEstaPantalla(Stage stage) throws IOException {
        // Utiliza PantallaUtils para cargar la pantalla de detalles de un proyecto con las dimensiones especificadas
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_DETALLES_PROYECTO.getDescripcion(),Constantes.TITULO_PANTALLA_DETALLES_PROYECTO.getDescripcion(),600,650);
        // Obtenemos el controlador de la pantalla de detalles de un proyecto
        DetallesProyectoController controller = fxmlLoader.getController();
        // Devuelve el controlador de la pantalla de detalles de un proyecto
        return controller;
    }
}