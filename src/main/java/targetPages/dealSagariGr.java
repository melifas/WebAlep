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


//δεν χρησιμοποιεί ημερομηνία για τις αναζητήσεις
public class dealSagariGr implements Runnable {
    private String city;

    public dealSagariGr(String city) {
        this.city = city;
    }
    public void run() {
        RecordsDAO dao = new RecordsDAO();
        final String query = "Αττική";
        Document page;
        {
            try {
                page = Jsoup.connect("https://www.dealsafari.gr/prosfores/xenodoxeia?location=" + URLEncoder.encode(query,"UTF-8")).get();

                Elements hotelNames = page.select("div.list-deal-company-name");
                Elements hotelPrice = page.select("span.list-deal-final-price");
                //Έλεγχος σύνσεση με την βάσξ δεδομένων
                if(DbUtil.getConn()!=null){
                    System.out.println("Επιτυχής Σύνδεση με την Βάση");
                }else{
                    System.out.println("Πρόβλημα σύνδεσης με την Βάση δεδομένων");
                }
                int sum = 0;
                System.out.println("--------------------------------------");
                //System.out.println("Αποτελέσματα απο DealSafari");
                for (int i =0; i< hotelNames.size(); i++){
                    String names = hotelNames.get(i).text().trim();
                    String priceString = hotelPrice.get(i).text().replaceAll("[^0-9]", "");
                    double price = Double.parseDouble(priceString);
                    sum+=price;
                    Records records  = new Records(names,price);
                    dao.addProduct(records);
                    System.out.println("| " + names  + " | " + price);
                }

                //Εαν βρέθηκαν αποτελέσματα εμφανισέ τα συγκεντρωτικά αποτελέσματα καθώς και έλεγχος διαίρεσης με το μηδέν
                if (hotelNames.size()!=0) {
                    System.out.println();
                    System.out.println("Βρέθηκαν "+hotelNames.size()+ " ξενοδοχεία απο το dealSafari.gr");
                    DecimalFormat df = new DecimalFormat("0.00");
                    double averagePrice =  sum / hotelNames.size();
                    df.setMaximumFractionDigits(4);
                    System.out.println("Μέσος όρος τιμών ξενοδοχείων  απο dealsafari.gr για την αναζήτηση " + city + " την ημερομηνία είναι "+ df.format(averagePrice)+"");
                }else{
                    System.out.println("Κανένα αποτέλεσμα δεν επεστράφει απο xenodoxeio.gr. Δοκιμάστε διαφορετικά κριτήρια αναζήτησης ή προσπαθείστε ξανά");
                }

                System.out.println("--------------------------------------");
            } catch (IOException e) {
                System.out.println(" Προέκυψε ενα σφάλμα κατά την διάρκεια εκτύπωσης αποτελεσμάτων απο την σελίδα dealsafari.gr. Προσπαθήστε ξανά αργότερα ");
            }
            catch (Exception e){
                System.out.println(" Προέκυψε ενα σφάλμα κατά την διάρκεια εκτύπωσης αποτελεσμάτων απο την σελίδα dealsafari.gr. Προσπαθήστε ξανά αργότερα ");
            }
        }
    }

}
