package scripts.scripter_app.api.util;

import org.tribot.api.General;
import org.tribot.api2007.Login;
import org.tribot.script.sdk.cache.BankCache;
import org.tribot.script.sdk.util.Retry;

import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.walker_engine.WalkingCondition;

public class StartingUtil {

	public StartingUtil() {
		Util.setDaxCredentials();
		// TODO set a useful walking condition
		DaxWalker.setGlobalWalkingCondition(new WalkingCondition() {
			@Override
			public State action() {
				return State.CONTINUE_WALKER;
			}
		});
		while (!Login.getLoginState().equals(Login.STATE.INGAME)) {
			General.sleep(500);
		}
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
