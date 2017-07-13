package rfitzpatrick.dbz.Utils;

import com.google.gson.*;
import rfitzpatrick.dbz.DBO.CustomDeck;

import java.lang.reflect.Type;

/**
 * Created by acwwl_000 on 1/8/2016.
 */
public class CustomDeckDeserializer implements JsonDeserializer<CustomDeck> {

    @Override
    public CustomDeck deserialize(JsonElement json, Type type,
                                  JsonDeserializationContext context) throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();

        final JsonElement jsonTitle = jsonObject.get("masteryCard");
        final String masteryCard = jsonTitle.getAsString();



        final JsonArray jsonMainPersonalityArray = jsonObject.get("mainPersonality").getAsJsonArray();
        final String[] mainPersonality = new String[jsonMainPersonalityArray.size()];
        for (int i = 0; i < mainPersonality.length; i++) {
            final JsonElement jsonMainPersonality = jsonMainPersonalityArray.get(i);
            mainPersonality[i] = jsonMainPersonality.getAsString();
        }
        final JsonArray jsonLifeDeckArray = jsonObject.get("lifeDeck").getAsJsonArray();
        final String[] lifeDeck = new String[jsonLifeDeckArray.size()];
        for (int i = 0; i < lifeDeck.length; i++) {
            final JsonElement jsonLifeDeck = jsonLifeDeckArray.get(i);
            lifeDeck[i] = jsonLifeDeck.getAsString();
        }

        final CustomDeck customDeck = new CustomDeck();
        customDeck.setMasteryCard(masteryCard);
        customDeck.setMainPersonality(mainPersonality);
        customDeck.setLifeDeck(lifeDeck);
        return customDeck;
    }
}
