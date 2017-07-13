package rfitzpatrick.dbz.DBO;

public class UrlsForSorting {

	private String url;
	private String cardName;
	private double jaccard_idx;
	private double cosine_similarity;
	private double sortingScore;
	
	public UrlsForSorting(String url, String cardName) {
		super();
		this.url = url;
		this.cardName = cardName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public UrlsForSorting(String url, double jaccard_idx, double cosine_similarity) {
		this.url = url;
		this.jaccard_idx = jaccard_idx;
		this.cosine_similarity = cosine_similarity;
	}
	public UrlsForSorting() {
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
