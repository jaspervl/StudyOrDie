package nl.glasbakheroes.StudyOrDie.custom;


import java.util.ArrayList;

import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/**
 * Main avatar in the game which the player controls.
 * @author enjee & Jasper
 *
 */
public class Avatar extends GameObject {
	public final static String AVATAR_IMAGE = "Avatar";
	/* Amount of keys the avatar picked up */
	private int numberOfKeys;
	
	@Override
	public String getImageId() {
		return AVATAR_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now.
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
