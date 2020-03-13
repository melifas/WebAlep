package targetPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;

public class dealSagariGr {
    public void printResults() {
        final String query = "Αττική";
        Document page;
        {
            try {
                page = Jsoup.connect("https://www.dealsafari.gr/prosfores/xenodoxeia?location=" + URLEncoder.encode(query,"UTF-8")).get();


                for (Element searchResult: page.select("div.list-deal-company-name")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
