module dk.easv.javafxopgaver {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.easv.javafxopgaver to javafx.fxml;
    exports dk.easv.javafxopgaver;
}