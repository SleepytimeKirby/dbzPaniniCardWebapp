package rfitzpatrick.Servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import rfitzpatrick.dbz.DBO.CardCombined;
import rfitzpatrick.dbz.DBO.DeckList;
import rfitzpatrick.dbz.Utils.CardRetrival;
import rfitzpatrick.utils.DatabaseManager;
import rfitzpatrick.dbz.Utils.DeckManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by acwwl_000 on 1/8/2016.
 */
public class dbzDeckServlet extends HttpServlet{

    public void init() throws ServletException{

    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
        throws ServletException, IOException {
        String json = new String();
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        CardRetrival cardRetrival = new CardRetrival(databaseManager.getDSLContext("dbz"));
        ArrayList<CardCombined> cards =  cardRetrival.getDeck(Integer.valueOf(request.getParameter("deckId")));
        Gson gson = new Gson();
        Type listOfCards = new TypeToken<ArrayList<CardCombined>>(){}.getType();
        json = gson.toJson(cards,listOfCards);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        String list = "";
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        DeckManager deckManager = new DeckManager(databaseManager.getDSLContext("dbz"));
        for(DeckList deck:deckManager.listDecks()){
            list = list + deck.getId() + "," + deck.getDeck_name() + ",";
        }
        //remove last comma
        list = list.substring(0,list.length()-1);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(list);
        out.flush();
    }
}
