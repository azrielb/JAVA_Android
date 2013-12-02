package BE;

public class Component {

	String name;
	String description;
	float cost;
	String serialNumber;
	boolean exist;

	public Component() {
		name = "a";
		description = "a";
		cost = 5;
		serialNumber = "#0000";
		exist = true;
	}

	public Component(String name, float cost, String serialNumber) {
		super();
		this.name = name;
		this.description = "";
		this.cost = cost;
		this.serialNumber = serialNumber;
		this.exist = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

}
