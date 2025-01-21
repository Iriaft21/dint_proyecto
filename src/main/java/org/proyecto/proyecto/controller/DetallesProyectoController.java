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
    void handlerSeleccionarFoto(ActionEvent event) {
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
        //LLamamos al método que nos permite ir a la pantalla anterior
        Utils.irPantallaProyectosPasandoLista(btn_atras, proyectos);
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
        //Se ponen invisibles estos dos botones
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
        //se ponen visibles estos dos botones
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
            proyecto.setAlto(Utils.validarTextFields(txt_alto));
            proyecto.setLargo(Utils.validarTextFields(txt_largo));
            proyecto.setEstado(txt_estado.getValue());
            //Con las fechas primero se pasa el valor a String antes de hacer el set
            proyecto.setFechaInicio(String.valueOf(txt_fechaInicio.getValue()));
            proyecto.setFechaFin(String.valueOf(txt_fechaFin.getValue()));
            proyecto.setPuntadasTotales(Utils.validarTextFields(txt_puntadasTotales));
            proyecto.setImagen(img_proyecto.getImage());
            //Se valida que los cambios sean válidos
            revisarErrores();
    }

    /**
     * Método para envíar diferentes alertas en base a los  errores
     */
    private void revisarErrores(){
        // Acabamos de validar los datos y obtenemos el tipo de error
        Proyecto.TipoError tipoError = proyecto.validar();
        if (!proyecto.datosVacios()) {
            // Evaluamos el tipo de error de acuerdo al resultado de la validación
            switch (tipoError) {
                case ERRORBORDADO:
                    // Mostrar alerta de error cuando las puntadas totales sean mayoree al area del bordado
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_PUNTADAS.getDescripcion(), Constantes.AVISO_PUNTADAS.getDescripcion());
                    break;
                case NEGATIVO:
                    // Mostrar alerta de error por cantidad negativa
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                    break;
                case SIN_ERROR:
                    //Si no da error, se avsia de la modificación del proyecto y volvemos a la pantalla de los proyectos
                    AlertaUtils.showAlertInformativa(Constantes.TITULO_PROYECTO_MODIFICADO.getDescripcion(), Constantes.AVISO_PROYECTO_MODIFICADO.getDescripcion());
                    //Una vez validado, se vuelve a la pantalla anterior
                    Utils.irPantallaProyectosPasandoLista(btn_atras, proyectos);
            }
        }else {
            //Si alguno de los datos esta vacío, salta este aviso
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
        }
    }

    /**
     * Inicializa el controlador configurando la imagen de un boton y haciendo otros dos botones no visibles
     */
    public void initialize(){
        // Se crea un objeto File que representa la ruta de un archivo de imagen
        File f = new File("src/main/resources/imagenesProyecto/candado.png");
        // Creamos un ImageView con la ruta de la imagen
        //A la cual le convertimos las barras invertidas en barras diagonales para garantizar que la ruta sea válida
        ImageView imageView = new ImageView(new Image("file:///" + f.getAbsolutePath().replace("\\", "/")));
        // Se establece el tamaño del ImageView
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        // Se establece la imagen en el ImageView como un gráfico del botón
        btn_modificable.setGraphic(imageView);

        //Se ponen estos dos botones invisibles por defecto
        btn_addFoto.setVisible(false);
        btn_guardarCambios.setVisible(false);
    }

    /**
     * Método que muestra los datos del proyecto
     */
    public void cargaDatos(){
        //Establecemos los valores de proyecto en los respectivos TextFields
        txt_nombre.setText(this.proyecto.getNombre());
        txt_diseniador.setText(this.proyecto.getDiseniador());
        txt_alto.setText(String.valueOf(this.proyecto.getAlto()));
        txt_largo.setText(String.valueOf(this.proyecto.getLargo()));
        txt_estado.setValue(this.proyecto.getEstado());
        //Creamos un formateador para la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //Obtenemos la fecha del proyecto
        String fechaInicio = this.proyecto.getFechaInicio();
        String fechaFin = this.proyecto.getFechaFin();
        //Comprobamos que la fecha de inicio no este vacía
        if (fechaInicio != null && !fechaInicio.isEmpty() && !fechaInicio.equals("null")) {
            //Le damos un cierto formato a la fecha de inicio y la establecemos en el TextField correspondiente
            txt_fechaInicio.setValue(LocalDate.parse(fechaInicio, formatter));
        }
        //Comprobamos que la fecha de fin no este vacía
        if (fechaFin != null && !fechaFin.isEmpty() && !fechaFin.equals("null")) {
            //Le damos un cierto formato a la fecha de fin y la establecemos en el TextField correspondiente
            txt_fechaFin.setValue(LocalDate.parse(fechaFin, formatter));
        }
        //Establecemos el resto de los valores en sus TextFields
        txt_puntadasTotales.setText(String.valueOf(this.proyecto.getPuntadasTotales()));
        txt_progreso.setText(String.valueOf(this.proyecto.getProgreso()));
        txt_descripcion.setText(this.proyecto.getDescripcion());
        //También establecemos la imagen en su respectivo ImageView
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