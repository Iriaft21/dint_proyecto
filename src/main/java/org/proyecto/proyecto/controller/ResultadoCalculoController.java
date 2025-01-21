package org.proyecto.proyecto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyecto.modelo.CalculoTela;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;
import org.proyecto.proyecto.utils.Utils;

import java.io.IOException;

/**
 * Controlador de la pantalla de resultados del cálculo
 *
 * Contiene metodos para mostrar la pantalla y para mostrar en ella el resultado de dichos cálculos
 */
public class ResultadoCalculoController {

    @FXML
    private Button btnAtras;

    @FXML
    private TextField txt_altoBordado;

    @FXML
    private TextField txt_altoTotal;

    @FXML
    private TextField txt_largoBordado;

    @FXML
    private TextField txt_largoTotal;

    /**
     * Método para volver a la pantalla anterior
     *
     * @param event El evento de acción
     */
    @FXML
    void onClickAtras(ActionEvent event) {
        //Se llama al método que nos lleva de vuelta a la pantalla anterior
        Utils.irPantallaCalculdora(btnAtras);
    }

    /**
     * Método en el que obtenemos los datos para la pantalla y los mostramos en los TextFields de la pantalla
     *
     * @param calculoTela El objeto que nos pasó la pantalla de Calculadora y de donde obtendremos los resultados
     */
    public void setCalculoFromMain(CalculoTela calculoTela){
        //Si el objeto no es nulo
        if (calculoTela != null) {
            //El objeto realiza los cálculos pertinentes y se redondea el resultado
            String alto = String.format("%.2f", calculoTela.tamanhoBordadoAlto());
            String largo = String.format("%.2f", calculoTela.tamanhoBordadoLargo());
            String largoTotal = String.format("%.2f", calculoTela.tamanhoTelaLargo());
            String altoTotal = String.format("%.2f", calculoTela.tamanhoTelaAlto());

            // Actualizar los campos de texto
            txt_altoBordado.setText(alto);
            txt_largoBordado.setText(largo);
            txt_largoTotal.setText(largoTotal);
            txt_altoTotal.setText(altoTotal);
        }
    }

    /**
     * Método para mostrar la pantalla de resultados
     *
     * @param stage La ventana principal donde se mostrará la pantalla
     * @return El controlador de la pantalla
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    public ResultadoCalculoController showEstaPantalla(Stage stage) throws IOException {
        // Utiliza PantallaUtils para cargar la pantalla de resultados con las dimensiones especificadas
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_PANTALLA_RESULTADO_CALCULADORA.getDescripcion(),Constantes.TITULO_PANTALLA_RESULTADO_CALCULADORA.getDescripcion(),600,350);
        //Le devolvemos el controlador asociado al archivo FXML
        return fxmlLoader.getController();
    }
}
