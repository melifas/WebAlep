package dao;

import DbUtil.DbUtil;
import pojo.Records;

import java.sql.*;

public class RecordsDAO {

    public int addProduct(Records records) {

        int status = 0;
        String sql = "INSERT INTO results(hotelnames,hotelprices) VALUES(?,?)";
        //String url = "jdbc:sqlite:web.db";
        try  {
            Connection conn = DbUtil.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, records.getHotelnames());
            ps.setString(2, records.getHotelprices());


            status = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  status;
    }
}

