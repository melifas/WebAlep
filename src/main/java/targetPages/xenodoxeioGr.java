package targetPages;

import dao.RecordsDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.Records;

import java.io.IOException;
import java.net.URLEncoder;

// η ημερομηνία αποθηκεύεται σε cookie
public class xenodoxeioGr  {
    public void print() {
        RecordsDAO dao = new RecordsDAO();
        final String query = "Αθήνα";
        final String date = "2020-03-15";
        Document page;
        {
            try {
                page = Jsoup.connect("https://www.xenodoxeio.gr/search?search_term=" + URLEncoder.encode(query,"UTF-8")+"&checkin"+URLEncoder.encode(date,"UTF-8")).get();


                Elements hotelNames = page.getElementsByClass("main-deal-hotel-name");
                Elements hotelPrice = page.getElementsByClass("main-deal-final-price palette-light");

                System.out.println("--------------------------------------");
                System.out.println("Αποτελέσματα απο xenodoxeioGr");
                for (int i =0; i< hotelNames.size(); i++){
                    String names = hotelNames.get(i).text().trim();
                    String priceString = hotelPrice.get(i).text().substring(0,3);
                    double price = Double.parseDouble(priceString);
                    Records records  = new Records(names,price);
                    dao.addProduct(records);

                    System.out.println("| " + names  + " | " + priceString);
                }
                System.out.println("--------------------------------------");
                /*for (Element searchResult: page.select("p.main-deal-hotel-name a")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
