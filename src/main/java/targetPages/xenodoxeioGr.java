package targetPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;

public class xenodoxeioGr {
    public void printResults() {
        final String query = "Αθήνα";
        Document page;
        {
            try {
                page = Jsoup.connect("https://www.xenodoxeio.gr/search?locations[]=" + URLEncoder.encode(query,"UTF-8")).get();


                for (Element searchResult: page.select("p.main-deal-hotel-name a")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



}
