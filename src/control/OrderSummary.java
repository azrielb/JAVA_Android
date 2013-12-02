package control;

import model.backend.BackendFactory;
import BE.Order;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.java5774_04_7842_7588.R;

public class OrderSummary extends Activity {

	Order currentOrder;
	int orderNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_summary);
		orderNumber = getIntent().getExtras().getInt("currentOrder");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(orderNumber);
		TextView address = (TextView) findViewById(R.id.addressOrder);
		address.setText(address.getText()+": "+ currentOrder.getAddres());
		TextView contact = (TextView) findViewById(R.id.contactOrder);
		contact.setText(contact.getText()+": " + currentOrder.getCustomer());
		TextView phoneNumber = (TextView) findViewById(R.id.phoneNumOrder);
		phoneNumber.setText(phoneNumber.getText()+": " +Long.toString(currentOrder.getCustomerPhone()));
		TextView startDate = (TextView) findViewById(R.id.createOrder);
		startDate.setText(startDate.getText()+": "+ currentOrder.getStart().toGMTString());
		//TextView finishDate = (TextView) findViewById(R.id.finishOrder);
		//finishDate.setText(finishDate.getText()+": "+ currentOrder.getFinish().toGMTString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_summary, menu);
		return true;
	}

}
 