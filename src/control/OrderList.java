package control;

import java.util.ArrayList;
import java.util.List;

import model.backend.BackendFactory;
import BE.Convertions;
import BE.Order;
import BE.Technician;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.java5774_04_7842_7588.R;

public class OrderList extends _Activity {
	protected class ordersAdapter extends ArrayAdapter<BE.Order> {
		public ordersAdapter(OrderList orderList, int orderListView,
				List<Order> orders) {
			super(orderList, orderListView, orders);
		}

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
				convertView = View.inflate(OrderList.this,
						R.layout.order_list_view, null);
			}
			Order order = orders.get(position);
			_Activity.setText(convertView, R.id.filterTextView,
					((Integer) order.getOrderNumber()).toString());
			_Activity.setText(convertView, R.id.addressTextView,
					order.getCity());
			_Activity.setText(convertView, R.id.nameTextView,
					order.getCustomer());
			_Activity.setText(convertView, R.id.dateTextView,
					Convertions.dateInstance.format(order.getCreateDate()));
			return convertView;
		}

	};

	private Technician technic;
	private List<BE.Order> orders = new ArrayList<BE.Order>();
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list);

		technic = (Technician) getIntent().getSerializableExtra("user");
		list = (ListView) findViewById(R.id.orderListView);
		setOrders(BackendFactory.getInstance().getOrdersByTechnicianId(
				technic.getId()));

		list.setClickable(true);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					int orderNumber = orders.get(position).getOrderNumber();
					Intent intent = new Intent(OrderList.this,
							OrderNavigation.class);
					intent.putExtra("selectedOrder", orderNumber);
					startActivity(intent);
				} catch (Exception ex) {
				}
			}
		});

		Button filterButton = (Button) findViewById(R.id.saveButton);
		filterButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText filter = (EditText) findViewById(R.id.filterOrders);
				String city = filter.getText().toString().trim();
				setOrders(BackendFactory.getInstance().getOrdersByCity(city,
						technic.getId()));

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_list, menu);
		return true;
	}

	protected void setOrders(ArrayList<Order> Orders) {
		list.setAdapter(null);
		orders = Orders;
		if (orders != null && !orders.isEmpty()) {
			ListAdapter adapter = new ordersAdapter(this,
					R.layout.order_list_view, orders);
			list.setAdapter(adapter);
		} else {
			Alert.show(this, "No items",
					"You don't have any orders. You can go sleep... :)");
		}
	}
}
