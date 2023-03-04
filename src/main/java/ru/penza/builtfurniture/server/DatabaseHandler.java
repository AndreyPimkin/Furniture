package ru.penza.builtfurniture.server;


import ru.penza.builtfurniture.AuthorizationController;
import ru.penza.builtfurniture.POJO.OneStrings;

import java.sql.*;


public class DatabaseHandler {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionString = "jdbc:sqlserver://DESKTOP-2JU1ID3:1433;databaseName=cargo_driving;user=sa;password=sa;encrypt=false;";
        dbConnection = DriverManager.getConnection(connectionString);
        return dbConnection;
    }

    public ResultSet autoUser(OneStrings oneStrings) {
        ResultSet resSet = null;
        String select = "SELECT * FROM client WHERE phone =? AND password = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, oneStrings.getStringOne());
            prSt.setString(2, oneStrings.getStringTwo());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet autoStaff(OneStrings oneStrings) {
        ResultSet resSet = null;
        String select = "SELECT * FROM staff WHERE phone =? AND password = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, oneStrings.getStringOne());
            prSt.setString(2, oneStrings.getStringTwo());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getFurnitureByBuy() {
        ResultSet resSet = null;
        String select = "SELECT * FROM furniture WHERE availability = 'На складе'";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getFurnitureByOrder() {
        ResultSet resSet = null;
        String select = "SELECT * FROM furniture WHERE availability = 'Не произведено'";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getHistory(OneStrings oneStrings) {
        ResultSet resSet = null;
        String select = "SELECT purchase.id_purchase, furniture.type, purchase.status FROM purchase" +
                "INNER JOIN furniture ON purchase.id_furniture = furniture.id_furniture WHERE id_client = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, oneStrings.getStringOne());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void buyFurniture(OneStrings oneStrings) {
        String insert = "INSERT INTO purchase VALUES (?, (SELECT id_furniture FROM furniture WHERE type = ?), ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, oneStrings.getStringOne());
            prSt.setString(2, oneStrings.getStringTwo());
            prSt.setString(3, oneStrings.getStringThree());
            prSt.setString(4, oneStrings.getStringFour());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void orderFurniture(OneStrings oneStrings) {
        String insert = "INSERT INTO purchase VALUES (?, (SELECT id_furniture FROM furniture WHERE type = ?), ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, oneStrings.getStringOne());
            prSt.setString(2, oneStrings.getStringTwo());
            prSt.setString(3, oneStrings.getStringThree());
            prSt.setString(4, oneStrings.getStringFour());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changePersonalData(OneStrings oneStrings) {
        String insert = "UPDATE client SET " +
                "fullname = (?,' ', ?, ' ', ?), " +
                "birthday = ? " +
                "WHERE id_client = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(5, oneStrings.getStringOne());
            prSt.setString(1, oneStrings.getStringTwo());
            prSt.setString(2, oneStrings.getStringThree());
            prSt.setString(3, oneStrings.getStringFour());
            prSt.setString(4, oneStrings.getStringFive());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}











