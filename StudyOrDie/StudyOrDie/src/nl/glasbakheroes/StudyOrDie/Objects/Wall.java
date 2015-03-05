package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Wall extends GameObject {
	public static final String WALL_IMAGE = "RockTwo";

	/** Returns the ImageId of the image to show. */
	@Override 
	public String getImageId() { 
		return WALL_IMAGE;   
	}
   
	@Override
	public void onTouched(GameBoard gameBoard) {
		
	} 

}
