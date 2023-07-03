package model;

import database.Database_main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BreedDiseaseResult {
    private int breedId;
    private String breedName;
    private String scientificName;
    private String diseaseGeneralName;
    Database_main db = Database_main.getInstance();
    Connection connection = db.getConnection();

    public BreedDiseaseResult(int breedId, String breedName, String scientificName, String diseaseGeneralName) throws SQLException {
        this.breedId = breedId;
        this.breedName =breedName;
        this.scientificName = scientificName;
        this.diseaseGeneralName =diseaseGeneralName;
    }

    public BreedDiseaseResult() throws SQLException {
    }

    public int getBreedId() {
        return breedId;
    }

    public String getDiseaseGeneralName() {
        return diseaseGeneralName;
    }

    public String getBreedName() {
        return breedName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public void setDiseaseGeneralName(String diseaseGeneralName) {
        this.diseaseGeneralName = diseaseGeneralName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public List<BreedDiseaseResult> getBreedDiseaseResults() throws SQLException {
        List<BreedDiseaseResult> results = new ArrayList<>();
        String query = "SELECT b.id AS breed_id, b.name AS breed_name, d.scientific_name, d.general_name AS disease_general_name FROM breeds b LEFT JOIN breeds_disease bd ON b.id = bd.breeds_id LEFT JOIN diseases d ON bd.diseases_name = d.scientific_name ORDER BY b.id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            //BreedDiseaseResult result = new BreedDiseaseResult();
            int id = resultSet.getInt("breed_id");
            String bname = resultSet.getString("breed_name");
            String sname = resultSet.getString("scientific_name");
            String gname = resultSet.getString("disease_general_name");
            BreedDiseaseResult result = new BreedDiseaseResult(id,bname, sname,gname);
            results.add(result);
        }
        return results;
    }


    public void updeteBreedDisease(String id, String breeds_name, String scientific_name, String general_name) throws SQLException {
        String query1 = "UPDATE diseases SET general_name = ? WHERE scientific_name = ?";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, general_name);
        statement1.setString(2, scientific_name);
        statement1.executeUpdate();

        String query2 = "UPDATE breeds_disease SET diseases_name = ? WHERE breeds_id = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, scientific_name);
        statement2.setString(2, id);
        statement2.executeUpdate();

        String query3 = "UPDATE breeds SET name = ? WHERE id = ?";
        PreparedStatement statement3 = connection.prepareStatement(query3);
        statement3.setString(1, breeds_name);
        statement3.setString(2, id);
        statement3.executeUpdate();


    }

    public void createBreedDisease(String id, String breeds_name, String scientific_name, String general_name) throws SQLException {

        String query1 = "INSERT INTO diseases (scientific_name, general_name) VALUES (?, ?)";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, scientific_name);
        statement1.setString(2, general_name);
        statement1.executeUpdate();

        String query3 = "INSERT INTO breeds (id, name) VALUES (?, ?)";
        PreparedStatement statement3 = connection.prepareStatement(query3);
        statement3.setString(1, id);
        statement3.setString(2, breeds_name);
        statement3.executeUpdate();

        String query2 = "INSERT INTO breeds_disease (breeds_id, diseases_name) VALUES (?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, id);
        statement2.setString(2, scientific_name);
        statement2.executeUpdate();


    }

    public void deleteBreedDisease(String id, String breeds_name, String scientific_name, String general_name) throws SQLException {
        String query2 = "DELETE FROM breeds_disease WHERE breeds_id = ? AND diseases_name = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, id);
        statement2.setString(2, scientific_name);
        statement2.executeUpdate();

        String query1 = "DELETE FROM diseases WHERE scientific_name = ?";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, scientific_name);
        statement1.executeUpdate();

        String query3 = "DELETE FROM breeds WHERE id = ?";
        PreparedStatement statement3 = connection.prepareStatement(query3);
        statement3.setString(1, id);
        statement3.executeUpdate();




    }


}
