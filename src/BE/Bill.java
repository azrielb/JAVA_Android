package BE;

public class Bill {
	public enum Payment{
		cash, check, creditCard
	}
	Order order;
	float cost;
	String SignatureFilePath;
	Payment payment;
}
