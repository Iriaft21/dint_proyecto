package org.proyecto.proyecto.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.CalculoTela;
import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controlador de la pantalla de la Calculadora
 *
 * Contiene metodos para obtener datos de TextField y limpiarlos, así como validar y extraer el valor de algunos de esos campos
 */
public class CalculadoraController {

    @FXML
    private Button btn_atras;

    @FXML
    private Button btn_calcular;

    @FXML
    private Button btn_limpiarCampos;

    @FXML
    private Button btn_salir;

    @FXML
    private MenuItem menuItem_inventario;

    @FXML
    private MenuItem menuItem_proyectos;

    @FXML
    private ComboBox<String> spinner_ct;

    @FXML
    private TextField txt_alto;

    @FXML
    private TextField txt_largo;

    @FXML
    private TextField txt_telaAcabado;

    @FXML
    private TextField txt_telaBorde;

    private ObservableList<String> tiposTelas;

    /**
     * Método de un botón para que al pulsarlo se vuelva al menú
     * @param event El evento de acción
     */
    @FXML
    void onClickAtras(ActionEvent event) {
        //Se llama al método que devuleve al menú a través de Utils y se le pasa el botón que activa la acción
        Utils.irAtrasMenu(btn_atras);
    }

    /**
     * Método para extraer valores, validarlos y que envíe errores en base a eso
     *
     * @param event El evento de acción
     */
    @FXML
    private void  onClickCalcular(ActionEvent event) {
        //se extraen los valores del TextField y de paso se validan parcialmente
        int alto = validar(txt_alto);
        int largo = validar(txt_largo);
        //en el caso del float se realizan una modificaciones del valor del ComboBox para que se adapte al tipo de dato del objeto
        float hilosTela = extraerHilos();
        int telaAcabado = validar(txt_telaAcabado);
        int telaBorde = validar(txt_telaBorde);
        //Se crea el objeto CalculoTela
        CalculoTela calculoTela = new CalculoTela(alto, largo, hilosTela, telaBorde, telaAcabado);

        // Acabamos de validar los datos y obtenemos el tipo de error
        CalculoTela.TipoError tipoError = calculoTela.validar();
            // Evaluamos el tipo de error de acuerdo al resultado de la validación
            switch (tipoError) {
                case NEGATIVO:
                    // Mostrar alerta de error por cantidad negativa
                    AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
                    break;
                case SIN_ERROR:
                    //Si no hay errores, se pasa el objeto a la siguiente pantalla
                    try {
                        // Obtenemos la ventana principal y la cerramos
                        Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_calcular);
                        // Mostramos la pantalla del resultado del calculo
                        ResultadoCalculoController resultadoCalculoController = new ResultadoCalculoController().showEstaPantalla(stage);
                        //Le pasamos el objeto a esa pantalla
                        resultadoCalculoController.setCalculoFromMain(calculoTela);
                    } catch (Exception e) {
                        //En caso de error, se muestra la causa
                        e.printStackTrace();
                    }
                    break;
            }
    }

    /**
     * Método para comprobar si los TextFields van vacíos o si cumplen cierto formato
     *
     * @param textField Los TextFields de la pantalla que necesitamos comprobar
     * @return El valor del TextField ya extraído y pasado a int
     * @throws IllegalArgumentException Si el valor pasado está vacío o tiene el formato incorrecto
     */
    private int validar(TextField textField){
        String texto = textField.getText().trim();
        //Comprobamos que el texto no vaya vacío
        if (texto.isEmpty()) {
            // Mostrar alerta informativa si los datos del hilo están vacíos
            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
            //De paso lanzamos una excepcion para avisar del error
            throw new IllegalArgumentException("El campo no puede estar vacío");
            //Se verifica que la String sea un número entero, tanto positivo como negativo
        }else if(!texto.matches("-?\\d+")){
            // Mostramos alerta de error de formato
            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
            //Se lanza una excepcion para visar del error
            throw new IllegalArgumentException("El campo debe contener solo números");
        }
        //Se devuelve el valor del TextField ya extraído y como un Int
        return Integer.parseInt(texto);
    }

    /**
     * Método para limpiar los TextFields
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickLimpiarCampos(ActionEvent event) {
        //Limpiamos los campos
        txt_alto.clear();
        txt_largo.clear();
        txt_telaAcabado.clear();
        txt_telaBorde.clear();
        //Ponemos el valor por defecto en el ComboBox
        spinner_ct.setValue("14 ct (5,4 hilos/cm)");
    }

    /**
     * Extrae el valor numérico de cuántas puntadas hay por centimetro dependiendo del tipo de tela
     *
     * @return El número de puntadas por centímetro como un float. Si el formato no coincide, devuelve 0.
     */
    float extraerHilos(){
        // Obtiene el tipo de tela seleccionado del spinner
        String tipoTela = spinner_ct.getValue();
        // Se define el patrón para buscar el número de hilos por cm
        Pattern pattern = Pattern.compile("\\((\\d+,?\\d*) hilos/cm\\)");
        //Se aplica el patrón a la cadena
        Matcher matcher = pattern.matcher(tipoTela);
        // Si encuentra una coincidencia
        if(matcher.find()){
            // Se extrae la cadena del grupo 1 (el número de hilos) y reemplazar la coma por un punto
            String hilosStr = matcher.group(1).replace(",", ".");
            // Convierte la cadena en float y lo devuelve
            return Float.parseFloat(hilosStr);
        }
        // Si no encuentra coincidencias, devuelve 0
        return 0;
    }

    /**
     * Inicializa el controlador configurando el comboBox y los valores que va a tener así como el valor por defecto
     */
    public void initialize(){
        // Se añaden los distintos valores al observableArrayList
        tiposTelas = FXCollections.observableArrayList("11 ct (4,2 hilos/cm)", "12 ct (4,6 hilos/cm)", "14 ct (5,4 hilos/cm)", "16ct (6,2 hilos/cm)",
                "18 ct (7 hilos/cm)", "20 ct (7,8 hilos/cm)", "22 ct (8,5 hilos/cm)", "24 ct (9,3 hilos/cm)");
        // Se añade ese observableArrayList al comboBox
        spinner_ct.setItems(tiposTelas);
        // Se pone uno de los valores por defecto
        spinner_ct.setValue("14 ct (5,4 hilos/cm)");
    }

    /**
     * Evento en el menú que lleva a la pantalla del inventario
     *
     * @param event El evento de acción
     */
    @FXML
    void onClickMenuItemInventario(ActionEvent event) {
        try {
            Stage stage = (Stage) menuItem_inventario.getParentPopup().getOwnerWindow().getScene().getWindow();
            stage.close();
            InventarioController inventarioController = new InventarioController().showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Evento en el menú que lleva a la pantalla del seguimiento de proyectos
     *
     * @param event El evento de acción
     */
    @FXML
    void onClickMenuItemProyectos(ActionEvent event) {
        //TODO lo de Utils
        try {
            Stage stage = (Stage) menuItem_proyectos.getParentPopup().getOwnerWindow().getScene().getWindow();
            stage.close();
            ProyectosController proyectosController = new ProyectosController().showEstaPantalla(stage);
        } catch (Exception e) {
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
        //TODO utils
        Platform.exit();
    }

    /**
     * Método para mostrar la pantalla de la calculadora
     *
     * @param stage La ventana principal donde se mostrará la pantalla
     * @return El controlador de la pantalla
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    public CalculadoraController showEstaPantalla(Stage stage) throws IOException {
        // Utiliza PantallaUtils para cargar la pantalla de la calculadora con las dimensiones especificadas
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_CALCULADORA.getDescripcion(),Constantes.TITULO_PANTALLA_CALCULADORA.getDescripcion(),600,425);

        // Obtenemos el controlador de la pantalla de la calculadora
        CalculadoraController controller = fxmlLoader.getController();

        // Devuelve el controlador de la pantalla de inventario
        return controller;
    }
}
