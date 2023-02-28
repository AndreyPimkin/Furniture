module ru.penza.builtfurniture {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ru.penza.builtfurniture to javafx.fxml;
    exports ru.penza.builtfurniture;
}