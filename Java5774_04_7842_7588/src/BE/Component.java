package BE;

import java.io.Serializable;

import conversions.toGoogleConvertions;

public class Component implements Serializable ,toGoogleConvertions{

	private static final long serialVersionUID = 1L;
	String name;
	String description;
	float cost;
	String serialNumber;
	Long orderId;

	public Component() {
		name = "a";
		description = "a";
		cost = 5;
		serialNumber = "#0000";
		orderId = -1L;
	}

	public Component(String name, float cost, String serialNumber) {
		super();
		this.name = name;
		this.description = "";
		this.cost = cost;
		this.serialNumber = serialNumber;
		orderId = -1L;
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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId= orderId;
	}

}
