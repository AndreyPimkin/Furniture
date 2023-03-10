package ru.penza.builtfurniture.server;


import ru.penza.builtfurniture.AuthorizationController;
import ru.penza.builtfurniture.POJO.OneStrings;

import java.sql.*;


public class DatabaseHandler {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionString = "jdbc:sqlserver://DESKTOP-2JU1ID3:1433;databaseName=build_in_furniture;user=sa;password=sa;encrypt=false;";
        dbConnection = DriverManager.getConnection(connectionString);
        return dbConnection;
    }

    public ResultSet autoUser(OneStrings oneStrings) {
        ResultSet resSet = null;
        String select = "SELECT * FROM client WHERE number =? AND password = ?";
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
        String select = "SELECT * FROM staff WHERE number =? AND password = ?";
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
        String select = "SELECT purchase.id_purchase, furniture.type, purchase.status FROM purchase " +
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
                "full_name = (? + ' ' +  ? + ' ' + ?), " +
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

    public void signUpUser(OneStrings oneStrings) throws ClassNotFoundException {
        String insert = "INSERT INTO  client(number,password)" + "VALUES(?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, oneStrings.getStringOne());
            prSt.setString(2, oneStrings.getStringTwo());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();}}


    public ResultSet getFurnitureByModerator() {
        ResultSet resSet = null;
        String select = "SELECT purchase.id_purchase, client.full_name, client.number, furniture.id_furniture, furniture.type " +
                "FROM purchase " +
                "INNER JOIN furniture ON purchase.id_furniture = furniture.id_furniture " +
                "INNER JOIN client ON purchase.id_client = client.id_client " +
                "WHERE purchase.status = 'Заказан'";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getStaff() {
        ResultSet resSet = null;
        String select = "SELECT * FROM staff";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void addStaff(OneStrings oneStrings) throws ClassNotFoundException {
        String insert = "INSERT INTO staff VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, oneStrings.getStringOne());
            prSt.setString(2, oneStrings.getStringTwo());
            prSt.setString(3, oneStrings.getStringThree());
            prSt.setString(4, oneStrings.getStringFour());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();}}

    public void deleteStaff(OneStrings oneStrings) throws ClassNotFoundException {
        String insert = "DELETE FROM staff WHERE id_staff = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, oneStrings.getStringOne());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();}}



    public ResultSet getFurnitureByMaster() {
        ResultSet resSet = null;
        String select = "SELECT purchase.id_purchase, purchase.date,furniture.id_furniture, furniture.type, " +
                "furniture.color, furniture.length,  furniture.width,  furniture.height , purchase.status" +
                "FROM purchase " +
                "INNER JOIN furniture ON purchase.id_furniture = furniture.id_furniture " +
                "WHERE purchase.status = 'Передан в разработку' OR purchase.status = 'Принято в работу' ";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void changeFurniture(OneStrings oneStrings) {
        String insert = "UPDATE purchase SET " +
                "status = ?" +
                "WHERE id_purchase = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, oneStrings.getStringTwo());
            prSt.setString(2, oneStrings.getStringOne());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changeFurnitureTwo(OneStrings oneStrings) {
        String insert = "UPDATE purchase SET " +
                "availability = ?" +
                "WHERE id_purchase = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, oneStrings.getStringTwo());
            prSt.setString(2, oneStrings.getStringOne());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}











