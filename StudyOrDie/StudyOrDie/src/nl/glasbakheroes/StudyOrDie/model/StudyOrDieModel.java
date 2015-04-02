package nl.glasbakheroes.StudyOrDie.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.activities.CoreActivity;
import nl.glasbakheroes.StudyOrDie.activities.GameOverActivity;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.custom.LevelLoader;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;

/**
 * Study or Die model - Class contains all the important data in the game. It
 * tells observers to update this data when it has changed.
 * 
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
	private int[] savedAvatarLocation = { 20, 8 };
	private int score = 0;
	private int levelsOpened = 1;
	private int currencyBalance = 100;
	private String saveFileString = "";
	private int selectedAvatarImage;

//	private boolean[] keys = { true, true , true, true , true , true, true , true , true, true, true};
//	private boolean[] doorLocked = { true, true , true, true , true , true, true , true , true, true, true};
	private boolean[] levelOpened = { true, false, false, false, false, false, false, false, false, false, false };

	/** Constructor */
	public StudyOrDieModel() {
		this.avatar = new Avatar(this);
		this.bosses = new ArrayList<Boss>();
		this.itemList = new ArrayList<Item>();
		fillItemList(); // Fills the item array
		this.handler = new Handler();
		this.timer.run();
	}

	/** Return the avatar */
	public Avatar getAvatar() {
		return avatar;
	}

	/** Return the level loader */
	public LevelLoader getLoader() {
		return loader;
	}

	/**
	 * Set the levelloader, this method is called after the onCreate for
	 * CoreActivity is called
	 */
	public void setLoader(LevelLoader levelLoader) {
		loader = levelLoader;
	}

	/**
	 * Adds a boss with a certain name and hitPoints.
	 * 
	 * @param name
	 *            The name of the boss
	 * @param hitPoints
	 *            The amount of hitpoints the boss starts with
	 * @param level
	 * 			  The major level this boss unlocks upon being beaten.
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
	public void removeItem(Item item) {
		getItemList().remove(item);
		update();
	}

	/** Fill the item array with items */
	public void fillItemList() {

		/* Add non-consumables / equipables */
		itemList.add(new Item("Apply papers", "Welcome at Saxion.", 5, 5, 5, false, 10));
		
		// We dont give these away for free anymore! 
//		itemList.add(new Item("Spiekbriefje", "Take a peek.", 5, 10, 0, false, 10));
//		itemList.add(new Item("Ruuds Iphone", "Feel the power of the crapple!", 15, 5, 5, false, 60));
//		itemList.add(new Item("Big Java Book", "The power is in the reading between the lines.", 30, -10, -10, false,
//				50));
//		itemList.add(new Item("Tristans terminal", "You didnt practice enough.", 3, 30, -10, false, 70));
//		itemList.add(new Item("CPO Book", "You feel yourself becoming very creative.", 25, -10, -20, false, 40));
		
		/* Add consumables */
		itemList.add(new Item("Koffie", "Take a sip and feel renewed!", 2, 20, 2, true, 50));
		itemList.add(new Item("Chocolade", "Keep it away from my apple", -30, 20, 2, true, 10));
		// others moved to addRandomItem(Down)
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

	/**
	 * Set the gameboard in the model class, this method is called after
	 * CoreActivity's onCreate method is called.
	 */
	public void setBoard(StudyOrDieGameBoard gameBoard) {
		this.board = gameBoard;
		board.setAvatar(avatar);
	}

	/**
	 * Add a step to the total number of steps made in the game by the avatar if
	 * there were 10 steps since the last energy-burn. Burn 1 energy
	 */
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

	/**
	 * Add 1 (second) to the timerValue to keep track of the time played Every
	 * time 10 seconds pass a motivation point will vanish
	 */
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

	/**
	 * Checking for the value of avatar energy, avatar motivation. If both are
	 * 0, GAME OVER and start from scratch
	 */
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

	/** Set the core activity */
	public void setActivity(CoreActivity activity) {
		this.activity = activity;
	}

	/** Regen avatar */
	public void spawnAfterFail() {
		currentLevel = 1;
		loader.loadLevel("Bottom");
		avatar.setCurrentHP(100);
		avatar.setCurrentEnergy(100);
		avatar.setCurrentMotivation(100);
		update();
	}

	public void randomEncounterOccured() {
		raiseScore(10);
		lastRandomBossStep = totalSteps;
	}

	/**
	 * Return whether or not the avatar enters a random battle, the chance to
	 * enter a fight raises exponential after more steps taken
	 */
	public boolean fightRandomBoss() {
		double chance = Math.pow(((double) (totalSteps - lastRandomBossStep) / 70), 4);
		if (Math.random() < chance) {
			return false; // Set to false to disable random boss
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

	/**
	 * Make sure the model knows that the game has already been initialized
	 * Called from StudyOrDieGame constructor
	 */
	public void gameHasBeenInitialized(boolean gameInitialized) {
		Log.w("Model", "Game has been initialized: " + gameInitialized); 
		this.gameInitialized = gameInitialized;
	}

	/**
	 * Check if the game has been initialized already Called from StudyOrDieGame
	 * constructor
	 */
	public boolean isGameInitialized() {
		return gameInitialized; 
	}

	/** Get the location of the avatar where he was before a fight occurred */
	public int[] getSavedLocation() {
		return savedAvatarLocation;
	}

	/** Save the current location of the avatar, called when entering a fight */
	public void setSavedLocation(int x, int y) {
		savedAvatarLocation[0] = x;
		savedAvatarLocation[1] = y;
	}

	public void openLevel(int level) {
		this.levelOpened[level] = true;
		levelsOpened++;
	}

	public boolean isLevelOpen(int level) {
		return this.levelOpened[level];
	}

	public CoreActivity getActivity() {
		return activity;
	}

	public int getScore() {
		return score;
	}

	public void raiseScore(int amount) {
		this.score += amount;
	}

	public void lowerScore(int amount) {
		this.score -= amount;
	}

	public int getNumberOpenedLevels() {
		return levelsOpened;
	}

	/**
	 * Set the currency balance of the player to a new value, if the new balance
	 * will be negative, return false for NOT executing the change. if the new
	 * balance is 0 or positive, return true for executing the change
	 */
	public boolean setBalance(int newBalance) {
		if (newBalance >= 0) {
			this.currencyBalance = newBalance;
			return true;
		} else {
			return false;
		}
	}

	public int getBalance() {
		return currencyBalance;
	} 

	public void saveGame() {
		setSavedLocation(avatar.getPositionX(), avatar.getPositionY());
		Log.w("Model", "Saving the game");
		String filename = "sod_save_game.txt";
		try {
		
			FileOutputStream fileOutput = new FileOutputStream(new File(activity.getApplicationContext().getFilesDir(),
					filename), false);
			OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutput);

			saveFileString = 	"picture:" + selectedAvatarImage
								+ ":location:" + savedAvatarLocation[0] + ":" + savedAvatarLocation[1]
								+ ":timer:" + timerValue
								+ ":steps:" + totalSteps
								+ ":level:" + currentLevel
								+ ":balance:" + currencyBalance
								+ ":name:" + avatar.getName()
								+ ":score:" + score
								+ ":hp:" + avatar.getCurrentHP()
								+ ":energy:" + avatar.getCurrentEnergy()
								+ ":motivation:" + avatar.getCurrentMotivation();
			
			for (Item i : itemList) {
				saveFileString += ":item:" + i.getName() + ":" + i.getDescription() + ":" + i.getHpModifier() + ":" + i.getEnergyModifier() + ":" + i.getMotivationModifier() + ":" + i.isConsumable() + ":" + i.getBuyCosts();
			}
			for (Boss b : bosses) {
				if (!b.getAlive()) {
					Log.w("Model", "Adding " + b.getName() + " to dead bosses");
					saveFileString += ":boss:" + b.getName();
				}
			} 
			for (int i = 0 ; i < levelOpened.length ; i++) {
				if (levelOpened[i]) {
					saveFileString += ":floor:" + i;
				}
			}
			for (Key k : avatar.getKeys()) {
				saveFileString += ":key:" + k.getType();
			}

			// Write the string to the file 
			outputWriter.write(saveFileString);

			/*
			 * ensure that everything is really written out and close
			 */
			Log.w("Model", "Saving was a success");
			outputWriter.close(); 
 
		} catch (IOException e) {
			Log.w("Model", "Saving failed");
			e.printStackTrace();
		} 
	}

	public boolean loadGame() {
		boolean succes = false;
		itemList.clear();
		String filename = "sod_save_game.txt";

		/*
		 * We have to use the openFileInput()-method the ActivityContext
		 * provides. Again for security reasons with openFileInput(...)
		 */

		FileInputStream fIn;
		try {
			fIn = new FileInputStream(new File(activity.getApplicationContext().getFilesDir(), filename));

			Scanner scan = new Scanner(fIn);
			if (!scan.hasNext()) {
				scan.close();
				return false;
			}

			scan.useDelimiter(":");
			while (scan.hasNext()) {
				String word = scan.next();
				if (word.equals("picture")) { 
					if (scan.hasNextInt()) {
						selectedAvatarImage = scan.nextInt();
						avatar.setAvatarImages(selectedAvatarImage);
						Log.w("Model", "Selected avatar: " + selectedAvatarImage);
					} 
				} else if (word.equals("location")) {
					if (scan.hasNextInt()) {
						int xPos = scan.nextInt();
						if (scan.hasNextInt()) {
							int yPos = scan.nextInt();
							Log.w("Position", xPos + " " + yPos);
							savedAvatarLocation[0] = xPos;
							savedAvatarLocation[1] = yPos;
						}
						Log.w("Model", "saved location: " + savedAvatarLocation[0] + ", " + savedAvatarLocation[1]);
					}
				} else if (word.equals("timer")) {
					if (scan.hasNextInt()) {
						timerValue = scan.nextInt();
						Log.w("Model", "Timer: " + timerValue);
					}
				} else if (word.equals("steps")) {
					if (scan.hasNextInt()) {
						totalSteps = scan.nextInt();
						lastRandomBossStep = totalSteps;
						Log.w("Model", "Steps: " + totalSteps);
					}
				} else if (word.equals("level")) {
					if (scan.hasNextInt()) {
						currentLevel = scan.nextInt();
						Log.w("Model", "Current level: " + currentLevel);
					}
				} else if (word.equals("balance")) {
					if (scan.hasNextInt()) {
						currencyBalance = scan.nextInt();
						Log.w("Model", "Current balance: " + currencyBalance);
					}
				} else if (word.equals("name")) { 
					if (scan.hasNext()) {
						avatar.setName(scan.next());
						Log.w("Model", "Avatar name: " + avatar.getName());
					}
				} else if (word.equals("score")) {
					if (scan.hasNextInt()) {
						score = scan.nextInt();
						Log.w("Model", "Set score to: " + score);
					}		
				} else if (word.equals("item")) {
					String itemName = "";
					String description = "";
					int hpMod = 0;
					int eneMod = 0;
					int motMod = 0;
					boolean consumable = false;
					int costs = 0;
					
					if (scan.hasNext()) {
						itemName = scan.next();
					} if (scan.hasNext()) {
						description = scan.next();
					} if (scan.hasNextInt()) {
						hpMod = scan.nextInt();
					} if (scan.hasNextInt()) {
						eneMod = scan.nextInt();
					} if (scan.hasNextInt()) {
						motMod = scan.nextInt();
					} if (scan.hasNextBoolean()) {
						consumable = scan.nextBoolean();
					} if (scan.hasNextInt()) {
						costs = scan.nextInt();
					}
					itemList.add(new Item(itemName, description, hpMod, eneMod, motMod, consumable, costs));
					Log.w("Model", "Added item: " + itemName);
				} else if (word.equals("boss")) {
					if (scan.hasNext()) { 
						String bossName = scan.next();
						getBoss(bossName).killBoss();
						Log.w("Model", "Boss dead: " + bossName);
					}
				} else if (word.equals("floor")) {
					if (scan.hasNextInt()) {
						int floor = scan.nextInt();
						openLevel(floor); 
						Log.w("Model", "Floor opened: " + floor);
					}
				} else if (word.equals("key")) {
					if (scan.hasNextInt()) {
						int keyType = scan.nextInt();
						avatar.addKey(new Key(keyType));
						Log.w("Model", "Added a key, type: " + keyType);
					}
				} else if (word.equals("hp")) {
					if (scan.hasNextInt()) {
						int currentHp = scan.nextInt();
						avatar.setCurrentHP(currentHp);
						Log.w("Model", "Set current HP to: " + currentHp);
					}
				} else if (word.equals("energy")) {
					if (scan.hasNextInt()) {
						int currentEnergy = scan.nextInt();
						avatar.setCurrentEnergy(currentEnergy);
						Log.w("Model", "Set current energy to: " + currentEnergy);
					}
				} else if (word.equals("motivation")) {
					if (scan.hasNextInt()) {
						int currentMotivation = scan.nextInt();
						avatar.setCurrentMotivation(currentMotivation);
						Log.w("Model", "Set current Motivation to: " + currentMotivation);
					}
				} 
			}			
			update();
			loader.loadLevel("savedLocation");
			scan.close();
			succes = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return succes;
		}
	}

	public void setSelectedImage(int imageId) {
		this.selectedAvatarImage = imageId;
	}
	
	/** Add random consumable to avatar inventory, called after winning a fight */
	public void addRandomItem() {
		String title = "";
		switch ((int) Math.random() * 6 + 1) {
			case 1:  itemList.add(new Item("Koffie", "Take a sip and feel renewed!", 2, 20, 2, true, 50)); 			title = "Koffie"; break; 
			case 2:	itemList.add(new Item("Energydrink", "Woah this seems very powerful!", 0, 15, -5, true, 30));	title = "Energydrink"; break; 
			case 3:	itemList.add(new Item("Bier", "Keep the spirits high.", -5, 5, 15, true, 30));					title = "Bier"; break;
			case 4:	itemList.add(new Item("Chocolade", "Keep it away from my apple", -30, 20, 2, true, 10));		title = "Chocolade"; break;
			case 5:	itemList.add(new Item("Kipburger", "3x beter dan hamburger", -5, 100, 100, true, 100));			title = "Kipburger"; break;
			case 6:	itemList.add(new Item("Pepsi", "Feel the taste.", 10, 10, 10, true, 40));						title = "Pepsi"; break;
		}
		Toast.makeText(getActivity(), "Looted a " + title, Toast.LENGTH_SHORT).show();
		
	}
}
