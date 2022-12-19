package AdminRecipe; // 임시용 , 향후 Main에 merge할때는 팀원이 구현한 DAO로 바꿔 사용하


public class Ingredient {
	private int ingredient_id;
	private String image;
	private String name;
	public int getIngredient_id() {
		return ingredient_id;
	}
	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}