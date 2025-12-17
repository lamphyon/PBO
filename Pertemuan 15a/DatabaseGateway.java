import java.sql.*;

public class DatabaseGateway {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/catalogue_sys?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    
    private Connection connection;
 
    public DatabaseGateway() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println(">>> Koneksi ke Katalog Aktif.");
        } catch (SQLException e) {
            System.err.println("Gagal menghubungkan database: " + e.getMessage());
        }
    }

    public Connection openLink() {
        return connection;
    }

    public void terminate() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println(">>> Koneksi diputus.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}