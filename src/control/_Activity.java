package control;

import android.app.Activity;
import android.widget.TextView;

public class _Activity extends Activity {
	protected void setText(int TextViewID, String text) {
		try {
			TextView theObject = (TextView) findViewById(TextViewID);
			theObject.setText(text);
		} catch (Exception e) {
		}
	}

	protected void appendText(int TextViewID, String text) {
		try {
			TextView theObject = (TextView) findViewById(TextViewID);
			theObject.setText(theObject.getText() + text);
		} catch (Exception e) {
		}

	}
}
