package model;

import database.Database_main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    Database_main db;
    public Animals animals = new Animals("",0,0);
    Users users;
    Connection connection;
    public Users user = new Users("","","");
    public Breeds breeds = new Breeds(0,"");
    public Authentications authentications = new Authentications("","");
    public BreedDiseaseResult breedDiseaseResult = new BreedDiseaseResult(0,"","","");


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

    public void addAnimal(String name_animal, String breed, String name_owner, String address, String phone) throws SQLException {
        String query1 = "INSERT IGNORE INTO breeds SET name = ?";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, breed);
        statement1.executeUpdate();

        String query2 = "INSERT INTO owners (name, address, phone_number) VALUES (?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, name_owner);
        statement2.setString(2, address);
        statement2.setString(3, phone);
        statement2.executeUpdate();

        String query3 = "INSERT INTO animals (name, breeds_id, owners_id) " +
                "SELECT ?, breeds.id, owners.id " +
                "FROM breeds, owners " +
                "WHERE breeds.name = ? AND owners.name = ?";
        PreparedStatement statement3 = connection.prepareStatement(query3);
        statement3.setString(1, name_animal);
        statement3.setString(2, breed);
        statement3.setString(3, name_owner);
        statement3.executeUpdate();
    }



}
