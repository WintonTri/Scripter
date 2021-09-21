package scripts.scripter_app.herblore.nodes;

import java.util.Optional;

import org.tribot.api2007.Inventory;
import org.tribot.script.sdk.Log;
import org.tribot.script.sdk.cache.BankCache;
import org.tribot.script.sdk.tasks.Amount;
import org.tribot.script.sdk.tasks.BankTask;

import scripts.scripter_app.api.framework.Node;
import scripts.scripter_app.herblore.WintonHerblore;
import scripts.scripter_app.herblore.data.Herb;
import scripts.scripter_app.herblore.data.HerbloreData;
import scripts.scripter_app.herblore.gui.HerbloreGUISettings;

public class GetHerbloreItems extends Node {

	@Override
	public String activity() {
		return "Get Herblore Items";
	}

	// Always run if no other tasks are valid
	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void execute() {
		
		Optional<Herb> bestGrimyHerbFound = getBestGrimyHerbFound();
		Optional<Herb> bestCleanHerbFound = getBestCleanHerbFound();
		boolean haveVialOfWater = this.getTotalItemsCount(HerbloreData.VIAL_OF_WATER_ID) > 0;
		
		boolean haveMixingItems = bestCleanHerbFound.isPresent() && haveVialOfWater;
		
		if (HerbloreGUISettings.cleanHerbs && bestGrimyHerbFound.isPresent())
			getGrimyItems(bestGrimyHerbFound);
		
		else if (HerbloreGUISettings.makeUnfPotions && haveMixingItems)
			getUnfItems(bestCleanHerbFound);
		else
			endScript();
	}

	// Loadout for cleaning items
	private void getGrimyItems(Optional<Herb> bestGrimyHerbFound) {
		log("Getting Herbs to clean");

		int grimyId = bestGrimyHerbFound.get().getGrimyId();

		BankTask.builder()
		.addInvItem(grimyId, Amount.fill(1))
		.build().execute();

	}
	
	// Loadout for unf items
	private void getUnfItems(Optional<Herb> bestCleanHerbFound) {
		log("Getting Unf Items");
		
		
		int cleanId = bestCleanHerbFound.get().getCleanId();

		BankTask.builder()
		.addInvItem(cleanId, Amount.range(1, 14))
		.addInvItem(HerbloreData.VIAL_OF_WATER_ID, Amount.range(1, 14))
		.build().execute();

	}

	public void endScript() {
		Log.log("Out of valid items, Attempting to end script");
		WintonHerblore.haveItems = false;
	}

	// Gets the best grimy herb to clean based on highest level & if player has
	public Optional<Herb> getBestGrimyHerbFound() {
		return HerbloreData.list.stream()
				.filter(herb -> herb.isCleaningEnabled())
				.filter(herb -> herb.levelReqValid())
				.filter(herb -> getTotalItemsCount(herb.getGrimyId()) > 0)
				.findFirst();
	}

	// Gets the best clean herb to turn into unf pot based on highest level & if player has
	public Optional<Herb> getBestCleanHerbFound() {
		return HerbloreData.list.stream()
				.filter(herb -> herb.isUnfMakingEnabled())
				.filter(herb -> herb.levelReqValid())
				.filter(herb -> getTotalItemsCount(herb.getCleanId()) > 0)
				.findFirst();
	}

	public int getTotalItemsCount(int id) {
		int invCount = Inventory.getCount(id);
		int bankCount = BankCache.getStack(id);
		int total = invCount + bankCount;
		return total;
	}

}
