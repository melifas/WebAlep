package dao;

import DbUtil.DbUtil;
import pojo.Records;

import java.sql.*;

public class RecordsDAO {


    //--------------------Προσθήκη records απο web scrapping---------------------------//
    public int addProduct(Records records) {

        int status = 0;
        String sql = "INSERT INTO results(hotelnames,hotelprices) VALUES(?,?)";
        //String url = "jdbc:sqlite:web.db";
        try {
            Connection conn = DbUtil.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, records.getHotelnames());
            ps.setDouble(2, records.getHotelprices());
            status = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    //---------------------Create Table----------------------
    public  void createNewTable(String tablename) {
        // SQLite connection string
        String url = "jdbc:sqlite:web.db";
        // SQL statement for creating a new table
        String sqlcreate = "CREATE TABLE IF NOT EXISTS "+tablename +"(\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sqlcreate);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //---------------------- Drop a table------------------------------

    public void dropIt() {
        String sql = "DROP TABLE results";

        try {
            Connection conn = DbUtil.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createIt() {

        String sql1 = "CREATE TABLE results( Id INTEGER PRIMARY KEY, hotelnames VARCHAR, hotelprices DOUBLE)";
        try {
            Connection conn = DbUtil.connect();
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.execute();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}



