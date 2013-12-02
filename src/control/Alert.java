package control;

import android.app.AlertDialog;
import android.content.Context;

public final class Alert {
	public static final void show(Context context, String title, String message) {
		AlertDialog ad = new AlertDialog.Builder(context).create();
		ad.setTitle(title);
		ad.setMessage(message);
		ad.show();
	}
}
