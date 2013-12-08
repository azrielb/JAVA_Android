package control;

import model.backend.BackendFactory;
import BE.Convertions;
import BE.Order;
import android.os.Bundle;
import android.view.Menu;

import com.example.java5774_04_7842_7588.R;

public class OrderSummary extends _Activity {

	Order currentOrder;
	int orderNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_summary);
		orderNumber = getIntent().getExtras().getInt("orderNumber");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(
				orderNumber);
		super.appendText(R.id.addressOrder, ": " + currentOrder.getFullAdress());
		super.appendText(R.id.contactOrder, ": " + currentOrder.getCustomer());
		super.appendText(R.id.phoneNumOrder,
				": " + Long.toString(currentOrder.getCustomerPhone()));
		super.appendText(R.id.createOrder, ": "
				+ Convertions.dateInstance.format(currentOrder.getStart()));
		super.appendText(R.id.finishOrder, ": "
				+ Convertions.dateInstance.format(currentOrder.getFinish()));
		super.appendText(R.id.assignedToOrder, ": "
				+ currentOrder.getTechnician().getName());
		super.appendText(R.id.statusOrder, ": "
				+ currentOrder.getStatus().toString());
		super.appendText(R.id.idOrder,
				": " + ((Integer) currentOrder.getOrderNumber()).toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_summary, menu);
		return true;
	}

}
