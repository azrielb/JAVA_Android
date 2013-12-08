package control;

import java.util.ArrayList;
import java.util.List;

import model.backend.BackendFactory;
import BE.Component;
import BE.Order;
import BE.Order.statuses;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
		orderNumber = getIntent().getExtras().getInt("orderNumber");
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
		if (currentOrder.getStatus() != statuses.FINISHED)
			componentList
					.setOnItemLongClickListener(new OnItemLongClickListener() {

						@Override
						public boolean onItemLongClick(AdapterView<?> parent,
								View view, final int position, long id) {
							DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									switch (which) {
									case DialogInterface.BUTTON_POSITIVE:
										components.get(position).setExist(true);
										components.remove(position);
										Toast.makeText(ComponentList.this,
												"the item was removed",
												Toast.LENGTH_LONG).show();
										break;

									case DialogInterface.BUTTON_NEGATIVE:
										Toast.makeText(ComponentList.this,
												"the item was not removed",
												Toast.LENGTH_LONG).show();
										break;
									}
								}
							};

							AlertDialog.Builder builder = new AlertDialog.Builder(
									ComponentList.this);
							builder.setMessage(
									"Are you sure yoy want to delete?")
									.setPositiveButton("Yes",
											dialogClickListener)
									.setNegativeButton("No",
											dialogClickListener).show();

							return true;
						}

					});
		if (currentOrder.getStatus() == statuses.FINISHED)
			Alert.show(this, "Read only",
					"The order was finished. You can't change the information.");
		else
			Alert.show(this, "You can delete items!",
					"Touch long-click for deleting.");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.component_list, menu);
		return true;
	}

}
