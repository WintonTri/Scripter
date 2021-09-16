package scripts.scripter_app.crafter.task;

import java.util.Optional;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.script.sdk.Inventory;
import org.tribot.script.sdk.MakeScreen;
import org.tribot.script.sdk.Waiting;
import org.tribot.script.sdk.query.Query;
import org.tribot.script.sdk.types.InventoryItem;

import scripts.scripter_app.api.framework.Node;
import scripts.scripter_app.api.util.BankUtil;
import scripts.scripter_app.crafter.data.CraftingData;
import scripts.scripter_app.crafter.data.object.UncutGem;

public class Cut extends Node {

	@Override
	public String activity() {
		return "Cutting";
	}

	@Override
	public boolean validate() {

		// Tool needed to cut gems
		if (Inventory.getCount("Chisel") == 0)
			return false;

		return CraftingData.getGemToCut().isPresent();
	}

	@Override
	public void execute() {

		Optional<UncutGem> gemToCut = CraftingData.getGemToCut();

		if (!gemToCut.isPresent())
			return;

		// Close Ge, ..., Bank interfaces
		if (!BankUtil.close())
			return;
		Interfaces.closeAll();

		int gemId = gemToCut.get().getGemId();
		int currentLevel = Skills.getActualLevel(SKILLS.CRAFTING);

		Optional<InventoryItem> gem = Query.inventory().idEquals(gemId).findFirst();
		Optional<InventoryItem> chisel = Query.inventory().nameEquals("Chisel").findFirst();

		if (useItems(gem, chisel) && MakeScreen.makeAll(gemId))
			Waiting.waitUntil(60000, () -> {
				int craftLv = Skills.getActualLevel(SKILLS.CRAFTING);
				boolean outOfGems = Inventory.getCount(gemId) == 0;
				boolean leveled = craftLv != currentLevel;
				return outOfGems || leveled;
			});

	}

	private boolean useItems(Optional<InventoryItem> gem, Optional<InventoryItem> chisel) {
		if (!gem.isPresent() || !chisel.isPresent())
			return false;
		return gem.get().click("Use") && chisel.get().click("Use")
				&& Waiting.waitUntil(3000, () -> MakeScreen.isOpen());
	}

}
