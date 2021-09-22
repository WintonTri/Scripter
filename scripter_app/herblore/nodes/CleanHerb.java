package scripts.scripter_app.herblore.nodes;

import java.util.Optional;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.script.sdk.Waiting;
import org.tribot.script.sdk.query.Query;
import org.tribot.script.sdk.types.InventoryItem;

import scripts.scripter_app.api.framework.Node;
import scripts.scripter_app.api.util.ClickingUtil;
import scripts.scripter_app.api.util.ClosingUtil;
import scripts.scripter_app.herblore.data.Herb;
import scripts.scripter_app.herblore.data.HerbloreData;
import scripts.scripter_app.herblore.gui.HerbloreGUISettings;

public class CleanHerb extends Node {

	@Override
	public String activity() {
		return "Clean Herb";
	}

	@Override
	public boolean validate() {
		if (!HerbloreGUISettings.cleanHerbs)
			return false;

		return getHerbToClean().isPresent();
	}

	@Override
	public void execute() {
		log("Execute");

		Optional<Herb> herbToClean = getHerbToClean();
		if (herbToClean.isEmpty())
			return;

		int grimyHerbId = herbToClean.get().getGrimyId();
		int startingHerbloreLevel = getHerbloreLv();

		ClosingUtil.closeInterfaces();
		if (!ClickingUtil.deselect())
			return;
		
		if (cleanHerb(grimyHerbId))
			waitUntilDoneCleaning(startingHerbloreLevel);

	}


	private boolean cleanHerb(int grimyHerbId) {
		Optional<InventoryItem> invHerb = Query.inventory().idEquals(grimyHerbId).findClosestToMouse();
		if (invHerb.isEmpty())
			return false;

		return ClickingUtil.deselect() && invHerb.get().click("Clean");
	}

	private void waitUntilDoneCleaning(int startingHerbloreLevel) {
		
		Waiting.waitUntil(60000, () -> {
			int herbloreLv = getHerbloreLv();
			boolean outOfGems = getHerbToClean().isEmpty();
			boolean leveled = herbloreLv != startingHerbloreLevel;
			return outOfGems || leveled;
		});
		
	}

	private int getHerbloreLv() {
		return Skills.getActualLevel(SKILLS.HERBLORE);
	}
	
	public static Optional<Herb> getHerbToClean() {
		return HerbloreData.list.stream()
				.filter(herb -> herb.isCleaningEnabled())
				.filter(herb -> herb.levelReqValid())
				.filter(herb -> Inventory.getCount(herb.getGrimyId()) > 0)
				.findFirst();
	}

}
