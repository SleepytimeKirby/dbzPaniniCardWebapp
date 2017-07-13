package rfitzpatrick.dbz.Utils;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import rfitzpatrick.dbz.DBO.CardDBO;
import rfitzpatrick.dbz.DatabaseJOOQ.tables.DbzCardlist;
import rfitzpatrick.dbz.DatabaseJOOQ.tables.DbzPrices;
import rfitzpatrick.dbz.Extractors.ArkadiaGamingExtractor;
import rfitzpatrick.dbz.Extractors.CFLExtractor;
import rfitzpatrick.dbz.Extractors.ArkadiaUrlExtractor;
import rfitzpatrick.dbz.Extractors.DBZOutpostUrlExtractor;
import rfitzpatrick.dbz.Extractors.DBZExchangeUrlExtractor;
import rfitzpatrick.dbz.Extractors.DBZOutpostPriceExtractor;


/**
 * Created by acwwl_000 on 1/5/2016.
 *
 *
 * 1 method for regenning prices (retrive url from db, retrive price, insert prices)
 * 1 method for generating urls (generate urls, store urls) and gen prices
 * \
 *
 *
 * //Temporarly removing CFL until i can come up with a fix 1/5/16
 */
public class PriceManager {

    private static DBZDBManager dbManager;
    private static ArkadiaUrlExtractor arkadiaGamingURLGenerator;
    private static DBZOutpostUrlExtractor dboutUrl;
    private static DBZExchangeUrlExtractor dbzxUrlGen;
    private static ArkadiaGamingExtractor arkadiaGamingExtractor;
    private static CFLExtractor cflExtractor;
    private static DSLContext context;
    private static DBZOutpostPriceExtractor dbzOutExtract;

    public PriceManager(DSLContext context) {
        //Use arkadia for both arkadigaming and dbzexchange
        arkadiaGamingExtractor = new ArkadiaGamingExtractor();
        cflExtractor = new CFLExtractor();
        this.context = context;
    }

    private void generate_urls(){

        arkadiaGamingURLGenerator = new ArkadiaUrlExtractor();
        dboutUrl = new DBZOutpostUrlExtractor();
        dbzxUrlGen = new DBZExchangeUrlExtractor();
        Result<Record> results = context.select().from(DbzCardlist.DBZ_CARDLIST).fetch();
        for(Record r: results){
            int id = r.getValue(DbzCardlist.DBZ_CARDLIST.ID);
            CardDBO entry = new CardDBO(id,r.getValue(DbzCardlist.DBZ_CARDLIST.NAME),r.getValue(DbzCardlist.DBZ_CARDLIST.SET),r.getValue(DbzCardlist.DBZ_CARDLIST.CARD_LEVEL),
                    r.getValue(DbzCardlist.DBZ_CARDLIST.NUMBER),r.getValue(DbzCardlist.DBZ_CARDLIST.STYLE),r.getValue(DbzCardlist.DBZ_CARDLIST.RARITY),r.getValue(DbzCardlist.DBZ_CARDLIST.TYPE));
            String[] urls = new String[]{"","",""};
            //gen urls
            urls[0] = arkadiaGamingURLGenerator.extract(entry);
            urls[1] = dboutUrl.extract(entry);
            urls[2] = dbzxUrlGen.extract(entry);
            //Insert Into Database, If Exist update
            context.insertInto(DbzPrices.DBZ_PRICES, DbzPrices.DBZ_PRICES.ID, DbzPrices.DBZ_PRICES.URLARK, DbzPrices.DBZ_PRICES.URLCFL, DbzPrices.DBZ_PRICES.URLDBZOUTPOST)
                    .values(id,urls[0],urls[1],urls[2]).onDuplicateKeyUpdate().set(DbzPrices.DBZ_PRICES.URLARK,urls[0]).set(DbzPrices.DBZ_PRICES.URLCFL,urls[1])
                    .set(DbzPrices.DBZ_PRICES.URLDBZOUTPOST,urls[2])
                    .execute();
        }
    }
    public void refreshAllEntries(){
        Result<Record> results = context.select().from(DbzPrices.DBZ_PRICES).fetch();
        for(Record r: results){
            String[] urls = new String[]{r.getValue(DbzPrices.DBZ_PRICES.URLARK),r.getValue(DbzPrices.DBZ_PRICES.URLCFL),r.getValue(DbzPrices.DBZ_PRICES.URLDBZOUTPOST)};
            double[] prices  = new double[]{-1,-1,-1};
            //gen urls
           prices[0] = arkadiaGamingExtractor.extract(urls[0]);
           //prices[1] = dbzOutExtract.extract(urls[1]);
            prices[2] = arkadiaGamingExtractor.extract(urls[2]);
            //Insert Into Database, If Exist update
            context.update(DbzPrices.DBZ_PRICES).set(DbzPrices.DBZ_PRICES.PRICEARK,prices[0]).set(DbzPrices.DBZ_PRICES.PRICEDBZOUTPOST,prices[2])
                    .where(DbzPrices.DBZ_PRICES.ID.equal(r.getValue(DbzPrices.DBZ_PRICES.ID))).execute();
        }
    }
    public void refreshSingEntry(int id){
        Result<Record> results = context.select().from(DbzPrices.DBZ_PRICES).where(DbzPrices.DBZ_PRICES.ID.equals(id)).fetch();
        for(Record r: results){
            String[] urls = new String[]{r.getValue(DbzPrices.DBZ_PRICES.URLARK),r.getValue(DbzPrices.DBZ_PRICES.URLCFL),r.getValue(DbzPrices.DBZ_PRICES.URLDBZOUTPOST)};
            double[] prices  = new double[]{-1,-1,-1};
            //gen urls
            prices[0] = arkadiaGamingExtractor.extract(urls[0]);
         //   prices[1] = cflExtractor.extract(urls[1]);
            prices[2] = arkadiaGamingExtractor.extract(urls[2]);
            //Insert Into Database, If Exist update
            context.update(DbzPrices.DBZ_PRICES).set(DbzPrices.DBZ_PRICES.PRICEARK,prices[0]).set(DbzPrices.DBZ_PRICES.PRICEDBZOUTPOST,prices[2])
                    .where(DbzPrices.DBZ_PRICES.ID.equal(id)).execute();
        }
    }
    public void initalize(){
        generate_urls();
        refreshAllEntries();
    }
}
