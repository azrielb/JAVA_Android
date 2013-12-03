package control;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class _Activity extends Activity {
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

	protected static void appendText(View view, int TextViewID,
			String text) {
		try {
			TextView theObject = (TextView) view.findViewById(TextViewID);
			appendText(theObject, text);
		} catch (Exception e) {
		}
	}

	private static void appendText(TextView theObject, String text) {
		theObject.setText(theObject.getText() + text);
	}
}
