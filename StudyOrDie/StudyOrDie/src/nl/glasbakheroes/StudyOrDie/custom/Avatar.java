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
	public final static String AVATAR_FRONT = "Avatar";
	public final static String AVATAR_BACK = "Avatar";
	public final static String AVATAR_LEFT = "Avatar";
	public final static String AVATAR_RIGHT = "Avatar";
	
	public String currentImage = AVATAR_FRONT;
	
	/* Amount of keys the avatar picked up */
	private int numberOfKeys;
	
	@Override
	public String getImageId() {
		return currentImage;
	}

	public void setImage(String direction){
		switch (direction) {
		case "Up" : if(!currentImage.equals(AVATAR_BACK)){
				currentImage = AVATAR_BACK;
				}
		case "Down" : if(!currentImage.equals(AVATAR_FRONT)){
			currentImage = AVATAR_FRONT;
			}
		case "Left" : if(!currentImage.equals(AVATAR_LEFT)){
			currentImage = AVATAR_LEFT;
			}
		case "Right" : if(!currentImage.equals(AVATAR_RIGHT)){
			currentImage = AVATAR_RIGHT;
			}
		}
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
