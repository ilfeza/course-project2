package model;

public class Owners {
    private int id;
    private String name;
    private String address;
    private String phone_number;

    public Owners(String name, String address, String phone_number){
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
