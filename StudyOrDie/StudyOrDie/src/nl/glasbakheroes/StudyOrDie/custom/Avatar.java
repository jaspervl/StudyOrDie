package nl.glasbakheroes.StudyOrDie.custom;


import java.util.ArrayList;

import android.util.Log;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;

/**
 * Main avatar in the game which the player controls.
 * @author Niels Jan & Jasper
 */
public class Avatar extends GameObject {
	public final static String AVATAR_FRONT = "AvatarFront";
	public final static String AVATAR_BACK = "AvatarBack";
	public final static String AVATAR_LEFT = "AvatarLeft";
	public final static String AVATAR_RIGHT = "AvatarRight";
	
	private String currentImage = AVATAR_FRONT;
	private String name = "Avatar_name";
	private StudyOrDieModel model;
	
	/* Dynamic game variables */
	private int currentHP = 100;
	private int maxHP = 100;
	private int currentEnergy = 100;
	private int maxEnergy = 100;
	private int currentMotivation = 100;
	private int maxMotivation = 100;
	/* The current items equipped by the avatar */
	private ArrayList<Item> equipped = new ArrayList<>();
	/* Amount of keys the avatar (picked up - amount used) */
	private int numberOfKeys;
	
	/**
	 * Constructor
	 * @param model	The only version of the model (Given by the model itself)
	 */
	public Avatar(StudyOrDieModel model) {
		this.model = model;
	}
	
	@Override
	public String getImageId() {
		return currentImage;
	}

	/**
	 * Sets the image for the avatar in the appropriate direction.
	 * @param direction	The direction the avatar is walking.
	 */
	public void setImage(String direction){
		switch (direction) {
		case "Up" : if(!currentImage.equals(AVATAR_BACK)){
				currentImage = AVATAR_BACK; 
				} break;
		case "Down" : if(!currentImage.equals(AVATAR_FRONT)){
			currentImage = AVATAR_FRONT; 
			} break;
		case "Left" : if(!currentImage.equals(AVATAR_LEFT)){
			currentImage = AVATAR_LEFT; 
			} break;
		case "Right" : if(!currentImage.equals(AVATAR_RIGHT)){
			currentImage = AVATAR_RIGHT; 
			} break; 
		default	: break;
		}
	}
	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	} 
	
	/** Accessors and mutators */
	public int getKeys() {
		return numberOfKeys;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		Log.w("Avatar.setName", "Name set to: " + name); 
		this.name = name;
		model.update();
	}
	
	/** Add a item to the currently equipped item list */
	public void addItem(Item item) {
		/* Add the item to the equipped list if there isn't one with the same name already */
		if (!itemExists(item.getName())) {
			equipped.add(item);
		}
		/* Adjust the HP with item values */
		maxHP += item.getHpModifier();
		maxEnergy += item.getEnergyModifier();
		maxMotivation += item.getMotivationModifier();
		/* Check for crazy stats */
		checkStats();
	}

	/** Remove a item from the currently equipped item list */
	public void removeItem(Item item) {
		/* Remove the item from the equipped list if there is one with the requested name */
		if (itemExists(item.getName())) {
			for (Item i : equipped) {
				if (i == item) {
					equipped.remove(item);
				}
			}
		}
		/* Adjust the HP with item values */
		maxHP -= item.getHpModifier();
		maxEnergy -= item.getEnergyModifier();
		maxMotivation -= item.getMotivationModifier();
		/* Check for crazy stats */
		checkStats();
		}

	/** Check the current max values compared to current values and adjust if needed */
	private void checkStats() {
		if (maxHP < currentHP) {
			currentHP = maxHP;
		}
		if (maxEnergy < currentEnergy) {
			currentEnergy = maxEnergy ;
		}
		if (maxMotivation < currentMotivation) {
			currentMotivation = maxMotivation;
		}
	}
	
	/** Check whether the item exists in the equipped item list or not */
	private boolean itemExists(String name) {
		for (Item item : equipped) {
			if (item.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *  Set the current stats to a new value 
	 * @param hp	The amount of HP to add or substract
	 * @param energy	The amount of energy to add or substract
	 * @param motivation	The amount of motivation to add or substract
	 */
	public void setCurrent(int hp, int energy, int motivation)
	{
		setCurrentHP(this.currentHP += hp);
		setCurrentEnergy(this.currentEnergy += energy);
		setCurrentMotivation(this.currentMotivation += motivation);
	}	
	
	/** Accessors and mutators for dynamic game variables */
	
	public int getCurrentHP() {
		return currentHP;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
	
	public int getCurrentEnergy() {
		return currentEnergy;
	}
	
	public int getMaxEnergy() {
		return maxEnergy;
	}

	public int getCurrentMotivation() {
		return currentMotivation;
	}
	
	public int getMaxMotivation() {
		return maxMotivation;
	}
	
	public void setCurrentEnergy(int currentEnergy) {
		if (currentEnergy > maxEnergy) {
			this.currentEnergy = maxEnergy;
		} else {
			this.currentEnergy = currentEnergy;
		}
		model.update();
	}

	public void setCurrentHP(int currentHP) {
		if (currentHP > maxHP) {
			this.currentHP = maxHP;
		} else {
			this.currentHP = currentHP;
		}
		model.update();
	}
	
	public void setCurrentMotivation(int currentMotivation) {
		if (currentMotivation > maxMotivation) {
			this.currentMotivation = maxMotivation;
		} else {
			this.currentMotivation = currentMotivation;
		}
		model.update();
	}
	
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		model.update();
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
		model.update();
	}
	public void removeKey() {
		numberOfKeys--;
	}
	
	public void addKey() {
		numberOfKeys++;
	}
	
}
