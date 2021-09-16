package scripts.scripter_app.api.objects;

import org.tribot.api2007.types.RSItemDefinition;

public class Material {

	private int itemId;
	private int quantity;

	public Material(int itemId, int quantity) {
		this.itemId = itemId;
		this.quantity = quantity;
	}
	
	public int getItemId() {
		return this.itemId;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public boolean isStackable() {
		RSItemDefinition itemDef = RSItemDefinition.get(this.itemId);
		return itemDef == null ? false : itemDef.isStackable();
	}

}
