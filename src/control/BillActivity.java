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

	float hours = 0;
	int orderNumber;
	Order currentOrder;
	Bill bill;
	ListView componentList;
	List<Component> components = new ArrayList<Component>();
	List<Integer> counter = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill);
		orderNumber = getIntent().getExtras().getInt("orderNumber");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(
				orderNumber);
		componentList = (ListView) findViewById(R.id.billList);
		components = currentOrder.getRequiredComponents();
		TextView total = (TextView) findViewById(R.id.totalP);
		List<Component> uniqList = uniqComponent();

		hours = currentOrder.getHours();
		super.appendText(R.id.WorkingHours, Float.toString(hours));

		total.setText(total.getText() + "\t" + Float.toString(totalPrice()));

		ListAdapter adapter = new ArrayAdapter<Component>(this,
				R.layout.component_list_view, uniqList) {
			@Override
			public View getDropDownView(int position, View convertView,
					ViewGroup parent) {
				return getCustomView(position, convertView, parent);
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				return getCustomView(position, convertView, parent);
			}

			View getCustomView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = View.inflate(BillActivity.this,
							R.layout.component_list_view, null);
				}
				String name = components.get(position).getName();
				String amount = (counter.get(position)).toString();
				_Activity.setText(convertView, R.id.componentName, name);
				_Activity.setText(
						convertView,
						R.id.serialNumber,
						amount
								+ "                      "
								+ Float.toString(components.get(position)
										.getCost()));
				return convertView;

			}
		};

		componentList.setAdapter(adapter);
		OnClickListener finish = new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		};
		Button cancelButton = (Button) findViewById(R.id.cancelBillButton);
		cancelButton.setOnClickListener(finish);
		Button saveButton = (Button) findViewById(R.id.saveBillButton);
		if (currentOrder.getFinish() == null) {
			Alert.show(this, "Read only",
					"The order was not completed, you can't finish it.");
			saveButton.setOnClickListener(finish);
		} else if (currentOrder.getStatus() == statuses.FINISHED) {
			Alert.show(this, "Read only",
					"The order was finished!");
			saveButton.setOnClickListener(finish);
		} else {
			saveButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					bill = new Bill(orderNumber, totalPrice());
					BackendFactory.getInstance().addBill(bill);
					currentOrder.setStatus(statuses.FINISHED);
					finish();
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bill, menu);
		return true;
	}

	private float totalPrice() {
		float price = 20 * hours;
		for (Component item : components)
			price += item.getCost();
		return price;
	}

	private List<Component> uniqComponent() {
		List<String> result = getComponentsNames((ArrayList<Component>) components);
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

	private ArrayList<String> getComponentsNames(ArrayList<Component> list) {
		ArrayList<String> result = new ArrayList<String>();
		for (Component item : list)
			result.add(item.getName());
		return result;
	}
}
