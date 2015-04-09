package nl.glasbakheroes.StudyOrDie.custom;

import java.util.ArrayList;

import nl.glasbakheroes.StudyOrDie.Objects.Key;
import nl.glasbakheroes.StudyOrDie.game.StudyOrDieGameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;
import android.util.Log;

/**
 * Main avatar in the game which the player controls.
 * 
 * @author Niels Jan & Jasper
 */
public class Avatar extends GameObject {
	public final static String AVATAR_FRONT_BLOND = "AvatarFrontBlond";
	public final static String AVATAR_BACK_BLOND = "AvatarBackBlond";
	public final static String AVATAR_LEFT_BLOND = "AvatarLeftBlond";
	public final static String AVATAR_RIGHT_BLOND = "AvatarRightBlond";
	public final static String AVATAR_FRONT_BROWN = "AvatarFrontBrown";
	public final static String AVATAR_BACK_BROWN = "AvatarBackBrown";
	public final static String AVATAR_LEFT_BROWN = "AvatarLeftBrown";
	public final static String AVATAR_RIGHT_BROWN = "AvatarRightBrown";
	public final static String AVATAR_FRONT_ASTRO = "AvatarFrontAstro";
	public final static String AVATAR_BACK_ASTRO = "AvatarBackAstro";
	public final static String AVATAR_LEFT_ASTRO = "AvatarLeftAstro";
	public final static String AVATAR_RIGHT_ASTRO = "AvatarRightAstro";
	public final static String AVATAR_FRONT_SANTA = "AvatarFrontSanta";
	public final static String AVATAR_BACK_SANTA = "AvatarBackSanta";
	public final static String AVATAR_LEFT_SANTA = "AvatarLeftSanta";
	public final static String AVATAR_RIGHT_SANTA = "AvatarRightSanta";

	public String imageFront;
	public String imageBack;
	public String imageLeft;
	public String imageRight;

	private String currentImage;
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
	private Item head;
	private Item body;
	private Item hand;
	private Item legs;
	private Item feet;
	/* Holds the keys which the avatar picks up */
	private ArrayList<Key> keys = new ArrayList<Key>();

	/**
	 * Constructor
	 * 
	 * @param model
	 *            The only version of the model (Given by the model itself)
	 */
	public Avatar(StudyOrDieModel model) {
		this.model = model;
		fill();
	}
	private void fill()
	{
		head = new Item(1, "Empty", "", 0, 0, 0, false, 0);
		body = new Item(2, "Empty", "", 0, 0, 0, false, 0);
		hand = new Item(3, "Empty", "", 0, 0, 0, false, 0);
		legs = new Item(4, "Empty", "", 0, 0, 0, false, 0);
		feet = new Item(5, "Empty", "", 0, 0, 0, false, 0);
	}

	public void setAvatarImages(int select) {

		model.setSelectedImage(select);

		switch (select) {
		case 1:
			imageFront = AVATAR_FRONT_BLOND;
			imageBack = AVATAR_BACK_BLOND;
			imageLeft = AVATAR_LEFT_BLOND;
			imageRight = AVATAR_RIGHT_BLOND;
			currentImage = imageFront;
			model.update();
			break;
		case 2:
			imageFront = AVATAR_FRONT_BROWN;
			imageBack = AVATAR_BACK_BROWN;
			imageLeft = AVATAR_LEFT_BROWN;
			imageRight = AVATAR_RIGHT_BROWN;
			currentImage = imageFront;
			model.update();
			break;
		case 3:
			imageFront = AVATAR_FRONT_ASTRO;
			imageBack = AVATAR_BACK_ASTRO;
			imageLeft = AVATAR_LEFT_ASTRO;
			imageRight = AVATAR_RIGHT_ASTRO;
			currentImage = imageFront;
			model.update();
			break;
		case 4:
			imageFront = AVATAR_FRONT_SANTA;
			imageBack = AVATAR_BACK_SANTA;
			imageLeft = AVATAR_LEFT_SANTA;
			imageRight = AVATAR_RIGHT_SANTA;
			currentImage = imageFront;
			model.update();
			break;
		}
	}

	@Override
	public String getImageId() {
		return currentImage;
	}

	/**
	 * Gets called when an item is being unequiped without being replaced by another item
	 * @param type  : slot of the item
	 */
	public void setEmpty(int type) {
		Item item = new Item(type, "Empty", "", 0, 0, 0, false, 0);
		switch (type) {
		case 1:
			head = item;
			break;
		case 2:
			body = item;
			break;
		case 3:
			hand = item;
			break;
		case 4:
			legs = item;
			break;
		case 5:
			feet = item;
			break;
		}
	}
	/**
	 * Called when a piece of equipment changes
	 */
	private void updateStats()
	{
		resetMax();
		if(head != null){
		setMax(head.getHpModifier(),head.getEnergyModifier(),head.getMotivationModifier());
		}
		if(body != null){
		setMax(body.getHpModifier(),body.getEnergyModifier(),body.getMotivationModifier());
		}
		if(hand != null){
		setMax(hand.getHpModifier(),hand.getEnergyModifier(),hand.getMotivationModifier());
		}
		if(legs != null){
		setMax(legs.getHpModifier(),legs.getEnergyModifier(),legs.getMotivationModifier());
		}
		if(feet != null){
		setMax(feet.getHpModifier(),feet.getEnergyModifier(),feet.getMotivationModifier());
		}
	}

	public boolean compareKey(int doortype) {
		for (Key a : keys) {
			if (a.getType() == doortype) {
				// numberOfKeys.remove(a); Don't remove the key ?
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the image for the avatar in the appropriate direction.
	 * 
	 * @param direction
	 *            The direction the avatar is walking.
	 */
	public void setImage(int direction) {
		switch (direction) {
		case StudyOrDieGameBoard.UP:
			if (!currentImage.equals(imageBack)) {
				currentImage = imageBack;
			}
			break;
		case StudyOrDieGameBoard.DOWN:
			if (!currentImage.equals(imageFront)) {
				currentImage = imageFront;
			}
			break;
		case StudyOrDieGameBoard.LEFT:
			if (!currentImage.equals(imageLeft)) {
				currentImage = imageLeft;
			}
			break;
		case StudyOrDieGameBoard.RIGHT:
			if (!currentImage.equals(imageRight)) {
				currentImage = imageRight;
			}
			break;
		default:
			break;
		}
	}

	public String getFrontImage() {
		return imageFront;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	}

	/** Accessors and mutators */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Log.w("Avatar.setName", "Name set to: " + name);
		this.name = name;
		model.update();
	}
	public boolean isEquipped(Item item)
	{
		if(head == item||body == item||hand == item||legs == item||feet == item)
		{
			return true;
		}
		return false;
		
	}

	/** Add a item to the currently equipped item list */
	public void addItem(Item item) {
		/*
		 * Add the item to the equipped list if there isn't one with the same
		 * name already
		 */
		switch (item.getType()) {
		case 1:
			if (head != null) {

			}
			head = item;
			break;
		case 2:
			if (body != null) {
				
			}
			body = item;
			break;
		case 3:
			if (hand != null) {
				
			}
			hand = item;
			break;
		case 4:
			if (legs != null) {
				
			}
			legs = item;
			break;
		case 5:
			if (feet != null) {
				
			}
			feet = item;
			break;
		}
		/* Adjust the HP with item values */
		updateStats();
		/* Check for crazy stats */
		checkStats();
	}
	private void setMax(int hp,int energy,int motivation)
	{
	    hp += getMaxHP();
	    energy += getMaxEnergy();
	    motivation += getMaxMotivation();
		setMaxHP(hp);
		setMaxEnergy(energy);
		setMaxMotivation(motivation);
	}

	/** Remove a item from the currently equipped item list */
	public void removeItem(Item item) {
		/*
		 * Remove the item from the equipped list if there is one with the
		 * requested name
		 */
		/* Adjust the HP with item values */

		
		setEmpty(item.getType());
		

		updateStats();
		/* Check for crazy stats */
		checkStats();
	}

	/**
	 * Check the current max values compared to current values and adjust if
	 * needed
	 */
	private void checkStats() {
		if (maxHP < currentHP) {
			currentHP = maxHP;
		}
		if (maxEnergy < currentEnergy) {
			currentEnergy = maxEnergy;
		}
		if (maxMotivation < currentMotivation) {
			currentMotivation = maxMotivation;
		}
	}

	/**
	 * Set the current stats to a new value
	 * 
	 * @param hp
	 *            The amount of HP to add or substract
	 * @param energy
	 *            The amount of energy to add or substract
	 * @param motivation
	 *            The amount of motivation to add or substract
	 */
	public void setCurrent(int hp, int energy, int motivation) {
		setCurrentHP(this.currentHP += hp);
		setCurrentEnergy(this.currentEnergy += energy);
		setCurrentMotivation(this.currentMotivation += motivation);
	}

	/** Accessors and mutators for dynamic game variables */

	public int getCurrentHP() {
		return currentHP;
	}
	
	

	public Item getHead() {
		return head;
	}
	public Item getBody() {
		return body;
	}
	public Item getHand() {
		return hand;
	}
	public Item getLegs() {
		return legs;
	}
	public Item getFeet() {
		return feet;
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
		if (currentEnergy >= 0) {
			if (currentEnergy > maxEnergy) {
				this.currentEnergy = maxEnergy;
			} else {
				this.currentEnergy = currentEnergy;
			}
			model.update();
		} else {
			this.currentEnergy = 0;
		}
	}

	public void setCurrentHP(int currentHP) {
		if (currentHP >= 0) {
			if (currentHP > maxHP) {
				this.currentHP = maxHP;
			} else {
				this.currentHP = currentHP;
			}
			model.update();
		} else {
			this.currentHP = 0;
		}
	}

	public void setCurrentMotivation(int currentMotivation) {
		if (currentMotivation >= 0) {
			if (currentMotivation > maxMotivation) {
				this.currentMotivation = maxMotivation;
			} else {
				this.currentMotivation = currentMotivation;
			}
			model.update();
		} else {
			this.currentMotivation = 0;
		}
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		model.update();
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
		model.update();
	}
	public void setMaxMotivation(int maxMotivation)
	{
		this.maxMotivation = maxMotivation;
		model.update();
	}

	public void addKey(Key object) {
		keys.add(object);
	}

	public ArrayList<Key> getKeys() {
		return keys;
	}
	private void resetMax(){
		maxHP = 100;
		maxEnergy = 100;
		maxMotivation = 100;
	}

	public void resetStats() {
		currentHP = 100;
		maxHP = 100;
		currentEnergy = 100;
		maxEnergy = 100;
		currentMotivation = 100;
		maxMotivation = 100;
	}

	public boolean hasKey(int keyType) {
		for (Key k : keys) {
			if (k.getType() == keyType) {
				return true;
			}
		}
		return false;
	}

}
