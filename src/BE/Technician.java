package BE;

import java.io.Serializable;

public class Technician implements Serializable {

	private static final long serialVersionUID = 1L;
	String firstName;
	String LastName;
	String password;
	String eMail;
	int id;
	int cellphone;
	int workerNumber;
	boolean busy;

	public Technician(String firstName, String lastName, String password,
			String eMail, int id) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		this.password = password;
		this.eMail = eMail;
		this.id = id;
		this.busy = false;
	}

	public Technician() {
		firstName = "worker";
		LastName = "worker";
		id = 0000000;
		cellphone = 0501234567;
		workerNumber = 0000;
		busy = false;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCellphone() {
		return cellphone;
	}

	public void setCellphone(int cellphone) {
		this.cellphone = cellphone;
	}

	public int getWorkerNumber() {
		return workerNumber;
	}

	public void setWorkerNumber(int workerNumber) {
		this.workerNumber = workerNumber;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

}
