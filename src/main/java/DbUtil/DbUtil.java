package DbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//θα πρέπει να προσπαθήσω να την κάνω singleton
public class DbUtil {

    private static  volatile Connection conn = null;


   /* public static Connection getConn() {
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
    }*/

    //νεα thread safe connection με singleton Pattern
    public static Connection getConn() {
        Connection conn = null;
        if (conn==null){
            synchronized (DbUtil.class){
                if (conn==null){
                    try {
                        // db parameters
                        String url = "jdbc:sqlite:web.db";
                        // create a connection to the database
                        conn = DriverManager.getConnection(url);
                        //System.out.println("Επιτυχής Σύνδεση με την Βάση");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return conn;
    }

    public static synchronized void  closeConnection(Connection c){
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
