package scripts.scripter_app.api.util;

import org.tribot.script.sdk.Widgets;

public class ClosingUtil {
	
	public static void closeInterfaces() {
		BankUtil.close(); // Updates bank cache
		Widgets.closeAll();
	}

}
