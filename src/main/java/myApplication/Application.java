package myApplication;

import DbUtil.DbUtil;
import org.w3c.dom.ls.LSOutput;
import targetPages.HotelsCom;
import targetPages.dealSagariGr;
import targetPages.ekdromiGr;
import targetPages.xenodoxeioGr;

import java.util.Scanner;

public class Application {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

       /* boolean quit = false;
        printActions();
        while (!quit) {
            System.out.println("\nEnter action: (6 to show available actions)");
            int action = input.nextInt();
            input.nextLine();

            switch (action) {
                case 0:
                    System.out.println("\nShutting down...");
                    quit = true;
                    break;
                case 1:
                    //viewProducts();
                    break;
                case 2:
                    //addProduct();
                    break;
                case 3:
                    //searchProductById();
                    break;
                case 4:
                    //addManyProducts();
                    break;
                case 5:
                    //searchProductsByCode();
                    break;
                case 6:
                    printActions();
                    break;
                case 7:
                    //viewTimeStatisticsOfProduct();
                    break;
                default:
                    System.out.println("Invalid Operation!Please try again");
            }*/


            //DbUtil.connect();
            System.out.println("-------------------------------------------");

            System.out.println("Αποτελέσματα απο ekdromi.gr");
            ekdromiGr ekdromi = new ekdromiGr();
            ekdromi.printResults();

            System.out.println("-------------------------------------------");

            System.out.println("Αποτελέσματα απο Hotels.com");
            HotelsCom hotelsCom = new HotelsCom();
            hotelsCom.printResults();

            System.out.println("--------------------------------------");

            System.out.println("Αποτελέσματα απο xenodoxeia.com");

            xenodoxeioGr xenodoxeio = new xenodoxeioGr();
            xenodoxeio.printResults();

            System.out.println("--------------------------------------");

            System.out.println("Αποτελέσματα απο dealSafari.gr");
            dealSagariGr dealsafari = new dealSagariGr();
            dealsafari.printResults();
        }
    }
   /* //-----------Μέθοδος εκτύπωσης διαθέσιμων λειτουργιών προς τον χρήστη--------
    private static void printActions () {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - to shutdown\n" +
                "1  - to print products in block chain\n" +
                "2  - to add a new product in block chain\n" +
                "3  - to search for a product by its id\n" +
                "4  - to add many products in block chain\n" +
                "5  - to search for a product by its code\n" +
                "6  - to print a list of available actions\n" +
                "7  - to view time statistics of a product\n"
        );
        System.out.println("Choose your action: ");
    }

    //-----------------------vvvvvvvvvv-----------------------------------//
}
*/