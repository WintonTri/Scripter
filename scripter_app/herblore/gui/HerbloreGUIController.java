package scripts.scripter_app.herblore.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.tribot.api.General;
import org.tribot.script.sdk.Log;

import com.allatori.annotations.DoNotRename;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import scripts.laniax.AbstractGUIController;
import scripts.scripter_app.herblore.WintonHerblore;

//@DoNotRename
public class HerbloreGUIController extends AbstractGUIController {

	@FXML
	private Button startScriptButton;

	@FXML
	private Slider mouseSpeedSlider;

	@FXML
	CheckBox cleanHerbsCheckbox, makeUnfPotionsCheckbox;

	@FXML
	CheckBox cleanGuamLeafCheckbox, makeUnfGuamLeafCheckbox;

	@FXML
	CheckBox cleanMarrentillCheckbox, makeUnfMarrentillCheckbox;

	@FXML
	CheckBox cleanTarrominCheckbox, makeUnfTarrominCheckbox;

	@FXML
	CheckBox cleanHarralanderCheckbox, makeUnfHarralanderCheckbox;

	@FXML
	CheckBox cleanRanarrWeedCheckbox, makeUnfRanarrWeedCheckbox;

	@FXML
	CheckBox cleanToadflaxCheckbox, makeUnfToadflaxCheckbox;

	@FXML
	CheckBox cleanIritLeafCheckbox, makeUnfIritLeafCheckbox;

	@FXML
	CheckBox cleanAvantoeCheckbox, makeUnfAvantoeCheckbox;

	@FXML
	CheckBox cleanKwuarmCheckbox, makeUnfKwuarmCheckbox;

	@FXML
	CheckBox cleanSnapdragonCheckbox, makeUnfSnapdragonCheckbox;

	@FXML
	CheckBox cleanCadantineCheckbox, makeUnfCadantineCheckbox;

	@FXML
	CheckBox cleanLantadymeCheckbox, makeUnfLantadymeCheckbox;

	@FXML
	CheckBox cleanDwarfWeedCheckbox, makeUnfDwarfWeedCheckbox;

	@FXML
	CheckBox cleanTorstolCheckbox, makeUnfTorstolCheckbox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void startScriptPressed() {
		Log.log("Starting Script");
		WintonHerblore.startScriptButtonPressed = true;
		saveSettings();
		this.getGUI().close();
	}

	public void saveSettings() {

		HerbloreGUISettings.mouseSpeed = this.mouseSpeedSlider.getValue();

		HerbloreGUISettings.cleanHerbs = this.cleanHerbsCheckbox.isSelected();
		HerbloreGUISettings.makeUnfPotions = this.makeUnfPotionsCheckbox.isSelected();

	}

}
