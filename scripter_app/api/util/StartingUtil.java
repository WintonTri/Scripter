package scripts.scripter_app.api.util;

import org.tribot.api.General;
import org.tribot.api2007.Login;
import org.tribot.script.sdk.cache.BankCache;
import org.tribot.script.sdk.util.Retry;

public class StartingUtil {

	public StartingUtil() {
		initializeBank();
	}

	public void main(boolean initBank) {

		Util.setDaxCredentials();

		while (!Login.getLoginState().equals(Login.STATE.INGAME)) {
			General.sleep(500);
		}

		if (initBank)
			initializeBank();
	}

	public boolean initializeBank() {
		return Retry.retry(5, () -> {
			if (BankCache.isInitialized())
				return true;
			if (BankUtil.open())
				BankCache.update();
			return false;
		});
	}

}
