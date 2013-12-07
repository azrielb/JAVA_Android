package control;

import java.util.ArrayList;

import model.backend.BackendFactory;
import BE.Component;
import BE.Order;
import android.R.layout;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
	ArrayList<Component> componentsToAdd = new ArrayList<Component>();
	ListView list;

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
		// -------------------------------------------------------------------
		list = (ListView) findViewById(R.id.addCompListView);

		ArrayAdapter<String> adapterl = new ArrayAdapter<String>(this,
				layout.simple_list_item_1, getComponentsNames(componentsToAdd));
		list.setAdapter(adapterl);

		// -------------------------------------------------------------------

		// set the spinner
		adapter = new ArrayAdapter<String>(this,
				layout.simple_list_item_checked, componentNames);
		spinner.setAdapter(adapter);
		// set when choosing item
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				choosenComponent = components.get(position);
				if (position != 0) {
					componentsToAdd.add(choosenComponent);
					choosenComponent.setExist(false);
					// reset the listview
					list.setAdapter(null);
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
							Addcomponent.this, layout.simple_list_item_1,
							getComponentsNames(componentsToAdd));
					list.setAdapter(adapter1);
					// reset the spinner
					spinner.setAdapter(null);
					components.remove(position);
					ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
							Addcomponent.this, layout.simple_list_item_checked,
							getComponentsNames(components));
					spinner.setAdapter(adapter2);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (Component item : componentsToAdd)
					currentOrder.addComponent(item);
				finish();
			}
		});
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (Component item : componentsToAdd)
					item.setExist(true);
				finish();
			}
		});
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
