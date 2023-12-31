package model;

import database.Database_main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Breeds {
    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();
    private int id;
    private String name;

    public Breeds(int id, String name) throws SQLException {
        this.id = id;
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Breeds> getAllBreeds() throws SQLException {
        List<Breeds> breeds = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `breeds`");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Breeds breed = new Breeds(id, name);
            breeds.add(breed);
        }
        return breeds;
    }

    public ObservableList<String> getAllBreeds2() throws SQLException {
        ObservableList<String> animals = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `breeds`");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            animals.add(name);
            System.out.println(name);
        }
        return animals;
    }
    public void updeteBreeds(String id, String name) throws SQLException {
        String query = "UPDATE breeds SET name = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, id);
        statement.executeUpdate();
    }
    public void createBreeds(String id, String name) throws SQLException {
        String query = "INSERT INTO breeds (id, name) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, name);
        statement.executeUpdate();
    }
    public void deleteBreeds(String id, String name) throws SQLException {
        String query = "DELETE FROM breeds WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
    }




}