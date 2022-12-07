package AdminRecipe; // 임시용 , 향후 Main에 merge할때는 팀원이 구현한 DAO로 바꿔 사용하


public class Base {
	private int base_id;
	private String name;
	private String image;
	public int getBase_id() {
		return base_id;
	}
	public void setBase_id(int base_id) {
		this.base_id = base_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
