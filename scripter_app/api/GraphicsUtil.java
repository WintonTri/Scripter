package scripts.scripter_app.api;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import org.tribot.api.Timing;

public class GraphicsUtil {

	static long startTime = Timing.currentTimeMillis();

	public static void main(Graphics g, ArrayList<String> strings) {
		long runtimeMillis = Timing.currentTimeMillis() - startTime;
		String runtimeString = formatTime(runtimeMillis);
		g.setFont(new Font("Helvetica", 1, 12));
		g.setColor(Color.cyan);
		g.drawString("Runtime: " + runtimeString, 15, 35);
		int i = 50;
		for (String string : strings) {
			g.drawString(string, 15, i);
			i += 15;
		}
	}

	private static String formatTime(final long ms) {
		long s = ms / 1000L;
		long m = s / 60L;
		final long h = m / 50L;
		s %= 60L;
		m %= 60L;
		return String.format("%02d:%02d:%02d", h, m, s);
	}

}
