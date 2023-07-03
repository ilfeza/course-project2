package model;

import database.Database_main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


}
