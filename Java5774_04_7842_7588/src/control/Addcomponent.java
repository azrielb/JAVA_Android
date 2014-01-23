package control;

import java.util.ArrayList;

import model.backend.BackendFactory;
import android.R.layout;
import android.app.ProgressDialog;
import android.os.AsyncTask;
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
import androidBE.Component;
import androidBE.Order.statuses;

import com.example.java5774_04_7842_7588.R;

import conversions.toGoogleConvertions;

public class Addcomponent extends _Activity {
	private ArrayList<Component> components = new ArrayList<Component>();
	private Component choosenComponent;
	ArrayList<String> componentNames;
	ArrayAdapter<String> adapter = null;
	Spinner spinner;
	Button saveButton;
	Button cancelButton;
	ArrayList<Component> componentsToAdd = new ArrayList<Component>();
	ListView list;

	public Addcomponent() {
		components.add(new Component("Choose One item -->", 0, "null"));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcomponent);
		// currentOrder = (Order) (getIntent()
		// .getSerializableExtra("currentOrder"));

		saveButton = (Button) findViewById(R.id.saveButton);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		list = (ListView) findViewById(R.id.addCompListView);
		ArrayAdapter<String> adapterl = new ArrayAdapter<String>(this,
				layout.simple_list_item_1, getComponentsNames(componentsToAdd));
		list.setAdapter(adapterl);
		// set the spinner
		spinner = (Spinner) findViewById(R.id.spinner);
		// set when choosing item
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				choosenComponent = components.get(position);
				if (position != 0) {
					componentsToAdd.add(choosenComponent);
					// reset the list view
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
				toGoogleConvertions[] Entities = new toGoogleConvertions[componentsToAdd
						.size() + 1];
				if (currentOrder.getStatus() == statuses.NEW) {
					currentOrder.setStatus(statuses.IN_PROGRESS);
					Entities[0] = currentOrder;
				} else
					Entities[0] = null;
				int i = 0;
				for (Component item : componentsToAdd) {
					currentOrder.addComponent(item);
					Entities[++i] = item;
				}
				new updateEntities(null, null, null).execute(Entities);
			}
		});
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// for (Component item : componentsToAdd)
				// item.setOrderId(-currentOrder.getOrderNumber());
				finish();
			}
		});

		new AsyncTask<Void, Void, ArrayList<Component>>() {
			@Override
			protected void onPreExecute() {
				progressDialog = ProgressDialog
						.show(Addcomponent.this, "Please wait",
								"Synchronizing with the server...", true);
			}

			@Override
			protected ArrayList<Component> doInBackground(Void... params) {
				try {
					ArrayList<Component> items = BackendFactory.getInstance()
							.getAvailableComponent();
					return items;
				} catch (Exception e) {
					return null;
				}
			}

			@Override
			protected void onPostExecute(ArrayList<Component> res) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				components.addAll(res);
				componentNames = getComponentsNames(components);
				adapter = new ArrayAdapter<String>(Addcomponent.this,
						layout.simple_list_item_checked, componentNames);
				spinner.setAdapter(adapter);
			}
		}.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addcomponent, menu);
		return true;
	}

	private ArrayList<String> getComponentsNames(ArrayList<Component> list) {
		ArrayList<String> result = new ArrayList<String>();
		if (list != null)
			for (Component item : list)
				result.add(item.getName());
		return result;
	}

}
