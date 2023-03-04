package ru.penza.builtfurniture;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.penza.builtfurniture.POJO.OneStrings;
import ru.penza.builtfurniture.server.DatabaseHandler;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonReg;

    @FXML
    private TextField inputNumber;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private PasswordField inputSecondPassword;

    @FXML
    void initialize() {
        buttonReg.setOnAction(actionEvent -> {
            OneStrings oneStrings = new OneStrings();
            DatabaseHandler dbHandler = new DatabaseHandler();
            oneStrings.setStringOne(inputNumber.getText());
            oneStrings.setStringTwo(inputPassword.getText());
            try {
                dbHandler.signUpUser(oneStrings);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            AuthorizationController.openWindow("ru/penza/builtfurniture/authorization.fxml", buttonBack, "Авторизация");
        });


        buttonBack.setOnAction(actionEvent -> {
            AuthorizationController.openWindow("ru/penza/builtfurniture/authorization.fxml", buttonBack, "Авторизация");
        });

    }

}
