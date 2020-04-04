package DbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public static synchronized Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:web.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            //System.out.println("Επιτυχής Σύνδεση με την Βάση");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void  closeConnection(Connection c){
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
