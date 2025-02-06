package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.*;

import java.io.IOException;

/**
 * Controlador de la pantalla del formulario de creación de un proyecto
 *
 * Contiene metodos para obtener datos de TextField, así como validarlos
 */
public class FormularioProyectoController {

    @FXML
    private Button btn_atras;

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
        Utils.seleccionarImagen(img);
    }

    /**
     * Inicializa el controlador configurando un comboBox con sus valores y el que tendrá por defecto
     */
    public void initialize(){
        btn_limpiarCampos.setId("limpiar");
        estados = FXCollections.observableArrayList(Constantes.ESTADO_PROYECTO_REUNIENDO.getDescripcion(), Constantes.ESTADO_PROYECTO_REUNIDOS.getDescripcion(),
                Constantes.ESTADO_PROYECTO_EN_PROCESO.getDescripcion(), Constantes.ESTADO_PROYECTO_COMPLETADO.getDescripcion());
        //Añadimos al ComboBox el ObservableList con los distintos estados del proyecto
        combo_estado.setItems(estados);
        //Se pone un valor por defecto
        combo_estado.setValue(Constantes.ESTADO_PROYECTO_EN_PROCESO.getDescripcion());
    }

    /**
     * Método para volver a la pantalla anterior
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickAtras(ActionEvent event) {
        Utils.irPantallaProyectos(btn_atras);
    }

    /**
     * Método para crear un nuevo proyecto a partir de la información pasada
     * @param event El evento de la acción
     */
    @FXML
    private void onClickCrear(ActionEvent event){
        //Se llama al método que va a extraer los datos y nos devuelve un objeto Proyecto
        Proyecto proyecto = extraerDatos();
        //Si el proyecto no es nulo
        if (proyecto != null){
            proyecto.insertarProyecto(proyecto);
            proyectos.add(proyecto);
            //Se llama al método que nos llevará de vuelta a la pantalla anterior
            Utils.irPantallaProyectosPasandoLista(btn_crear, proyectos);
        }
    }

    /**
     * Método que extraer los datos de los correspondientes campos y se los pasa a otro método para validarlos
     *
     * @return Resultado de la validación del proyecto
     */
    private Proyecto extraerDatos(){
        Proyecto proyecto =  null;
        // Obtiene el nombre del proyecto desde el campo de texto
        String nombre = txt_nombre.getText();
        // Obtiene el estado del proyecto desde el combo box
        String estado = combo_estado.getValue();
        // Obtiene las fechas de inicio y fin del proyecto y las convierte a String
        String fechaInicio = String.valueOf(dp_fechaInicio.getValue());
        String fechaFin = String.valueOf(dp_fechaFin.getValue());
        // Se obtiene la descripción del proyecto y si está vacío, establece "Sin descripción"
        String descripcion = !txt_descripcion.getText().isEmpty()? txt_descripcion.getText() : Constantes.DESCRIPCION_VACIA.getDescripcion();
        // Se obtiene el diseñador del proyecto y si está vacío, establece "No especificado"
        String diseniador = !txt_diseniador.getText().isEmpty()? txt_diseniador.getText() : Constantes.DISENIADOR_VACIO.getDescripcion();
        // Valida y obtiene las dimensiones del proyecto
        int alto = Utils.validarTextFields(txt_alto);
        int largo = Utils.validarTextFields(txt_largo);
        // Valida y obtiene el número total de puntadas del proyecto
        int puntadasTotales = Utils.validarTextFields(txt_puntadasTotales);

        // Crea un nuevo objeto Proyecto con los datos obtenidos
        proyecto = new Proyecto(nombre, descripcion, diseniador, alto, largo, estado, puntadasTotales, fechaInicio, fechaFin, img.getImage());
        // Valida el proyecto y devuelve el resultado
        return avisosValidar(proyecto);
    }

    /**
     * Método para enviar errores
     *
     * @param proyecto El objeto a validar
     * @return El objeto proyecto si no hay errores, sino un null
     */
    private Proyecto avisosValidar(Proyecto proyecto){
        //Validamos los datos y obtenemos el tipo de error
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
                    //Si no da error, se avsia de la creación del proyecto
                    AlertaUtils.showAlertInformativa(Constantes.TITULO_PROYECTO_CREADO.getDescripcion(), Constantes.AVISO_PROYECTO_CREADO.getDescripcion());
                    //Devolvemos el objeto
                    return proyecto;
            }
        }else{
            //Si alguno de los datos esta vacío, salta este aviso
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
        }
        return null;
    }

    /**
     * Método para limpiar los TextFields
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickLimpiarCampos(ActionEvent event) {
        //Ponemos el valor por defecto en el ComboBox
        combo_estado.setValue(Constantes.ESTADO_PROYECTO_EN_PROCESO.getDescripcion());
        //Se ponen los valores de los DatePicker en null
        dp_fechaInicio.setValue(null);
        dp_fechaFin.setValue(null);
        //Limpiamos los campos
        txt_alto.clear();
        txt_largo.clear();
        txt_descripcion.clear();
        txt_puntadasTotales.clear();
        txt_nombre.clear();
        txt_diseniador.clear();
        //Eliminamos la imagen del ImageView
        img.setImage(null);
    }

    /**
     * Método de un botón para salir del programa
     *
     * @param event El evento de acción
     */
    @FXML
    void onClickSalir(ActionEvent event) {
        //Llamamos al método de Utils
        Utils.botonSalir();
    }

    /**
     * Método para mostrar la pantalla del formulario de creación del proyecto
     *
     * @param stage La ventana principal donde se mostrará la pantalla
     * @return El controlador de la pantalla
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    public FormularioProyectoController showEstaPantalla(Stage stage) throws IOException {
        // Utiliza PantallaUtils para cargar la pantalla del formulario de creación del proyecto con las dimensiones especificadas
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_FORMULARIO_PROYECTO.getDescripcion(),Constantes.TITULO_PANTALLA_FORMULARIO_PROYECTO.getDescripcion(),550,600);
        // Obtenemos el controlador de la pantalla del formulario de creación del proyecto
        FormularioProyectoController controller = fxmlLoader.getController();
        // Devuelve el controlador de la pantalla del formulario de creación del proyecto
        return controller;
    }
}