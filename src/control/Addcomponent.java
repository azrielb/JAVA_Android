package control;

import java.util.ArrayList;

import model.backend.BackendFactory;
import BE.Component;
import BE.Order;
import android.R.layout;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.java5774_04_7842_7588.R;

public class Addcomponent extends _Activity {

	private Order currentOrder;
	private ArrayList<Component> components = new ArrayList<Component>();
	private Component choosenComponent;
	ArrayList<String> componentNames;
	ArrayAdapter<String> adapter = null;
	Spinner spinner;
	Button saveButton;
	Button cancelButton;
	//ArrayList<Component> componentsToAdd = new ArrayList<Component>();
	//ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcomponent);
		spinner = (Spinner) findViewById(R.id.spinner);
		int orderNumber = getIntent().getExtras().getInt("number");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(
				orderNumber);
		components = BackendFactory.getInstance().getAvailableComponent();
		componentNames = getComponentsNames(components);
		saveButton = (Button) findViewById(R.id.saveButton);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		
		//set the spinner
		adapter = new ArrayAdapter<String>(this,
				layout.simple_list_item_checked, componentNames);
		spinner.setAdapter(adapter);

		// set when choosing item
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				choosenComponent = components.get(position);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Addcomponent.this,
						OrderNavigation.class);
				currentOrder.addComponent(choosenComponent);
				intent.putExtra("selectedOrder", currentOrder.getOrderNumber());
				startActivity(intent);
			}
		});
		/*
	    //list = (ListView) findViewById(R.id.addCompListView);
		list = (ListView) findViewById(R.id.addCompListView);
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
					convertView = View.inflate(Addcomponent.this,
							R.layout.component_list_view, null);
				}

				list.super.setText(R.id.componentName,
						components.get(position).getName());
				list.super.setText(R.id.serialNumber,
						components.get(position).getSerialNumber());

				return convertView;
			}

		};
		list.setAdapter(adapter);
*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addcomponent, menu);
		return true;
	}

	private ArrayList<String> getComponentsNames(ArrayList<Component> list) {
		ArrayList<String> result = new ArrayList<String>();
		for (Component item : list)
			result.add(item.getName());
		return result;
	}

}
