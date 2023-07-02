package model;

import database.Database_main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    Database_main db;
    Users users;
    Connection connection;
    public Users user = new Users("","","");
    public Breeds breeds = new Breeds(0,"");
    public Authentications authentications = new Authentications("","");


    public Model() throws SQLException {
        db = Database_main.getInstance();
        connection = db.getConnection();
    }

    public void addUser(String login, String password) throws SQLException {
        password = Logins.hashString(password);
        String query = "INSERT INTO authentications (login, hash_password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.executeUpdate();
    }

    public int authorization(String login, String password) throws SQLException {
        //String query = "INSERT INTO logins (name, hash_password) VALUES (?, ?)";
        password = Logins.hashString(password);
        String query = "SELECT check_authentication(?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt(1);
        } else {
            return -1;
        }
    }

    public void change_data(String name, String address, String phone_number) throws SQLException {
        String query1 = "UPDATE users SET name = ? WHERE login = ?";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, name);
        statement1.setString(2, authentications.getLogin());
        statement1.executeUpdate();

        String query2 = "UPDATE users SET address = ? WHERE login = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, address);
        statement2.setString(2, authentications.getLogin());
        statement2.executeUpdate();
        //System.out.println(authentications.getHash_password());

        String query3 = "UPDATE users SET phone_number = ? WHERE login = ?";
        PreparedStatement statement3 = connection.prepareStatement(query3);
        statement3.setString(1, phone_number);
        statement3.setString(2, authentications.getLogin());
        statement3.executeUpdate();
    }





}
