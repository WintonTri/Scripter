package scripts.scripter_app.crafter.data.object;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

public class UncutGem {

	private int levelReq;
	private int gemId;

	public UncutGem(int levelReq, int gemId) {
		this.levelReq = levelReq;
		this.gemId = gemId;
	}

	public int getLevelReq() {
		return this.levelReq;
	}

	public int getGemId() {
		return this.gemId;
	}

	public boolean levelReqValid() {
		return Skills.getActualLevel(SKILLS.CRAFTING) >= this.levelReq;
	}

}
