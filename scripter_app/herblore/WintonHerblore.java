package scripts.scripter_app.herblore;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.tribot.api.General;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.sdk.Log;
import org.tribot.script.sdk.cache.BankCache;
import org.tribot.script.sdk.input.Mouse;

import scripts.laniax.GUI;
import scripts.scripter_app.api.framework.Node;
import scripts.scripter_app.api.util.StartingUtil;
import scripts.scripter_app.herblore.gui.HerbloreGUISettings;
import scripts.scripter_app.herblore.nodes.CleanHerb;
import scripts.scripter_app.herblore.nodes.GetHerbloreItems;
import scripts.scripter_app.herblore.nodes.MakeUnfPot;

@ScriptManifest(authors = { "Winton" }, category = "Herblore", name = "CleanHerblore")
public class WintonHerblore extends Script {

	private URL fxml;
	private GUI gui;

	private final String fxmlUrl = "https://raw.githubusercontent.com/WintonTri/Scripter/main/scripter_app/herblore/gui/Herblore.fxml";

	public static boolean startScriptButtonPressed = false;

	public static boolean haveItems = true;
	ArrayList<Node> tasks = new ArrayList<Node>(
			Arrays.asList(new CleanHerb(), new MakeUnfPot(), new GetHerbloreItems()));

	@Override
	public void run() {

		loadGUISettings();

		if (startScriptButtonPressed) {
			executeTasks();
		}

	}

	private void loadGUISettings() {
		try {
//			fxml = new File(path).toURI().toURL();
			fxml = new URL(fxmlUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		gui = new GUI(fxml);
		gui.show();
		while (gui.isOpen()) {
			General.sleep(500);
		}

		Mouse.setSpeed((int) Math.round(HerbloreGUISettings.mouseSpeed));

	}

	private void executeTasks() {

		new StartingUtil().main(true);

		if (!BankCache.isInitialized()) {
			Log.log("Failed to initialize bank cache");
			return;
		}

		while (haveItems) {
			sleep(100);

			Optional<Node> validNode = getValidNode();
			if (validNode.isPresent())
				validNode.get().execute();

		}

	}

	private Optional<Node> getValidNode() {
		return this.tasks.stream().filter(node -> node.validate()).findFirst();
	}

}
