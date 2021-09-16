package scripts.scripter_app.api;

import org.tribot.api2007.Game;
import org.tribot.api2007.types.RSVarBit;

public class SettingRequirement {

	private int settingValue;
	private int completeValue;
	private boolean isVarbit;

	public SettingRequirement(int settingValue, int completeValue, boolean isVarbit) {
		this.settingValue = settingValue;
		this.completeValue = completeValue;
		this.isVarbit = isVarbit;
	}

	public int getSettingValue() {
		return this.settingValue;
	}

	public int getCompleteValue() {
		return this.completeValue;
	}

	public boolean isVarbit() {
		return this.isVarbit;
	}
	
	public boolean isQuestRequirementValid() {
		if (isVarbit) {
			RSVarBit var = RSVarBit.get(this.settingValue);
			return var == null ? false : var.getValue() == this.completeValue;
		}
		return Game.getSetting(this.settingValue) == this.completeValue;
	}

}
