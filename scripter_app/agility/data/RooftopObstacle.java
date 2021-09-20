package scripts.scripter_app.agility.data;


import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class RooftopObstacle {

	private final String name;
	private final RSTile objectTile;
	private final RSArea useObjectArea;
	private DeltaPositionHover deltaHover;

	public RooftopObstacle(final String obstacle, final RSTile objectTile, final RSArea useObjectArea) {
		this.name = obstacle;
		this.objectTile = objectTile;
		this.useObjectArea = useObjectArea;
	}
	
	public RooftopObstacle(final String obstacle, final RSTile objectTile, final RSArea useObjectArea, DeltaPositionHover deltaHover) {
		this(obstacle, objectTile, useObjectArea);
		this.deltaHover = deltaHover;
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

	public DeltaPositionHover getTransition() {
		return this.deltaHover;
	}

	public RooftopObstacle addDeltaPositionHover(int x, int y) {
		deltaHover = new DeltaPositionHover(x, y);
		return this;
	}

}