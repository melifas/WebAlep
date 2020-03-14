package targetPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;

public class xenodoxeioGr {
    public void printResults() {
        final String query = "Αττική";
        Document page;
        {
            try {
                page = Jsoup.connect("https://www.xenodoxeio.gr/search?locations[]=" + URLEncoder.encode(query,"UTF-8")).get();


                Elements hotelNames = page.select("p.main-deal-hotel-name a");
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
