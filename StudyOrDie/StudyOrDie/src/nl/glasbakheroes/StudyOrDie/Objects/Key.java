package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Key extends GameObject {

	public static final String KEY_IMAGE = "Key";
	private int type;

	public Key(int type) {
		this.type = type;
	}

	@Override
	public String getImageId() {
		return KEY_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	}

	public int getType() {
		return type;

	}

}
