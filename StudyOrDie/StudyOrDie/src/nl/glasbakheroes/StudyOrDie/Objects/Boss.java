package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/** A boss in the game overworld */
public class Boss extends GameObject{
	public static final String BOSS_IMAGE = "Boss";
	private String name;
	private boolean alive = true;

	
	public Boss(String name) {
		this.name = name;
	}
	
	
	@Override
	public String getImageId() {
		return BOSS_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	}
	
	public String getName() {
		return name;
	}
	
	public void killBoss() {
		alive = false;
	}
	
	public boolean getAlive() {
		return alive;
	}
}
