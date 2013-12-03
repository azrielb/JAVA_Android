package control;

import java.util.ArrayList;
import java.util.List;

import model.backend.BackendFactory;
import BE.Component;
import BE.Order;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.java5774_04_7842_7588.R;

public class ComponentList extends _Activity {

	Order currentOrder;
	ListView componentList;
	List<Component> components = new ArrayList<Component>();
	int orderNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_component_list);
		orderNumber = getIntent().getExtras().getInt("number");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(
				orderNumber);
		componentList = (ListView) findViewById(R.id.componentListView);
		components = currentOrder.getRequiredComponents();
		ListAdapter adapter = new ArrayAdapter<Component>(this,
				R.layout.component_list_view, components) {
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
					convertView = View.inflate(ComponentList.this,
							R.layout.component_list_view, null);
				}
				_Activity.setText(convertView, R.id.componentName, components
						.get(position).getName());
				_Activity.setText(convertView, R.id.serialNumber, components
						.get(position).getSerialNumber());
				return convertView;
			}
		};
		componentList.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.component_list, menu);
		return true;
	}

}
