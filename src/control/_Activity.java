package control;

import android.app.Activity;
import android.widget.TextView;

public class _Activity extends Activity {
	protected void setText(int TextViewID, String text) {
		TextView theObject = (TextView) findViewById(TextViewID);
		theObject.setText(text);
	}

	protected void appendText(int TextViewID, String text) {
		TextView theObject = (TextView) findViewById(TextViewID);
		theObject.setText(theObject.getText() + text);
	}
}
