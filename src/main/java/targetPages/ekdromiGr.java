package targetPages;

import dao.RecordsDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.Records;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.concurrent.RunnableFuture;


//φερνει κανονικά τις αναζητήσεις αλλά όχι όλες γιατί είναι δυναμική η ιστοσελίδα
public class ekdromiGr  {
    public void print(String city,String date) {
        RecordsDAO dao = new RecordsDAO();
        /*final String query = "Αθήνα";
        final String date = "25/03/2020";*/
        Document page;
        {
            try {
                //page = Jsoup.connect("https://www.ekdromi.gr/frontend/deals/search?term=" + URLEncoder.encode(query,"UTF-8")).get();

                page = Jsoup.connect("https://www.ekdromi.gr/frontend/deals/live_deals?date=" + URLEncoder.encode(date,"UTF-8") +
                                           "&city[]=" + URLEncoder.encode(city,"UTF-8")).get();

                Elements hotelNames = page.getElementsByClass("list_deal_title");
                Elements hotelPrice = page.getElementsByClass("price");
                double sum = 0;
                System.out.println("--------------------------------------");
                System.out.println("Αποτελέσματα απο EkdromiGr");
                for (int i =0; i< hotelNames.size(); i++){
                    String names = hotelNames.get(i).text().trim();
                    //regular expression για αντικατάσταση όλων των χαρακτήρων εκτός των αριθμητικών
                    String priceString = hotelPrice.get(i).text().replaceAll("[^0-9]", "");
                    double price = Double.parseDouble(priceString);
                    //Υπολογισμός αθροίσματος τιμών προς χρησιμοποίηση στον μέσο όρο ξενοδοχείων
                    sum+=sum+price;
                    Records records  = new Records(names,price);
                    dao.addProduct(records);
                    System.out.println("| " + names  + " | " + priceString);
                }
                //Εαν βρέθηκαν αποτελέσματα εμφανισέ τα συγκεντρωτικά αποτελέσματα
                if (hotelNames.size()!=0) {
                    System.out.println("Βρέθηκαν"+hotelNames.size()+ "ξενοδοχεία");
                    double averagePrice = sum / hotelNames.size();
                    System.out.println("Μέσος όρος τιμών ξενοδοχείων για την αναζήτηση "+ city+ " την ημερομηνία "+date+" είναι "+averagePrice);
                    System.out.println("--------------------------------------");
                }else{
                    System.out.println("Κανένα αποτέλεσμα δεν επεστράφει");
                }

                System.out.println("--------------------------------------");
               /* for (Element searchResult: page.select("div.list_deal_title")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }*/
            } catch (IOException e) {
                System.out.println("Προέκυψε ενα σφάλμα κατά την διάρκεια εκτύπωσης αποτελεσμάτων απο την σελίδα Ekdromi. Προσπαθήστε ξανά αργότερα ");
            }
            catch (Exception e){
                System.out.println("Προέκυψε ενα σφάλμα κατά την διάρκεια εκτύπωσης αποτελεσμάτων απο την σελίδα Ekdromi. Προσπαθήστε ξανά αργότερα ");
            }
        }
    }
}
