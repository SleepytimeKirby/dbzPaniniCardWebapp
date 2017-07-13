/**
 *
 * <div itemprop="offers" itemscope itemtype="http://schema.org/Offer">
 * <meta itemprop="priceCurrency" content="USD">
 * <meta itemprop="price" content="$ 3.00">
 *
 */
package rfitzpatrick.dbz.Extractors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by acwwl_000 on 1/5/2016.
 */
public class CFLExtractor {

    private String url;
    private Document doc;

    public CFLExtractor() {
    }

    public double extract(String urlInput) {
        try {
            doc = Jsoup.connect(urlInput).get();
        } catch (IOException ex) {
            return -1;
        }
        double cardPrice = -1;
        Elements divs = doc.select("div");
        for (Element div : divs) {
            if (div.hasAttr("itemprop")) {
                if (div.attr("itemprop").equals("offers")) {
                   for(Element meta:div.children()){
                       if(meta.hasAttr("itemprop")){
                           if(meta.attr("itemprop").equals("price")){
                               cardPrice = Double.valueOf(meta.attr("content").replace("$","".replace(" ","")));
                           }
                       }
                   }
                }
            }
        }
        return cardPrice;
    }
}
