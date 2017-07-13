package rfitzpatrick.utils;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import rfitzpatrick.dbz.DBO.CustomDeck;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class DeckImporter {
	
	
	private static String deckDirectory = "decks"; //from root
	public DeckImporter(){
		
	}
	
	public String[] readDecksFile() {
		File[] roots = File.listRoots();
		File dir = new File(roots[0], deckDirectory);
		for (File deck : dir.listFiles()) {
			try {
				String deckName = deck.getName().replace(".o8d", "");
				CustomDeck cDeck = new CustomDeck();
				InputStream in = new FileInputStream(deck);
				Document doc = Jsoup.parse(in, null, deck.getPath(), Parser.xmlParser());
				Elements deckCards = doc.getElementsByTag("section");
				for (Element section : deckCards) {
					if (section.attr("name").equals("Starting")) {
						cDeck.setMasteryCard(section.child(0).text());
					} else if (section.attr("name").equals("Sensei Deck")) {
						int i = 0;
						for (Element cards : section.children()) {
							cDeck.addToMainPersonality(i, cards.text());
							i++;
						}
					} else {
						int i = 0;
						for (Element cards : section.children()) {
							for (int z = 1; z <= Integer.valueOf(cards.attr("qty")); z++) {
								cDeck.addToLifeDeck(i, cards.text());
								i++;
							}
						}
					}
				}
				Gson gson = new Gson();
				String[] deckList = new String[2];
				deckList[0] = deckName;
				deckList[1] = gson.toJson(cDeck);
				String fakeUser = "xXDBZN00B420NOSCOPEXx";
				//To-Do Move to webapp
			//	DeckManager deckManager = new DeckManager();
			//	deckManager.storeDeck(fakeUser,deckList[0],deckList[1]);
				System.out.println(deckName);
				System.out.println(deckList[1]);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new String[]{"error","error"};
	}


	

}
