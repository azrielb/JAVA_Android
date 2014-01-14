package control;

import model.backend.BackendFactory;
import BE.Technician;
import android.app.ProgressDialog;
import android.os.AsyncTask;
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

				String fname = ((EditText) findViewById(R.id.filterText))
						.getText().toString().trim();
				String lname = ((EditText) findViewById(R.id.lastName))
						.getText().toString().trim();
				String idStr = ((EditText) findViewById(R.id.id)).getText()
						.toString().trim();
				String pass = ((EditText) findViewById(R.id.password))
						.getText().toString().trim();
				String email = ((EditText) findViewById(R.id.email)).getText()
						.toString().trim();

				if (fname.length() == 0 || lname.length() == 0
						|| pass.length() == 0 || email.length() == 0) {
					Alert.showToast(NewAccount.this,
							"Empty detail! Please fill all details!");
					return;
				}
				new AsyncTask<String, Void, String>() {
					@Override
					protected void onPreExecute() {
						progressDialog = ProgressDialog.show(NewAccount.this,
								"Please wait",
								"Synchronizing with the server...", true);
					}

					@Override
					protected String doInBackground(String... params) {
						try {
							Technician user = new Technician(params[0], params[1],
									params[2], params[3], Long.parseLong(params[4]));
							BackendFactory.getInstance().addTechnician(user);
							return null;
						} catch (Exception e) {
							return "User not created!\nAre you alredy in the system? Please check your password.";
						}
					}

					@Override
					protected void onPostExecute(String res) {
						if (progressDialog.isShowing()) {
							progressDialog.dismiss();
						}

						if (res != null) {
							Alert.showToast(NewAccount.this, res);
						} else {
							Alert.showToast(NewAccount.this,
									"The user has been created successfully");
							finish();
						}
					}
				}.execute(fname, lname, pass, email, idStr);
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
