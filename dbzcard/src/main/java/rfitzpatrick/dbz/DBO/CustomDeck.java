package rfitzpatrick.dbz.DBO;

public class CustomDeck {

	private String masteryCard;
	private String[] mainPersonality;
	private String[] lifeDeck;
	
	public CustomDeck(){
		mainPersonality = new String[4];
		lifeDeck = new String[60];
	}
	public CustomDeck(String masteryCard,String[] mainPersonality, String[] lifeDeck){
		this.masteryCard = masteryCard;
		this.mainPersonality = mainPersonality;
		this.lifeDeck = lifeDeck;
	}

	public void addToLifeDeck(int idx,String lifeDeckCard){
		lifeDeck[idx] = lifeDeckCard;
	}
	
	public void addToMainPersonality(int idx,String mainPersonalityCard){
		mainPersonality[idx] = mainPersonalityCard;
	}
	
	public String getMasteryCard() {
		return masteryCard;
	}

	public void setMasteryCard(String masteryCard) {
		this.masteryCard = masteryCard;
	}

	public String[] getMainPersonality() {
		return mainPersonality;
	}

	public void setMainPersonality(String[] mainPersonality) {
		this.mainPersonality = mainPersonality;
	}

	public String[] getLifeDeck() {
		return lifeDeck;
	}

	public void setLifeDeck(String[] lifeDeck) {
		this.lifeDeck = lifeDeck;
	}
	
}
