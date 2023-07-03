package model;

import database.Database_main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Diseases {
    public String scientific_name;
    public String general_name;

    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();

    public Diseases(String scientific_name, String general_name) throws SQLException {
        this.scientific_name = scientific_name;
        this.general_name = general_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public void setGeneral_name(String general_name) {
        this.general_name = general_name;
    }

    public String getGeneral_name() {
        return general_name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public ObservableList<String> getAllDisease2() throws SQLException {
        ObservableList<String> animals = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `diseases`");
        while (resultSet.next()) {
            String name = resultSet.getString("scientific_name");
            animals.add(name);
            System.out.println(name);
        }
        return animals;
    }
}
