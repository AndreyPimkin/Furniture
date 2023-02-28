package ru.penza.builtfurniture;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.penza.builtfurniture.POJO.OneStrings;
import ru.penza.builtfurniture.server.DatabaseHandler;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonOpenReg;

    @FXML
    private TextField inputNumber;

    @FXML
    private PasswordField inputPassword;

    @FXML
    void initialize() {
        buttonOpenReg.setOnAction(actionEvent -> {
            openWindow("ru/penza/builtfurniture/registration.fxml", buttonOpenReg, "Регистрация");
        });

        buttonLogin.setOnAction(actionEvent -> {
            OneStrings oneStrings = new OneStrings();
            DatabaseHandler dbHandler = new DatabaseHandler();
            ResultSet resultAuto;
            oneStrings.setStringOne(inputNumber.getText());
            oneStrings.setStringTwo(inputPassword.getText());
            resultAuto = dbHandler.autoUser(oneStrings);
            try {
                if(resultAuto.next()){
                    openWindow("ru/penza/builtfurniture/.fxml", buttonOpenReg, "Регистрация");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        });
    }

    public static void openWindow(String path, Button button, String title) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AuthorizationController.class.getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene((new Scene(root)));
        stage.getIcons().add(new Image("file:src/main/resources/picture/icon.png"));
        stage.setTitle(title);
        stage.show();
    }

}
