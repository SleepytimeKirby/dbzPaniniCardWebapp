package rfitzpatrick.dbz.Utils;


import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import rfitzpatrick.dbz.DBO.CardCombined;
import rfitzpatrick.dbz.DBO.CustomDeck;
import rfitzpatrick.dbz.DatabaseJOOQ.tables.DbzCardlist;
import rfitzpatrick.dbz.DatabaseJOOQ.tables.DbzPrices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acwwl_000 on 1/7/2016.
 */
public class CardRetrival {

    private DSLContext dslContext;

    public CardRetrival(DSLContext context){
        dslContext = context;

    }

    public ArrayList<CardCombined> searchCards(String cardName){
        ArrayList<CardCombined> cards = new ArrayList<>();
        Result<Record> results = dslContext.select().from(DbzCardlist.DBZ_CARDLIST)
                .join(DbzPrices.DBZ_PRICES)
                .on(DbzCardlist.DBZ_CARDLIST.ID.equal(DbzPrices.DBZ_PRICES.ID))
                .where(DbzCardlist.DBZ_CARDLIST.NAME.contains(cardName)).fetch();
        for(Record r:results){
            CardCombined card = new CardCombined();
            card.setId(r.getValue(DbzCardlist.DBZ_CARDLIST.ID));
            card.setName(r.getValue(DbzCardlist.DBZ_CARDLIST.NAME));
            card.setSet(r.getValue(DbzCardlist.DBZ_CARDLIST.SET));
            card.setCardLevel(r.getValue(DbzCardlist.DBZ_CARDLIST.CARD_LEVEL));
            card.setCardNum(r.getValue(DbzCardlist.DBZ_CARDLIST.NUMBER));
            card.setStyle(r.getValue(DbzCardlist.DBZ_CARDLIST.STYLE));
            card.setRarity(r.getValue(DbzCardlist.DBZ_CARDLIST.RARITY));
            card.setType(r.getValue(DbzCardlist.DBZ_CARDLIST.TYPE));
            card.setArkPrice(r.getValue(DbzPrices.DBZ_PRICES.PRICEARK));
            card.setArkUrl( r.getValue(DbzPrices.DBZ_PRICES.URLARK));
            card.setDbzoPrice(r.getValue(DbzPrices.DBZ_PRICES.PRICEDBZOUTPOST));
            card.setDbzoUrl(r.getValue(DbzPrices.DBZ_PRICES.URLDBZOUTPOST));

        cards.add(card);
        }
        return cards;
    }
    public ArrayList<CardCombined> getDeck(int id){
        ArrayList<CardCombined> cards = new ArrayList<>();
        //To-Do move ot webapp
        DeckManager deckManager = null;
        CustomDeck customDeck = deckManager.retriveDeck(id);
        List<String> listOfCards = new ArrayList<String>();
        listOfCards.add(customDeck.getMasteryCard());
        for(String c:customDeck.getMainPersonality()){
            listOfCards.add(c);
        }
        for(String c:customDeck.getLifeDeck()){
            listOfCards.add(c);
        }
        for(String cardName:listOfCards) {
            Result<Record> results = dslContext.select().from(DbzCardlist.DBZ_CARDLIST.join(DbzPrices.DBZ_PRICES).on(DbzCardlist.DBZ_CARDLIST.ID.equal(DbzPrices.DBZ_PRICES.ID)))
                    .where(DbzCardlist.DBZ_CARDLIST.NAME.contains(cardName)).limit(1).fetch();
            for (Record r : results) {
                CardCombined card = new CardCombined(r.getValue(DbzCardlist.DBZ_CARDLIST.ID), r.getValue(DbzCardlist.DBZ_CARDLIST.NAME), r.getValue(DbzCardlist.DBZ_CARDLIST.SET),
                        r.getValue(DbzCardlist.DBZ_CARDLIST.CARD_LEVEL), r.getValue(DbzCardlist.DBZ_CARDLIST.NUMBER), r.getValue(DbzCardlist.DBZ_CARDLIST.STYLE),
                        r.getValue(DbzCardlist.DBZ_CARDLIST.RARITY), r.getValue(DbzCardlist.DBZ_CARDLIST.TYPE), r.getValue(DbzPrices.DBZ_PRICES.PRICEARK),
                        r.getValue(DbzPrices.DBZ_PRICES.URLARK), r.getValue(DbzPrices.DBZ_PRICES.PRICEDBZOUTPOST), r.getValue(DbzPrices.DBZ_PRICES.URLDBZOUTPOST));
                cards.add(card);
            }
        }

        return cards;
    }

}
