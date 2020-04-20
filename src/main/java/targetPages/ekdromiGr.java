package targetPages;

import DbUtil.DbUtil;
import dao.RecordsDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.Records;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.RunnableFuture;


//φερνει κανονικά τις αναζητήσεις αλλά όχι όλες γιατί είναι δυναμική η ιστοσελίδα
public class ekdromiGr implements Runnable {


    private String city;
    private String date;

    private boolean exit;

    public ekdromiGr() {
    }

    public ekdromiGr(String city, String date) {
        this.city = city;
        this.date = date;
    }

    public void run() {

        while (!exit) {
            RecordsDAO dao = new RecordsDAO();

        /*final String query = "Αθήνα";
        final String date = "25/03/2020";*/
            Document page;
            {
                try {
                    //page = Jsoup.connect("https://www.ekdromi.gr/frontend/deals/search?term=" + URLEncoder.encode(query,"UTF-8")).get();

                    page = Jsoup.connect("https://www.ekdromi.gr/frontend/deals/live_deals?date=" + URLEncoder.encode(date, "UTF-8") +
                            "&city[]=" + URLEncoder.encode(city, "UTF-8")).get();

                    Elements hotelNames = page.getElementsByClass("list_deal_title");
                    Elements hotelPrice = page.getElementsByClass("price");
                    //Έλεγχος σύνσεση με την βάσξ δεδομένων
                    if (DbUtil.getConn() != null) {
                        System.out.println("Επιτυχής Σύνδεση με την Βάση");
                    } else {
                        System.out.println("Πρόβλημα σύνδεσης με την Βάση δεδομένων");
                    }
                    int sum = 0;
                    //System.out.println("Αποτελέσματα απο EkdromiGr");
                    for (int i = 0; i < hotelNames.size(); i++) {
                        String names = hotelNames.get(i).text().trim();
                        //regular expression για αντικατάσταση όλων των χαρακτήρων εκτός των αριθμητικών
                        String priceString = hotelPrice.get(i).text().replaceAll("[^0-9]", "");
                        int price = Integer.parseInt(priceString);
                        //Υπολογισμός αθροίσματος τιμών προς χρησιμοποίηση στον μέσο όρο τιμών ξενοδοχείων
                        sum += price;
                        Records records = new Records(names, price);
                        dao.addProduct(records);
                        System.out.println("| " + names + " | " + priceString + " from dealSafari ");
                    }
                    //Εαν βρέθηκαν αποτελέσματα εμφανισέ τα συγκεντρωτικά αποτελέσματα καθώς και έλεγχος διαίρεσης με το μηδέν
                    if (hotelNames.size() != 0) {
                        System.out.println();
                        System.out.println("Βρέθηκαν " + hotelNames.size() + " ξενοδοχεία απο το Ekdromi.gr");
                        DecimalFormat df = new DecimalFormat("0.00");
                        double averagePrice = sum / hotelNames.size();
                        df.setMaximumFractionDigits(4);
                        System.out.println("Μέσος όρος τιμών ξενοδοχείων  απο ekdromi.gr για την αναζήτηση " + city + " την ημερομηνία " + date + " είναι " + df.format(averagePrice) + "");
                    } else {
                        System.out.println("Κανένα αποτέλεσμα δεν επεστράφει apo ekdromi.gr. Δοκιμάστε διαφορετικά κριτήρια αναζήτησης ή προσπαθείστε ξανά");
                    }

                    System.out.println("--------------------------------------");
               /* for (Element searchResult: page.select("div.list_deal_title")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }*/
                } catch (IOException e) {
                    System.out.println(" Προέκυψε ενα σφάλμα κατά την διάρκεια εκτύπωσης αποτελεσμάτων απο την σελίδα Ekdromi. Προσπαθήστε ξανά αργότερα ");
                } catch (Exception e) {
                    System.out.println(" Προέκυψε ενα σφάλμα κατά την διάρκεια εκτύπωσης αποτελεσμάτων απο την σελίδα Ekdromi. Προσπαθήστε ξανά αργότερα ");
                }
            }
        }
        System.out.println("Results from ekdromi.gr stopped");
    }

    public synchronized void stop(){
        exit = true;
    }
}
