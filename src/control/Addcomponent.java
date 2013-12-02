package control;

import android.os.Bundle;
import android.view.Menu;

import com.example.java5774_04_7842_7588.R;

public class Addcomponent extends _Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcomponent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addcomponent, menu);
		return true;
	}

}
