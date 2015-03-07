package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Wall extends GameObject {
	public static final String WALL_HORIZONTAL_IMAGE = "WallHorizontal";
	public static final String WALL_VERTICAL_IMAGE = "WallVertical";
	public String orientation;

	public Wall(String orientation) {
		this.orientation = orientation;
	}
	
	
	/** Returns the ImageId of the image to show. */
	@Override 
	public String getImageId() { 
		if (orientation.equals("Horizontal")) {
			return WALL_HORIZONTAL_IMAGE;
		} else {
			return WALL_VERTICAL_IMAGE;
		}
	}
   
	@Override
	public void onTouched(GameBoard gameBoard) {
		
	} 

}
