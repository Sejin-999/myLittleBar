package Drink;

public class Drinks {
	private int drink_id;
	private int base_id;
	private String image;
	private String name;
	private String base_image;
	private String base_name;
	public String getBase_image() {
		return base_image;
	}
	public void setBase_image(String base_image) {
		this.base_image = base_image;
	}
	public String getBase_name() {
		return base_name;
	}
	public void setBase_name(String base_name) {
		this.base_name = base_name;
	}
	public int getDrink_id() {
		return drink_id;
	}
	public void setDrink_id(int drink_id) {
		this.drink_id = drink_id;
	}
	public int getBase_id() {
		return base_id;
	}
	public void setBase_id(int base_id) {
		this.base_id = base_id;
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
