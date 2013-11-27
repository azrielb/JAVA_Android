package control;

import BE.Order;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.java5774_04_7842_7588.R;

public class OrderSummary extends Activity {

	Order currentOrder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_summary);
		currentOrder = (Order)  getIntent().getSerializableExtra("selectedOrder");
		TextView address = (TextView) findViewById(R.id.addressOrder);
		address.setText(address.getText()+": "+ currentOrder.getAddres());
		TextView contact = (TextView) findViewById(R.id.contactOrder);
		contact.setText(currentOrder.getCustomer());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_summary, menu);
		return true;
	}

}
