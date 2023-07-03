package model;

import database.Database_main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
