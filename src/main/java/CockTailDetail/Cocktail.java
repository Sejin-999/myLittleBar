package CockTailDetail;

public class Cocktail {
	private String cName; //c == cocktail //drink
	private String cImage;  
	
	private String [] iNameBuf ;  // i == inger..
	private String [] iImageBuf;  
	
	private String bName; //b == base
	private String bImage;  
	
	
	
	
	public String[] getiNameBuf() {
		return iNameBuf;
	}
	public void setiNameBuf(String[] iNameBuf) {
		this.iNameBuf = iNameBuf;
	}
	public String[] getiImageBuf() {
		return iImageBuf;
	}
	public void setiImageBuf(String[] iImageBuf) {
		this.iImageBuf = iImageBuf;
	}
	
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	
	public String getbImage() {
		return bImage;
	}
	public void setbImage(String bImage) {
		this.bImage = bImage;
	}

	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcImage() {
		return cImage;
	}
	public void setcImage(String cImage) {
		this.cImage = cImage;
	}
	
	
	
}
