package scripts.scripter_app.agility.data;


import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

// Data for hovering, used to hover dx, dy position from player.
// Unused for newer versions
public class DeltaPositionHover {

	private int x, y;

	public DeltaPositionHover(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public static void hoverPredictedLocation(DeltaPositionHover data) {
		RSTile tileToHover = getRelativePredictedTile(data);
		if (tileToHover != null && tileToHover.isOnScreen()) {
			General.println("Hovering Next Object Location");
			Clicking.hover(tileToHover);
		}
	}

	public static void adjustCameraToPredictedTileLocation(DeltaPositionHover data) {
		RSTile tile = getRelativePredictedTile(data);
		if (tile != null && tile.isOnScreen()) {
			General.println("Adjusting To Next Object");
			tile.adjustCameraTo();
		}
	}

	public static RSTile getRelativePredictedTile(DeltaPositionHover data) {
		if (data == null)
			return null;
		return Player.getPosition().translate(data.getX(), data.getY());
	}

}
