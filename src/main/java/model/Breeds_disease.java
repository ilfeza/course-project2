package model;

public class Breeds_disease {
    private int breeds_id;
    private String diseases_name;

    public Breeds_disease(String diseases_name){
        this.diseases_name = diseases_name;
    }

    public String getDiseases_name() {
        return diseases_name;
    }

    public void setDiseases_name(String diseases_name) {
        this.diseases_name = diseases_name;
    }
}
