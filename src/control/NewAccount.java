package control;

import model.backend.BackendFactory;
import BE.Technician;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.java5774_04_7842_7588.R;

public class NewAccount extends _Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_account);
		Button button = (Button) findViewById(R.id.addNewAccount);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String fname = ((EditText) findViewById(R.id.filterOrders))
						.getText().toString().trim();
				String lname = ((EditText) findViewById(R.id.lastName))
						.getText().toString().trim();
				int id = Integer.parseInt(((EditText) findViewById(R.id.id))
						.getText().toString().trim());
				String pass = ((EditText) findViewById(R.id.password))
						.getText().toString().trim();
				String email = ((EditText) findViewById(R.id.email)).getText()
						.toString().trim();
				if (BackendFactory.getInstance().getUserByIdAndPassword(id,
						pass) != null) {
					Alert.show(NewAccount.this, "User exists",
							"You are alredy in the system!");
					return;
				}
				
				Technician user = new Technician(fname, lname, pass, email, id);
				BackendFactory.getInstance().addTechnician(user);
				
				if (BackendFactory.getInstance().getUserByIdAndPassword(id,
						pass) == null) {
					Alert.show(NewAccount.this, "User not created",
							"May you are alredy in the system? Please check your password.");
					return;
				}
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_account, menu);
		return true;
	}

}
