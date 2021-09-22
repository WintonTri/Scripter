package scripts.scripter_app.herblore.nodes;

import java.util.Optional;

import org.tribot.api2007.Inventory;
import org.tribot.script.sdk.GameState;
import org.tribot.script.sdk.MakeScreen;
import org.tribot.script.sdk.Waiting;
import org.tribot.script.sdk.query.Query;
import org.tribot.script.sdk.types.InventoryItem;

import scripts.scripter_app.api.framework.Node;
import scripts.scripter_app.api.util.ClosingUtil;
import scripts.scripter_app.herblore.data.Herb;
import scripts.scripter_app.herblore.data.HerbloreData;
import scripts.scripter_app.herblore.gui.HerbloreGUISettings;

public class MakeUnfPot extends Node {

	@Override
	public String activity() {
		return "Make Unf Pot";
	}

	@Override
	public boolean validate() {

		if (!HerbloreGUISettings.makeUnfPotions)
			return false;

		// Vial of water needed to make unf pot.
		if (Inventory.getCount(HerbloreData.VIAL_OF_WATER_ID) == 0)
			return false;

		return getHerbToMix().isPresent();
	}

	@Override
	public void execute() {
		log("Execute");

		// TODO
		Optional<Herb> herbToMix = getHerbToMix();

		if (!herbToMix.isPresent())
			return;

		int cleanHerbId = herbToMix.get().getCleanId();
		int unfId = herbToMix.get().getUnfId();

		Optional<InventoryItem> cleanHerb = Query.inventory().idEquals(cleanHerbId).findFirst();
		Optional<InventoryItem> vialOfWater = Query.inventory().idEquals(HerbloreData.VIAL_OF_WATER_ID).findFirst();

		ClosingUtil.closeInterfaces();
		if (!deselect())
			return;

		if (useItems(cleanHerb, vialOfWater) && MakeScreen.makeAll(unfId))
			Waiting.waitUntil(60000, () -> {
				boolean outOfGems = Inventory.getCount(cleanHerbId) == 0;
				return outOfGems;
			});

	}

	public static Optional<Herb> getHerbToMix() {
		return HerbloreData.list.stream()
				.filter(herb -> herb.isUnfMakingEnabled())
				.filter(herb -> herb.levelReqValid())
				.filter(herb -> Inventory.getCount(herb.getCleanId()) > 0)
				.findFirst();
	}

	private boolean useItems(Optional<InventoryItem> firstItem, Optional<InventoryItem> secondItem) {
		if (!firstItem.isPresent() || !secondItem.isPresent())
			return false;
		if (!deselect())
			return false;
		return firstItem.get().click("Use") && secondItem.get().click("Use")
				&& Waiting.waitUntil(3000, () -> MakeScreen.isOpen());
	}

	// Deselect any selected item to avoid a script lock up (Ty EZPIE man)
	private boolean deselect() {
		if (!GameState.isAnyItemSelected())
			return true;
		String selectedItemName = GameState.getSelectedItemName();
		Query.inventory().nameEquals(selectedItemName).findClosestToMouse().ifPresent(c -> c.click("Cancel"));
		return Waiting.waitUntil(2000, () -> !GameState.isAnyItemSelected());
	}

}
