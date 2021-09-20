package scripts.scripter_app.agility.task;

import java.util.Arrays;

import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.sdk.Waiting;

import scripts.scripter_app.agility.data.RooftopObstacle;
import scripts.scripter_app.api.util.ClickingUtil;

public class InteractObstacleObject {

	private String[] objectActions = { "Climb", "Cross", "Jump", "Jump-on", "Jump-in", "Jump-to", "Climb-across",
			"Walk-across", "Squeeze-through", "Climb-over", "Climb-down", "Climb-up", "Walk-on", "Balance", "Jump-up",
			"Swing-across", "Teeth-grip", "Leap", "Hurdle", "Jump-off", "Vault", "Grab", "Climb-on" };

	private RSObject obj;
	private RooftopObstacle[] courseData;
	private RSArea startingArea;

	public InteractObstacleObject(RSObject obj, RooftopObstacle[] courseData, RSArea startingArea) {
		this.obj = obj;
		this.courseData = courseData;
		this.startingArea = startingArea;
	}

	public boolean interact() {

		if (this.obj == null || this.startingArea == null)
			return false;

		return ClickingUtil.click(obj, objectActions) && waitUntilInNewArea();
	}

	private boolean waitUntilInNewArea() {
		return Waiting.waitUntil(10000, () -> {

			RSTile playerTile = Player.getPosition();
			if (this.startingArea.contains(playerTile))
				return false;

			boolean inAnyArea = getAreaIn(playerTile) != null;

			return inAnyArea;
		});

	}

	private RSArea getAreaIn(RSTile playerTile) {
		RooftopObstacle areaData = Arrays.stream(courseData)
				.filter(data -> data.getUseObjectArea().contains(playerTile)).findFirst().orElse(null);
		return areaData == null ? null : areaData.getUseObjectArea();
	}

}
