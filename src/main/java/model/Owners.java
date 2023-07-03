package model;

import database.Database_main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Owners {
    private int id;
    private String name;
    private String address;
    private String phone_number;
    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();

    public Owners(String name, String address, String phone_number) throws SQLException {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }


    public ObservableList<String> getAllOwners() throws SQLException {
        ObservableList<String> owners = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `owners`");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            owners.add(name);
            System.out.println(name);
        }
        return owners;
    }

    public void addOwnes(String name, String address, String phone_number) throws SQLException {
        String query = "INSERT INTO owners (name, address, phone_number) VALUES (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, address);
        statement.setString(3, phone_number);
        statement.executeUpdate();
    }

}
