package scripts.scripter_app.herblore.data;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

public class Herb {

	private int levelReq;
	private int grimyId;
	private int cleanId;
	private int unfId;

	private boolean cleaningEnabled;
	private boolean unfEnabled;

	public Herb(int levelReq, int grimyId, int cleanId, int unfId, boolean cleaningEnabled, boolean unfEnabled) {
		this.levelReq = levelReq;
		this.grimyId = grimyId;
		this.cleanId = cleanId;
		this.unfId = unfId;
		this.cleaningEnabled = cleaningEnabled;
		this.unfEnabled = unfEnabled;
	}

	public int getLevelReq() {
		return levelReq;
	}

	public int getGrimyId() {
		return grimyId;
	}

	public int getCleanId() {
		return cleanId;
	}

	public int getUnfId() {
		return unfId;
	}

	public boolean isCleaningEnabled() {
		return cleaningEnabled;
	}

	public boolean isUnfMakingEnabled() {
		return unfEnabled;
	}

	public boolean levelReqValid() {
		return Skills.getCurrentLevel(SKILLS.HERBLORE) >= this.levelReq;
	}

}
