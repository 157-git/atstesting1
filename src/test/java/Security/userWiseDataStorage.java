package Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class userWiseDataStorage {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/manytomany_uni";
        String user = "root";
        String password = "root";

        //single table
//        String query = "SELECT " +
//                "id AS user_id, " +
//                "SUM(OCTET_LENGTH(CAST(id AS CHAR))) AS total_storage_bytes, " +  
//                "ROUND(SUM(OCTET_LENGTH(CAST(id AS CHAR))) / (1024 * 1024), 2) AS total_storage_MB " +  
//                "FROM course " +
//                "GROUP BY id;";
        
        //multiple table using union
        String query = "SELECT 'course' AS table_name, id AS user_id, " +
                "SUM(OCTET_LENGTH(CAST(id AS CHAR))) AS total_storage_bytes, " +
                "ROUND(SUM(OCTET_LENGTH(CAST(id AS CHAR))) / (1024 * 1024), 2) AS total_storage_MB " +
                "FROM course GROUP BY id " +
                "UNION ALL " +
                "SELECT 'student' AS table_name, id AS user_id, " +
                "SUM(OCTET_LENGTH(CAST(id AS CHAR))) AS total_storage_bytes, " +
                "ROUND(SUM(OCTET_LENGTH(CAST(id AS CHAR))) / (1024 * 1024), 2) AS total_storage_MB " +
                "FROM student GROUP BY id;";
        

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("user_id") +
                                   " | Storage: " + rs.getLong("total_storage_bytes") + " Bytes" +
                                   " | Storage in MB: " + rs.getDouble("total_storage_MB") + " MB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

