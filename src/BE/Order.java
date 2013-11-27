package BE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum statuses {
		NEW, FINISHED, WAITING_FOR_COMPONENT, IN_PROGRESS
	};

	public enum Urgency {
		low, medium, high
	}

	int orderNumber;
	String addres;
	String customer;
	long customerPhone;
	Date createDate;
	Date start;
	Date finish;
	Technician technician;
	statuses status;
	String title;
	String detailes;
	Urgency urgency;
	String technicianComments;
	ArrayList<Component> requiredComponents;
	Bill bill;

	public Order(int orderNumber, String addres, String customer,
			Date createDate) {
		super();
		this.orderNumber = orderNumber;
		this.addres = addres;
		this.customer = customer;
		this.createDate = createDate;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}
}
