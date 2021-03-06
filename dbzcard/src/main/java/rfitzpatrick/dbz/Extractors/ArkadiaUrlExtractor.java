package rfitzpatrick.dbz.Extractors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import rfitzpatrick.dbz.DBO.CardDBO;
import rfitzpatrick.dbz.DBO.UrlsForSorting;

public class ArkadiaUrlExtractor {

    private Document doc;
    private String baseUrl = "http://arkadiagaming.com/search?type=product&q=";
    private double average = 0;
    private double jaccard_max = 0; 
    double f1_avg_cnt = 0;
//Input is card name, get Url, Extract, And Store
    public ArkadiaUrlExtractor(){

    }
    
    public String extract(CardDBO card){
    	return extract(card.getName());
    }
    public String extract(String card) {
    	  String url = card.replace("'", "");
    	  url = url.replace(" ", "%20");
    	 url = baseUrl + url;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            return "-1";
        }
        ArrayList<UrlsForSorting> urls = new ArrayList<UrlsForSorting>();
        Elements search_results = doc.getElementsByClass("search-result");
        for (Element result : search_results) {
            Elements u = result.getElementsByTag("a");
            Elements name = result.getElementsByTag("p"); // Only one a and p element are in the results	
                    urls.add(new UrlsForSorting(u.get(0).attr("href"),name.get(0).text()));
        }
        Similarities calculate = new Similarities();
        for(UrlsForSorting u : urls){
        	double cosine = calculate.cosine_similarity(card, u.getCardName()); //f1
        	double jaccard = calculate.jaccard_coefficient(card, u.getCardName());	  //f2
        	average = average+cosine;
           u.setCosine_similarity(cosine);
           u.setJaccard_idx(jaccard);
           if (jaccard > jaccard_max)
        	   jaccard_max = jaccard;
           f1_avg_cnt++;
        }
        for(UrlsForSorting u : urls){
        	double calculated_rank;
        	double f1 = u.getCosine_similarity();
        	double f1_avg = average;
        	double f2 = u.getJaccard_idx();
        	double f2_max = jaccard_max;
        	calculated_rank = ((f2/f2_max)*f1) + ((1-(f2/f2_max))*(f1_avg/f1_avg_cnt));
        	u.setSortingScore(calculated_rank);
        }
        Collections.sort(urls, new Comparator<UrlsForSorting>(){
                public int compare(UrlsForSorting u1, UrlsForSorting u2) {
                     return((u1.getSortingScore() > u2.getSortingScore()) ? -1:
                    	 (u1.getSortingScore() < u2.getSortingScore() ? 1 : 0));
                		
                }
        });
        return urls.get(0).getUrl();
    }
}
