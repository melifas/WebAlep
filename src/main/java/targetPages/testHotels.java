package targetPages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;

public class testHotels {
    public void print() {
        final String query = "Αθήνα";
        final String startdate = "2020-03-15";
        //TODO Να βρώ ενα τρόπο να αυξανω την ημερομηνια κατα 1
        final String enddate = "2020-03-16";

        Document page;
        {
            try {
                page = Jsoup.connect("https://el.hotels.com/search.do?q-destination=" + URLEncoder.encode(query,"UTF-8")+"&q-check-in="+ URLEncoder.encode(startdate,"UTF-8")
                        +"&q-check-out="+URLEncoder.encode(enddate,"UTF-8") + "&q-rooms=1&q-room-0-adults=1&q-room-0-children=0"
                ).get();

                //page = Jsoup.connect("https://el.hotels.com/search.do?q-destination = Αθήνα").get();

                Elements hotelNames = page.getElementsByClass("property-name-link");
                //Elements hotelPrice = page.select("");
                Elements hotelRates = page.select("strong.guest-reviews-badge");
                System.out.println("--------------------------------------");
                System.out.println("Αποτελέσματα απο HotelGr");
                for (int i =0; i< hotelNames.size(); i++){
                    String names = hotelNames.get(i).text().trim();
                    //String priceString = hotelPrice.get(i).text();
                    String ratesString = hotelRates.get(i).text();
                    //double price = Double.parseDouble(priceString);
                    System.out.println("| " + names  + " | "  + " | " + ratesString );
                }
                System.out.println("--------------------------------------");



               /* for (Element searchResult: page.select("h3.p-name a")) {
                    final String title = searchResult.text();

                    System.out.println(title);
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
