package myApplication;

import DbUtil.DbUtil;
import dao.RecordsDAO;
import org.w3c.dom.ls.LSOutput;
import targetPages.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    static Scanner input = new Scanner(System.in);
    static RecordsDAO dao = new RecordsDAO();

    public static void main(String[] args) {
        //truncate λειτουργία δεν υπάρχει στην sqllite γιατό κάνω drop,create-----------//
        dao.dropIt();
        dao.createIt();
//-----------------------------------------------------------------------------------------------//
        boolean quit = false;
        printActions();
        while (!quit) {
            //System.out.println("\nEnter action: (6 to show available actions)");
            //int action = readInt();
            System.out.println("\nEnter action: (6 to show available actions)");
            int action = input.nextInt();
            input.nextLine();
            switch (action) {
                case 0:
                    System.out.println("\nΈξοδος...");
                    quit = true;
                    break;
                case 1:
                    prompt();
                    break;
                case 2:
                    statisticsFromDb();
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
                    System.out.println("Επιλέξτε μια έγκυρη λειτουργία");
            }
        }
    }//τέλος main

        //-----------Μέθοδος εκτύπωσης διαθέσιμων λειτουργιών προς τον χρήστη--------
        private static void printActions () {
            System.out.println("\nAvailable actions:\npress");
            System.out.println("0  - Για έξοδο απο την εφαρμογή\n" +
                    "1  - Για εκτέλεση web scarpping \n" +
                    "2  - to add a new product in block chain\n" +
                    "3  - to search for a product by its id\n" +
                    "4  - to add many products in block chain\n" +
                    "5  - to search for a product by its code\n" +
                    "6  - to print a list of available actions\n" +
                    "7  - to view time statistics of a product\n"
            );
            //System.out.println("Choose your action: \n");
        }

        public  static void statisticsFromDb(){
            System.out.println("Συνολικός αριθμός δωματίων απο scrapping ");
            System.out.println(dao.countRecords());
            System.out.println("Μέση συνολική τιμή δωματίων");
            System.out.println(dao.AveragePrice());
        }
        //----------------------------Εκτύπωση Αποτελεσμάτων--------------------//
        public static void printResults (String city, String date) throws InterruptedException {

           ExecutorService exec = Executors.newFixedThreadPool(8);
            exec.execute(new ekdromiGr(city, date));
            exec.execute(new HotelsCom(city, converDate(date)));
            exec.execute(new xenodoxeioGr(city, date));
            exec.execute(new dealSagariGr(city));

            exec.shutdown();

           /* String convertedDate = converDate(date);

            Thread t1 = new Thread(new ekdromiGr(city, date));
            Thread t2 = new Thread(new HotelsCom(city, convertedDate));
            Thread t3 = new Thread(new xenodoxeioGr(city, date));
            Thread t4 = new Thread(new dealSagariGr(city));
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();*/

        }





    //-------------------------Eπειδή κάποιες Ιστοσελίδες χρειάζονται ημερομηνία σε διαφορετικό format έφτιαξα αυτή την μέθοδο-------------------
        public static String converDate(String date){
            SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyy");
            Date dateValue = null;
            try {
                dateValue = input.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //Αλλαγή του format απο dd/MM/yyy σε yyy-MM-dd
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
            String converted = output.format(dateValue);

            return  converted;
        }

        //------------------------Προτροπή χρήστη για είσοδο στοιχείων scraping---------------------------------//
        public static void prompt () {
            Scanner input = new Scanner(System.in);
            System.out.println("Παρακαλώ δώστε πόλη αναζήτησης");
            String city = input.nextLine();

            Boolean isOk = false;
            while (!isOk){
                System.out.println("Παρακαλώ δώστε ημερονηνία σε μορφή dd/MM/yyyy");
                String date = input.nextLine();
                if (validationDate(date)) {
                    try {
                        printResults(city, date);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isOk = true;
                    break;
                }else {
                    System.out.println("Δεν δώθηκε έγκυρη ημερομηνία");
                }
            }

            /*while (!isOk){
                System.out.println("Παρακαλώ δώστε ημερονηνία σε μορφή dd/MM/yyyy");
                String date = input.nextLine();
                if (validationDate(date)){
                    return true;
                }*//*else{
                    System.out.println("Δεν δώθηκε έγκυρη ημερομηνία");
                }*//*
                break;
            }
            return false;*/
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
   /* public static int readInt()
    {
        boolean error=false;
        int x=0;
        do
        {
            try
            {
                // create object of scanner class.
                Scanner input=new Scanner(System.in);
                // enter here.
                System.out.println(" Παρακαλώ επιλέξτε μια ενέργεια ή πατήστε 6 για να δείτε τις διαθέσιμες επιλογές σας");
                x=input.nextInt();
                error=false;
            }
            catch(InputMismatchException e)
            {
                // accept integer only.
                System.out.println("Παρακαλώ δώστε αριθμό");
                error=true;
            }
        }
        while(error);
        return(x);
    }*/
    //-----------------------------------------------------------------------------------//
}

