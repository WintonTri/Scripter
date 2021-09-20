package scripts.scripter_app.agility.task;

import java.util.Arrays;
import java.util.Optional;

import org.tribot.api2007.GroundItems;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.script.sdk.Waiting;

import scripts.scripter_app.api.util.ClickingUtil;

public class MarksLooting {

	private final String MARKS_OF_GRACE = "Mark of grace";

	private final RSArea lootArea;

	public MarksLooting(RSArea lootArea) {
		this.lootArea = lootArea;
	}

	public boolean loot() {

		if (this.lootArea == null)
			return false;

		Optional<RSGroundItem> groundMarkOfGrace = getGroundMarkOfGrace();
		if (groundMarkOfGrace.isEmpty())
			return false;

		return ClickingUtil.click(groundMarkOfGrace.get(), "Take")
				&& Waiting.waitUntil(10000, () -> getGroundMarkOfGrace().isEmpty());

	}

	private Optional<RSGroundItem> getGroundMarkOfGrace() {
		if (this.lootArea == null)
			return null;

		return Arrays.stream(GroundItems.findNearest(this.MARKS_OF_GRACE))
				.filter(gItem -> this.lootArea.contains(gItem)).findFirst();

	}

}
