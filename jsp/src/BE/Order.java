package BE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.datanucleus.annotations.Unowned;

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
	Long createDate;
	Long start;
	Long finish;
	int technicianID;
	statuses status;
	String detailes;
	String technicianComments;
	@Unowned
	ArrayList<Component> requiredComponents;
	int billID;

	public Order(Long orderNumber, String city, String customer,
			Date createDate, String phone) {
		this.orderNumber = orderNumber;
		this.city = city;
		this.customer = customer;
		this.createDate = createDate.getTime();
		this.customerPhone = phone;
		start = null;
		finish = null;
		technicianID = -1;
		status = statuses.NEW;
		requiredComponents = new ArrayList<Component>();
		billID = -1;
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

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate.getTime();
	}

	public int getTechnicianID() {
		return technicianID;
	}

	public void setTechnicianID(int _technicianID) {
		technicianID = _technicianID;
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

	public int getBill() {
		return billID;
	}

	public void setBill(int _bill) {
		billID = _bill;
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
		Long miliSecDiff = finish - start;
		return (float) (TimeUnit.MILLISECONDS.toMinutes(miliSecDiff)) / 60;
	}
}
