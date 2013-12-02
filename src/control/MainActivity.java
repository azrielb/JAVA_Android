package control;

import model.backend.BackendFactory;
import BE.Technician;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.java5774_04_7842_7588.R;

public class MainActivity extends Activity {

	Technician tecnic = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button logButton = (Button) findViewById(R.id.logButton);
		Button newAccount = (Button) findViewById(R.id.addNewAccount);
		logButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, OrderList.class);
				int id = Integer
						.parseInt(((TextView) findViewById(R.id.filterOrders))
								.getText().toString());
				String password = ((TextView) findViewById(R.id.passwordUser))
						.getText().toString();
				tecnic = BackendFactory.getInstance().getUserByIdAndPassword(
						id, password);
				if (tecnic != null) {
					intent.putExtra("user", tecnic);
					startActivity(intent);
				} else {

				}
			}
		});
		newAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, NewAccount.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
