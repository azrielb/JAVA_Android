package conversions;

import java.util.Date;
import android.text.format.DateFormat;

public class Convertions {
	public static String toDateString(Date date) {
		return DateFormat.format("[dd/MM/yyyy]", date).toString();
	}
	public static String toTimeString(Date date) {
		return DateFormat.format("[HH:mm]", date).toString();
	}
	public static String toDateString(Long date) {
		return DateFormat.format("[dd/MM/yyyy]", date).toString();
	}
	public static String toTimeString(Long date) {
		return DateFormat.format("[HH:mm]", date).toString();
	}	
	public static String Join(String[] strings, String seperator) {
		int length = strings.length;
		int i = 0;
		String joined = "";
		for (; i < length; ++i) {
			if (strings[i] != null && !strings[i].isEmpty()) {
				joined = strings[i++];
				break;
			}
		}
		for (; i < length; ++i)
			if (!strings[i].isEmpty())
				joined += seperator + strings[i];
		return joined;
	}
}
