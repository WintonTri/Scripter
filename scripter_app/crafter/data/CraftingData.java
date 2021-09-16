package scripts.scripter_app.crafter.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.tribot.script.sdk.Inventory;

import scripts.scripter_app.crafter.data.object.UncutGem;

public class CraftingData {

	// Level Req, Item Id
	public static UncutGem 
	
		ZENYTE = new UncutGem(89, 19496),
		ONYX = new UncutGem(67, 6571),
		DRAGONSTONE = new UncutGem(55, 1631),
		DIAMOND = new UncutGem(43, 1617),
		RUBY = new UncutGem(34, 1619),
		EMERALD = new UncutGem(27, 1621),
		SAPPHIRE = new UncutGem(20, 1623),
		RED_TOPAZ = new UncutGem(16, 1629),
		JADE = new UncutGem(13, 1627),
		OPAL = new UncutGem(1, 1625)
		
	;

	public static ArrayList<UncutGem> list = new ArrayList<UncutGem>(Arrays.asList(

			ZENYTE, ONYX, DRAGONSTONE, DIAMOND, RUBY, EMERALD, SAPPHIRE, RED_TOPAZ, JADE, OPAL
			
	));
	
	
	public static Optional<UncutGem> getGemToCut() {
		return CraftingData.list.stream()
		.filter(gem -> gem.levelReqValid())
		.filter(gem -> Inventory.getCount(gem.getGemId()) > 0)
		.findFirst();
	}

}
