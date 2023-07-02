package model;

public class Animals {
    private int id;
    private String name;
    private int breeds_id;
    private int owners_id;

    public Animals(String name, int breeds_id, int owners_id){
        this.name = name;
        this.breeds_id = breeds_id;
        this.owners_id = owners_id;
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
}
