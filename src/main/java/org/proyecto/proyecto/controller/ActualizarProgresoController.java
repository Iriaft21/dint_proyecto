package org.proyecto.proyecto.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Utils;

import java.io.IOException;

/**
 * Controlador de la pantalla de actualizar progreso
 *
 * Contiene un método para actualizar el susodicho
 */
public class ActualizarProgresoController {

    @FXML
    private Button btn_actualizar;

    @FXML
    private Button btn_salir;

    @FXML
    private TextField txt_puntadasNuevas;

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
     * Método para actualizar el valor del progreso
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickActualizar(ActionEvent event) {
        int puntadasNuevas = 0;
        if(txt_puntadasNuevas != null && proyecto!= null){
            // Obtenemos el valor de nuevas puntadas desde el campo de texto
            puntadasNuevas = Integer.parseInt(txt_puntadasNuevas.getText());
            // Calculamos y redondeamos progreso del proyecto
            float redondeado = Math.round(proyecto.calcularProgreso(puntadasNuevas) * 100)/100f;
            // Establecemos nuevo progreso del proyecto
            proyecto.setProgreso(redondeado);
            if(proyecto.getProgreso()==100){
                //Marcamos el proyecto como completado si el progreso es 100%
                proyecto.setEstado("Completado");
            }
        }
        try {
            // Obtiene la pantalla actual y se cierra
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_actualizar);
            // Mostramos la pantalla de detalles de ese proyecto
            DetallesProyectoController detallesProyectoController = new DetallesProyectoController().showEstaPantalla(stage);
            // Le pasamos el proyecto en cuestión
            detallesProyectoController.setProyecto(this.proyecto);
            //También le pasamos la lista con los proyectos
            detallesProyectoController.setObservableList(this.proyectos);
            //Llamamos al método que va a cargar los datos
            detallesProyectoController.cargaDatos();
        } catch (Exception e) {
            //En caso de error, mostramos la causa
            e.printStackTrace();
        }
    }

    /**
     * Método de un botón para salir del programa
     *
     * @param event El evento de acción
     */
    @FXML
    void onClickSalir(ActionEvent event) {
        //Llamamos al método de Utils para salir de la pantalla
        Utils.botonSalir();
    }

    /**
     * Método para mostrar la pantalla del formulario de creación del proyecto
     *
     * @param stage La ventana principal donde se mostrará la pantalla
     * @return El controlador de la pantalla
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    public ActualizarProgresoController showEstaPantalla(Stage stage) throws IOException {
        // Utiliza PantallaUtils para cargar la pantalla de modificacion del progreso con las dimensiones especificadas
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_ACTUALIZAR_PROGRESO.getDescripcion(),Constantes.TITULO_PANTALLA_ACTUALIZAR_PROGRESO.getDescripcion(),500,300);
        // Obtenemos el controlador de la pantalla de modificacion del progreso
        ActualizarProgresoController controller = fxmlLoader.getController();
        // Devuelve el controlador de la pantalla de modificacion del progreso
        return controller;
    }
}
