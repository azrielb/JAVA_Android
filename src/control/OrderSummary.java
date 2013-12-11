package control;

import model.backend.BackendFactory;
import BE.Convertions;
import BE.Order;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.java5774_04_7842_7588.R;

public class OrderSummary extends _Activity {

	Order currentOrder;
	int orderNumber;
	TextView orderDescruption;
	TextView phoneNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_summary);
		orderNumber = getIntent().getExtras().getInt("orderNumber");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(
				orderNumber);
		orderDescruption = (EditText) findViewById(R.id.orderDescription);
		Button save = (Button) findViewById(R.id.summarySaveButton);
		super.setText(R.id.orderDescription,
				currentOrder.getTechnicianComments());
		super.appendText(R.id.firstNameLabel,
				": " + currentOrder.getFullAdress());
		super.appendText(R.id.contactOrder, ": " + currentOrder.getCustomer());
		phoneNumber = (TextView) findViewById(R.id.phoneNumOrder);
		super.setText(R.id.phoneNumOrder, (currentOrder.getCustomerPhone()));
		super.appendText(R.id.createOrder, (": " + Convertions.dateInstance
				.format(currentOrder.getCreateDate())));
		if (currentOrder.getFinish() != null)
			super.appendText(R.id.finishOrder, (": " + Convertions.dateInstance
					.format(currentOrder.getFinish().getTime())));
		super.appendText(R.id.assignedToOrder, ": "
				+ currentOrder.getTechnician().getName());
		super.appendText(R.id.statusOrder, ": "
				+ currentOrder.getStatus().toString());
		super.appendText(R.id.idOrder,
				": " + ((Integer) currentOrder.getOrderNumber()).toString());

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				currentOrder.setTechnicianComments(orderDescruption.getText()
						.toString());
				finish();
			}
		});
		phoneNumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = currentOrder.getCustomerPhone();
			    Intent intent = new Intent(Intent.ACTION_CALL);
			    intent.setData(Uri.parse("tel:" +number));
			    startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_summary, menu);
		return true;
	}

}
