package control;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import androidBE.Order.statuses;

import com.example.java5774_04_7842_7588.R;

public class WorkingTime extends _Activity {
	private Calendar startDate;
	private DatePicker startDatePicker;
	private TimePicker startTimePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_working_time);
		// currentOrder = (Order)
		// (getIntent().getSerializableExtra("currentOrder"));

		startDatePicker = (DatePicker) findViewById(R.id.date_picker);
		startTimePicker = (TimePicker) findViewById(R.id.time_picker);

		if (currentOrder.getStart() != null && currentOrder.getStart() > 0) {
			startDate = Calendar.getInstance();
			startDate.setTimeInMillis(currentOrder.getStart());
			startDatePicker.init(startDate.get(Calendar.YEAR),
					startDate.get(Calendar.MONTH),
					startDate.get(Calendar.DAY_OF_MONTH), null);
			startTimePicker.setCurrentHour(startDate.get(Calendar.HOUR_OF_DAY));
			startTimePicker.setCurrentMinute(startDate.get(Calendar.MINUTE));
			if (currentOrder.getFinish() != null
					&& currentOrder.getFinish() > 0) {
				Long miliSecDiff = currentOrder.getFinish()
						- startDate.getTimeInMillis();
				Float hours = (float) (TimeUnit.MILLISECONDS
						.toMinutes(miliSecDiff)) / 60;
				super.setText(R.id.working_hours, hours.toString());
			}
		}

		Button save = (Button) findViewById(R.id.Save_button);
		Button Cancel = (Button) findViewById(R.id.Cancel_button);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String s_hours = ((TextView) findViewById(R.id.working_hours))
						.getText().toString();
				startDate = Calendar.getInstance();
				startDate.set(startDatePicker.getYear(),
						startDatePicker.getMonth(),
						startDatePicker.getDayOfMonth(),
						startTimePicker.getCurrentHour(),
						startTimePicker.getCurrentMinute());
				currentOrder.setStart(startDate.getTimeInMillis());
				if (s_hours.length() > 0) {
					currentOrder.setFinish(startDate.getTimeInMillis()
							+ TimeUnit.MINUTES.toMillis((long) (Float
									.parseFloat(s_hours) * 60)));
					currentOrder.setStatus(statuses.ACTION_DONE);
				} else {
					currentOrder.setFinish(-1L);
					currentOrder.setStatus(statuses.IN_PROGRESS);
				}
				new updateEntities(null, null, null).execute(currentOrder);
			}
		});
		Cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.working_time, menu);
		return true;
	}
}
