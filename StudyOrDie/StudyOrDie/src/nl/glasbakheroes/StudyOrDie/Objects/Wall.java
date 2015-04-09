package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/** A wall, non-interactable item ingame */
public class Wall extends GameObject {
	public static final String WALL_HORIZONTAL_IMAGE = "WallHorizontal";
	public static final String WALL_VERTICAL_IMAGE = "WallVertical";
	public static final String WALL_NORTH_WEST = "WallNW";
	public static final String WALL_SOUTH_WEST = "WallSW";
	public static final String WALL_SOUTH_EAST = "WallSE";
	public static final String WALL_NORTH_EAST = "WallNE";
	private String orientation;

	public Wall(String orientation) {
		this.orientation = orientation;
	}
	
	
	/** Returns the ImageId of the image to show. */
	@Override 
	public String getImageId() { 
		switch (orientation) {
		case "Horizontal" : return WALL_HORIZONTAL_IMAGE;
		case "Vertical" : return WALL_VERTICAL_IMAGE;
		case "NW" : return WALL_NORTH_WEST;
		case "SW" : return WALL_SOUTH_WEST;
		case "SE" : return WALL_SOUTH_EAST;
		case "NE" : return WALL_NORTH_EAST;
		default : return null;
		}
	}
   
	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	} 

}
