package targetPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;


//φερνει κανονικά τις αναζητήσεις αλλά όχι όλες γιατί είναι δυναμική η ιστοσελίδα
public class ekdromiGr implements Runnable {
    public void run() {
        final String query = "Αθήνα";
        final String date = "15/03/2020";
        Document page;
        {
            try {
                //page = Jsoup.connect("https://www.ekdromi.gr/frontend/deals/search?term=" + URLEncoder.encode(query,"UTF-8")).get();

                page = Jsoup.connect("https://www.ekdromi.gr/frontend/deals/live_deals?date=" + URLEncoder.encode(date,"UTF-8") +
                                           "&city[]="  + URLEncoder.encode(query,"UTF-8")).get();

                Elements hotelNames = page.getElementsByClass("list_deal_title");
                Elements hotelPrice = page.getElementsByClass("price");
                System.out.println("--------------------------------------");
                System.out.println("Αποτελέσματα απο EkdromiGr");
                for (int i =0; i< hotelNames.size(); i++){
                    String names = hotelNames.get(i).text().trim();
                    String priceString = hotelPrice.get(i).text();
                    //double price = Double.parseDouble(priceString);
                    System.out.println("| " + names  + " | " + priceString);
                }

                System.out.println("--------------------------------------");
               /* for (Element searchResult: page.select("div.list_deal_title")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
