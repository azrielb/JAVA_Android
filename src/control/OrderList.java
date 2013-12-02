package control;

import java.util.ArrayList;
import java.util.List;

import model.backend.BackendFactory;
import BE.Order;
import BE.Technician;
import android.app.Activity;
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
import android.widget.TextView;

import com.example.java5774_04_7842_7588.R;

public class OrderList extends Activity {

	Order choosenOrder;
	Technician technic;
	List<BE.Order> orders = new ArrayList<BE.Order>();
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list);
		technic = (Technician) getIntent().getSerializableExtra("user");
		list = (ListView) findViewById(R.id.orderListView);
		orders = BackendFactory.getInstance().getOrdersByTechnicianId(technic.getId());

		ListAdapter adapter = new ArrayAdapter<BE.Order>(this,
				R.layout.order_list_view, orders) {
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

				TextView orderIdTextView = (TextView) convertView
						.findViewById(R.id.filterTextView);

				TextView orderAddressTextView = (TextView) convertView
						.findViewById(R.id.addressTextView);

				TextView orderNameTextView = (TextView) convertView
						.findViewById(R.id.nameTextView);

				TextView orderDateTextView = (TextView) convertView
						.findViewById(R.id.dateTextView);

				orderIdTextView.setText(((Integer) orders.get(position)
						.getOrderNumber()).toString());

				orderAddressTextView.setText(orders.get(position).getCity());

				orderNameTextView.setText(orders.get(position).getCustomer());

				orderDateTextView.setText(orders.get(position).getCreateDate()
						.toGMTString());

				return convertView;
			}

		};
		list.setAdapter(adapter);

		list.setClickable(true);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					choosenOrder = orders.get(position);
					int orderNumber= choosenOrder.getOrderNumber();
					Intent intent = new Intent(OrderList.this,
							OrderNavigation.class);
					intent.putExtra("selectedOrder", orderNumber);
					startActivity(intent);
				} catch (Exception ex) {
					//int x = 5;
					return;
				}

			}
		});
		
		Button filterButton = (Button) findViewById(R.id.billButton);
		 filterButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText filter= (EditText) findViewById(R.id.filterOrders);
				String city= filter.getText().toString().trim();
				orders= BackendFactory.getInstance().getOrdersByCity(city, technic.getId());
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_list, menu);
		return true;
	}
}