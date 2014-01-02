package BE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Technician implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id	Long id;
	String firstName;
	String lastName;
	String password;
	String eMail;
	int cellphone;
	boolean busy;

	public Technician(String firstName, String lastName, String password,
			String eMail, Long id, int cellphone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.eMail = eMail;
		this.id = id;
		this.busy = false;
		this.cellphone = cellphone;
	}

	public Technician() {
		this.firstName = "worker";
		this.lastName = "worker";
		this.id = 0L;
		this.cellphone = 0501234567;
		this.busy = false;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCellphone() {
		return cellphone;
	}

	public void setCellphone(int cellphone) {
		this.cellphone = cellphone;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}
}
