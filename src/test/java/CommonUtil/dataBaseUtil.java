package CommonUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dataBaseUtil {

	    private static final String URL = "jdbc:mysql://localhost:3306/Recruiters";
	    private static final String USER = "root";
	    private static final String PASSWORD = "root";
	    private static Connection connection;

	    public static void connect() {
	        try {
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("Database connection established.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static Connection getConnection() {
	        return connection;
	    }

	    public static void disconnect() {
	        if (connection != null) {
	            try {
	                connection.close();
	                System.out.println("Database connection closed.");
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
