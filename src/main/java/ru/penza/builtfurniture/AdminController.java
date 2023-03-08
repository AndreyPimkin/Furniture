package ru.penza.builtfurniture;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.penza.builtfurniture.POJO.OneStrings;
import ru.penza.builtfurniture.server.DatabaseHandler;

public class AdminController {

    @FXML
    private TextField phoneStaff;

    @FXML
    private Button addClient;

    @FXML
    private Button addStaff;

    @FXML
    private TableColumn<?, ?> availabilityColumn;

    @FXML
    private TableColumn<?, ?> colorColumn;

    @FXML
    private TextField dateClient;

    @FXML
    private TableColumn<?, ?> dateClientColumn;

    @FXML
    private TableColumn<?, ?> dateFurnitureColumn;

    @FXML
    private TableColumn<?, ?> datePColumn;

    @FXML
    private Button deleteClient;

    @FXML
    private Button deleteStaff;


    @FXML
    private TextField fullNameClient;

    @FXML
    private TableColumn<?, ?> fullNameClientColumn;

    @FXML
    private TextField fullNameStaff;

    @FXML
    private TableColumn<OneStrings, String> fullNameStaffColumn;

    @FXML
    private TableColumn<?, ?> heightColumn;

    @FXML
    private TableColumn<?, ?> idClientColumn;

    @FXML
    private TableColumn<?, ?> idClientPColumn;

    @FXML
    private TableColumn<?, ?> idFurnitureColumn;

    @FXML
    private TableColumn<?, ?> idFurniturePColumn;

    @FXML
    private TableColumn<?, ?> idPurchaseColumn;

    @FXML
    private TableColumn<OneStrings, String> idStaffColumn;

    @FXML
    private TableColumn<?, ?> lengthColumn;

    @FXML
    private TableColumn<?, ?> materialColumn;

    @FXML
    private TextField passwordClient;

    @FXML
    private TableColumn<?, ?> passwordClientColumn;

    @FXML
    private TextField passwordStaff;

    @FXML
    private TableColumn<OneStrings, String> passwordStaffColumn;

    @FXML
    private TextField phoneClient;

    @FXML
    private TableColumn<?, ?> phoneClientColumn;

    @FXML
    private TableColumn<OneStrings, String> phoneStaffColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private ComboBox<String> roleStaff;

    @FXML
    private TableColumn<OneStrings, String> roleStaffColumn;

    @FXML
    private TableColumn<?, ?> statusPColumn;

    @FXML
    private TableView<?> tableClient;

    @FXML
    private TableView<?> tableFurniture;

    @FXML
    private TableView<?> tablePurchase;

    @FXML
    private TableView<OneStrings> tableStaff;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> widthColumn;

    private String selectRole = "";

    private final ObservableList<OneStrings> listStaff = FXCollections.observableArrayList();
    DatabaseHandler dbHandler = new DatabaseHandler();

    OneStrings oneStrings;

    OneStrings oneStringsTwo;

    private final String[] listRole = new String[]{"Модератор", "Админ", "Мастер"};

    @FXML
    void initialize() {
        try {
            initStaff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        roleStaff.getItems().addAll(this.listRole);
        roleStaff.setOnAction(this::getRole);

        addStaff.setOnAction(actionEvent -> {
            oneStrings = new OneStrings();
            oneStrings.setStringOne(fullNameStaff.getText());
            oneStrings.setStringTwo(phoneStaff.getText());
            oneStrings.setStringThree(passwordStaff.getText());
            oneStrings.setStringFour(selectRole);
            try {
                dbHandler.addStaff(oneStrings);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            refreshTableStaff();
        });

        deleteClient.setOnAction(actionEvent -> {
            oneStrings = tableStaff.getSelectionModel().getSelectedItem();
            oneStringsTwo = new OneStrings();
            oneStringsTwo.setStringOne(oneStrings.getStringOne());
            try {
                dbHandler.deleteStaff(oneStringsTwo);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            refreshTableStaff();
        });


    }

    private void initStaff() throws SQLException {
        ResultSet rs;
        rs = dbHandler.getStaff();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            listStaff.add(new OneStrings(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5)));
        }

    }

    private void refreshTableStaff() {
        tableStaff.getItems().clear();
        try {
            initStaff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idStaffColumn.setCellValueFactory(new PropertyValueFactory<>("stringOne"));
        fullNameStaffColumn.setCellValueFactory(new PropertyValueFactory<>("stringTwo"));
        phoneStaffColumn.setCellValueFactory(new PropertyValueFactory<>("stringThree"));
        passwordStaffColumn.setCellValueFactory(new PropertyValueFactory<>("stringFour"));
        roleStaffColumn.setCellValueFactory(new PropertyValueFactory<>("stringFive"));
        tableStaff.setItems(listStaff);
    }
    private void getRole(ActionEvent actionEvent) {
        selectRole = roleStaff.getValue();
    }
}
