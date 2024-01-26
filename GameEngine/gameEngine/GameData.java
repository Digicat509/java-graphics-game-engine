package gameEngine;

import java.util.prefs.Preferences;

public class GameData {
	Preferences prefs;
	public GameData() {
		 prefs = Preferences.userNodeForPackage(GameData.class);
	}
	public void addData(String reference, String data) {
		prefs.put(reference, data);
	}
	public String getData(String refrence) {
		return prefs.get(refrence, null);
	}
	public void removeData(String refrence) {
		prefs.remove(refrence);
	}
}
