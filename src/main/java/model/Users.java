package model;

import database.Database_main;

import java.sql.*;

public class Users {
    private String login;
    private String name;
    private String address;
    private String phone_number;
    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();

    public Users(String name, String address, String phone_number) throws SQLException {
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

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Users getAll(String login) throws SQLException {
        Users u = new Users("","","");

        String query = "SELECT u.name, u.address, u.phone_number\n" +
                "FROM doctors u\n" +
                "JOIN authentications a ON u.login = a.login\n" +
                "WHERE a.login = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            u.setName(result.getString(1));
            u.setAddress(result.getString(2));
            u.setPhone_number(result.getString(3));
            return u;
        }
        else {
            return u;
        }
    }




}
