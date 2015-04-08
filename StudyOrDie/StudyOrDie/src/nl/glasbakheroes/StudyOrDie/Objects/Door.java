package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/** A door which can be locked or unlocked */
public class Door extends GameObject {

	public final static String DOOR_CLOSED_IMAGE = "DoorClosed";
	private boolean locked;
	private int doortype;
	
	public Door() {
		locked = false;
	}
	public Door(int doortype) {
		this.locked = true;
		this.doortype = doortype;
	}
	
	
	@Override
	public String getImageId() {
		return DOOR_CLOSED_IMAGE;
	}

	public boolean isLocked() {
		return locked;
	}
	public int getType()
	{
		return doortype;
	}
	@Override
	public void onTouched(GameBoard gameBoard) {
		// TODO Auto-generated method stub
		
	}
	

}