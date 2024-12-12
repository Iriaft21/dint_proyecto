package org.proyecto.proyecto;

import javafx.application.Application;
import javafx.stage.Stage;
import org.proyecto.proyecto.controller.MenuController;

import java.io.IOException;

public class MenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new MenuController().startPrimeFrame();
    }

    public static void main(String[] args) {
        launch();
    }
}