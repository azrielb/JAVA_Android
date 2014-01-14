package control;

import java.util.ArrayList;
import java.util.List;

import model.backend.BackendFactory;
import BE.Order;
import BE.Technician;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import conversions.Convertions;

public class OrderList extends _Activity {

	private Technician technic;
	private List<BE.Order> orders = new ArrayList<BE.Order>();
	private ListView list;

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
			_Activity.setText(convertView, R.id.orderNumber, order
					.getOrderNumber().toString());
			_Activity.setText(convertView, R.id.cityTextView, order.getCity());
			_Activity.setText(convertView, R.id.nameTextView,
					order.getCustomer());
			_Activity.setText(convertView, R.id.dateTextView,
					Convertions.toDateString(order.getCreateDate()));
			return convertView;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list);
		technic = (Technician) getIntent().getSerializableExtra("user");
		list = (ListView) findViewById(R.id.orderListView);

		new AsyncTask<Long, Void, ArrayList<Order>>() {
			@Override
			protected void onPreExecute() {
				progressDialog = ProgressDialog
						.show(OrderList.this, "Please wait",
								"Synchronizing with the server...", true);
			}

			@Override
			protected ArrayList<Order> doInBackground(Long... params) {
				ArrayList<Order> items = null;
				try {
					items = (BackendFactory.getInstance()
							.getOrdersByTechnicianId(params[0]));
				} catch (Exception e) {
				}
				return items;
			}

			@Override
			protected void onPostExecute(ArrayList<Order> res) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				setOrders(res);

			}
		}.execute(technic.getId());

		list.setClickable(true);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					//long orderNumber = orders.get(position).getOrderNumber();
					Intent intent = new Intent(OrderList.this,
							OrderNavigation.class);
					//intent.putExtra("selectedOrder", orderNumber);
					intent.putExtra("selectedOrder", orders.get(position));
					startActivity(intent);
				} catch (Exception ex) {
				}
			}
		});

		Button filterButton = (Button) findViewById(R.id.filterButton);
		filterButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText filter = (EditText) findViewById(R.id.filterText);
				String filterStr = filter.getText().toString().trim();
				new AsyncTask<String, Void, ArrayList<Order>>() {
					@Override
					protected void onPreExecute() {
						progressDialog = ProgressDialog.show(OrderList.this,
								"Please wait",
								"Synchronizing with the server...", true);
					}

					@Override
					protected ArrayList<Order> doInBackground(String... params) {
						ArrayList<Order> items = null;
						try {
							items = (BackendFactory.getInstance()
									.getFilteredOrders(params[0],
											Long.parseLong(params[1])));
						} catch (Exception e) {
						}
						return items;
					}

					@Override
					protected void onPostExecute(ArrayList<Order> res) {
						if (progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
						setOrders(res);

					}
				}.execute(filterStr, technic.getId().toString());

				try {
					setOrders(BackendFactory.getInstance().getFilteredOrders(
							filterStr, technic.getId()));
				} catch (Exception e) {
					// TODO: handle exception
				}

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
		if (orders == null || orders.isEmpty()) {
			Alert.showToast(this,
					"You don't have any orders. You can go to sleep... :)");
		} else {
			ListAdapter adapter = new ordersAdapter(this,
					R.layout.order_list_view, orders);
			list.setAdapter(adapter);
		}
	}
}
