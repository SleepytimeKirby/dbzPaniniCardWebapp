package rfitzpatrick.dbz.Extractors;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rfitzpatrick.dbz.DBO.CardDBO;
import rfitzpatrick.dbz.DBO.UrlsForSorting;
//To-Do: Add in the Card number for search
public class DBZOutpostUrlExtractor {
	private String baseUrl = "http://dbzoutpost.tictail.com/search?q=";
	JsonParser parser = new JsonParser();

	// Input is card name, get Url, Extract, And Store
	public DBZOutpostUrlExtractor() {

	}

	public String extract(CardDBO card) {
		return extract(card.getName(),card.getCardNum());
	}

	public String extract(String card,String card_num) {
		String url = card_num + " " + card.replace("'", "");
		url = url.replace(" ", "%20");
		url = baseUrl + url;
		InputStream input;
		try {
			input = new URL(url).openStream();
		} catch (IOException ex) {
			return "-1";
		}
		Reader read = new InputStreamReader(input);
		JsonObject root = parser.parse(read).getAsJsonObject();
		JsonArray products = root.get("products").getAsJsonArray();
		double max = 0;
		double average = 0;
		 Similarities calculate = new Similarities();
		 ArrayList<UrlsForSorting> urls = new ArrayList<UrlsForSorting>();
		for(JsonElement obj: products){
			String extracted_url = obj.getAsJsonObject().get("url").getAsString();
			String name = obj.getAsJsonObject().get("title").getAsString();
			double cosine = calculate.cosine_similarity(card, name); //f1
        	double jaccard = calculate.jaccard_coefficient(card, name);	  //f2
        	average = average+cosine;
        	UrlsForSorting u = new UrlsForSorting(extracted_url,name);
        	u.setCosine_similarity(cosine);
        	u.setJaccard_idx(jaccard);
        	urls.add(u);
        	if (jaccard > max)
        		max = jaccard;
		}
		average = average/products.size();
		for(UrlsForSorting u : urls){
        	double calculated_rank;
        	double f1 = u.getCosine_similarity();
        	double f1_avg = average;
        	double f2 = u.getJaccard_idx();
        	double f2_max = max;
        	calculated_rank = ((f2/f2_max)*f1) + ((1-(f2/f2_max))*f1_avg);
        	u.setSortingScore(calculated_rank);
        }
		Collections.sort(urls, new Comparator<UrlsForSorting>(){
            public int compare(UrlsForSorting u1, UrlsForSorting u2) {
                 return((u1.getSortingScore() > u2.getSortingScore()) ? -1:
                	 (u1.getSortingScore() < u2.getSortingScore() ? 1 : 0));
            		
            }
		});
		try {
			read.close();
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return urls.get(0).getUrl();
		
	}
}
