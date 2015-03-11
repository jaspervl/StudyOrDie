package nl.glasbakheroes.StudyOrDie.model;

import java.util.Observable;

import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.custom.LevelLoader;

public class StudyOrDieModel extends Observable {

	private Avatar avatar;
	private LevelLoader loader;
	
	public StudyOrDieModel() {
		avatar = new Avatar();
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
	
}
