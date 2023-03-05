package ru.penza.builtfurniture;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.penza.builtfurniture.POJO.OneStrings;
import ru.penza.builtfurniture.server.DatabaseHandler;

public class ModeratorController {

    @FXML
    private Button acceptButton;

    @FXML
    private TableColumn<OneStrings, String>  fullName;

    @FXML
    private TableColumn<OneStrings, String>  nameFurniture;

    @FXML
    private TableColumn<OneStrings, String>  number;

    @FXML
    private TableColumn<OneStrings, String>  numberFurniture;

    @FXML
    private TableColumn<OneStrings, String>  phone;

    @FXML
    private TableView<OneStrings> moderatorTable;
    @FXML
    private Button refuseButton;

    private final ObservableList<OneStrings> listFurniture = FXCollections.observableArrayList();
    DatabaseHandler dbHandler = new DatabaseHandler();

    OneStrings oneStrings;

    OneStrings oneStringsTwo;

    @FXML
    void initialize() {
        try {
            initOrders();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        acceptButton.setOnAction(actionEvent -> {
            oneStrings = moderatorTable.getSelectionModel().getSelectedItem();
            oneStringsTwo = new OneStrings();
            oneStringsTwo.setStringOne(oneStrings.getStringOne());
            oneStringsTwo.setStringOne("Передан в разработку");
            dbHandler.changeFurniture(oneStringsTwo);
            refreshTable();
        });


        refuseButton.setOnAction(actionEvent -> {
            oneStrings = moderatorTable.getSelectionModel().getSelectedItem();
            oneStringsTwo = new OneStrings();
            oneStringsTwo.setStringOne(oneStrings.getStringOne());
            oneStringsTwo.setStringOne("Отменен");
            dbHandler.changeFurniture(oneStringsTwo);
            refreshTable();
        });

        number.setCellValueFactory(new PropertyValueFactory<>("stringOne"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("stringTwo"));
        phone.setCellValueFactory(new PropertyValueFactory<>("stringThree"));
        numberFurniture.setCellValueFactory(new PropertyValueFactory<>("stringFour"));
        nameFurniture.setCellValueFactory(new PropertyValueFactory<>("stringFive"));
        moderatorTable.setItems(listFurniture);

    }

    private void initOrders() throws SQLException {
        dbHandler = new DatabaseHandler();
        ResultSet rs;
        rs = dbHandler.getFurnitureByModerator();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            listFurniture.add(new OneStrings(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5)));
        }

    }

    private void refreshTable() {
        moderatorTable.getItems().clear();
        try {
            initOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        number.setCellValueFactory(new PropertyValueFactory<>("stringOne"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("stringTwo"));
        phone.setCellValueFactory(new PropertyValueFactory<>("stringThree"));
        numberFurniture.setCellValueFactory(new PropertyValueFactory<>("stringFour"));
        nameFurniture.setCellValueFactory(new PropertyValueFactory<>("stringFive"));
        moderatorTable.setItems(listFurniture);
    }

}
