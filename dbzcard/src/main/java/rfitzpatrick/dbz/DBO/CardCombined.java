package rfitzpatrick.dbz.DBO;

/**
 * Created by acwwl_000 on 1/7/2016.
 */
public class CardCombined {

    private  int id;
    private  String name;
    private  String set;
    private  String cardLevel;
    private  String cardNum;
    private  String style;
    private  String rarity;
    private  String type;
    private  double arkPrice;
    private  String arkUrl;
    private  double dbzoPrice;
    private  String dbzoUrl;
    public CardCombined(int id, String name,String set, String cardLevel,
                   String cardNum, String style,String rarity, String type,double arkPrice, String arkUrl,double dbzoPrice, String dbzoUrl) {
        this.id = id;
        this.name = name;
        this.set = set;
        this.cardLevel = cardLevel;
        this.cardNum = cardNum;
        this.style = style;
        this.rarity = rarity;
        this.type = type;
        this.arkPrice = arkPrice;
        this.arkUrl = arkUrl;
        this.dbzoPrice = dbzoPrice;
        this.dbzoUrl = dbzoUrl;

    }
    public CardCombined(){}
    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  String getSet() {
        return set;
    }

    public  void setSet(String set) {
        this.set = set;
    }

    public  String getCardLevel() {
        return cardLevel;
    }

    public  void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel;
    }

    public  String getCardNum() {
        return cardNum;
    }

    public  void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public  String getStyle() {
        return style;
    }

    public  void setStyle(String style) {
        this.style = style;
    }

    public  String getRarity() {
        return rarity;
    }

    public  void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public  String getType() {
        return type;
    }

    public  void setType(String type) {
        this.type = type;
    }

    public  double getArkPrice() {
        return arkPrice;
    }

    public  void setArkPrice(double arkPrice) {
        this.arkPrice = arkPrice;
    }

    public  String getArkUrl() {
        return arkUrl;
    }

    public  void setArkUrl(String arkUrl) {
        this.arkUrl = arkUrl;
    }

    public  double getDbzoPrice() {
        return dbzoPrice;
    }

    public  void setDbzoPrice(double dbzoPrice) {
        this.dbzoPrice = dbzoPrice;
    }

    public  String getDbzoUrl() {
        return dbzoUrl;
    }

    public  void setDbzoUrl(String dbzoUrl) {
        this.dbzoUrl = dbzoUrl;
    }
}
