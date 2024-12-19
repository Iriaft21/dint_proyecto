package org.proyecto.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.proyecto.proyecto.controller.MenuController;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.PantallaUtils;

import java.io.IOException;

public class MenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MenuController controller = new MenuController();
        controller.showMenu(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}