package scripts.scripter_app.api.util;

import org.tribot.api.Clicking;
import org.tribot.api.interfaces.Clickable;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.sdk.GameState;
import org.tribot.script.sdk.Waiting;
import org.tribot.script.sdk.query.Query;

public class ClickingUtil {

	public static boolean click(Clickable clickable, String... clickActions) {
		if (clickable == null)
			return false;
		RSTile clickableTile = null;
		if (clickable instanceof RSObject) {
			clickableTile = ((RSObject) clickable).getPosition();
		}
		if (clickable instanceof RSNPC) {
			clickableTile = ((RSNPC) clickable).getPosition();
		}
		if (clickable instanceof RSGroundItem) {
			clickableTile = ((RSGroundItem) clickable).getPosition();
		}
		return makeClickableVisible(clickableTile) && Clicking.click(clickActions, clickable);
	}

	private static boolean makeClickableVisible(RSTile clickableTile) {
		if (clickableTile == null)
			return true;
		if (!clickableTile.isOnScreen() || !clickableTile.isClickable())
			clickableTile.adjustCameraTo();
		return clickableTile.isOnScreen() && clickableTile.isClickable();
	}
	
	public static boolean deselect() {
		if (!GameState.isAnyItemSelected())
			return true;
		String selectedItemName = GameState.getSelectedItemName();
		Query.inventory().nameEquals(selectedItemName).findClosestToMouse().ifPresent(c -> c.click("Cancel"));
		return Waiting.waitUntil(2000, () -> !GameState.isAnyItemSelected());
	}

}
