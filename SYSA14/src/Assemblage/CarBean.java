package Assemblage;

public class CarBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private UserBean owner;
	private String brand;
	private String year;
	private int price;
	private boolean forSale;
	private int id;
	private String description;
	
	public String getOwnerUsername() {
		if(owner != null){
			return owner.getUsername();
		} else {
			return null;
		}
	}
	
	public String getOwnerFirstname() {
		if(owner != null){
			return owner.getFirstName();
		} else {
			return null;
		}
	}
	public String getOwnerLastname() {
		if(owner != null){
			return owner.getLastName();
		} else {
			return null;
		}
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isForSale() {
		return forSale;
	}
	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}
	public UserBean getOwner() {
		return owner;
	}
	public void setOwner(UserBean owner) {
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
