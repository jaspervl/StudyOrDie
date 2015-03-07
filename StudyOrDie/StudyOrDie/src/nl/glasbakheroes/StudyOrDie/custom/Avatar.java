package nl.glasbakheroes.StudyOrDie.custom;


import java.util.ArrayList;

import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Avatar extends GameObject {
	
	private int numberOfKeys;
	
	public final static String AVATAR_IMAGE = "Avatar";
	
	@Override
	public String getImageId() {
		return AVATAR_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		
	} 
	
	public int getKeys() {
		return numberOfKeys;
	}
	
	public void removeKey() {
		numberOfKeys--;
	}
	
	public void addKey() {
		numberOfKeys++;
	}
	

}
