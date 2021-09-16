package scripts.scripter_app.api.util;

import org.tribot.api2007.Banking;
import org.tribot.script.sdk.Bank;
import org.tribot.script.sdk.cache.BankCache;

import scripts.dax_api.api_lib.DaxWalker;

public class BankUtil {

	public static boolean open() {
		if (Banking.isBankLoaded())
			return true;
		if (!Bank.isNearby() && !DaxWalker.walkToBank())
			return false;
		return Bank.openBank();
	}

	public static boolean close() {
		BankCache.update();
		return Bank.close();
	}

}
