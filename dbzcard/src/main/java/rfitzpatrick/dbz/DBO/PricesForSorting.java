package rfitzpatrick.dbz.DBO;

public class PricesForSorting {

	private double price;
	private String cardName;
	private double jaccard_idx;
	private double cosine_similarity;
	private double sortingScore;
	
	public PricesForSorting(double price, String cardName) {
		super();
		this.price = price;
		this.cardName = cardName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getJaccard_idx() {
		return jaccard_idx;
	}
	public void setJaccard_idx(double jaccard_idx) {
		this.jaccard_idx = jaccard_idx;
	}
	public double getCosine_similarity() {
		return cosine_similarity;
	}
	public void setCosine_similarity(double cosine_similarity) {
		this.cosine_similarity = cosine_similarity;
	}
	public PricesForSorting(double price, double jaccard_idx, double cosine_similarity) {
		this.price = price;
		this.jaccard_idx = jaccard_idx;
		this.cosine_similarity = cosine_similarity;
	}
	public PricesForSorting() {
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public double getSortingScore() {
		return sortingScore;
	}
	public void setSortingScore(double sortingScore) {
		this.sortingScore = sortingScore;
	}
	
}
