package database;

import model.Logins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database_main {
    private static Database_main instance;
    private Connection connection;

    public Database_main() throws SQLException {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2296_veterinary_clinic",
                    "std_2296_veterinary_clinic", "12345678");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Database_main getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database_main();
        }
        return instance;
    }

    public void addUser(Logins user) throws SQLException {
        try {
            String query = "INSERT INTO logins (name, hash_password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getHash_password());
            statement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
