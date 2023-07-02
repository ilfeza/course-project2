package model;

import database.Database_main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Breeds {
    private int id;
    private String name;
    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();


    public Breeds(int id, String name) throws SQLException {
        this.id = id;
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
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
}