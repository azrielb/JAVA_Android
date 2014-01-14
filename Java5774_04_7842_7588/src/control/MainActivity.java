package control;

import model.backend.BackendFactory;
import BE.Technician;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.java5774_04_7842_7588.R;

public class MainActivity extends _Activity {

	Technician tecnic = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button loginButton = (Button) findViewById(R.id.logButton);
		Button newAccount = (Button) findViewById(R.id.addNewAccount);
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strID = ((TextView) findViewById(R.id.userID)).getText()
						.toString();
				String password = ((TextView) findViewById(R.id.passwordUser))
						.getText().toString();
				try {
					if (strID.length() == 0 || password.length() == 0)
						throw new Exception("Empty field!!");

					new AsyncTask<String, Void, Technician>() {
						@Override
						protected void onPreExecute() {
							progressDialog = ProgressDialog.show(
									MainActivity.this, "Please wait",
									"Synchronizing with the server...", true);
						}

						String error = null;

						@Override
						protected Technician doInBackground(String... params) {
							Technician result = null;
							try {
								result = BackendFactory.getInstance()
										.getUserByIdAndPassword(
												Long.parseLong(params[0]),
												params[1]);
							} catch (Exception e) {
								error = e.getMessage();
							}
							return result;
						}

						@Override
						protected void onPostExecute(Technician user) {
							if (progressDialog.isShowing()) {
								progressDialog.dismiss();
							}
							if (user != null) {
								Intent intent = new Intent(MainActivity.this,
										OrderList.class);
								intent.putExtra("user", user);
								startActivity(intent);
							} else {
								if (error != null)
									Alert.showAlertDialog(MainActivity.this, "Exception",error);
								Alert.showToast(MainActivity.this,
										"Invalid ID or password!");
							}
						}
					}.execute(strID, password);
				} catch (Exception e) {
					Alert.showToast(MainActivity.this, e.getMessage());
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
