package appEngineBE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	Long orderID;
	float cost;
	String SignatureFilePath;

	public Bill(Long orderID, float cost) {
		this.orderID = orderID;
		this.cost = cost;
		this.SignatureFilePath = "";
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getSignatureFilePath() {
		return SignatureFilePath;
	}

	public void setSignatureFilePath(String signatureFilePath) {
		SignatureFilePath = signatureFilePath;
	}
}
