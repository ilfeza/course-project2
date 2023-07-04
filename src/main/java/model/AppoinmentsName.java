package model;

import database.Database_main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentsName {
    private String date_and_time;
    private String animals_name;
    private String owners_name;
    private String doctors_name;
    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();

    public AppoinmentsName(String date_and_time, String animals_name, String owners_name, String doctors_name) throws SQLException {
        this.date_and_time = date_and_time;
        this.animals_name = animals_name;
        this.owners_name = owners_name;
        this.doctors_name = doctors_name;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public String getAnimals_name() {
        return animals_name;
    }

    public String getDoctors_name() {
        return doctors_name;
    }

    public String getOwners_name() {
        return owners_name;
    }

    public void setOwners_name(String owners_name) {
        this.owners_name = owners_name;
    }

    public void setAnimals_name(String animals_name) {
        this.animals_name = animals_name;
    }

    public void setDoctors_name(String doctors_name) {
        this.doctors_name = doctors_name;
    }

    public List<AppoinmentsName> getAllAppoinments() throws SQLException {
        List<AppoinmentsName> appoinments = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT\n" +
                "  appointments.date_and_time,\n" +
                "  animals.name AS animal_name,\n" +
                "  owners.name AS owner_name,\n" +
                "  doctors.name AS doctor_name\n" +
                "FROM\n" +
                "  appointments\n" +
                "JOIN\n" +
                "  animals ON appointments.animals_id = animals.id\n" +
                "JOIN\n" +
                "  owners ON appointments.owners_id = owners.id\n" +
                "JOIN\n" +
                "  doctors ON appointments.doctors_id = doctors.id\n");
        while (resultSet.next()) {
            String date_and_time = resultSet.getString("date_and_time");
            String animal_name = resultSet.getString("animal_name");
            String owner_name = resultSet.getString("owner_name");
            String doctor_name = resultSet.getString("doctor_name");

            AppoinmentsName appointment = new AppoinmentsName(date_and_time, animal_name, owner_name, doctor_name);
            appoinments.add(appointment);
        }
        return appoinments;
    }
}
