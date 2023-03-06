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

public class MasterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button acceptButton;

    @FXML
    private TableColumn<OneStrings, String>  color;

    @FXML
    private TableColumn<OneStrings, String>  date;

    @FXML
    private TableView<OneStrings> masterTable;

    @FXML
    private TableColumn<OneStrings, String>  name;

    @FXML
    private TableColumn<OneStrings, String>  number;

    @FXML
    private TableColumn<OneStrings, String>  numberFurniture;

    @FXML
    private Button readyButton;

    @FXML
    private Button refuseButton;

    @FXML
    private TableColumn<OneStrings, String> size;

    @FXML
    private TableColumn<OneStrings, String>  status;

    private final ObservableList<OneStrings> listFurniture = FXCollections.observableArrayList();
    DatabaseHandler dbHandler = new DatabaseHandler();

    OneStrings oneStrings;

    OneStrings oneStringsTwo;

    @FXML
    void initialize() {

        acceptButton.setOnAction(actionEvent -> {
            oneStrings = masterTable.getSelectionModel().getSelectedItem();
            oneStringsTwo = new OneStrings();
            oneStringsTwo.setStringOne(oneStrings.getStringOne());
            oneStringsTwo.setStringOne("Принято в работу");
            dbHandler.changeFurniture(oneStringsTwo);
            refreshTable();
        });

        readyButton.setOnAction(actionEvent -> {
            oneStrings = masterTable.getSelectionModel().getSelectedItem();
            oneStringsTwo = new OneStrings();
            oneStringsTwo.setStringOne(oneStrings.getStringOne());
            oneStringsTwo.setStringOne("На складе");
            dbHandler.changeFurnitureTwo(oneStringsTwo);
            refreshTable();
        });

        refuseButton.setOnAction(actionEvent -> {
            oneStrings = masterTable.getSelectionModel().getSelectedItem();
            oneStringsTwo = new OneStrings();
            oneStringsTwo.setStringOne(oneStrings.getStringOne());
            oneStringsTwo.setStringOne("Отменен");
            dbHandler.changeFurniture(oneStringsTwo);
            refreshTable();
        });

        number.setCellValueFactory(new PropertyValueFactory<>("stringOne"));
        date.setCellValueFactory(new PropertyValueFactory<>("stringTwo"));
        numberFurniture.setCellValueFactory(new PropertyValueFactory<>("stringThree"));
        name.setCellValueFactory(new PropertyValueFactory<>("stringFour"));
        color.setCellValueFactory(new PropertyValueFactory<>("stringFive"));
        size.setCellValueFactory(new PropertyValueFactory<>("stringSix"));
        status.setCellValueFactory(new PropertyValueFactory<>("stringSeven"));
        masterTable.setItems(listFurniture);
    }


    private void initOrders() throws SQLException {
        dbHandler = new DatabaseHandler();
        ResultSet rs;
        rs = dbHandler.getFurnitureByMaster();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            listFurniture.add(new OneStrings(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5),
                    (rs.getString(6) + " "  + rs.getString(7) + " " + rs.getString(8)),  rs.getString(9) ));
        }

    }

    private void refreshTable() {
        masterTable.getItems().clear();
        try {
            initOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        number.setCellValueFactory(new PropertyValueFactory<>("stringOne"));
        date.setCellValueFactory(new PropertyValueFactory<>("stringTwo"));
        numberFurniture.setCellValueFactory(new PropertyValueFactory<>("stringThree"));
        name.setCellValueFactory(new PropertyValueFactory<>("stringFour"));
        color.setCellValueFactory(new PropertyValueFactory<>("stringFive"));
        size.setCellValueFactory(new PropertyValueFactory<>("stringSix"));
        status.setCellValueFactory(new PropertyValueFactory<>("stringSeven"));
        masterTable.setItems(listFurniture);
    }
}
