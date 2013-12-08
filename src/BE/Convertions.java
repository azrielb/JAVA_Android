package BE;

import java.text.DateFormat;

public class Convertions {
	public static final DateFormat dateInstance = DateFormat.getDateInstance();
	public static final DateFormat TimeInstance = DateFormat.getTimeInstance();

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
