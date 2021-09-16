package scripts.scripter_app.agility.data;


import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class RooftopObstacle {

	private final String name;
	private final RSTile objectTile;
	private final RSArea useObjectArea;
	private PredictedObjectTransition objTransition;

	public RooftopObstacle(final String obstacle, final RSTile objectTile, final RSArea useObjectArea) {
		this.name = obstacle;
		this.objectTile = objectTile;
		this.useObjectArea = useObjectArea;
	}

	public String getName() {
		return this.name;
	}

	public RSTile getObjectTile() {
		return this.objectTile;
	}

	public RSArea getUseObjectArea() {
		return this.useObjectArea;
	}

	public PredictedObjectTransition getTransition() {
		return this.objTransition;
	}

	public RooftopObstacle addHover(int x, int y) {
		objTransition = new PredictedObjectTransition(x, y);
		return this;
	}

}