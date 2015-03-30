package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/**
 * A elevator object which can be locked or unlocked, brings you to the next
 * major level
 */
public class Elevator extends GameObject {
	public static final String ELEVATOR_IMAGE = "ElevatorOpen";

	public Elevator() {
	}

	@Override
	public String getImageId() {
		return ELEVATOR_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	}
}
