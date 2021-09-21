package scripts.scripter_app.herblore.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.tribot.api.General;

import com.allatori.annotations.DoNotRename;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import scripts.laniax.AbstractGUIController;
import scripts.scripter_app.herblore.WintonHerblore;

@DoNotRename
public class HerbloreGUIController extends AbstractGUIController {

	@FXML @DoNotRename
	Button startScriptButton;

	@FXML @DoNotRename
	Slider mouseSpeedSlider;

	@FXML @DoNotRename
	CheckBox cleanHerbsCheckbox, makeUnfPotionsCheckbox;

	@FXML @DoNotRename
	CheckBox cleanGuamLeafCheckbox, makeUnfGuamLeafCheckbox;

	@FXML @DoNotRename
	CheckBox cleanMarrentillCheckbox, makeUnfMarrentillCheckbox;

	@FXML @DoNotRename
	CheckBox cleanTarrominCheckbox, makeUnfTarrominCheckbox;

	@FXML @DoNotRename
	CheckBox cleanHarralanderCheckbox, makeUnfHarralanderCheckbox;

	@FXML @DoNotRename
	CheckBox cleanRanarrWeedCheckbox, makeUnfRanarrWeedCheckbox;

	@FXML @DoNotRename
	CheckBox cleanToadflaxCheckbox, makeUnfToadflaxCheckbox;

	@FXML @DoNotRename
	CheckBox cleanIritLeafCheckbox, makeUnfIritLeafCheckbox;

	@FXML @DoNotRename
	CheckBox cleanAvantoeCheckbox, makeUnfAvantoeCheckbox;

	@FXML @DoNotRename
	CheckBox cleanKwuarmCheckbox, makeUnfKwuarmCheckbox;

	@FXML @DoNotRename
	CheckBox cleanSnapdragonCheckbox, makeUnfSnapdragonCheckbox;

	@FXML @DoNotRename
	CheckBox cleanCadantineCheckbox, makeUnfCadantineCheckbox;

	@FXML @DoNotRename
	CheckBox cleanLantadymeCheckbox, makeUnfLantadymeCheckbox;

	@FXML @DoNotRename
	CheckBox cleanDwarfWeedCheckbox, makeUnfDwarfWeedCheckbox;

	@FXML @DoNotRename
	CheckBox cleanTorstolCheckbox, makeUnfTorstolCheckbox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML @DoNotRename
	public void startScriptPressed() {
		General.println("Start Script Clicked");
		WintonHerblore.startScriptButtonPressed = true;
		saveSettings();
		this.getGUI().close();
	}

	public void saveSettings() {

		// Mouse speed
		HerbloreGUISettings.mouseSpeed = this.mouseSpeedSlider.getValue();

		// General
		HerbloreGUISettings.cleanHerbs = this.cleanHerbsCheckbox.isSelected();
		HerbloreGUISettings.makeUnfPotions = this.makeUnfPotionsCheckbox.isSelected();

		// Guam Leaf
		HerbloreGUISettings.cleanGuamLeaf = this.cleanGuamLeafCheckbox.isSelected();
		HerbloreGUISettings.makeUnfGuamLeaf = this.makeUnfGuamLeafCheckbox.isSelected();

		// Marrentill
		HerbloreGUISettings.cleanMarrentill = this.cleanMarrentillCheckbox.isSelected();
		HerbloreGUISettings.makeUnfMarrentill = this.makeUnfMarrentillCheckbox.isSelected();

		// Tarromin
		HerbloreGUISettings.cleanTarromin = this.cleanTarrominCheckbox.isSelected();
		HerbloreGUISettings.makeUnfTarromin = this.makeUnfTarrominCheckbox.isSelected();

		// Irit Leaf
		HerbloreGUISettings.cleanIritLeaf = this.cleanIritLeafCheckbox.isSelected();
		HerbloreGUISettings.makeUnfIritLeaf = this.makeUnfIritLeafCheckbox.isSelected();

		// Ranarr Weed
		HerbloreGUISettings.cleanRanarrWeed = this.cleanRanarrWeedCheckbox.isSelected();
		HerbloreGUISettings.makeUnfRanarrWeed = this.makeUnfRanarrWeedCheckbox.isSelected();

		// Toadflax
		HerbloreGUISettings.cleanToadflax = this.cleanToadflaxCheckbox.isSelected();
		HerbloreGUISettings.makeUnfToadflax = this.makeUnfToadflaxCheckbox.isSelected();

		// Avantoe
		HerbloreGUISettings.cleanAvantoe = this.cleanAvantoeCheckbox.isSelected();
		HerbloreGUISettings.makeUnfAvantoe = this.makeUnfAvantoeCheckbox.isSelected();

		// Kwuarm
		HerbloreGUISettings.cleanKwuarm = this.cleanKwuarmCheckbox.isSelected();
		HerbloreGUISettings.makeUnfKwuarm = this.makeUnfKwuarmCheckbox.isSelected();

		// Snapdragon
		HerbloreGUISettings.cleanSnapdragon = this.cleanSnapdragonCheckbox.isSelected();
		HerbloreGUISettings.makeUnfSnapdragon = this.makeUnfSnapdragonCheckbox.isSelected();

		// Cadantine
		HerbloreGUISettings.cleanCadantine = this.cleanCadantineCheckbox.isSelected();
		HerbloreGUISettings.makeUnfCadantine = this.makeUnfCadantineCheckbox.isSelected();

		// Lantadyme
		HerbloreGUISettings.cleanLantadyme = this.cleanLantadymeCheckbox.isSelected();
		HerbloreGUISettings.makeUnfLantadyme = this.makeUnfLantadymeCheckbox.isSelected();

		// Dwarf Weed
		HerbloreGUISettings.cleanDwarfWeed = this.cleanDwarfWeedCheckbox.isSelected();
		HerbloreGUISettings.makeUnfDwarfWeed = this.makeUnfDwarfWeedCheckbox.isSelected();

		// Torstol
		HerbloreGUISettings.cleanTorstol = this.cleanTorstolCheckbox.isSelected();
		HerbloreGUISettings.makeUnfTorstol = this.makeUnfTorstolCheckbox.isSelected();

	}

}
