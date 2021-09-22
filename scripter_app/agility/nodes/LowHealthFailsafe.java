package scripts.scripter_app.agility.nodes;

import org.tribot.api.General;
import org.tribot.api2007.Combat;

import scripts.scripter_app.api.framework.Node;

public class LowHealthFailsafe extends Node {

	int LOW_HP_THRESHHOLD_PERCENT = General.random(15, 20);
	int MIN_IN_MILLISECONDS = 60000;

	@Override
	public String activity() {
		return "Low health failsafe";
	}

	@Override
	public boolean validate() {
		return Combat.getHPRatio() <= this.LOW_HP_THRESHHOLD_PERCENT;
	}

	@Override
	public void execute() {
		int randomAfkTime = General.random(MIN_IN_MILLISECONDS * 3, MIN_IN_MILLISECONDS * 4);
		log("Afking to recover health, Sleeping: " + randomAfkTime + " milliseconds");
		General.sleep(randomAfkTime);
	}

}
