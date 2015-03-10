package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Elevator extends GameObject {

	public static final String ELEVATOR_LOCKED_IMAGE = "ElevatorLocked";
	public static final String ELEVATOR_OPENED_IMAGE = "ElevatorOpen";
	public boolean locked;
	
	public Elevator(Boolean locked) {
		this.locked = locked;
	}
	
	@Override
	public String getImageId() {
		if (locked) {
			return ELEVATOR_LOCKED_IMAGE;
		} else {
			return ELEVATOR_OPENED_IMAGE;
		}
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	}
}
