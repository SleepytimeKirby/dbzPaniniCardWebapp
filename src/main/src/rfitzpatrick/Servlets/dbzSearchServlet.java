package rfitzpatrick.Servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import rfitzpatrick.dbz.DBO.CardCombined;
import rfitzpatrick.dbz.Utils.CardRetrival;
import rfitzpatrick.utils.DatabaseManager;

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
public class dbzSearchServlet extends HttpServlet {

    public void init() throws ServletException {

    }
    public void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String json = new String();
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        CardRetrival cardRetrival = new CardRetrival(databaseManager.getDSLContext("dbz"));
        ArrayList<CardCombined> cards =  cardRetrival.searchCards(request.getParameter("cardName"));
        Gson gson = new Gson();
        Type listOfCards = new TypeToken<ArrayList<CardCombined>>(){}.getType();
        json = gson.toJson(cards,listOfCards);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
