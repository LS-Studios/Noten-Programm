module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens lsstudios.gui to javafx.fxml;
    exports lsstudios.gui;
    exports lsstudios.main;
    opens lsstudios.main to javafx.fxml;
}
