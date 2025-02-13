package org.proyecto.proyecto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyecto.reports.Reporte;
import org.proyecto.proyecto.reports.ReporteFiltro;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class GenerarInformeController {

    @FXML
    private Button btn_generar;

    @FXML
    private ComboBox<String> combo_campos;

    @FXML
    private TextField txt_buscar;

    @FXML
    private Button btn_atras;

    /**
     * Método para volver a la pantalla anterior
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickAtras(ActionEvent event) {
        try {
            // Obtenemos la ventana actual y la cerramos
            Stage stage = new PantallaUtils().cerrarEstaPantalla(btn_atras);
            // Mostramos la pantalla de la calculadora
            InventarioController inventarioController = new InventarioController().showEstaPantalla(stage);
        } catch (Exception e) {
            //En caso de error, mostramos la causa
            e.printStackTrace();
        }
    }

    /**
     * Inicializa el controlador configurando el comboBox y los valores que va a tener así como el valor por defecto
     */
    public void initialize(){
        ObservableList<String> campos = FXCollections.observableArrayList(Constantes.CAMPO_NOMBRE.getDescripcion(), Constantes.CAMPO_MARCA.getDescripcion(), Constantes.CAMPO_CANTIDAD.getDescripcion());
        combo_campos.setItems(campos);
        combo_campos.setValue(Constantes.CAMPO_NOMBRE.getDescripcion());
    }

    /**
     * Genera reportes pasadole datos el usuario
     *
     * @param event El evento de la acción
     */
    @FXML
    void onClickGenerar(ActionEvent event) {
        try{
            Connection conn = Utils.conexion();
            if (conn != null) {
                //Llamamos a la clase Reporte
                ReporteFiltro generator = new ReporteFiltro();
                generator.generarReporte(conn, combo_campos.getValue(), txt_buscar.getText());
            } else {
                System.out.println("Error al conectar a la base de datos.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Método para mostrar la pantalla de generar informes
     *
     * @param stage La ventana principal donde se mostrará la pantalla
     * @return El controlador de la pantalla
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    public GenerarInformeController showEstaPantalla(Stage stage) throws IOException {
        // Utiliza PantallaUtils para cargar la pantalla de inventario con las dimensiones especificadas
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_GENERAR_INFORMES.getDescripcion(), Constantes.TITULO_PANTALLA_GENERAR_INFORMES.getDescripcion(), 400, 250);
        // Obtenemos el controlador de la pantalla de inventario
        GenerarInformeController controller = fxmlLoader.getController();

        // Devuelve el controlador de la pantalla de inventario
        return controller;
    }
}
