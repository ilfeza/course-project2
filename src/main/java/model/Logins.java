package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Logins {
    private int id;
    private String name;
    private String hash_password;

    public Logins(String name, String hash_password){
        this.name = name;
        this.hash_password = hashString(hash_password);
    }

    public static String hashString(String input) {
        String key = "veterinary_clinic";
        String hashedString = "";
        hashedString = String.valueOf(input.hashCode());


        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(key.getBytes());
            byte[] bytes = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            hashedString = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(" "+hashedString);

        return hashedString;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }
}
