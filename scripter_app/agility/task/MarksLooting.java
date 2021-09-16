package scripts.scripter_app.agility.task;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSGroundItem;

import scripts.scripter_app.api.util.ClickingUtil;

public class MarksLooting {

	String MARKS_OF_GRACE = "Mark of grace";
	int MARKS_OF_GRACE_ID = 11849;

	MarksLooting(RSArea areaIn) {
		if (areaIn == null)
			return;
		RSGroundItem[] marks = getGroundMarkOfGrace(areaIn);
		if (marks.length > 0) {
			General.println("Looting Mark of grace");
			int preMarksCount = countInventoryGraces();
			if (ClickingUtil.click(marks[0], "Take")) {
				Timing.waitCondition(() -> countInventoryGraces() != preMarksCount, General.randomLong(8000, 10000));
			}
		}
	}

	private RSGroundItem[] getGroundMarkOfGrace(RSArea areaIn) {
		return GroundItems.findNearest(gItem -> {
			return gItem.getID() == MARKS_OF_GRACE_ID && areaIn.contains(gItem);
		});
	}

	private int countInventoryGraces() {
		return Inventory.getCount(MARKS_OF_GRACE);
	}

}
