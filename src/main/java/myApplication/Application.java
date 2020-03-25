package myApplication;

import DbUtil.DbUtil;
import dao.RecordsDAO;
import org.w3c.dom.ls.LSOutput;
import targetPages.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    static Scanner input = new Scanner(System.in);
    static RecordsDAO dao = new RecordsDAO();

    public static void main(String[] args) {
        //truncate λειτουργεία δεν υπάρχει στην sqllite γιατό κάνω drop,create-----------//
        dao.dropIt();
        dao.createIt();

        boolean quit = false;
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
                    prompt ();
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
            }
        }






      /*  try {
            Thread dealSafariThread = new Thread(new dealSagariGr());
            dealSafariThread.start();
        }catch (Exception e){
            e.getMessage();
        }*/



        /*try {
            Thread ekdromiGr = new Thread(new ekdromiGr());


            ekdromiGr.start();
        }catch (Exception e){
            e.getMessage();
        }*/


        /*try {
            Thread xenodoxeioGr = new Thread(new xenodoxeioGr());
            xenodoxeioGr.start();
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Thread hotelsGr = new Thread(new HotelsCom());
            hotelsGr.start();
        }catch (Exception e){
            e.getMessage();
        }*/




      /* try {
           testHotels ts = new testHotels();
           ts.print();
       }catch (Exception e){
           e.getMessage();
       }*/


        /*trivago tr = new trivago();
        tr.print();*/


        }


        //-----------Μέθοδος εκτύπωσης διαθέσιμων λειτουργιών προς τον χρήστη--------
        private static void printActions () {
            System.out.println("\nAvailable actions:\npress");
            System.out.println("0  - to shutdown\n" +
                    "1  - to scrap web content\n" +
                    "2  - to add a new product in block chain\n" +
                    "3  - to search for a product by its id\n" +
                    "4  - to add many products in block chain\n" +
                    "5  - to search for a product by its code\n" +
                    "6  - to print a list of available actions\n" +
                    "7  - to view time statistics of a product\n"
            );
            System.out.println("Choose your action: \n");
        }

        //----------------------------Εκτύπωση Αποτελεσμάτων--------------------//
        public static void printResults (String city, String date){
           /* HotelsCom hotel = new HotelsCom();
            hotel.print(city, date);
            xenodoxeioGr xe = new xenodoxeioGr();
            xe.print();*/
            ekdromiGr ekdromiGr = new ekdromiGr();
            ekdromiGr.print(city, date);
           /* dealSagariGr deal = new dealSagariGr();
            deal.print();*/
        }
        //------------------------Προτροπή χρήστη για είσοδο στοιχείων scraping---------------------------------//
        public static void prompt () {
            Scanner input = new Scanner(System.in);
            System.out.println("Please give us a city");
            String city = input.nextLine();

            Boolean isOk = false;
            while (!isOk){
                System.out.println("Enter a valid date");
                String date = input.nextLine();
                if (validationDate(date)) {
                    printResults(city, date);
                    isOk = true;
                    break;
                }else {
                    System.out.println("not a valid date provided");
                }
            }
            //System.out.println("Please give us a date");
            //String date = input.nextLine();

        }

//-----------------------------------Validation Ημερομηνίας---------------------------//
    public static boolean validationDate(String DOB){
        DateFormat df = new SimpleDateFormat("dd/MM/yyy");
        Date BOD=null;
        df.setLenient(false);
        try{
            BOD = df.parse(DOB);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //----------------------------------------------------------------------------- //
    }

