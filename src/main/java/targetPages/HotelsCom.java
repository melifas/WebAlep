package targetPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;

public class HotelsCom  {

    public void printResults() {
        final String query = "Αττική";
        Document page;
        {
            try {
                page = Jsoup.connect("https://el.hotels.com/search.do?q-destination=" + URLEncoder.encode(query,"UTF-8")+"").get();

                //page = Jsoup.connect("https://el.hotels.com/search.do?q-destination = Αθήνα").get();


                for (Element searchResult: page.select("h3.p-name a")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

