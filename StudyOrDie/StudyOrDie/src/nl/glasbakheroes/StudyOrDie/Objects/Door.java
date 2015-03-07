package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

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
		// TODO Auto-generated method stub
		
	}

	public boolean isLocked() {
		return locked;
	}
	

}
