package scripts.scripter_app.crafter;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.sdk.Log;
import org.tribot.script.sdk.Waiting;
import org.tribot.script.sdk.cache.BankCache;

import scripts.scripter_app.api.framework.Node;
import scripts.scripter_app.api.util.GraphicsUtil;
import scripts.scripter_app.api.util.StartingUtil;
import scripts.scripter_app.crafter.task.Cut;
import scripts.scripter_app.crafter.task.GetCraftingItems;

@ScriptManifest(authors = { "Winton" }, category = "Crafting", name = "GemCrafter")
public class GemCrafter extends Script implements Painting {

	public static boolean haveItems = true;
	ArrayList<Node> tasks = new ArrayList<Node>(Arrays.asList(new Cut(), new GetCraftingItems()));

	@Override
	public void run() {

		new StartingUtil();

		if (!BankCache.isInitialized()) {
			Log.log("Failed to initialize bank");
			return;
		}

		while (haveItems) {

			Waiting.wait(150);
			Optional<Node> validTask = tasks.stream().filter(node -> node.validate()).findFirst();

			if (validTask.isPresent())
				validTask.get().execute();

		}
	}

	@Override
	public void onPaint(Graphics g) {
		ArrayList<String> list = new ArrayList<String>();
		// TODO add name of item being crafted
		GraphicsUtil.main(g, list);
	}

}
