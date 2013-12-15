package BE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum statuses {
		NEW, IN_PROGRESS, ACTION_DONE, SIGNATURED, FINISHED,
	};

	int orderNumber;
	String city;
	String addres;
	String customer;
	String customerPhone;
	Date createDate;
	Calendar start;
	Calendar finish;
	Technician technician;
	statuses status;
	String title;
	String detailes;
	String technicianComments;
	ArrayList<Component> requiredComponents;
	Bill bill;

	public Order(int orderNumber, String city, String customer,
			Date createDate, String phone) {
		super();
		this.orderNumber = orderNumber;
		this.city = city;
		this.customer = customer;
		this.createDate = createDate;
		this.customerPhone = phone;
		start = null;
		finish = null;
		technician = new Technician();
		status = statuses.NEW;
		requiredComponents = new ArrayList<Component>();
		bill = new Bill(orderNumber, 0);
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

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getFinish() {
		return finish;
	}

	public void setFinish(Calendar finish) {
		this.finish = finish;
	}

	public statuses getStatus() {
		return status;
	}

	public void setStatus(statuses status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetailes() {
		return detailes;
	}

	public void setDetailes(String detailes) {
		this.detailes = detailes;
	}

	public String getTechnicianComments() {
		return technicianComments;
	}

	public void setTechnicianComments(String technicianComments) {
		this.technicianComments = technicianComments;
	}

	public ArrayList<Component> getRequiredComponents() {
		return requiredComponents;
	}

	public void setRequiredComponents(ArrayList<Component> requiredComponents) {
		this.requiredComponents = requiredComponents;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void addComponent(Component item) {
		item.setExist(false);
		requiredComponents.add(item);
	}

	public String getFullAddress() {
		return Convertions.Join(new String[] { addres, city }, ", ");
	}

	public float getHours() {
		if (finish == null || start == null)
			return 0;
		Long miliSecDiff = finish.getTimeInMillis() - start.getTimeInMillis();
		return (float) (TimeUnit.MILLISECONDS.toMinutes(miliSecDiff)) / 60;
	}
}
