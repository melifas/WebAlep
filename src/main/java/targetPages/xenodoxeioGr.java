package targetPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;

// η ημερομηνία αποθηκεύεται σε cookie
public class xenodoxeioGr implements Runnable {
    public void run() {
        final String query = "Αθήνα";
        final String date = "2020-03-15";
        Document page;
        {
            try {
                page = Jsoup.connect("https://www.xenodoxeio.gr/search?search_term=" + URLEncoder.encode(query,"UTF-8")+"&checkin"+URLEncoder.encode(date,"UTF-8")).get();


                Elements hotelNames = page.getElementsByClass("main-deal-hotel-name");
                Elements hotelPrice = page.getElementsByClass("main-deal-final-price palette-light");


                for (int i =0; i< hotelNames.size(); i++){
                    String names = hotelNames.get(i).text().trim();
                    String priceString = hotelPrice.get(i).text();
                    //double price = Double.parseDouble(priceString);
                    System.out.println("| " + names  + " | " + priceString);
                }

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
