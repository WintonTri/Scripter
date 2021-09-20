package scripts.scripter_app.agility;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.tribot.api.General;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.scripter_app.agility.nodes.HandleAgilityCourse;
import scripts.scripter_app.agility.nodes.LowHealthFailsafe;
import scripts.scripter_app.agility.task.WintonRooftopAgilityTask;
import scripts.scripter_app.api.framework.Node;
import scripts.scripter_app.api.util.GraphicsUtil;
import scripts.scripter_app.api.util.StartingUtil;

@ScriptManifest(authors = { "Winton" }, category = "Agility", name = "Winton's Rooftop Agility")
public class WintonRooftopAgilityScript extends Script implements Painting {

	ArrayList<Node> tasks = new ArrayList<Node>(Arrays.asList(new LowHealthFailsafe(), new HandleAgilityCourse()));

	public static boolean keepLooping = true; // TODO something to set this false

	@Override
	public void run() {

		new StartingUtil().main(false);

		while (keepLooping) {
			General.sleep(100);

			Optional<Node> validNode = getFirstValidNode();
			if (validNode.isPresent())
				validNode.get().execute();

		}

	}

	private Optional<Node> getFirstValidNode() {
		return this.tasks.stream().filter(node -> node.validate()).findFirst();
	}

	@Override
	public void onPaint(Graphics g) {
		ArrayList<String> list = new ArrayList<String>();
		GraphicsUtil.main(g, list);
	}

}
