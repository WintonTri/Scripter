package scripts.scripter_app.herblore.data;

import java.util.ArrayList;
import java.util.Arrays;

import scripts.scripter_app.herblore.gui.HerbloreGUISettings;

public class HerbloreData {

	public static int VIAL_OF_WATER_ID = 227;

	
	// int levelReq, int grimyId, int cleanId, int unfId, boolean skipClean, boolean skipUnf
	public static Herb
	
		GUAM = new Herb(3, 199, 249, 91, HerbloreGUISettings.cleanGuamLeaf, HerbloreGUISettings.makeUnfGuamLeaf),
		MARRENTILL = new Herb(5, 201, 251, 93, HerbloreGUISettings.cleanMarrentill, HerbloreGUISettings.makeUnfMarrentill),
		TARROMIN = new Herb(11, 203, 253, 95, HerbloreGUISettings.cleanTarromin, HerbloreGUISettings.makeUnfHarralander),
		HARRALANDER = new Herb(20, 205, 255, 97, HerbloreGUISettings.cleanHarralander, HerbloreGUISettings.makeUnfHarralander),
		RANARR_WEED = new Herb(25, 207, 257, 99, HerbloreGUISettings.cleanRanarrWeed, HerbloreGUISettings.makeUnfRanarrWeed),
		TOADFLAX = new Herb(30, 3049, 2998, 3002, HerbloreGUISettings.cleanToadflax, HerbloreGUISettings.makeUnfToadflax),
		IRIT_LEAF = new Herb(40, 209, 259, 101, HerbloreGUISettings.cleanIritLeaf, HerbloreGUISettings.makeUnfIritLeaf),
		AVANTOE = new Herb(48, 211, 261, 103, HerbloreGUISettings.cleanAvantoe, HerbloreGUISettings.makeUnfAvantoe),
		KWUARM = new Herb(54, 213, 263, 105, HerbloreGUISettings.cleanKwuarm, HerbloreGUISettings.makeUnfKwuarm),
		SNAPDRAGON = new Herb(59, 3051, 3000, 3004, HerbloreGUISettings.cleanSnapdragon, HerbloreGUISettings.makeUnfSnapdragon),
		CADANTINE = new Herb(65, 215, 265, 107, HerbloreGUISettings.cleanCadantine, HerbloreGUISettings.makeUnfCadantine),
		LANTADYME = new Herb(67, 2485, 2481, 2483, HerbloreGUISettings.cleanLantadyme, HerbloreGUISettings.makeUnfLantadyme),
		DWARF_WEED = new Herb(70, 217, 267, 109, HerbloreGUISettings.cleanDwarfWeed, HerbloreGUISettings.makeUnfDwarfWeed),
		TORSTIL = new Herb(75, 219, 269, 111, HerbloreGUISettings.cleanTorstol, HerbloreGUISettings.makeUnfTorstol)
		
	;

	public static ArrayList<Herb> list = new ArrayList<Herb>(Arrays.asList(
			GUAM, MARRENTILL, TARROMIN, HARRALANDER, RANARR_WEED,
			TOADFLAX, IRIT_LEAF, AVANTOE, KWUARM, SNAPDRAGON, CADANTINE,
			LANTADYME, DWARF_WEED, TORSTIL
	));

}
