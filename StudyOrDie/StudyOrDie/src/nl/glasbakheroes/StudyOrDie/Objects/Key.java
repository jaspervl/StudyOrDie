package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/**
 * Key class. Each is unique and can open a door with the same type value
 * @author Jasper
 *
 */
public class Key extends GameObject {

	public static final String KEY_IMAGE = "Key";
	/*
	 *  Type value to be used to compare
	 */
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

	/*
	 * Only a getter is used in essence of encapsulation. The type can only be set once in the constructor
	 */
	public int getType() {
		return type;

	}

}
