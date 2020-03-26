package targetPages;

import dao.RecordsDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.Records;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;

public class HotelsCom  {

    public void print(String city,String date) {
        RecordsDAO dao = new RecordsDAO();

        //TODO Να βρώ ενα τρόπο να αυξανω την ημερομηνια κατα 1
        final String enddate = increasDateByOne(date);

        Document page;
        {
            try {
                page = Jsoup.connect("https://el.hotels.com/search.do?q-destination=" + URLEncoder.encode(city,"UTF-8")+"&q-check-in="+ URLEncoder.encode(date,"UTF-8")
                        +"&q-check-out"+URLEncoder.encode(enddate,"UTF-8")+"&q-rooms=1&q-room-0-adults=1&q-room-0-children=0"
                ).get();

                //page = Jsoup.connect("https://el.hotels.com/search.do?q-destination = Αθήνα").get();

                Elements hotelNames = page.getElementsByClass("property-name-link");
                Elements hotelPricelink = page.select("div.price");

                int sum = 0;
                //Elements hotelRates = page.select("strong.guest-reviews-badge");
                System.out.println("--------------------------------------");
                System.out.println("Αποτελέσματα απο HotelGr");
                for (int i =0; i< hotelPricelink.size(); i++){
                    String names = hotelNames.get(i).text().trim();
                    String priceString = hotelPricelink.get(i).text();
                    if (priceString.length()>7){
                        String pricenotready = priceString.substring(7);
                        //Αφαίρεση με regex όλων των χαρακτήρων εκτός αριθμούς
                        String priceready = pricenotready.replaceAll("[^0-9]", "");
                        double price = Double.parseDouble(priceready);
                        sum+=price;
                        Records records  = new Records(names,price);
                        dao.addProduct(records);
                        System.out.println("| " + names  + " | "  + " | " + price );
                    }else{
                        String priceready = priceString.replaceAll("[^0-9]", "");
                        double price = Double.parseDouble(priceready);
                        sum+=price;
                        Records records  = new Records(names,price);
                        dao.addProduct(records);
                        System.out.println("| " + names  + " | "  + " | " + price );
                    }
                }
                //Εαν βρέθηκαν αποτελέσματα εμφανισέ τα συγκεντρωτικά αποτελέσματα καθώς και έλεγχος διαίρεσης με το μηδέν
                if (hotelNames.size()!=0) {
                    System.out.println();
                    System.out.println("Βρέθηκαν "+hotelNames.size()+ " ξενοδοχεία");
                    DecimalFormat df = new DecimalFormat("0.00");
                    double averagePrice =  sum / hotelNames.size();
                    df.setMaximumFractionDigits(4);
                    System.out.println("Μέσος όρος τιμών ξενοδοχείων για την αναζήτηση " + city + "την ημερομηνία "+"είναι "+ df.format(averagePrice)+"");
                    System.out.println("--------------------------------------");
                }else{
                    System.out.println("Κανένα αποτέλεσμα δεν επεστράφει. Δοκιμάστε διαφορετικά κριτήρια αναζήτησης ή προσπαθείστε ξανά");
                }
            } catch (Exception e) {
                System.out.println("Ενδέχεται κάποια αποτελέσματα να μην επεστράφησαν");
            }
        }
    }
    //Το site θέλει στο url αρχική ημερομηνία(την οποία μας δίνει ο χρήστης). Για να μας παρουσιάσει αποτελέσματα θέλει και τελική ημερομηνία η οποία by Default είναι μια ημέρα μεγαλύτερη.
    //Η μέθοδος αυτή απλά αυξάνει την ημερομηνία κατά 1 για να μπει στο url.
    public String increasDateByOne(String startdate){
        Character last = startdate.charAt(9);
        //Κάντο int και αύξησε το κατά 1
        Integer convert =Character.getNumericValue(last);
        Integer incremented = convert+1;

        //Κάνε το int σε String
        String  myString = String.valueOf(incremented);
        //Κάνε το String Char
        Character lastChar = myString.charAt(0);

        //Αντικατέστησε την θέση με το συγκεκριμένο index με τον χαρακτήρα
        StringBuilder sb = new StringBuilder(startdate);
        sb.setCharAt(9,lastChar);
        return sb.toString();
    }


}
