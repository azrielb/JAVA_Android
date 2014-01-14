package control;

import BE.Order;
import BE.Order.statuses;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.java5774_04_7842_7588.R;

public class OrderNavigation extends _Activity {

	Order currentOrder;
	Long orderNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_navigation);
		currentOrder = (Order) (getIntent()
				.getSerializableExtra("selectedOrder"));

		OnClickListener buttonsClick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Class<?> destinationActivity = null;
				switch (v.getId()) {
				case R.id.summarySaveButton:
					destinationActivity = OrderSummary.class;
					break;
				case R.id.addComponentButton:
					if (currentOrder.getStatus()
							.compareTo(statuses.ACTION_DONE) < 1)
						destinationActivity = Addcomponent.class;
					else
						Alert.showToast(OrderNavigation.this,
								R.string.order_is_closed);
					break;
				case R.id.componentListButton:
					destinationActivity = ComponentList.class;
					break;
				case R.id.billButton:
					destinationActivity = BillActivity.class;
					break;
				case R.id.workDetailsButton:
					if (currentOrder.getStatus()
							.compareTo(statuses.ACTION_DONE) < 1)
						destinationActivity = WorkingTime.class;
					else
						Alert.showToast(OrderNavigation.this,
								R.string.order_is_closed);
					break;
				case R.id.returnToOrderList:
					destinationActivity = null;
					break;
				}
				if (destinationActivity == null)
					finish();
				else {
					Intent intent = new Intent(OrderNavigation.this,
							destinationActivity);
					intent.putExtra("currentOrder", currentOrder);
					startActivity(intent);
				}
			}
		};
		for (int i : new int[] { R.id.summarySaveButton,
				R.id.addComponentButton, R.id.componentListButton,
				R.id.billButton, R.id.workDetailsButton, R.id.returnToOrderList }) {
			Button button = (Button) findViewById(i);
			button.setOnClickListener(buttonsClick);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
		return true;
	}

}
