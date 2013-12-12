package control;

import java.util.List;

import model.backend.BackendFactory;
import BE.Component;
import BE.Order;
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

import com.example.java5774_04_7842_7588.R;

public class ComponentList extends _Activity {
	protected class componentAdapter extends ArrayAdapter<Component> {

		public componentAdapter(ComponentList componentList,
				int componentListView, List<Component> components) {
			super(componentList, componentListView, components);
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
				convertView = View.inflate(ComponentList.this,
						R.layout.component_list_view, null);
			}
			_Activity.setText(convertView, R.id.componentName,
					components.get(position).getName());
			_Activity.setText(convertView, R.id.serialNumber,
					components.get(position).getSerialNumber());
			return convertView;
		}
	};

	private Order currentOrder = null;
	private ListView componentList = null;
	private List<Component> components = null;
	private int orderNumber = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_component_list);
		orderNumber = getIntent().getExtras().getInt("orderNumber");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(
				orderNumber);
		componentList = (ListView) findViewById(R.id.componentListView);

		setComponents(currentOrder.getRequiredComponents());
		componentList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case DialogInterface.BUTTON_POSITIVE:
							components.get(position).setExist(true);
							components.remove(position);
							setComponents(components);
							Alert.showToast(ComponentList.this,
									"The item has been removed successfuly");
							break;
						case DialogInterface.BUTTON_NEGATIVE:
							Alert.showToast(ComponentList.this,
									"The removing has been canceled.");
							break;
						}
					}
				};
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ComponentList.this);
				builder.setMessage("Are you sure you want to remove this item?")
						.setPositiveButton("Ok", dialogClickListener)
						.setNegativeButton("Cancel", dialogClickListener)
						.show();
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.component_list, menu);
		return true;
	}

	private void setComponents(List<Component> _components) {
		componentList.setAdapter(null);
		components = _components;
		ListAdapter adapter = new componentAdapter(this,
				R.layout.component_list_view, components);
		componentList.setAdapter(adapter);
	}
}
