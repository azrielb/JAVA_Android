package control;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.java5774_04_7842_7588.R;

public class WorkDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.work_details, menu);
		return true;
	}

}