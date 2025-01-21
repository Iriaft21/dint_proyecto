package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Proyecto;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Utils;

import java.io.IOException;

/**
 * Controlador de la pantalla de seguimiento de los proyectos
 *
 * Tiene distintos métodos relacionados con botones y modificación/creación/eliminado de proyectos,
 * así como métodos relacionados con el ArrayList de la pantalla
 */
public class ProyectosController {

    @FXML
    private Button btn_aniadir;

    @FXML
    private Button btn_atras;

    @FXML
    private Button btn_eliminar;

    @FXML
    private Button btn_salir;

    @FXML
    private ListView<Proyecto> listView;

    @FXML
    private MenuItem menuItem_calculadora;

    @FXML
    private MenuItem menuItem_inventario;

    private ObservableList<Proyecto> proyectos;

    /**
     * Establece la lista de proyectos y actualiza el ListView asociado
     *
     * @param proyectos La lista e de proyectos a establecer
     */
    public void setObservableList(ObservableList<Proyecto> proyectos) {
        // Asigna la lista de proyectos a la variable de instancia
        this.proyectos = proyectos;
        // Establece la lista en el ListView asociado
        this.listView.setItems(this.proyectos);
        // Refresca el ListView para asegurar que se muestre la lista actualizada
        this.listView.refresh();
    }

    /**
     * Método para volver al menú
     *
     * @param event
     */
    @FXML
    void onClickAtras(ActionEvent event) {
        //Llamamos al método estático de utils para que nos lleve de vuelta al menú
        Utils.irAtrasMenu(btn_atras);
    }

    /**
     * Método para añadir un nuevo proyecto
     *
     * @param event El evento de la acción
     */
    @FXML
    private void onClickAniadir(ActionEvent event){
        try {
            // Obtenemos la ventana actual y la cerramos
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_aniadir);
            // Mostramos la pantalla del formulario para crear el proyecto
            FormularioProyectoController formularioProyectoController = new FormularioProyectoController().showEstaPantalla(stage);
            // Se le pasa la lista para que la actualice
            formularioProyectoController.setObservableList(proyectos);
        } catch (Exception e) {
            //En caso de error, se muestra la causa
            e.printStackTrace();
        }
    }

    /**
     * Método para eliminar un proyecto
     * @param event
     */
    @FXML
    void onClickEliminar(ActionEvent event) {
        // Se obtiene el proyecto que se desea eliminar
        Proyecto proyectoBorrar = listView.getSelectionModel().getSelectedItem();
        // Si dicho proyecto no es nulo
        if(proyectoBorrar != null){
            // Se procede a eliminarlo del ListView
            listView.getItems().remove(proyectoBorrar);
        }
    }


    /**
     * Método para salir del programa
     *
     * @param event
     */
    @FXML
    void onClickSalir(ActionEvent event) {
        //LLamamos al correspondiente método de Utils para salir del programa
        Utils.botonSalir();
    }

    /**
     * Método para cuando se clica dos veces en un elemento del ListView, redirija a los detalles de ese proyecto
     *
     * @param click Clics del ratón necesarios para el evento
     */
    @FXML
    private void  onMouseClickedListView(MouseEvent click) {
        //se se ha clicados veces en el elemento
        if(click.getClickCount() == 2){
            //Se obtiene el proyecto que ha sido seleccionado
            Proyecto proyectoDetalle = listView.getSelectionModel().getSelectedItem();
            try {
                // Obtiene el Stage actual a partir del ListView
                Stage stage = (Stage)listView.getScene().getWindow();
                //Cierra la ventana
                stage.close();
                // Mostramos la pantalla de detalles de ese proyecto
                DetallesProyectoController detallesProyectoController = new DetallesProyectoController().showEstaPantalla(stage);
                // Le pasamos el proyecto en cuestión
                detallesProyectoController.setProyecto(proyectoDetalle);
                //También le pasamos la lista con los proyectos
                detallesProyectoController.setObservableList(proyectos);
                //Llamamos al método que va a cargar los datos
                detallesProyectoController.cargaDatos();
            } catch (Exception e) {
                //En caso de error, mostramos la causa
                e.printStackTrace();
            }
        }
    }

    /**
     * Inicializa el controlador configurando el ListView y sus celdas personalizadas y lo actualiza
     */
    public void initialize(){
        // Inicializa la lista observable de proyectos
        proyectos = FXCollections.observableArrayList();
        // Establece la lista en el ListView
        listView.setItems(proyectos);
        //Llamamos al método que genera celdas personalizadas
        listViewPersonalizado();
        // Refrescamos el ListView para asegurar que se muestre la lista actualizada en caso de haber algún cambio
        listView.refresh();
    }

    /**
     * Método que crea celdas personalizadas del ListView
     */
    public void listViewPersonalizado(){
        listView.setCellFactory(param -> new ListCell<Proyecto>() {
            @Override
            protected void updateItem(Proyecto proyecto, boolean empty) {
                super.updateItem(proyecto, empty);
                // Si la celda está vacía o el proyecto es nulo, vacía la celda
                if (empty || proyecto == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    //Creamos etiquetas para mostrar el nombre, estado y progreso del proyecto
                    Label nombreLabel = new Label("Nombre: " + proyecto.getNombre());
                    Label estadoLabel = new Label("Estado: " + proyecto.getEstado());
                    Label progresoLabel = new Label("Progreso: " + proyecto.getProgreso() + "%");
                    // Añadimos las etiquetas a un VBox para organizarlas verticalmente
                    VBox vBox = new VBox(nombreLabel, estadoLabel, progresoLabel);
                    //Ponemos espacio entre las etiquetas
                    vBox.setSpacing(5);
                    // Creamos un ImageView para mostrar la imagen del proyecto
                    ImageView imageView = new ImageView();
                    if (proyecto.getImagen() != null) {
                        try {
                            //Se añade a la ImageView la imagen del proyecto
                            imageView.setImage(proyecto.getImagen());
                        } catch (Exception e) {
                            // Si da error, se da aviso de ello
                            System.out.println("Invalid URL: " + e.getMessage());
                        }
                    }
                    //Se modifica el anchoy alto de la imagen, además de  mantener la proporción de la imagen
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    imageView.setPreserveRatio(true);
                    // Se añade el VBox a un HBox para organizarlo horizontalmente
                    HBox hBox = new HBox(vBox, imageView);
                    // Se añade espacio entre el texto y la imagen
                    hBox.setSpacing(340);
                    //  Se establece el contenido gráfico de la celda con el HBox
                    setGraphic(hBox);
                    setText(null);
                }
            }
        });
    }

    /**
     * Evento en el menú que lleva a la pantalla de la calculadora
     *
     * @param event El evento de acción
     */
    @FXML
    void onClickMenuItemCalculadora(ActionEvent event) {
        //Llamamos a Utils y al método para que nos lleven a la pantalla de la calculadora
        Utils.menuCalculadora(menuItem_calculadora);
    }

    /**
     * Evento en el menú que lleva a la pantalla del inventario
     *
     * @param event El evento de acción
     */
    @FXML
    void onClickMenuItemInventario(ActionEvent event) {
        //Llamamos a Utils y al método para que nos lleven a la pantalla del inventario
        Utils.menuInventario(menuItem_inventario);
    }

    /**
     * Método para mostrar la pantalla del seguimiento de proyectos
     *
     * @param stage La ventana principal donde se mostrará la pantalla
     * @return El controlador de la pantalla
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    public ProyectosController showEstaPantalla(Stage stage) throws IOException {
        // Utiliza PantallaUtils para cargar la pantalla de seguimiento de proyectos con las dimensiones especificadas
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_PROYECTOS.getDescripcion(), Constantes.TITULO_PANTALLA_PROYECTOS.getDescripcion(), 600, 600);
        // Obtenemos el controlador de la pantalla de seguimiento de proyectos
        ProyectosController controller = fxmlLoader.getController();

        // Devuelve el controlador de la pantalla de seguimiento de proyectos
        return controller;
    }
}
