package scripts.scripter_app.herblore;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.tribot.api.General;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

import scripts.laniax.GUI;

@ScriptManifest(authors = { "Winton" }, category = "Herblore", name = "WintonHerblore")
public class WintonHerblore extends Script {

	private URL fxml;
	private GUI gui;

	private final String fxmlUrl = "";

	public static boolean startScriptButtonPressed = false;

	private final String path = "C:\\Users\\Winton\\Scene Builder\\Herblore.fxml";

	@Override
	public void run() {

		loadGUISettings();

		if (startScriptButtonPressed)
			executeTasks();

	}

	private void loadGUISettings() {
		try {
			fxml = new File(path).toURI().toURL();
//			fxml = new URL(fxmlUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
//		gui = new GUI(fxml, null, false);
		gui = new GUI(fxml);
		gui.show();
		while (gui.isOpen()) {
			General.sleep(500);
		}
		
	}

	private void executeTasks() {

	}

}
