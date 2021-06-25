package scripts.scripter_app.agility.task;

import java.util.Arrays;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.scripter_app.agility.data.RooftopObstacle;
import scripts.scripter_app.agility.data.TransitionData;
import scripts.scripter_app.api.WalkingUtil;

public class UtilAgility {

	double LOW_HP_THRESHHOLD = General.randomDouble(0.15, 0.2);

	ABCUtil ABC = new ABCUtil();

	int MIN_IN_MILLISECONDS = 60000;
	int sameTileCount;

	protected boolean handleStartTravel(RSTile startingTile, RooftopObstacle[] courseData) {
		General.println("Handling Start Travel");
		if (WalkingUtil.goNearTile(startingTile, 10))
			if (!Timing.waitCondition(() -> inAnyArea(courseData), General.randomLong(8000, 12000)))
				WalkingUtil.goNearTile(startingTile, 2);
			else
				return true;
		return false;
	}

	protected void afkIfLowHp() {
		double hpPercent = getCurrentHp() / getMaxHp();
		boolean hpTooLow = hpPercent <= LOW_HP_THRESHHOLD;
		if (hpTooLow) {
			General.println("Forcing Afk to recover health");
			int randomAfkTime = General.random(MIN_IN_MILLISECONDS * 3, MIN_IN_MILLISECONDS * 4);
			General.sleep(randomAfkTime);
		}
	}

	protected int getAgilityXp() {
		return Skills.getXP(SKILLS.AGILITY);
	}

	protected int getCurrentHp() {
		return Skills.getCurrentLevel(SKILLS.HITPOINTS);
	}

	protected int getMaxHp() {
		return Skills.getActualLevel(SKILLS.HITPOINTS);
	}

	protected RSArea getAreaIn(RooftopObstacle[] courseData) {
		RSTile playerTile = Player.getPosition();
		RooftopObstacle areaData = Arrays.stream(courseData)
				.filter(data -> data.getUseObjectArea().contains(playerTile)).findFirst().orElse(null);
		return areaData == null ? null : areaData.getUseObjectArea();
	}

	protected boolean inAnyArea(RooftopObstacle[] courseData) {
		return getAreaIn(courseData) != null;
	}

	protected boolean waitUntilObjectInteractionComplete(int startHp, int startXp, RooftopObstacle[] courseData,
			RSArea startArea, TransitionData translateData) {

		if (startArea == null)
			return false;

		if (ABC.shouldRotateCamera()) {
			TransitionData.adjustToTile(translateData);
		}
		if (ABC.shouldHover()) {
			TransitionData.hoverTile(translateData);
		}

		sameTileCount = 0;
		RSTile startingTile = Player.getPosition();
		return Timing.waitCondition(() -> {
			General.sleep(350, 500);
			RSTile newTile = Player.getPosition();
			if (!startingTile.equals(newTile)) {
				sameTileCount = 0;
			} else
				sameTileCount++;
			boolean exceedCount = sameTileCount > 7;
			boolean gainedXp = startXp != getAgilityXp();
			boolean newArea = inAnyArea(courseData) && !startArea.contains(Player.getPosition());
			boolean notMoving = !Player.isMoving();
			boolean fallen = getCurrentHp() < startHp;
			if (exceedCount)
				General.println("Exceeded Same Tile Limit");
			return exceedCount || fallen || (gainedXp && newArea && notMoving);
		}, General.randomLong(12000, 15000));

	}

}
