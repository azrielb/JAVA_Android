package BE;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Payment {
		cash, check, creditCard
	}

	int orderID;
	float cost;
	String SignatureFilePath;
	Payment payment;
	Bitmap signaturePicture;

	public Bill(int orderID, float cost) {
		this.orderID = orderID;
		this.cost = cost;
		this.SignatureFilePath = "";
		this.payment = Payment.creditCard;
		this.signaturePicture = null;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
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

	public Bitmap getSignaturePicture() {
		return signaturePicture;
	}

	public void setSignaturePicture(Bitmap signaturePicture) {
		this.signaturePicture = signaturePicture;
	}

}
