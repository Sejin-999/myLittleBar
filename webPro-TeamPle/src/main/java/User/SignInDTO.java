package User;

public class SignInDTO {
	private int user_id;
	private boolean is_admin;
	
	public SignInDTO(int user_id, boolean is_admin) {
		this.user_id = user_id;
		this.is_admin = is_admin;
	}
	public boolean getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}