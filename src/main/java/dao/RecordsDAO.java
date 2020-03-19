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



