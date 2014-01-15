package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.backend.BackendFactory;
import BE.Bill;
import BE.Component;
import BE.Order;
import BE.Order.statuses;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.java5774_04_7842_7588.R;

public class BillActivity extends _Activity {

	float hours;
	Long orderNumber;
	Order currentOrder;
	Bill bill = null;
	ListView componentList;
	List<Component> components = new ArrayList<Component>();
	List<Integer> counter = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill);

		currentOrder = (Order) (getIntent()
				.getSerializableExtra("currentOrder"));
		componentList = (ListView) findViewById(R.id.billList);
		hours = currentOrder.getHours();
		super.appendText(R.id.workHours, Float.toString(hours));
		Button backButton = (Button) findViewById(R.id.backBillButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		new AsyncTask<Long, Void, Void>() {
			Bill tempBill = null;
			List<Component> tempComponents = null;

			@Override
			protected void onPreExecute() {
				progressDialog = ProgressDialog
						.show(BillActivity.this, "Please wait",
								"Synchronizing with the server...", true);
			}

			@Override
			protected Void doInBackground(Long... params) {
				try {
					tempComponents = BackendFactory.getInstance()
							.getComponentsByOrderNumber(params[1]);
				} catch (Exception e) {
				}
				try {
					tempBill = BackendFactory.getInstance().getBillById(
							params[0]);
				} catch (Exception e) {
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void res) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				if (tempBill == null)
					BillActivity.this.bill = new Bill(orderNumber, 0);
				else
					BillActivity.this.bill = tempBill;
				BillActivity.this.components = tempComponents;
				List<Component> uniqList = uniqComponent();
				TextView total = (TextView) findViewById(R.id.totalP);
				total.setText(total.getText() + "\t"
						+ Float.toString(totalPrice()));

				ListAdapter adapter = new ArrayAdapter<Component>(
						BillActivity.this, R.layout.component_list_view,
						uniqList) {
					@Override
					public View getDropDownView(int position, View convertView,
							ViewGroup parent) {
						return getCustomView(position, convertView, parent);
					}

					@Override
					public View getView(int position, View convertView,
							ViewGroup parent) {
						return getCustomView(position, convertView, parent);
					}

					View getCustomView(int position, View convertView,
							ViewGroup parent) {
						if (convertView == null) {
							convertView = View.inflate(BillActivity.this,
									R.layout.component_list_view, null);
						}
						String amount = (counter.get(position)).toString();
						_Activity.setText(convertView, R.id.componentName,
								components.get(position).getName());
						_Activity.setText(
								convertView,
								R.id.serialNumber,
								amount
										+ "                      "
										+ Float.toString(components.get(
												position).getCost()));
						return convertView;

					}
				};
				componentList.setAdapter(adapter);
			}
		}.execute(currentOrder.getBillId(), currentOrder.getOrderNumber());

		// from here we have to check

		Button nextStepButton = (Button) findViewById(R.id.nextStepButton);
		nextStepButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (currentOrder.getStatus()) {
				case NEW:
				case IN_PROGRESS:
					Alert.showToast(BillActivity.this,
							"This order is still open!");
					break;
				case ACTION_DONE:
					Intent intent = new Intent(BillActivity.this,
							SignatureActivity.class);
					intent.putExtra("currentOrder", currentOrder);
					startActivity(intent);
					return;
					// "return" instead of "break" - for ignoring the "finish"
					// line.
				case SIGNATURED:
					bill.setCost(totalPrice());
					try {
						BackendFactory.getInstance().addBill(bill);
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}
					currentOrder.setStatus(statuses.FINISHED);
					break;
				case FINISHED:
					Alert.showToast(BillActivity.this,
							"This order is closed!\nThank you for the excellent work!");
					break;
				}
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bill, menu);
		return true;
	}

	private float totalPrice() {
		float price = 20 * hours;
		if (components != null)
			for (Component item : components)
				price += item.getCost();
		return price;
	}

	private List<Component> uniqComponent() {
		List<String> result = getComponentsNames(components);
		Set<String> uniqueComponents = new HashSet<String>(result);
		List<Component> items = new ArrayList<Component>();
		for (String str : uniqueComponents)
			for (Component item : components) {
				if (item.getName().equals(str)) {
					items.add(item);
					break;
				}
			}
		int count = 0;
		for (Component item : items) {
			count = Collections.frequency(result, item.getName());
			counter.add(count);
		}
		Collections.reverse(counter);
		return items;
	}

	private ArrayList<String> getComponentsNames(List<Component> list) {
		ArrayList<String> result = new ArrayList<String>();
		if (list != null)
			for (Component item : list)
				result.add(item.getName());
		return result;
	}
}
