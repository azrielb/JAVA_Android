package control;

import model.backend.BackendFactory;
import BE.Order;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.java5774_04_7842_7588.R;

public class OrderNavigation extends Activity {

	Order currentOrder;
	int orderNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_navigation);
		orderNumber = getIntent().getExtras().getInt("selectedOrder");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(
				orderNumber);
		Button summaryButton = (Button) findViewById(R.id.summaryButton);
		Button componetAddButton = (Button) findViewById(R.id.addComponentButton);
		Button componetsList = (Button) findViewById(R.id.componentListButton);
		Button billButton = (Button) findViewById(R.id.billButton);

		summaryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OrderNavigation.this,
						OrderSummary.class);
				intent.putExtra("currentOrder", orderNumber);
				startActivity(intent);
			}
		});

		componetAddButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OrderNavigation.this,
						Addcomponent.class);
				startActivity(intent);
			}
		});

		componetsList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OrderNavigation.this,
						ComponentList.class);
				intent.putExtra("number", orderNumber);
				startActivity(intent);
			}
		});
		
		billButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OrderNavigation.this,
						BillActivity.class);
				intent.putExtra("orderNum", orderNumber);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
		return true;
	}

}
