package BE;

import java.io.Serializable;

import conversions.toGoogleConvertions;

public class Technician implements Serializable ,toGoogleConvertions{

	private static final long serialVersionUID = 1L;
	String firstName;
	String LastName;
	String password;
	String eMail;
	Long id;
	String cellphone;

	public Technician(String firstName, String lastName, String password,
			String eMail, Long id) {
		this.firstName = firstName;
		LastName = lastName;
		this.password = password;
		this.eMail = eMail;
		this.id=id;
	}

	public Technician() {
		firstName = "worker";
		LastName = "worker";
		id = 0L;
		cellphone = "";
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

	public String getName() {
		return firstName +" "+LastName;
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

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
}
