package targetPages;

import dao.RecordsDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pojo.Records;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;

public class kayak {
    public void print() {
        RecordsDAO dao = new RecordsDAO();

        final String query = "Χανιά";
        //final String date = "25/03/2020";
        Document page;
        {
            try {
                //page = Jsoup.connect("https://www.ekdromi.gr/frontend/deals/search?term=" + URLEncoder.encode(query,"UTF-8")).get();
                https://www.gr.kayak.com/hotels/%CE%A7%CE%B1%CE%BD%CE%B9%CE%AC/2020-03-26/2020-03-27?sort=rank_a

                page = Jsoup.connect("https://www.esky.gr/xenodohia/ci/ath/xenodohia-athhna?checkInDate=2020-03-28&checkOutDate=2020-03-29").get();


                Elements hotelNames = page.select("hotel-details").select("li.hotel-name");
                //Elements hotelPrice = page.getElementsByClass("price");
                int sum = 0;
                System.out.println("--------------------------------------");
                System.out.println("Αποτελέσματα απο kayak,gr");
                for (int i =0; i< hotelNames.size(); i++){
                    String names = hotelNames.get(i).text().trim();
                    //regular expression για αντικατάσταση όλων των χαρακτήρων εκτός των αριθμητικών
                   // String priceString = hotelPrice.get(i).text().replaceAll("[^0-9]", "");
                    //int price = Integer.parseInt(priceString);
                    //Υπολογισμός αθροίσματος τιμών προς χρησιμοποίηση στον μέσο όρο τιμών ξενοδοχείων
                    //sum+=price;
                    //Records records  = new Records(names,price);
                    //dao.addProduct(records);
                    System.out.println("| " + names  + " | " );
                }
                //Εαν βρέθηκαν αποτελέσματα εμφανισέ τα συγκεντρωτικά αποτελέσματα καθώς και έλεγχος διαίρεσης με το μηδέν
               /* if (hotelNames.size()!=0) {
                    System.out.println();
                    System.out.println("Βρέθηκαν "+hotelNames.size()+ " ξενοδοχεία");
                    DecimalFormat df = new DecimalFormat("0.00");
                    double averagePrice =  sum / hotelNames.size();
                    df.setMaximumFractionDigits(4);
                    System.out.println("Μέσος όρος τιμών ξενοδοχείων για την αναζήτηση " + city + "την ημερομηνία "+"είναι "+ df.format(averagePrice)+"");
                    System.out.println("--------------------------------------");
                }else{
                    System.out.println("Κανένα αποτέλεσμα δεν επεστράφει. Δοκιμάστε διαφορετικά κριτήρια αναζήτησης ή προσπαθείστε ξανά");
                }*/

                System.out.println("--------------------------------------");
               /* for (Element searchResult: page.select("div.list_deal_title")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }*/
            } catch (IOException e) {
                System.out.println(" Προέκυψε ενα σφάλμα κατά την διάρκεια εκτύπωσης αποτελεσμάτων απο την σελίδα Ekdromi. Προσπαθήστε ξανά αργότερα ");
            }
            catch (Exception e){
                System.out.println(" Προέκυψε ενα σφάλμα κατά την διάρκεια εκτύπωσης αποτελεσμάτων απο την σελίδα Ekdromi. Προσπαθήστε ξανά αργότερα ");
            }
        }
    }
}
