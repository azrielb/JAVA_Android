package BE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.datanucleus.annotations.Unowned;
@Entity
public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Payment {
		cash, check, creditCard
	}

	@Id Long orderID;
	float cost;
	String SignatureFilePath;
	@Unowned Payment payment;

	public Bill(Long orderID, float cost) {
		this.orderID = orderID;
		this.cost = cost;
		this.SignatureFilePath = "";
		this.payment = Payment.creditCard;
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

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}


}
