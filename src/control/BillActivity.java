package control;

import model.backend.BackendFactory;
import BE.Order;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.java5774_04_7842_7588.R;

public class BillActivity extends Activity {

	int orderNumber;
	Order currentNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill);
		orderNumber =  getIntent().getExtras().getInt("orderNum");
		currentNumber=BackendFactory.getInstance().getOrderByNumber(orderNumber);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bill, menu);
		return true;
	}

}
