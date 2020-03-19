package targetPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;



//δεν χρησιμοποιεί ημερομηνία για τις αναζητήσεις
public class dealSagariGr {
    public void print() {
        final String query = "Αττική";
        Document page;
        {
            try {
                page = Jsoup.connect("https://www.dealsafari.gr/prosfores/xenodoxeia?location=" + URLEncoder.encode(query,"UTF-8")).get();

                Elements hotelNames = page.select("div.list-deal-company-name");
                Elements hotelPrice = page.select("span.list-deal-final-price");
                System.out.println("--------------------------------------");
                System.out.println("Αποτελέσματα απο DealSafari");
                for (int i =0; i< hotelNames.size(); i++){
                    String names = hotelNames.get(i).text().trim();
                    String priceString = hotelPrice.get(i).text();
                    //double price = Double.parseDouble(priceString);
                    System.out.println("| " + names  + " | " + priceString);
                }


               /* for (Element searchResult: page.select("div.list-deal-company-name")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }

                for (Element searchResult: page.select("span.list-deal-final-price")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }*/


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
