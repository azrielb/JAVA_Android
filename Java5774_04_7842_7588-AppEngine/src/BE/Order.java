package BE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum statuses {
		NEW, IN_PROGRESS, ACTION_DONE, SIGNATURED, FINISHED,
	};

	@Id
	Long orderNumber;
	String city;
	String addres;
	String customer;
	String customerPhone;
	Date createDate;
	Long start;
	Long finish;
	Long technicianId;
	statuses status;
	String title;
	String detailes;
	String technicianComments;
	Long billId;

	public Order(Long orderNumber, String city, String customer,
			Date createDate, String phone) {
		super();
		this.orderNumber = orderNumber;
		this.city = city;
		this.customer = customer;
		this.createDate = createDate;
		this.customerPhone = phone;
		start = null;
		finish = null;
		technicianId = -1L;
		status = statuses.NEW;
		billId = -1L;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
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

	public Long getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(Long technicianId) {
		this.technicianId = technicianId;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getFinish() {
		return finish;
	}

	public void setFinish(Long finish) {
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
/*
	public ArrayList<Component> getRequiredComponents() {
		return requiredComponents;
	}

	public void setRequiredComponents(ArrayList<Component> requiredComponents) {
		this.requiredComponents = requiredComponents;
	}
*/
	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
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

	public String getFullAddress() {
		return Convertions.Join(new String[] { addres, city }, ", ");
	}
}
