package model;

import database.Database_main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Animals {
    private int id;
    private String name;
    private int breeds_id;
    private int owners_id;
    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();

    public Animals(String name, int breeds_id, int owners_id) throws SQLException {
        this.name = name;
        this.breeds_id = breeds_id;
        this.owners_id = owners_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBreeds_id() {
        return breeds_id;
    }

    public int getOwners_id() {
        return owners_id;
    }
    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreeds_id(int breeds_id) {
        this.breeds_id = breeds_id;
    }

    public void setOwners_id(int owners_id) {
        this.owners_id = owners_id;
    }


    public List<Animals> getAllAnimals() throws SQLException {
        List<Animals> animals = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `animals`");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int breeds_id = resultSet.getInt("breeds_id");
            int owners_id = resultSet.getInt("owners_id");
            Animals animal = new Animals(name, breeds_id, owners_id);
            animals.add(animal);
        }
        return animals;
    }

    public ObservableList<String> getAllAnimals2() throws SQLException {
        ObservableList<String> animals = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `animals`");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            animals.add(name);
            System.out.println(name);
        }
        return animals;
    }

    public String getOwner(String animal) throws SQLException {
        String owner = "";

        String query = "SELECT o.name AS owner_name FROM animals a JOIN owners o ON a.owners_id = o.id WHERE a.name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, animal);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return result.getString(1);
        } else {
            return "";
        }

    }

    public void addAnimals(String name, String breeds, String owner) throws SQLException {
        String query = "INSERT INTO animals (name, breeds_id, owners_id)\n" +
                "SELECT ?, breeds.id, owners.id\n" +
                "FROM breeds, owners\n" +
                "WHERE breeds.name = ?\n" +
                "AND owners.name = ?\n";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, breeds);
        statement.setString(3, owner);
        statement.executeUpdate();
    }




}
