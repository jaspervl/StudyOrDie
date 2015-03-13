package nl.glasbakheroes.StudyOrDie.model;

import java.util.ArrayList;
import java.util.Observable;

import android.util.Log;

import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.custom.LevelLoader;

public class StudyOrDieModel extends Observable {

	private Avatar avatar;
	private LevelLoader loader;
	private ArrayList<Boss> bosses;
	
	public StudyOrDieModel() {
		avatar = new Avatar();
		bosses = new ArrayList<Boss>();
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
	
	public LevelLoader getLoader() {
		return loader;
		
	}

	public void setLoader(LevelLoader levelLoader) {
		loader = levelLoader;
	}
	
	public void addBoss(String name) {
		bosses.add(new Boss(name));
	}
	
	public Boss getBoss(String bossName) {
		for (Boss b : bosses) { 		  // Baas b ! xD
			if (b.getName().equals(bossName)) {
				return b;
			}
		}
		Log.w("StudyOrDieModel.getBoss", "Boss not found, NullPointerException incoming");
		return null;
	}
	
	public boolean bossExcist(String bossName) {
		for (Boss b : bosses) {
			if (b.getName().equals(bossName)) {
				return true;
			}
		}
		return false;
	}	
}
