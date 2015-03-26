package nl.glasbakheroes.StudyOrDie.model;

import java.util.ArrayList;
import java.util.Observable;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.activities.CoreActivity;
import nl.glasbakheroes.StudyOrDie.activities.GameOverActivity;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.custom.LevelLoader;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;

/**
 * Study or Die model - Class contains all the important data in the game. It tells observers to update this data when it has changed.
 * @author Niels Jan
 */
public class StudyOrDieModel extends Observable {

	/** Instance variables */
	private Avatar avatar;
	private LevelLoader loader;
	private ArrayList<Boss> bosses;
	private ArrayList<Item> itemList;
	private int currentLevel = 1;
	private StudyOrDieGameBoard board;
	private int totalSteps;
	private Handler handler;
	private int timerValue = -10;
	private CoreActivity activity;
	private int lastRandomBossStep = 0; 
	private boolean gameInitialized = false;
	private int[] beforeFightLocation = {20, 8};
	private boolean[] levelOpened = {true, false, false, false, false, false, false, false, false, false, false};
	
	/* 2 arrays which enable or disable special items/npc's for each major level */
	private boolean[] keys = {true, true};
	private boolean[] doorLocked = {true, true};
	
	
	/** Constructor */
	public StudyOrDieModel() {
		avatar = new Avatar(this);
		bosses = new ArrayList<Boss>();
		itemList = new ArrayList<Item>();
		fillItemList(); // Fills the item array
		handler = new Handler();
		timer.run();
	} 
	
	/** Return the avatar */
	public Avatar getAvatar() {
		return avatar;
	}
	
	/** Return the level loader */
	public LevelLoader getLoader() {
		return loader;
	}

	/** Set the levelloader, this method is called after the onCreate for CoreActivity is called */
	public void setLoader(LevelLoader levelLoader) {
		loader = levelLoader;
	}
	 
	/**
	 * Adds a boss with a certain name and hitPoints.
	 * @param name	The name of the boss
	 * @param hitPoints	The amount of hitpoints the boss starts with
	 */
	public void addBoss(String name, int hitPoints, int level) {
		bosses.add(new Boss(name, hitPoints, level, this));
	}
	
	/** Return the boss with a given name */
	public Boss getBoss(String bossName) {
		for (Boss b : bosses) { 		 
			if (b.getName().equals(bossName)) {
				return b;
			}
		}
		Log.w("StudyOrDieModel.getBoss", "Boss not found, NullPointerException incoming");
		return null;
	}
	
	/** Check whether a boss exists or not */
	public boolean bossExcist(String bossName) {
		for (Boss b : bosses) {
			if (b.getName().equals(bossName)) {
				return true;
			}
		}
		return false;
	}

	/** Returns the item list */
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	/** Remove a item from the avatar item list */
	public void removeItem(Item item)
	{
		getItemList().remove(item);
		update();
	}
	
	/** Fill the item array with items */
	private void fillItemList(){ 
		 
		itemList.add(new Item("Spiekbriefje", "Take a peek.", 5, 10, 0, false));
		itemList.add(new Item("Ruuds Iphone", "Feel the power of the crapple!", 15, 5, 5, false));
		itemList.add(new Item("Big Java Book", "The power is in the reading between the lines.", 30, -10, -10, false));
		itemList.add(new Item("Tristans terminal", "You didnt practice enough.", 3, 30, -10, false));
		itemList.add(new Item("CPO Book", "You feel yourself becoming very creative.", 25, -10, -20, false));
		itemList.add(new Item("Koffie", "Take a sip and feel renewed!", 2, 20, 2, true));
		itemList.add(new Item("Energydrink", "Woah this seems very powerful!", 0, 15, -5, true));
		itemList.add(new Item("Bier", "Keep the spirits high.", -5, 5, 15, true));
		itemList.add(new Item("Chocolade", "Keep it away from my apple", -30, 20, 2, true));
		itemList.add(new Item("Kipburger", "3x beter dan hamburger", -5, 100, 100, true)); 
		itemList.add(new Item("Pepsi", "Feel the taste.", 10, 10, 10, true));
	}
	
	/** Add a item to the avatar */
	public void addItemToAvatar(Item item) {
		avatar.addItem(item);
		update();
	}
	
	/** Remove a item from the avatar */
	public void unEquipAvatarItem(Item item) {
		avatar.removeItem(item);
		update();
	}
	
	/** Tell the observers that the data has changed */
	public void update() {
		setChanged();
		notifyObservers();
	}
	
	/** Set the current level */
	public void setLevel(int level) {
		currentLevel = level;
	}
	
	/** Get the current level */
	public int getLevel() {
		return currentLevel;
	}

	/** Set the gameboard in the model class, this method is called after CoreActivity's onCreate method is called. */
	public void setBoard(StudyOrDieGameBoard gameBoard) {
		this.board = gameBoard;
		board.setAvatar(avatar);
	}
	
	/** Add a step to the total number of steps made in the game by the avatar 
	 * if there were 10 steps since the last energy-burn. Burn 1 energy*/
	public void addStep() {
		this.totalSteps++;
		update();
		if (totalSteps % 10 == 0) {
			avatar.setCurrentEnergy(avatar.getCurrentEnergy() - 1);
		}
	}
	
	/** Get the total number of steps made until now by the avatar */
	public int getSteps() {
		return totalSteps;
	}
	
	/** Add 1 (second) to the timerValue to keep track of the time played 
	 * Every time 10 seconds pass a motivation point will vanish */
	Runnable timer = new Runnable() {
		@Override
		public void run() {
			timerValue += 1;
			handler.postDelayed(timer, 1000);
			/* Can we also just update the overworld stat view? */
			update();
			if (timerValue % 10 == 0) {
				avatar.setCurrentMotivation(avatar.getCurrentMotivation() - 1);
				exhaustCheck();
			}
		}
	};	
	
	/** Gets the approximate time passed in seconds */
	public int getTime() {
		return timerValue;
	}
	
	/** Returns the array with booleans for each key to see if it is present or not */
	public boolean[] getKeys() {
		return keys;
	}
	
	/** Returns the array with booleans for each door to see whether it is locked or not */
	public boolean[] getDoors() {
		return doorLocked;
	}
	
	/** Remove a key from the game in a certain sub-level */
	public void removeKey(int subLevel) {
		switch (subLevel) {
		case 2 	: keys[0] = false; break;
		case 12 : keys[1] = false; break;
		default	: break;
		}
		setBeforeFightLocation(avatar.getPositionX(), avatar.getPositionY());
		loader.loadLevel("Fight");
	}
	
	/** Checking for the value of avatar energy, avatar motivation.
	 * If both are 0, GAME OVER and start from scratch */
	private void exhaustCheck() {
		if (avatar.getCurrentEnergy() <= 0 && avatar.getCurrentMotivation() <= 0) {
			currentLevel = 1; 
			loader.loadLevel("Bottom");
			String name = avatar.getName();
			avatar = new Avatar(this);
			totalSteps = 0;
			timerValue = 0;
			avatar.setName(name);
			avatar.resetStats();
			Intent gameOverIntent = new Intent(activity, GameOverActivity.class);
			activity.startActivity(gameOverIntent);
			update();
		}
	}
	
	/** 'Unlock' a locked door in a certain sub-level */
	public void unlockDoor(int subLevel) {
		switch (subLevel) {
		case 3 	: doorLocked[0] = false; break;
		case 13 : doorLocked[1] = false; break;
		default	: break;
		}
	}
	
	/** Set the core activity */
	public void setActivity(CoreActivity activity) {
		this.activity = activity;
	}
	
	/** Set the timer value to 0 */
	public void resetTimer() {
		timerValue = 0;
	}
	
	public void randomEncounterOccured() {
		lastRandomBossStep = totalSteps;
	}
	
	/** Return whether or not the avatar enters a random battle, the chance to enter a fight raises exponential after more steps taken */
	public boolean fightRandomBoss() {
		double chance = Math.pow(((double) (totalSteps - lastRandomBossStep) / 70), 4);
		if (Math.random() < chance) {
			return true;
		}
		return false;
	}
	
	/** Find the random boss and return it */
	public Boss findRandomBoss() {
		for (Boss b : bosses) {
			if (b.isRandomBoss()) {
				return b;
			}
		}
		Log.w("StudyOrDieModel", "No random boss found, null returned");
		return null;
	}

	/** Remove a boss from boss array list */
	public void removeBoss(Boss boss) {
		bosses.remove(boss);
	}
	
	/** Make sure the model knows that the game has already been initialized
	 * Called from StudyOrDieGame constructor */
	public void gameHasBeenInitialized() {
		this.gameInitialized = true;
	}
	
	/** Check if the game has been initialized already
	 * Called from StudyOrDieGame constructor */
	public boolean isGameInitialized() {
		return gameInitialized;
	}
	
	/** Get the location of the avatar where he was before a fight occurred */
	public int[] getBeforeFightLocation() {
		return beforeFightLocation;
	}
	
	/** Save the current location of the avatar, called when entering a fight */
	public void setBeforeFightLocation(int x, int y) {
		 beforeFightLocation[0] = x;
		 beforeFightLocation[1] = y;
	}
	
	public void openLevel(int level) {
		this.levelOpened[level] = true;
	}
	
	public boolean isLevelOpen(int level) {
		return this.levelOpened[level];
	}
	
}
