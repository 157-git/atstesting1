package Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class userWiseDataStorage {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/recruiters";
        String user = "root";
        String password = "root";
        
//      String query=  "SELECT " +
//        "id AS user_id, " +
//        "SUM(" +
//        "IFNULL(OCTET_LENGTH(CAST(id AS CHAR)), 0) + " + // Size of id
//        "IFNULL(OCTET_LENGTH(name), 0) + " +             // Size of name
//        "IFNULL(OCTET_LENGTH(resume), 0) " +             // Size of resume (LONGBLOB)
//        ") AS total_storage_bytes, " +
//        "ROUND(" +
//        "SUM(" +
//        "IFNULL(OCTET_LENGTH(CAST(id AS CHAR)), 0) + " +
//        "IFNULL(OCTET_LENGTH(name), 0) + " +
//        "IFNULL(OCTET_LENGTH(resume), 0) " +
//        ") / (1024 * 1024), 6" +                        // Convert bytes to MB
//        ") AS total_storage_MB " +
//        "FROM datafetch " +
//        "GROUP BY id;";
        
        
      String query ="SELECT " +
              "id AS user_id, " +
              "ROUND(SUM(IFNULL(OCTET_LENGTH(CAST(id AS CHAR)), 0) + " +
              "IFNULL(OCTET_LENGTH(name), 0) + " +
              "IFNULL(OCTET_LENGTH(resume), 0)) / 1048576, 6) AS total_storage_MB " +
              "FROM datafetch " +
              "GROUP BY id;";
      
        

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("user_id") +
//                                   " | Storage: " + rs.getLong("total_storage_bytes") + " Bytes" +
                                   " | Storage in MB: " + rs.getDouble("total_storage_MB") + " MB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

