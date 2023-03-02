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

public class MainPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBuy;

    @FXML
    private Button buttonBuyTwo;

    @FXML
    private TableColumn<OneStrings, String> colorOne;

    @FXML
    private TableColumn<OneStrings, String> colorTwo;

    @FXML
    private TableColumn<OneStrings, String> dateOne;

    @FXML
    private TableColumn<OneStrings, String> dateTwo;

    @FXML
    private TableColumn<OneStrings, String> materialOne;

    @FXML
    private TableColumn<OneStrings, String> materialTwo;

    @FXML
    private TableColumn<OneStrings, String> nameOne;

    @FXML
    private TableColumn<OneStrings, String> nameThree;

    @FXML
    private TableColumn<OneStrings, String> nameTwo;

    @FXML
    private TableColumn<OneStrings, String> number;

    @FXML
    private TableColumn<OneStrings, String> priceOne;

    @FXML
    private TableColumn<OneStrings, String> priceTwo;

    @FXML
    private TableColumn<OneStrings, String> sizeOne;

    @FXML
    private TableColumn<OneStrings, String> sizeTwo;

    @FXML
    private TableColumn<OneStrings, String> status;

    @FXML
    private TableView<OneStrings> tableOne;

    @FXML
    private TableView<OneStrings> tableTwo;

    @FXML
    private TableView<OneStrings> tableThree;

    private final ObservableList<OneStrings> listFurniture = FXCollections.observableArrayList();
    private final ObservableList<OneStrings> listFurnitureTwo = FXCollections.observableArrayList();

    private final ObservableList<OneStrings> listHistory = FXCollections.observableArrayList();

    DatabaseHandler dbHandler = new DatabaseHandler();
    @FXML
    void initialize() {
        try {
            initFurnitureOne();
            initFurnitureTwo();
            initHistory();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        filling(nameOne, priceOne, materialOne, dateOne, colorOne, sizeOne, tableOne);
        filling(nameTwo, priceTwo, materialTwo, dateTwo, colorTwo, sizeTwo, tableTwo);

        number.setCellValueFactory(new PropertyValueFactory<>("stringOne"));
        nameThree.setCellValueFactory(new PropertyValueFactory<>("stringTwo"));
        status.setCellValueFactory(new PropertyValueFactory<>("stringThree"));
        tableThree.setItems(listFurniture);

        buttonBuy.setOnAction(actionEvent -> {

        });

        buttonBuyTwo.setOnAction(actionEvent -> {

        });
    }

    private void filling(TableColumn<OneStrings, String> nameTwo, TableColumn<OneStrings, String> priceTwo, TableColumn<OneStrings, String> materialTwo,
                         TableColumn<OneStrings, String> dateTwo, TableColumn<OneStrings, String> colorTwo, TableColumn<OneStrings, String> sizeTwo,
                         TableView<OneStrings> tableTwo) {
        nameTwo.setCellValueFactory(new PropertyValueFactory<>("stringOne"));
        priceTwo.setCellValueFactory(new PropertyValueFactory<>("stringTwo"));
        materialTwo.setCellValueFactory(new PropertyValueFactory<>("stringThree"));
        dateTwo.setCellValueFactory(new PropertyValueFactory<>("stringFour"));
        colorTwo.setCellValueFactory(new PropertyValueFactory<>("stringFive"));
        sizeTwo.setCellValueFactory(new PropertyValueFactory<>("stringSix"));
        tableTwo.setItems(listFurniture);
    }

    private void initFurnitureOne() throws SQLException {
        dbHandler = new DatabaseHandler();
        ResultSet rs;
        rs = dbHandler.getFurnitureByBuy();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            listFurniture.add(new OneStrings(rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7),
                    (rs.getString(8) + ","  + rs.getString(9) + "," + rs.getString(10))));
        }
    }


    private void initFurnitureTwo() throws SQLException {
        dbHandler = new DatabaseHandler();
        ResultSet rs;
        rs = dbHandler.getFurnitureByOrder();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            listFurnitureTwo.add(new OneStrings(rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7),
                    (rs.getString(8) + ","  + rs.getString(9) + "," + rs.getString(10))));
        }
    }


    private void initHistory() throws SQLException {
        dbHandler = new DatabaseHandler();
        ResultSet rs;
        OneStrings oneStrings = new OneStrings();
        oneStrings.setStringOne(String.valueOf(AuthorizationController.idClient));
        rs = dbHandler.getHistory(oneStrings);
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            listHistory.add(new OneStrings(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
    }


}
