package rfitzpatrick.dbz.Extractors;

import org.jooq.DSLContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import rfitzpatrick.dbz.DatabaseJOOQ.tables.DbzCardlist;
import rfitzpatrick.dbz.Utils.DBZDBManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by acwwl_000 on 1/5/2016.
 */
public class OCTGNSetExtractor {

    private static DBZDBManager dbManager;
    private static String basePath = "Sets\\";
    private static DSLContext dslContext;
    public OCTGNSetExtractor(){
        dbManager = DBZDBManager.getDatabaseManager();
        dslContext = dbManager.getDSLContext();
    }

    public void readXml() throws IOException {

        File[] roots = File.listRoots();
        File dir = new File(roots[0],"xmls");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File xmlFile : directoryListing) {
                InputStream inputStream = new FileInputStream(xmlFile);
                Document xmlDoc = Jsoup.parse(inputStream,null,xmlFile.toPath().toString(),Parser.xmlParser());
                Elements cards = xmlDoc.getElementsByTag("card");
                storeCards(cards,xmlFile.getName());
            }
        }
    }

    private void storeCards(Elements cards,String set){
        for(Element card:cards){
            //name,set,level,number,style,rarity,type
            set = set.replace(".xml","");
            String[] cardData = new String[]{"",set,"","","","",""};
            cardData[0] = card.attr("name");
            for(Element properties:card.children()){
                if(properties.attr("name").equals("Card Level")){
                    cardData[2] = properties.attr("value");
                }
                if(properties.attr("name").equals("Card Number")){
                    cardData[3] = properties.attr("value");
                }
                if(properties.attr("name").equals("Style")){
                    cardData[4] = properties.attr("value");
                }
                if(properties.attr("name").equals("Rarity")){
                    cardData[5] = properties.attr("value");
                }
                if(properties.attr("name").equals("Type")){
                    cardData[6] = properties.attr("value");
                }
            }

            dslContext.insertInto(DbzCardlist.DBZ_CARDLIST,DbzCardlist.DBZ_CARDLIST.NAME,DbzCardlist.DBZ_CARDLIST.SET,DbzCardlist.DBZ_CARDLIST.CARD_LEVEL,DbzCardlist.DBZ_CARDLIST.NUMBER,DbzCardlist.DBZ_CARDLIST.STYLE,DbzCardlist.DBZ_CARDLIST.RARITY,DbzCardlist.DBZ_CARDLIST.TYPE)
                    .values(cardData[0],cardData[1],cardData[2],cardData[3],cardData[4],cardData[5],cardData[6]).execute();

        }
    }
}
