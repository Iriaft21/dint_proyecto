package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.Hilo;
import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Utils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Controlador de la pantalla del inventario
 *
 * Tiene distintos métodos relacionados con una tabla, tales como añadir, eliminar y modificar.
 * Así como enviar errores de los distintos valores que se validan.
 */
public class InventarioController {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_salir;

    @FXML
    private Button btn_atras;

    @FXML
    private TableColumn<Hilo, String> colCant;

    @FXML
    private TableColumn<Hilo, String> colMarca;

    @FXML
    private TableColumn<Hilo, String> colNombre;

    @FXML
    private Label lbl_titulo;

    @FXML
    private TableView<Hilo> table_hilos;

    @FXML
    private TextField txt_cant;

    @FXML
    private ComboBox<String> txt_marca;

    @FXML
    private TextField txt_num;

    @FXML
    private Button btn_eliminar;

    @FXML
    private MenuItem menuItem_calculadora;

    @FXML
    private MenuItem menuItem_proyectos;

    private ObservableList<String> marcasHilos;

    /**
     * Método para eliminar algún hilo de la tabla
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickEliminar(ActionEvent event) {
        //Buscamos que hilo es el que se ha seleccionado en la tabla
        Hilo hiloSeleccionado = table_hilos.getSelectionModel().getSelectedItem();
        //Eliminamos el hilo de la tabla
        table_hilos.getItems().remove(hiloSeleccionado);
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
     * Método para salir del programa
     *
     * @param event
     */
    @FXML
    void onClickSalir(ActionEvent event) {
        //LLamamosal correspondiente método de Utils para salir del programa
        Utils.botonSalir();
    }

    /**
     * Inicializa el controlador configurando la tabla y una alerta personalizada, asi como la modificacion de datos
     */
    public void initialize(){
        //Llamamos la metodo que crea la tabla
        crearTabla();
        //Añadimos a la tabla el ObservableList donde se almacenarán los hilos
        ObservableList<Hilo> datosHilos = FXCollections.observableArrayList();
        table_hilos.setItems(datosHilos);
        //Llamamos al método de modificacion de datos
        modificarDatos();
    }

    /**
     * Método que configuara y crea la tabla
     */
    void crearTabla(){
        // Inicializa la lista de marcas de hilos con algunas opciones predeterminadas
        marcasHilos = FXCollections.observableArrayList("DMC", "Anchor", "Kreinik", "Madeira", "Otros");
        // Permite la edición en la tabla de hilos
        table_hilos.setEditable(true);
        // Establece las opciones del ComboBox para seleccionar la marca
        txt_marca.setItems(marcasHilos);
        //Establece un valor por defecto del ComboBox
        txt_marca.setValue("DMC");

        // Configura la columna de marcas
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colMarca.setText("Marca");
        colMarca.setPrefWidth(200);

        // Configura la columna de nombres
        colNombre.setCellValueFactory(new PropertyValueFactory<Hilo, String>("nombre"));
        colNombre.setText("Nombre");
        colNombre.setPrefWidth(200);

        // Configura la columna de cantidad
        colCant.setCellValueFactory(new PropertyValueFactory<Hilo, String>("cantidad"));
        colCant.setText("Cantidad");
        colCant.setPrefWidth(200);
    }

    /**
     * Método para añadir hilos a la tabla
     *
     * @param event El eventp de la acción
     */
    @FXML
    void onClickAdd(ActionEvent event) {
        //Se extrean de los TextFields los valores que hacen falta
        String nombre = txt_num.getText();
        String marca = txt_marca.getValue();
        String cantStr = txt_cant.getText();

        //Se crea un objeto Hilo con esos valores
        Hilo hilo = new Hilo(nombre, marca, cantStr);
        //Llamamos al método que valida y llama alertas en caso de error
        if(datosValidos(hilo)){
            // Añadimos el hilo a la tabla si no hay errores
            table_hilos.getItems().add(hilo);
        }
        //Lllamamos al método que limpia los TextFields
        limpiarCampos();
    }

    /**
     * Método para validar valores y en caso negativo llamar alertas
     *
     * @param hilo El objeto del que deseamos validar los valores
     * @return
     */
    public Boolean datosValidos(Hilo hilo) {
        // Validamos la cantidad del hilo y obtenemos el tipo de error
        Hilo.TipoError tipoError = hilo.validarCantidad();

        //Comprobamos que los datos no estén vacíos
        if (!hilo.datosVacios()) {
            // Evaluamos el tipo de error de acuerdo al resultado de la validación
            switch (tipoError) {
                case FORMATO:
                    // Mostramos alerta de error de formato
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
                    return false;
                case NUMEXCESIVO:
                    // Mostrar alerta de error por demasiadas unidades de un hilo añadidas de golpe
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_DEMASIADAS_UNIDADES.getDescripcion(), Constantes.AVISO_DEMASIADAS_UNIDADES.getDescripcion());
                    return false;
                case NEGATIVO:
                    // Mostramos alerta de error por cantidad negativa
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                    return false;
                case SIN_ERROR:
                    return true;
            }
        }else{
            // Mostrar alerta informativa si los datos del hilo están vacíos
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
            return false;
        }
        return true;
    }

    /**
     * Método para modificar datos
     */
    public void modificarDatos(){
        // Crear un hipervínculo con el texto "Comprar hilos"
        Hyperlink hyperlink = new Hyperlink("Comprar hilos");
        // Establecer una acción para cuando se clique en el hipervínculo
        hyperlink.setOnAction(e -> {
            try {
                // Se abre el navegador predeterminado y navega a la URL especificada
                Desktop.getDesktop().browse(new URI("https://www.casacenina.es/hilos-y-hilados.html"));
            } catch (IOException | URISyntaxException ex) {
                // En caso de error, imprimimos la causa
                ex.printStackTrace();
            }
        });
        // Llamamos al método para modificar la marca del hilo
        modificarMarca();
        // Llamamos al método para modificar el nombre del hilo
        modificarNombre();
        // Llamamos al método para modificar la cantidad del hilo
        modificarCantidad(hyperlink);
    }

    /**
     * Método para modificar la marca de un hilo
     */
    public void modificarMarca(){
        // Establecemos la celda de la columna de marcas como un ComboBoxTableCell y le pasamos la lista de hilos del ComboBox
        colMarca.setCellFactory(ComboBoxTableCell.<Hilo, String>forTableColumn(marcasHilos));
        //Evento cuando se modifica la columna
        colMarca.setOnEditCommit(
                (TableColumn.CellEditEvent<Hilo, String> t) -> {
                    // Obtenemos el objeto Hilo de la fila editada
                    Hilo hilo = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    // Guardamos el valor original
                    String valorOriginal = hilo.getMarca();
                    //Guardamos el nuevo valor
                    String nuevoValor = t.getNewValue();
                    // Aplicamos el nuevo valor temporalmente para validarlo
                    hilo.setMarca(nuevoValor);

                    if (!datosValidos(hilo)) {
                        // Si la validación falla, revertimos el valor original
                        hilo.setMarca(valorOriginal);
                    }
                });
    }

    /**
     * Método para modificar la marca de un hilo
     */
    public void modificarNombre(){
        // Establecemos la celda de la columna de nombre como un TextFieldTableCell
        colNombre.setCellFactory(TextFieldTableCell.<Hilo>forTableColumn());
        //Evento cuando se modifica la columna
        colNombre.setOnEditCommit(
                (TableColumn.CellEditEvent<Hilo, String> t) -> {
                    Hilo hilo = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    // Guardamos el valor original
                    String valorOriginal = hilo.getNombre();
                    //Guardamos el nuevo valor
                    String nuevoValor = t.getNewValue();
                    // Aplicamos el nuevo valor temporalmente para validarlo
                    hilo.setNombre(nuevoValor);

                    if (!datosValidos(hilo)) {
                        // Si la validación falla, revertimos el valor original
                        hilo.setNombre(valorOriginal);
                    }
                });
    }

    /**
     * Método para modificar la cantidad del hilo
     * @param hyperlink Hipervínculo necesitamos para una alerta personalizada
     */
    public void modificarCantidad(Hyperlink hyperlink){
        // Establecemos la celda de la cantidad de nombre como un TextFieldTableCell
        colCant.setCellFactory(TextFieldTableCell.<Hilo>forTableColumn());
        //Evento cuando se modifica la columna
        colCant.setOnEditCommit((TableColumn.CellEditEvent<Hilo, String> t) ->{
            // Obtenemos el objeto Hilo de la fila editada
            Hilo hilo = t.getTableView().getItems().get(t.getTablePosition().getRow());
            // Guardamos el valor original
            String valorOriginal = hilo.getCantidad();
            //Guardamos el nuevo valor
            String nuevoValor = t.getNewValue();
            // Aplicamos el nuevo valor temporalmente para validarlo
            hilo.setCantidad(nuevoValor);

            if (!datosValidos(hilo)) {
                // Si la validación falla, revertimos el valor original
                hilo.setCantidad(valorOriginal);
            }

            if(nuevoValor.trim().equals("0")){
                //Muestra una alerta personalizada en caso de que la cantidad moficada sea 0
                AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_SIN_HILO.getDescripcion(), Constantes.AVISO_SIN_HILO.getDescripcion(), hyperlink);
            }
        });
    }

    /**
     * Método para limpiar los TextFields
     */
    public void limpiarCampos(){
        //Limpiamos los campos
        txt_num.clear();
        txt_cant.clear();
        //Ponemos el valor por defecto en el ComboBox
        txt_marca.setValue("DMC");
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
     * Evento en el menú que lleva a la pantalla del seguimiento de proyectos
     *
     * @param event El evento de acción
     */
    @FXML
    void onClickMenuItemProyectos(ActionEvent event) {
        //Llamamos a Utils y al método para que nos lleven a la pantalla de seguimiento de los proyectos
        Utils.menuProyectos(menuItem_proyectos);
    }

    /**
     * Método para mostrar la pantalla del inventario
     *
     * @param stage La ventana principal donde se mostrará la pantalla
     * @return El controlador de la pantalla
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    public InventarioController showEstaPantalla(Stage stage) throws IOException {
        // Utiliza PantallaUtils para cargar la pantalla de inventario con las dimensiones especificadas
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_INVENTARIO.getDescripcion(), Constantes.TITULO_PANTALLA_INVENTARIO.getDescripcion(), 600, 600);
        // Obtenemos el controlador de la pantalla de inventario
        InventarioController controller = fxmlLoader.getController();

        // Devuelve el controlador de la pantalla de inventario
        return controller;
    }
}
