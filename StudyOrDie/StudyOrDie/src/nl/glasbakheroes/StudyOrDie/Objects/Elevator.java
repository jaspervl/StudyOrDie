package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Elevator extends GameObject {

	public static final String ELEVATOR_IMAGE = "Elevator";

	@Override
	public String getImageId() {
		return ELEVATOR_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// TODO Auto-generated method stub
		
	}
}
