package scripts.scripter_app.api;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.dax_api.api_lib.DaxWalker;

public class WalkingUtil {

	public static boolean goNearTile(RSTile tile, int distance) {
		RSTile playerTile = Player.getPosition();
		int destinationPlane = tile.getPlane();
		int playerPlane = playerTile.getPlane();
		if (playerTile.distanceTo(tile) <= distance && destinationPlane == playerPlane)
			return true;
		Util.setRun();
		if (DaxWalker.walkTo(tile))
			Timing.waitCondition(() -> Player.getPosition().distanceTo(tile) <= distance,
					General.randomLong(15000, 20000));
		return Player.getPosition().distanceTo(tile) <= distance;
	}

}
