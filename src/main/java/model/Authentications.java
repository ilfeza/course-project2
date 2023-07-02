package model;

public class Authentications {
    private static String login;
    private static String hash_password;

    public Authentications(String login, String hash_password){
        this.login = login;
        this.hash_password = hash_password;
    }

    public String getLogin() {
        return login;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }
}
