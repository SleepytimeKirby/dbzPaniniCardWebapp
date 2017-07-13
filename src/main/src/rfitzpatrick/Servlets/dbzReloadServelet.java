package rfitzpatrick.Servlets;

import rfitzpatrick.dbz.Extractors.OCTGNSetExtractor;
import rfitzpatrick.utils.DatabaseManager;
import rfitzpatrick.utils.DeckImporter;
import rfitzpatrick.dbz.Utils.PriceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by acwwl_000 on 1/8/2016.
 */
public class dbzReloadServelet extends HttpServlet{

    public void init() throws ServletException {

    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        OCTGNSetExtractor extractor = new OCTGNSetExtractor();
        extractor.readXml(); //DeckList
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        PriceManager testing = new PriceManager(databaseManager.getDSLContext("dbz"));
        testing.refreshAllEntries(); // Price Points
        DeckImporter deckImporter = new DeckImporter();
        deckImporter.readDecksFile();

    }




}
