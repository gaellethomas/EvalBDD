package evaluationBDDCRUD;

public class TypeFood {
	Long typeFoodId;
	String wordingTypeFood;

	public TypeFood(String wordingTypeFood) {
		this.typeFoodId = null;
		this.wordingTypeFood = wordingTypeFood;
	}

	public TypeFood(Long typeFoodId) {
		this.typeFoodId = typeFoodId;
	}

	public Long getTypeFoodId() {
		return typeFoodId;
	}

	public void setTypeFoodId(Long typeFoodId) {
		this.typeFoodId = typeFoodId;
	}

	public String getWordingTypeFood() {
		return wordingTypeFood;
	}

	public void setWordingTypeFood(String wordingTypeFood) {
		this.wordingTypeFood = wordingTypeFood;
	}

}
