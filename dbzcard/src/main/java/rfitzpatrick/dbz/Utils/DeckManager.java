package rfitzpatrick.dbz.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import rfitzpatrick.dbz.DBO.CustomDeck;
import rfitzpatrick.dbz.DBO.DeckList;
import rfitzpatrick.dbz.DatabaseJOOQ.tables.DbzDecks;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by acwwl_000 on 1/6/2016.
 */
public class DeckManager {

    private DSLContext dslContext;
    public DeckManager(DSLContext context){
         dslContext = context;
    }

    public void storeDeck(String user_id,String deckName, String deckList){
        dslContext.insertInto(DbzDecks.DBZ_DECKS, DbzDecks.DBZ_DECKS.USER_ID, DbzDecks.DBZ_DECKS.DECK_NAME, DbzDecks.DBZ_DECKS.SERALIZED_DECK)
                .values(user_id,deckName,deckList.getBytes()).execute();
    }
    public void updateDeck(int id,String user_id,String deckList, String deckName){
        dslContext.update(DbzDecks.DBZ_DECKS).set(DbzDecks.DBZ_DECKS.DECK_NAME,deckName).set(DbzDecks.DBZ_DECKS.SERALIZED_DECK,deckList.getBytes())
                .where(DbzDecks.DBZ_DECKS.ID.equal(id)).execute();
    }
    public void deleteDeck(int id){
        dslContext.deleteFrom(DbzDecks.DBZ_DECKS).where(DbzDecks.DBZ_DECKS.ID.equal(id)).execute();
    }
    public CustomDeck retriveDeck(int id){
        Result<Record> results = dslContext.select().from(DbzDecks.DBZ_DECKS).where(DbzDecks.DBZ_DECKS.ID.equal(id)).fetch();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CustomDeck.class, new CustomDeckDeserializer());
        Gson gson = gsonBuilder.create();
        Type bytesType = new TypeToken<byte[]>(){}.getType();
        String json = new String(results.get(0).getValue(DbzDecks.DBZ_DECKS.SERALIZED_DECK));
        Type typeString = new TypeToken<String>(){}.getType();
        CustomDeck cDeck = gson.fromJson(json,CustomDeck.class);
        return cDeck;
    }
    public ArrayList<DeckList> listDecks(String userId){
        ArrayList<DeckList> output = new ArrayList<>();
        Result<Record> results = dslContext.select().from(DbzDecks.DBZ_DECKS).where(DbzDecks.DBZ_DECKS.USER_ID.equal(userId)).fetch();
        for(Record r: results){
            Gson gson = new Gson();
            DeckList deck = new DeckList(r.getValue(DbzDecks.DBZ_DECKS.ID),r.getValue(DbzDecks.DBZ_DECKS.USER_ID),
                    r.getValue(DbzDecks.DBZ_DECKS.DECK_NAME));
            output.add(deck);
        }
        return output;
    }
    public ArrayList<DeckList> listDecks(){
        ArrayList<DeckList> output = new ArrayList<>();
        Result<Record> results = dslContext.select().from(DbzDecks.DBZ_DECKS).fetch();
            for(Record r: results){
                Gson gson = new Gson();
                DeckList deck = new DeckList(r.getValue(DbzDecks.DBZ_DECKS.ID),r.getValue(DbzDecks.DBZ_DECKS.USER_ID),
                        r.getValue(DbzDecks.DBZ_DECKS.DECK_NAME));
            output.add(deck);
            }
        return output;
    }

}
