package rfitzpatrick.dbz.Extractors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;

public class ArkadiaGamingExtractor{

    private String url;
    private Document doc;


//Input is card name, Search database, Retrive Formated SubURL, Go TO URL, Process Data, Extract, And Store
    public ArkadiaGamingExtractor(){

    }

    public double extract(String urlInput) {
        try {
            doc = Jsoup.connect(urlInput).get();
        } catch (IOException ex) {
            return -1;
        }
        double cardPrice = -1;
        Elements header = doc.select("meta");
        for (Element metaTag : header) {
            if (metaTag.hasAttr("property")) {
                if (metaTag.attr("property").equals("og:price:amount")) {
                    cardPrice = Double.valueOf(metaTag.attr("content"));
                }
            }
        }
        return cardPrice;
    }
}