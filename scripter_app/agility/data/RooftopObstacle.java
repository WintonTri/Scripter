package scripts.scripter_app.agility.data;


import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class RooftopObstacle {

	private final String name;
	private final RSTile objectTile;
	private final RSArea useObjectArea;
	private TransitionData translate;

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

	public TransitionData getTranslate() {
		return this.translate;
	}

	public RooftopObstacle addHover(int x, int y) {
		translate = new TransitionData(x, y);
		return this;
	}

}