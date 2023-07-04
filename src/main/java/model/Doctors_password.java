package model;

import database.Database_main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Doctors_password {
    private String login;
    private String name;
    private String address;
    private String phone;
    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();

    public Doctors_password(String login, String name, String address, String phone) throws SQLException {
        this.login = login;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public List<Doctors_password> getAllDoctors() throws SQLException {
        List<Doctors_password> doctors_passwords = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `doctors`");
        while (resultSet.next()) {
            String login = resultSet.getString("login");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String phone_number = resultSet.getString("phone_number");

            Doctors_password doctors_password = new Doctors_password(login, name, address, phone_number);
            doctors_passwords.add(doctors_password);
        }
        return doctors_passwords;
    }

    public String getAddress() {
        return address;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void updeteDoctors(String login, String password, String name, String address, String phone) throws SQLException {
        password = Logins.hashString(password);
        System.out.println(password);
        String query2 = "UPDATE doctors\n" +
                "SET name = ?,\n" +
                "    address = ?,\n" +
                "    phone_number = ?\n" +
                "WHERE login = ?";

        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, name);
        statement2.setString(2, address);
        statement2.setString(3, phone);
        statement2.setString(4, login);
        statement2.executeUpdate();

        String query1 = "UPDATE authentications SET hash_password = ? WHERE login = ?";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, password);
        statement1.setString(2, login);
        statement1.executeUpdate();



    }

    public void createDoctors(String login, String password, String name, String address, String phone) throws SQLException {
        password = Logins.hashString(password);
        String query = "INSERT INTO authentications (login, hash_password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.executeUpdate();

        String query2 = "INSERT INTO doctors (name, address, phone_number, login)\n" +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, name);
        statement2.setString(2, address);
        statement2.setString(3, phone);
        statement2.setString(4, login);
        statement2.executeUpdate();
    }

    public void deleteDoctors(String login, String password, String name, String address, String phone) throws SQLException {
        password = Logins.hashString(password);
        try {
            String query2 = "DELETE FROM doctors WHERE login = ?";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, login);
            statement2.executeUpdate();

            String query1 = "DELETE FROM authentications WHERE login = ?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, login);
            statement1.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
