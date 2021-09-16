package scripts.scripter_app.api.util;

import org.tribot.api.General;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.api2007.Game;
import org.tribot.api2007.Options;

import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.api_lib.models.DaxCredentials;
import scripts.dax_api.api_lib.models.DaxCredentialsProvider;

public class Util {
	
	static int abcEnergyActivationPoint = General.random(15, 50);

	public static void setRun() {
		if (Game.isRunOn())
			return;
		int currentRunEnergy = Game.getRunEnergy();
		if (currentRunEnergy >= abcEnergyActivationPoint) {
			abcEnergyActivationPoint = new ABCUtil().generateRunActivation();
			Options.setRunEnabled(true);
		}
	}

	public static void setDaxCredentials() {
		DaxWalker.setCredentials(new DaxCredentialsProvider() {

			@Override
			public DaxCredentials getDaxCredentials() {
				return new DaxCredentials("sub_DPjXXzL5DeSiPf", "PUBLIC-KEY");
			}

		});
	}

}
