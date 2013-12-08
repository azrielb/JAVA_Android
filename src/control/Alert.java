package control;

import android.content.Context;
import android.widget.Toast;

public final class Alert {
	public static final void show(Context context, String title, String message) {
//		AlertDialog ad = new AlertDialog.Builder(context).create();
//		ad.setTitle(title);
//		ad.setMessage(message);
//		ad.show();

		Toast.makeText(context, title + "\n" + message, Toast.LENGTH_LONG)
				.show();
	}
}
