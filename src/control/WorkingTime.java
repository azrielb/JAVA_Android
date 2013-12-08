package control;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import model.backend.BackendFactory;
import BE.Order;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.java5774_04_7842_7588.R;

public class WorkingTime extends _Activity {
	private int orderNumber;
	private Order currentOrder;
	private Calendar startDate;
	private Calendar finishDate;
	private DatePicker startDatePicker;
	private TimePicker startTimePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_working_time);
		orderNumber = getIntent().getExtras().getInt("orderNumber");
		currentOrder = BackendFactory.getInstance().getOrderByNumber(
				orderNumber);
		startDatePicker = (DatePicker) findViewById(R.id.date_picker);
		startTimePicker = (TimePicker) findViewById(R.id.time_picker);
		startDate = currentOrder.getStart();
		if (startDate != null) {
			startDatePicker.init(startDate.get(Calendar.YEAR),
					startDate.get(Calendar.MONTH),
					startDate.get(Calendar.DAY_OF_MONTH), null);
			startTimePicker.setCurrentHour(startDate.get(Calendar.HOUR_OF_DAY));
			startTimePicker.setCurrentMinute(startDate.get(Calendar.MINUTE));
			finishDate = currentOrder.getFinish();
			if (finishDate != null) {
				Long miliSecDiff = finishDate.getTimeInMillis()
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
				startDate=Calendar.getInstance();
				startDate.set(startDatePicker.getYear(),
						startDatePicker.getMonth(),
						startDatePicker.getDayOfMonth(),
						startTimePicker.getCurrentHour(),
						startTimePicker.getCurrentMinute());
				currentOrder.setStart(startDate);
				if (s_hours.length() > 0) {
					finishDate=Calendar.getInstance();
					finishDate.setTimeInMillis(startDate.getTimeInMillis()
							+ TimeUnit.MINUTES.toMillis((long) (Float
									.parseFloat(s_hours) * 60)));
					currentOrder.setFinish(finishDate);
				} else {
					currentOrder.setFinish(finishDate = null);
				}
				finish();
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
