package scripts.scripter_app.api;

import org.tribot.api.General;
import org.tribot.api2007.Login;

import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.walker_engine.WalkingCondition;

public class StartingUtil {

	public StartingUtil() {
		Util.setDaxCredentials();
		DaxWalker.setGlobalWalkingCondition(new WalkingCondition() {
			@Override
			public State action() {
				return State.CONTINUE_WALKER;
			}
		});
		while (!Login.getLoginState().equals(Login.STATE.INGAME)) {
			General.sleep(500);
		}
	}

}
