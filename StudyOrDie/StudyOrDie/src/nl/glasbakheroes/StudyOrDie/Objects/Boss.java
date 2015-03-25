package nl.glasbakheroes.StudyOrDie.Objects;

import android.util.Log;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;

/** A boss in the game overworld */
public class Boss extends GameObject{
	public static final String BOSS_IMAGE = "Boss";
	public static final String BOSS_IMAGE_HENK = "Henkie";
	private String name;
	private boolean alive = true;
	private int hitPoints;
	private StudyOrDieModel model;
	private boolean randomBoss = false;

	
	/** Constructor, if the boss name is 'Random' pick a random name and set it */
	public Boss(String name, int hitPoints, StudyOrDieModel model) {
		if (name.equals("Random")) {
			this.name = generateRandomName();
			randomBoss = true;
		} else {
			this.name = name;
		}
		this.hitPoints = hitPoints;
		this.model = model;
	}
	
	/** Generate a random name for a random boss */
	private String generateRandomName() {
		switch ((int)(Math.random() * 10 + 1)) {
		case 1: return "Henk";
		case 2: return "Piet";
		case 3: return "Gerard";
		case 4: return "Jos";
		case 5: return "Willem";
		case 6: return "Klaas";
		case 7: return "Peter";
		case 8: return "Kees";
		case 9: return "Nick";
		case 10: return "Jasper";
		default: return "Sjoerd";
		}
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
		if (randomBoss) {
			model.removeBoss(this);
		} else {
			alive = false;
		}
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setHP(int hp) {
		this.hitPoints = hp;
		model.update();
	}
	
	public int getHP() {
		return hitPoints;
	}
	
	/** Check whether the boss is randomly generated one or not */
	public boolean isRandomBoss() {
		return randomBoss;
	}
	
	
}
