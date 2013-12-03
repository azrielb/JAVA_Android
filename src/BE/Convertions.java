package BE;

import java.text.DateFormat;
import java.util.Date;

public class Convertions {
	public static DateFormat dateFormat = DateFormat.getDateInstance();

	public static String formatDateToString(Date d) {
		return dateFormat.format(d);
	}

	public static String Join(String[] strings, String seperator) {
		int length = strings.length;
		int i = 0;
		String joined = "";
		for (; i < length; ++i) {
			if (!strings[i].isEmpty()) {
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
