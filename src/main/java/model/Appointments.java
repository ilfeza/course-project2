package model;

import database.Database_main;

import java.sql.Connection;
import java.sql.SQLException;

public class Appointments {
    private int id;
    private String date_and_time;
    private int animals_id;
    private int owners_id;
    private int doctors_id;
    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();

    public Appointments(String date_and_time, int animals_id, int owners_id, int doctors_id) throws SQLException {
        this.date_and_time = date_and_time;
        this.animals_id = animals_id;
        this.owners_id = owners_id;
        this.doctors_id = doctors_id;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

    public void setAnimals_id(int animals_id) {
        this.animals_id = animals_id;
    }

    public void setOwners_id(int owners_id) {
        this.owners_id = owners_id;
    }

    public void setDoctors_id(int doctors_id) {
        this.doctors_id = doctors_id;
    }

    public int getId() {
        return id;
    }

    public int getOwners_id() {
        return owners_id;
    }

    public int getAnimals_id() {
        return animals_id;
    }

    public int getDoctors_id() {
        return doctors_id;
    }

    public String getDate_and_time() {
        return date_and_time;
    }


}
