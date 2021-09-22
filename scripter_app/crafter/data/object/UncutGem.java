package scripts.scripter_app.crafter.data.object;

import java.util.Optional;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.WorldHopper;
import org.tribot.script.sdk.types.definitions.ItemDefinition;

public class UncutGem {

	private final int levelReq;
	private final int itemId;

	public UncutGem(int levelReq, int itemId) {
		this.levelReq = levelReq;
		this.itemId = itemId;
	}

	public int getLevelReq() {
		return this.levelReq;
	}

	public int getUncutGemId() {
		return this.itemId;
	}
	
	public boolean reqsValid() {
		return levelReqValid() && membersReqValid();
	}

	// Checks if player has the levels to cut the gem
	private boolean levelReqValid() {
		return Skills.getActualLevel(SKILLS.CRAFTING) >= this.levelReq;
	}
	
	/** Checks if member requirements are needed and valid
	 * 
	 * @return True if on a members world or members not required, else false.
	 */
	private boolean membersReqValid() {
		boolean onMembersWorld = WorldHopper.isCurrentWorldMembers().orElse(false);
		Optional<ItemDefinition> itemDef = ItemDefinition.get(this.itemId);
		boolean isMembersItem = itemDef.isPresent() ? itemDef.get().isMembersOnly() : false;
		return onMembersWorld || !isMembersItem;
	}

}
