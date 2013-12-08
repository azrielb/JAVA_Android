package BE;

public class Bill {

	public enum Payment {
		cash, check, creditCard
	}

	int orderID;
	float cost;
	String SignatureFilePath;
	Payment payment;

	public Bill(int orderID, float cost) {
		super();
		this.orderID = orderID;
		this.cost = cost;
		this.SignatureFilePath = "";
		this.payment = Payment.creditCard;
	}
}
