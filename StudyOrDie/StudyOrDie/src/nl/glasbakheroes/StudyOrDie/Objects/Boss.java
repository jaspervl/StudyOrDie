package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/** A boss in the game overworld */
public class Boss extends GameObject{
	public static final String BOSS_IMAGE = "Boss";

	@Override
	public String getImageId() {
		return BOSS_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	}
}
