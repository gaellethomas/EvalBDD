package evaluationBDDCRUD;

import java.util.Scanner;

//class describe food properties
public class Food {
	private Long idFood;
	private String nameFood;
	private Long typeFoodId;
	private float energeticValue;
	private float proteinLevel;
	private float glucidLevel;
	private float lipidLevel;

	public Food(String nameFood, Long typeFoodId, float energeticValue, float proteinLevel, float glucidLevel,
			float lipidLevel) {
		this.idFood = null;
		this.nameFood = nameFood;
		this.typeFoodId = typeFoodId;
		this.energeticValue = energeticValue;
		this.proteinLevel = proteinLevel;
		this.glucidLevel = glucidLevel;
		this.lipidLevel = lipidLevel;
	}

	public Food(Long idFood, String nameFood, Long typeFoodId, float energeticValue, float proteinLevel,
			float glucidLevel, float lipidLevel) {
		this.idFood = idFood;
		this.nameFood = nameFood;
		this.typeFoodId = typeFoodId;
		this.energeticValue = energeticValue;
		this.proteinLevel = proteinLevel;
		this.glucidLevel = glucidLevel;
		this.lipidLevel = lipidLevel;
	}

	public Food() {

	}

	public Food(Long idFood) {
		this.idFood = idFood;
	}

	public Long getIdFood() {
		return idFood;
	}

	public void setIdFood(Long idFood) {
		this.idFood = idFood;
	}

	public String getNameFood() {
		return nameFood;
	}

	public void setNameFood(String nameFood) {
		this.nameFood = nameFood;
	}

	public Long getTypeFoodId() {
		return typeFoodId;
	}

	public void setTypeFoodId(Long typeFoodId) {
		this.typeFoodId = typeFoodId;
	}

	public float getEnergeticValue() {
		return energeticValue;
	}

	public void setEnergeticValue(float energeticValue) {
		this.energeticValue = energeticValue;
	}

	public float getProteinLevel() {
		return proteinLevel;
	}

	public void setProteinLevel(float proteinLevel) {
		this.proteinLevel = proteinLevel;
	}

	public float getGlucidLevel() {
		return glucidLevel;
	}

	public void setGlucidLevel(float glucidLevel) {
		this.glucidLevel = glucidLevel;
	}

	public float getLipidLevel() {
		return lipidLevel;
	}

	public void setLipidLevel(float lipidLevel) {
		this.lipidLevel = lipidLevel;
	}

	public Food defineFood() {
		Scanner sc = new Scanner(System.in);
		String nameFood;
		System.out.println("Veuillez saisir le nom de votre aliment à ajouter.");
		nameFood = sc.nextLine();

		Long typeFoodId;
		System.out.println("Veuillez saisir son code catégorie.");
		typeFoodId = sc.nextLong();

		float energeticValue;
		System.out.println("Veuillez saisir sa valeur énergetique en kcal pour 100g.");
		energeticValue = sc.nextFloat();

		float proteinLevel;
		System.out.println("Veuillez saisir son taux de protéine en g/100g.");
		proteinLevel = sc.nextFloat();

		float glucidLevel;
		System.out.println("Veuillez saisir son taux de glucide en g/100g.");
		glucidLevel = sc.nextFloat();

		float lipidLevel;
		System.out.println("Veuillez saisir son taux de lipides en g/100g.");
		lipidLevel = sc.nextFloat();

		Food newFood = new Food(nameFood, typeFoodId, energeticValue, proteinLevel, glucidLevel, lipidLevel);
		sc.close();
		return newFood;
	}

	@Override
	public String toString() {
		return "Food [idFood=" + idFood + ", nameFood=" + nameFood + ", typeFoodId=" + typeFoodId + ", energeticValue="
				+ energeticValue + ", proteinLevel=" + proteinLevel + ", glucidLevel=" + glucidLevel + ", lipidLevel="
				+ lipidLevel + "]";
	}

}
