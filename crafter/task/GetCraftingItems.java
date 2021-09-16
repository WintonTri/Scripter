package scripts.scripter_app.crafter.task;

import java.util.Optional;

import org.tribot.script.sdk.Inventory;
import org.tribot.script.sdk.Log;
import org.tribot.script.sdk.cache.BankCache;
import org.tribot.script.sdk.tasks.Amount;
import org.tribot.script.sdk.tasks.BankTask;
import org.tribot.script.sdk.tasks.BankTask.Builder;

import scripts.scripter_app.api.framework.Node;
import scripts.scripter_app.crafter.GemCrafter;
import scripts.scripter_app.crafter.data.CraftingData;
import scripts.scripter_app.crafter.data.object.UncutGem;

public class GetCraftingItems extends Node {

	int CHISEL_ID = 1755;

	@Override
	public String activity() {
		return "Getting Items";
	}

	// Always run if no other tasks are valid
	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void execute() {
		Optional<UncutGem> bestGemFound = getBestGemFound();
		if (!bestGemFound.isPresent())
			endScript();
		else
			getItems(bestGemFound);
	}

	private void getItems(Optional<UncutGem> bestGemFound) {

		int gemId = bestGemFound.get().getGemId();

		BankTask.builder()
			.addInvItem(CHISEL_ID, Amount.of(1))
			.addInvItem(gemId, Amount.fill(1))
			.build().execute();

	}

	public void endScript() {
		Log.log("Out of valid items, Attempting to end script");
		GemCrafter.haveItems = false;
	}

	// Gets the best gem based on highest level & if player has
	public Optional<UncutGem> getBestGemFound() {
		return CraftingData.list.stream().filter(gem -> gem.levelReqValid())
				.filter(gem -> getTotalItemsCount(gem.getGemId()) > 0).findFirst();
	}

	public int getTotalItemsCount(int id) {
		int invCount = Inventory.getCount(id);
		int bankCount = BankCache.getStack(id);
		int total = invCount + bankCount;
		return total;
	}

}
