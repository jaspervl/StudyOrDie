package nl.glasbakheroes.StudyOrDie.model;

import java.util.ArrayList;
import java.util.Observable;

import android.os.Handler;
import android.util.Log;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
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
	
	
	/** Constructor */
	public StudyOrDieModel() {
		avatar = new Avatar(this);
		bosses = new ArrayList<Boss>();
		itemList = new ArrayList<Item>();
		fillArray(); // What array? ;-)
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
	public void addBoss(String name, int hitPoints) {
		bosses.add(new Boss(name, hitPoints, this));
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
	private void fillArray(){
			itemList.add(new Item("Niels sigar", "Smoking is bad for you", -5,true));	// javadoc on constructor for Item?
			itemList.add(new Item("Percy DJ kit", "party..huh", 10,false));
			itemList.add(new Item("Thomas pencilcase", "I ran out of ideas", 2,false));
			itemList.add(new Item("Ruuds' Iphone", "None is as feared", 5,false));
			itemList.add(new Item("Niels handtasje", "Its scary and pink", 10,false));
			itemList.add(new Item("Niels sig..", "Guess I did run out of ideas", 20,false));
	}
	
	/** Add a item to the avatar */
	public void addItemToAvatar(Item item) {
		avatar.addItem(item);
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
			update();
			if (timerValue % 10 == 0) {
				avatar.setCurrentMotivation(avatar.getCurrentMotivation() - 1);
			}
		}
	};	
	
	/** Gets the approximate time passed in seconds */
	public int getTime() {
		return timerValue;
	}
}
