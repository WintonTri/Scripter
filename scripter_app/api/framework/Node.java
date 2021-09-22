package scripts.scripter_app.api.framework;

import org.tribot.api.General;

public abstract class Node {

	public abstract String activity();

	public abstract boolean validate();

	public abstract void execute();

	public void log(Object o) {
		General.println(activity() + ": " + o);
	}

}
