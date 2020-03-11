package myApplication;

import DbUtil.DbUtil;
import targetPages.HotelsCom;

public class Application {
    public static void main(String[] args) {

        DbUtil.connect();

        HotelsCom hotelsCom = new HotelsCom();
        hotelsCom.printResults();
    }

}
