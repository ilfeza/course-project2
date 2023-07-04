package model;

import database.Database_main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    Database_main db;
    public Doctors_password doctors_password = new Doctors_password("","","","");
    public Animals animals = new Animals("",0,0);
    Users users;
    Connection connection;
    public AppoinmentsName appointments = new AppoinmentsName("","","","");
    public Logins logins = new Logins("","");
    public Doctors doctors = new Doctors("","","");
    public Diseases diseases = new Diseases("","");
    public Users user = new Users("","","");
    public Breeds breeds = new Breeds(0,"");
    public Owners owners = new Owners("","","");
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
        String query1 = "UPDATE doctors SET name = ? WHERE login = ?";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, name);
        statement1.setString(2, authentications.getLogin());
        statement1.executeUpdate();

        String query2 = "UPDATE doctors SET address = ? WHERE login = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, address);
        statement2.setString(2, authentications.getLogin());
        statement2.executeUpdate();
        //System.out.println(authentications.getHash_password());

        String query3 = "UPDATE doctors SET phone_number = ? WHERE login = ?";
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

    public void addAppointnent(String dateAndTime, String animalsName, String ownersName, String disease) throws SQLException {
        String doctors_login = doctors.getLogin();
        String query1 = "INSERT INTO appointments (date_and_time, animals_id, owners_id, doctors_id)\n" +
                "VALUES (?, (SELECT id FROM animals WHERE name = ?), \n" +
                "       (SELECT id FROM owners WHERE name = ?), \n" +
                "       (SELECT id FROM doctors WHERE login = ?))";

        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, dateAndTime);
        statement1.setString(2, animalsName);
        statement1.setString(3, ownersName);
        statement1.setString(4, doctors_login);
        statement1.executeUpdate();





        String query2 = "INSERT INTO appointments_disease (appointments_id, diseases_name)\n" +
                "VALUES ((SELECT id FROM appointments WHERE date_and_time = ?), \n" +
                "        (SELECT scientific_name FROM diseases WHERE general_name = ?))";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, dateAndTime);
        statement2.setString(2, disease);
        statement2.executeUpdate();

    }


}
