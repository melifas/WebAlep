package myApplication;

import DbUtil.DbUtil;
import targetPages.HotelsCom;
import targetPages.xenodoxeioGr;

public class Application {
    public static void main(String[] args) {

        DbUtil.connect();

        HotelsCom hotelsCom = new HotelsCom();
        hotelsCom.printResults();

        System.out.println( "--------------------------------------");


        xenodoxeioGr xenodoxeio = new xenodoxeioGr();
        xenodoxeio.printResults();
    }

}
