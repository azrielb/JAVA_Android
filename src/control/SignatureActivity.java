package control;

import java.io.File;
import java.io.FileOutputStream;

import model.backend.BackendFactory;
import BE.Order;
import BE.Order.statuses;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.java5774_04_7842_7588.R;

public class SignatureActivity extends _Activity {

	int orderNumber = 0;
	Order currentOrder = null;
	GestureOverlayView gestureView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signature);

		orderNumber = getIntent().getExtras().getInt("orderNumber");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(
				orderNumber);

		Button save = (Button) findViewById(R.id.DoneButton);
		Button clear = (Button) findViewById(R.id.clearButton);

		gestureView = (GestureOverlayView) findViewById(R.id.signaturePad);
		gestureView.setFadeOffset(5000);

		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					saveSig(gestureView);
					currentOrder.setStatus(statuses.SIGNATURED);
					finish();
				} catch (Exception e) {
					Alert.showToast(SignatureActivity.this, e.getMessage());
				}
			}
		});

		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					gestureView.clear(false);
				} catch (Exception e) {

				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void saveSig(View view) {
		try {
			String sigName = Integer.toString(orderNumber) + ".png";
			GestureOverlayView gestureView1 = (GestureOverlayView) findViewById(R.id.signaturePad);
			gestureView1.setDrawingCacheEnabled(true);

			Bitmap bm = Bitmap.createBitmap(gestureView1.getDrawingCache());
			File f = new File(Environment.getExternalStorageDirectory()
					+ File.separator + sigName);
			f.createNewFile();
			FileOutputStream os = new FileOutputStream(f);
			// compress to specified format (PNG), quality - which is ignored
			// for PNG, and out stream
			bm.compress(Bitmap.CompressFormat.PNG, 100, os);
			os.close();
		} catch (Exception e) {
			Log.v("Gestures", e.getMessage());
			e.printStackTrace();
		}
	}
}