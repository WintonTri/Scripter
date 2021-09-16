package scripts.scripter_app.agility.task;

import java.util.Arrays;

import org.tribot.api.General;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSObjectDefinition;
import org.tribot.api2007.types.RSTile;

import scripts.scripter_app.agility.data.RooftopAgilityData;
import scripts.scripter_app.agility.data.RooftopAgilityData.RooftopObstacles;
import scripts.scripter_app.agility.data.RooftopObstacle;
import scripts.scripter_app.agility.data.PredictedObjectTransition;
import scripts.scripter_app.api.util.ClickingUtil;
import scripts.scripter_app.api.util.Util;
import scripts.scripter_app.api.util.WalkingUtil;

public class WintonRooftopAgilityTask extends UtilAgility {

	String[] objectActions = { "Climb", "Cross", "Jump", "Jump-on", "Jump-in", "Jump-to", "Climb-across", "Walk-across",
			"Squeeze-through", "Climb-over", "Climb-down", "Climb-up", "Walk-on", "Balance", "Jump-up", "Swing-across",
			"Teeth-grip", "Leap", "Hurdle", "Jump-off", "Vault", "Grab", "Climb-on" };

	public boolean canContinue() {
		return true; // TODO change to whatever
	}

	public void doTask() {
		RooftopObstacles obstacleData = RooftopAgilityData.getBestObstacle();
		RooftopObstacle[] courseData = obstacleData.getCourse();
		if (getOnCourse(obstacleData)) {
			Util.setRun();
			interactCourseObjects(courseData);
			afkIfLowHp();
		}
	}

	private boolean getOnCourse(RooftopObstacles obstacleData) {
		RSTile startingTile = obstacleData.getStartTile();
		RooftopObstacle[] courseData = obstacleData.getCourse();
		return inAnyArea(courseData) || handleStartTravel(startingTile, courseData);
	}

	private void interactCourseObjects(RooftopObstacle[] courseData) {
		for (RooftopObstacle obj : courseData) {

			String objectName = obj.getName();
			RSTile objectTile = obj.getObjectTile();
			PredictedObjectTransition translateData = obj.getTransition();
			RSArea useObjArea = obj.getUseObjectArea();

			if (!useObjArea.contains(Player.getPosition())) {
				continue;
			}

			int preXp = getAgilityXp(), preHp = getCurrentHp();
			RSArea startArea = getAreaIn(courseData);

			if (interactObject(objectName, objectTile)) {
				waitUntilObjectInteractionComplete(preHp, preXp, courseData, startArea, translateData);
			}

			if (preHp > getCurrentHp()) {
				General.println("Fall Detected");
				break;
			}

			RSArea inArea = getAreaIn(courseData);
			new MarksLooting(inArea);
		}
	}

	private boolean interactObject(String objectName, RSTile objectTile) {
		return getCloseToObject(objectTile) && clickObject(objectName, objectTile);
	}

	private RSObject getObjectToClick(String objectName, RSTile objectTile) {
		return Arrays.stream(Objects.getAt(objectTile)).filter(obj -> {
			RSObjectDefinition objDef = obj.getDefinition();
			String objName = objDef == null ? null : objDef.getName();
			return objName != null && objName.equals(objectName);
		}).findFirst().orElse(null);
	}

	private boolean clickObject(String objectName, RSTile objectTile) {
		RSObject objectToClick = getObjectToClick(objectName, objectTile);
		return ClickingUtil.click(objectToClick, objectActions);
	}

	private boolean getCloseToObject(RSTile objectTile) {
		if (objectTile == null)
			return false;
		return WalkingUtil.goNearTile(objectTile, 8);
	}

}
