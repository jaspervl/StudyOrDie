package nl.glasbakheroes.StudyOrDie.model;

import java.util.Observable;

import nl.glasbakheroes.StudyOrDie.custom.Avatar;

public class StudyOrDieModel extends Observable {

	private Avatar avatar;
	
	public StudyOrDieModel() {
		avatar = new Avatar();
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
	
}
