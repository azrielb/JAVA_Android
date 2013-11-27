package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BE.Order;
import BE.Technician;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.java5774_04_7842_7588.R;

public class OrderList extends Activity {

	Order choosenOrder;
	Technician user;
	List<BE.Order> orders = new ArrayList<BE.Order>();
	ListView list;

	BE.Order t1 = new BE.Order(1, "Tel Aviv", "baruch", new Date());
	BE.Order t2 = new BE.Order(2, "Jerusalem", "shmuel", new Date());
	BE.Order t3 = new BE.Order(3, "Haifa", "ohad", new Date());
	BE.Order t4 = new BE.Order(4, "Nechalim", "noam", new Date());
	BE.Order t5 = new BE.Order(5, "Haifa", "kuku", new Date());
	BE.Order t6 = new BE.Order(6, "Herzelia", "bobo", new Date());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list);
		// user = (Technician) getIntent().getSerializableExtra("currentUser");
		list = (ListView) findViewById(R.id.orderListView);
		orders.add(t1);
		orders.add(t2);
		orders.add(t3);
		orders.add(t4);
		orders.add(t5);
		orders.add(t6);

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
						.findViewById(R.id.idTextView);

				TextView orderAddressTextView = (TextView) convertView
						.findViewById(R.id.addressTextView);

				TextView orderNameTextView = (TextView) convertView
						.findViewById(R.id.nameTextView);

				TextView orderDateTextView = (TextView) convertView
						.findViewById(R.id.dateTextView);

				orderIdTextView.setText(((Integer) orders.get(position)
						.getOrderNumber()).toString());

				orderAddressTextView.setText(orders.get(position).getAddres());

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
					Intent intent = new Intent(OrderList.this,
							OrderSummary.class);
					intent.putExtra("selectedOrder", choosenOrder);
					startActivity(intent);
				} catch (Exception ex) {
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
}
