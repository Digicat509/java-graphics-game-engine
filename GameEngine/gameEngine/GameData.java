package gameEngine;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

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
	public void clearData() throws BackingStoreException {
		prefs.clear();
	}
	public void addData(File f) throws IOException{
		if (f.createNewFile()) {
	        System.out.println("File created: " + f.getName());
	    } 
		else {
	    	  System.out.println("File already exists.");
		  }
	}
}
