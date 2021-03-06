package nl.glasbakheroes.StudyOrDie.Objects;

import android.util.Log;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;

/** A boss in the game overworld */
public class Boss extends GameObject{
	public static final String BOSS_IMAGE_RUUD = "Ruud";
	public static final String BOSS_IMAGE_FRANK = "Frank";
	public static final String BOSS_IMAGE_CONCIERGE = "Concierge";
	public static final String BOSS_IMAGE_TRISTAN = "Tristan";
	public static final String BOSS_IMAGE_EVERT = "Evert";
	public static final String BOSS_IMAGE_SYNTAXIS = "Syntaxis";
	public static final String BOSS_IMAGE_JAN = "Jan";
	
	private String name;
	private boolean alive = true;
	private int hitPoints;
	private StudyOrDieModel model;
	private boolean randomBoss = false;
	private int level;
	private String specialAttackName = "NONE";
	private int specialAttackDamage = 0;
	private String weakness = "";
	private Item specialItem;

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
			setBossTraits();
		}
		this.hitPoints = hitPoints * model.getDifficulty();
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
		} else if (name.equals("Frank")) {
			return BOSS_IMAGE_FRANK;
		} else if (name.equals("Tristan")) {
			return BOSS_IMAGE_TRISTAN;
		} else if (name.equals("Evert")) {
			return BOSS_IMAGE_EVERT;
		} else if (name.equals("Syntaxis")) {
			return BOSS_IMAGE_SYNTAXIS;
		} else if (name.equals("Jan")) {
			return BOSS_IMAGE_JAN;
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
	/**
	 *  Kills a boss
	 */
	public void killBoss() {
		if (randomBoss) {
			model.removeBoss(this);
		} else {
			alive = false;
		}
	}
	/*
	 * Setters/getters
	 */
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
	
	/** Set the special traits for each boss */
	private void setBossTraits() {
		if (name.equals("Tristan")) {
			specialAttackName = "Terminating your terminal";
			specialAttackDamage = 20;
			weakness = "Skip class";
			specialItem = new Item(Item.HAND,"Tristans terminal", "You didnt practice enough.", 3, 30, -10, false, 70);
		} else if (name.equals("Ruud")) {
			specialAttackName = "Data loss";
			specialAttackDamage = 25;
			weakness = "Apple talk";
			specialItem = new Item(Item.HAND,"Ruuds Iphone", "Feel the power of the crapple!", 15, 5, 5, false, 60);
		} else if (name.equals("Frank")) {
			specialAttackName = "Hard question";
			specialAttackDamage = 30;
			weakness = "Hard question";
			specialItem = new Item(Item.HAND,"CPO Book", "You feel yourself becoming very creative.", 25, -10, -20, false, 40);
		} else if (name.equals("Evert")) {
			specialAttackName = "More exercises";
			specialAttackDamage = 35;
			weakness = "Throw exception";
			specialItem = new Item(Item.HAND,"Big Java Book", "The power is in the reading between the lines.", 30, -10, -10, false, 50);
		} else if (name.equals("Syntaxis")) {
			specialAttackName = "Monthly bill";
			specialAttackDamage = 40;
			weakness = "None";
			specialItem = new Item(Item.HAND,"Spiekbriefje", "Take a peek.", 5, 10, 0, false, 10);
		} else if (name.equals("Jan")) {
			specialAttackName = "Supply more documents!";
			specialAttackDamage = 50;
			weakness = "None";
			specialItem = new Item(Item.HEAD,"Highscore", "Brag to your friends!", 100, 100, 100, false, 500);
		} else {
			specialItem = new Item(Item.HAND,"Broom", "Clean your dorm room!", 10, -5, -5, false, 20);
		}
	}

	
	public String getWeakness() {
		return weakness;
	}	
	
	public Item getSpecialItem() {
		return specialItem;
	}
}
