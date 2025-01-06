module org.proyecto.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.proyecto.proyecto to javafx.fxml, javafx.base;
    exports org.proyecto.proyecto;
    exports org.proyecto.proyecto.controller;
    opens org.proyecto.proyecto.controller to javafx.fxml, javafx.base;
    opens org.proyecto.proyecto.modelo to javafx.fxml, javafx.base;
}