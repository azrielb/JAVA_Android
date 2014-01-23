package control;

import model.backend.BackendFactory;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidBE.Order.statuses;

import com.example.java5774_04_7842_7588.R;

import conversions.Convertions;

public class OrderSummary extends _Activity {

	TextView orderDescription;
	TextView phoneNumber;
	String techName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_summary);
		// currentOrder = (Order) (getIntent()
		// .getSerializableExtra("currentOrder"));

		new AsyncTask<Long, Void, String>() {
			@Override
			protected void onPreExecute() {
				progressDialog = ProgressDialog
						.show(OrderSummary.this, "Please wait",
								"Synchronizing with the server...", true);
			}

			@Override
			protected String doInBackground(Long... params) {
				try {
					return (BackendFactory.getInstance().getUserById(params[0]))
							.getName();
				} catch (Exception e) {
					return null;
				}
			}

			@Override
			protected void onPostExecute(String res) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				OrderSummary.this.techName = res;
				OrderSummary.super.appendText(R.id.assignedToOrder, ": "
						+ techName);

			}
		}.execute(currentOrder.getTechnicianId());

		orderDescription = (EditText) findViewById(R.id.orderDescription);
		Button save = (Button) findViewById(R.id.summarySaveButton);
		super.setText(R.id.orderDescription,
				currentOrder.getTechnicianComments());
		super.appendText(R.id.firstNameLabel,
				": " + currentOrder.getFullAddress());
		super.appendText(R.id.contactOrder, ": " + currentOrder.getCustomer());
		phoneNumber = (TextView) findViewById(R.id.phoneNumOrder);
		super.setText(R.id.phoneNumOrder, (currentOrder.getCustomerPhone()));
		super.appendText(R.id.createOrder,
				(": " + Convertions.toDateString(currentOrder.getCreateDate())));
		Long finish = currentOrder.getFinish();
		if (finish != null && finish > 0)
			super.appendText(R.id.finishOrder,
					(": " + Convertions.toDateString(finish)));
		super.appendText(R.id.statusOrder, ": "
				+ currentOrder.getStatus().toString());
		super.appendText(R.id.idOrder, ": "
				+ currentOrder.getOrderNumber().toString());
		if (currentOrder.getStatus().compareTo(statuses.SIGNATURED) < 0) {
			save.setVisibility(View.VISIBLE);
			save.setClickable(true);
			orderDescription.setFocusable(true);
			save.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					currentOrder.setTechnicianComments(orderDescription
							.getText().toString());
					if (currentOrder.getStatus() == statuses.NEW)
						currentOrder.setStatus(statuses.IN_PROGRESS);
					new updateEntities(null, null, null).execute(currentOrder);
				}
			});
		} else {
			save.setClickable(false);
			orderDescription.setFocusable(false);
			save.setVisibility(View.INVISIBLE);
		}
		phoneNumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = currentOrder.getCustomerPhone();
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + number));
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
