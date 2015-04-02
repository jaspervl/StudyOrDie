package nl.glasbakheroes.StudyOrDie.Objects;

import android.util.Log;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;

/** A boss in the game overworld */
public class Boss extends GameObject{
	public static final String BOSS_IMAGE_RUUD = "Ruud";
	public static final String BOSS_IMAGE_CONCIERGE = "Concierge";
	private String name;
	private boolean alive = true;
	private int hitPoints;
	private StudyOrDieModel model;
	private boolean randomBoss = false;
	private int level;
	private String specialAttackName = "NONE";
	private int specialAttackDamage = 0;

	/**
	 * Construct a (random) boss
	 * @param name		The name of the boss.
	 * @param hitPoints	The amount of hitpoints the boss has
	 * @param level		The level which will be opened after winning a fight against this boss
	 * @param model		The model with all the relevant data
	 */
	public Boss(String name, int hitPoints, int level, StudyOrDieModel model) {
		if (name.equals("Random")) {
			this.name = generateRandomName();
			randomBoss = true;
			specialAttackName = "Shoo from this area!";
			specialAttackDamage = 15;
		} else {
			this.name = name;
			setSpecialAttack();
		}
		this.hitPoints = hitPoints;
		this.model = model;
		this.level = level;
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
		case 10: return "Niels";
		default: return "Sjoerd";
		}
	}

	@Override
	public String getImageId() {
		if (randomBoss) {
			return BOSS_IMAGE_CONCIERGE;
		} else if (name.equals("Ruud")) {
			return BOSS_IMAGE_RUUD;
		}
		Log.w("Boss", "Boss image not found, nullpointer exception incoming");
		return null;
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

	public int getLevel() {
		return level;
	}

	public String getSpecialAttackName() {
		return specialAttackName;
	}

	public int getSpecialAttackDamage() {
		return specialAttackDamage;
	}
	
	/** Set the special attack damage and name for each boss */
	private void setSpecialAttack() {
		if (name.equals("Wombat")) {
			specialAttackName = "Wingclap";
			specialAttackDamage = 20;
		} else if (name.equals("Ruud")) {
			specialAttackName = "Data loss";
			specialAttackDamage = 25;
		}
	}
	
	
}
