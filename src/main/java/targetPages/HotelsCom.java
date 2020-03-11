package targetPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HotelsCom  {

    public void printResults() {
        Document doc;

        {
            try {
                doc = Jsoup.connect("https://en.wikipedia.org/").get();
                Elements newsHeadlines = doc.select("#mp-itn b a");
                for (Element element : newsHeadlines) {
                    System.out.println(newsHeadlines.text());
                }


                System.out.println("-----------------------------------------------------");

                System.out.println(doc.outerHtml());


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

