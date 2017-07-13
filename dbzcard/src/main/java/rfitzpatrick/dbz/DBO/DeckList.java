package rfitzpatrick.dbz.DBO;

/**
 * Created by acwwl_000 on 1/7/2016.
 */
public class DeckList {

    private  int  id;
    private  String user_id;
    private  String deck_name;


    public DeckList() {
    }
    public DeckList(int id, String user_id, String deck_name) {
        this.id = id;
        this.user_id = user_id;
        this.deck_name = deck_name;

    }
    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  String getUser_id() {
        return user_id;
    }

    public  void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public  String getDeck_name() {
        return deck_name;
    }

    public  void setDeck_name(String deck_name) {
        this.deck_name = deck_name;
    }

}
