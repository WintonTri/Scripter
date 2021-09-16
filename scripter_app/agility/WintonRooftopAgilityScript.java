package scripts.scripter_app.agility;

import java.awt.Graphics;
import java.util.ArrayList;

import org.tribot.api.General;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.scripter_app.agility.task.WintonRooftopAgilityTask;
import scripts.scripter_app.api.util.GraphicsUtil;
import scripts.scripter_app.api.util.StartingUtil;

@ScriptManifest(authors = { "Winton" }, category = "Agility", name = "Winton's Rooftop Agility")
public class WintonRooftopAgilityScript extends Script implements Starting, Painting {

	WintonRooftopAgilityTask task = new WintonRooftopAgilityTask();

	@Override
	public void run() {
		General.println(task.canContinue());
		while (task.canContinue()) {
			task.doTask();
		}
	}

	@Override
	public void onPaint(Graphics g) {
		ArrayList<String> list = new ArrayList<String>();
		GraphicsUtil.main(g, list);
	}

	@Override
	public void onStart() {
		new StartingUtil();
	}

}
