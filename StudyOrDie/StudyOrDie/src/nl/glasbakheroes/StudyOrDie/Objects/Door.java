package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/** A door which can be locked or unlocked */
public class Door extends GameObject {

	public final static String DOOR_CLOSED_IMAGE = "DoorClosed";
	private boolean locked = true;
	
	public Door(Boolean locked) {
		this.locked = locked;
	}
	
	@Override
	public String getImageId() {
		return DOOR_CLOSED_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	}

	public boolean isLocked() {
		return locked;
	}
	

}
