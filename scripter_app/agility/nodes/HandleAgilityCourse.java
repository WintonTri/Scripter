package scripts.scripter_app.agility.nodes;

import java.util.Arrays;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.scripter_app.agility.data.RooftopAgilityData;
import scripts.scripter_app.agility.data.RooftopAgilityData.RooftopObstacles;
import scripts.scripter_app.agility.data.RooftopObstacle;
import scripts.scripter_app.agility.task.InteractObstacleObject;
import scripts.scripter_app.agility.task.MarksLooting;
import scripts.scripter_app.api.framework.Node;
import scripts.scripter_app.api.util.Util;
import scripts.scripter_app.api.util.WalkingUtil;

public class HandleAgilityCourse extends Node {

	@Override
	public String activity() {
		return "HandleAgilityCourse";
	}

	// Always run if player does not need to AFK & heal.
	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void execute() {

		// Load new data
		RooftopObstacles obstacleData = RooftopAgilityData.getBestObstacle();
		RooftopObstacle[] courseData = obstacleData.getCourse();

		Util.setRun();
		if (travelToCourse(obstacleData)) {
			interactCourseObjects(courseData);
		}

	}

	// Interacts with every object in the course
	private void interactCourseObjects(RooftopObstacle[] courseData) {
		for (RooftopObstacle obj : courseData) {

			// Data
			String objectName = obj.getName();
			RSTile objectTile = obj.getObjectTile();
			RSArea useObjArea = obj.getUseObjectArea();

			// Skip since player is not in correct area.
			if (!useObjArea.contains(Player.getPosition())) {
				continue;
			}

			// Load Data
			RSObject nextObj = getNextObj(objectName, objectTile);
			int preHp = getCurrentHp();

			// Object interaction
			InteractObstacleObject interactObj = new InteractObstacleObject(nextObj, courseData, useObjArea);
			if (!interactObj.interact())
				break;

			// Break loop if fall is detected
			if (preHp > getCurrentHp()) {
				General.println("Fall Detected");
				break;
			}

			// Marks looting
			RSArea inArea = getCourseAreaPlayerIsOn(courseData);
			new MarksLooting(inArea).loot();
		}
	}

	// Get object to interact with
	private RSObject getNextObj(String objectName, RSTile objectTile) {
		return Arrays.stream(Objects.getAt(objectTile, Filters.Objects.nameEquals(objectName))).findFirst()
				.orElse(null);
	}

	// Gets current hp
	private int getCurrentHp() {
		return Skills.getCurrentLevel(SKILLS.HITPOINTS);
	}

	// Travels to the course
	private boolean travelToCourse(RooftopObstacles obstacleData) {
		RSTile startingTile = obstacleData.getStartTile();
		RooftopObstacle[] courseData = obstacleData.getCourse();

		boolean inCouseArea = inAnyCourseArea(courseData);
		return inCouseArea || handleStartTravel(startingTile, courseData);
	}

	// Travel to first object of the course
	private boolean handleStartTravel(RSTile startingTile, RooftopObstacle[] courseData) {
		General.println("Handling Start Travel");
		if (startingTile == null)
			return false;

		// Handle traveling to starting tile
		if (WalkingUtil.goNearTile(startingTile, 10) && Timing.waitCondition(() -> inAnyCourseArea(courseData), 10000))
			return true;

		// Failsafe: In case within the 10 tiles isn't in area.
		return WalkingUtil.goNearTile(startingTile, 1)
				&& Timing.waitCondition(() -> inAnyCourseArea(courseData), 10000);
	}

	// Returns the area the player is in
	private RSArea getCourseAreaPlayerIsOn(RooftopObstacle[] courseData) {
		RSTile playerTile = Player.getPosition();
		RooftopObstacle areaData = Arrays.stream(courseData)
				.filter(data -> data.getUseObjectArea().contains(playerTile)).findFirst().orElse(null);
		return areaData == null ? null : areaData.getUseObjectArea();
	}

	private boolean inAnyCourseArea(RooftopObstacle[] courseData) {
		return getCourseAreaPlayerIsOn(courseData) != null;
	}

}
