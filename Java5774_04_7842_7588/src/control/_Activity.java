package control;

import java.io.Serializable;

import model.backend.BackendFactory;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidBE.Order;
import conversions.toGoogleConvertions;

public class _Activity extends Activity {
	protected ProgressDialog progressDialog;
	static protected Order currentOrder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		progressDialog = new ProgressDialog(this);
		this.progressDialog.dismiss();
		setProgressBarIndeterminateVisibility(false);
	}

	protected void setText(int TextViewID, String text) {
		setText(this, TextViewID, text);
	}

	protected static void setText(Activity activity, int TextViewID, String text) {
		try {
			TextView theObject = (TextView) activity.findViewById(TextViewID);
			theObject.setText(text);
		} catch (Exception e) {
		}
	}

	protected static void setText(View view, int TextViewID, String text) {
		try {
			TextView theObject = (TextView) view.findViewById(TextViewID);
			theObject.setText(text);
		} catch (Exception e) {
		}
	}

	protected void appendText(int TextViewID, String text) {
		appendText(this, TextViewID, text);
	}

	protected static void appendText(Activity activity, int TextViewID,
			String text) {
		try {
			TextView theObject = (TextView) activity.findViewById(TextViewID);
			appendText(theObject, text);
		} catch (Exception e) {
		}
	}

	protected static void appendText(View view, int TextViewID, String text) {
		try {
			TextView theObject = (TextView) view.findViewById(TextViewID);
			appendText(theObject, text);
		} catch (Exception e) {
		}
	}

	private static void appendText(TextView theObject, String text) {
		theObject.setText(theObject.getText() + text);
	}

	protected class updateEntities extends
			AsyncTask<toGoogleConvertions, Void, Exception> {
		protected Class<?> destinationActivity;
		protected String extraString;
		protected Serializable extraContent;

		public updateEntities(Class<?> destination_Activity,
				String extra_String, Serializable extra_Content) {
			destinationActivity = destination_Activity;
			extraString = extra_String;
			extraContent = extra_Content;
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(_Activity.this, "Please wait",
					"Synchronizing with the server...", true);
		}

		@Override
		protected Exception doInBackground(toGoogleConvertions... params) {
			for (toGoogleConvertions param : params) {
				try {
					if (param instanceof androidBE.Bill) {
						BackendFactory.getInstance().updateBill(
								(androidBE.Bill) param);
					} else if (param instanceof androidBE.Component) {
						BackendFactory.getInstance().updateComponent(
								(androidBE.Component) param);
					} else if (param instanceof androidBE.Order) {
						BackendFactory.getInstance().updateOrder(
								(androidBE.Order) param);
					} else if (param instanceof androidBE.Technician) {
						BackendFactory.getInstance().updateTechnician(
								(androidBE.Technician) param);
					} else if (param != null)
						throw new Exception("Invalid object");
				} catch (Exception e) {
					return e;
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Exception e) {
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			if (e != null)
				Alert.showToast(_Activity.this, e.getMessage());
			if (destinationActivity == null)
				finish();
			else if (_Activity.class.isAssignableFrom(destinationActivity)) {
				Intent intent = new Intent(_Activity.this, destinationActivity);
				intent.putExtra(extraString, extraContent);
				startActivity(intent);
			}
		}

	}
}
