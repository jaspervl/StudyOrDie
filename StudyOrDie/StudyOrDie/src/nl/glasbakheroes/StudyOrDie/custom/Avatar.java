package nl.glasbakheroes.StudyOrDie.custom;


import java.util.ArrayList;

import android.util.Log;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;
import nl.glasbakheroes.StudyOrDie.model.StudyOrDieModel;

/**
 * Main avatar in the game which the player controls.
 * @author Niels Jan & Jasper & Thomas
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
	
	public void removeKey() {
		numberOfKeys--;
	}
	
	public void addKey() {
		numberOfKeys++;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		Log.w("Avatar.setName", "Name set to: " + name); 
		this.name = name;
		model.update();
	}
	
	public void addItem(Item item)
	{
		
		int index = equipped.indexOf(item);
		if(index != -1)
		{
			maxHP = maxHP + (equipped.get(index).getHpModifier() * -1);
			maxEnergy = maxEnergy + (equipped.get(index).getEnergyModifier() * -1);
			maxMotivation = maxMotivation + (equipped.get(index).getMotivationModifier() * -1);
			equipped.remove(index);
			return;
		}
		equipped.add(item);
		checkItems();
	}
	
	public void setCurrent(int hp, int energy, int motivation)
	{
		this.currentHP += hp;
		this.currentEnergy += energy;
		this.currentMotivation += motivation;
	}
	
	private void checkItems(){
		int currentMaxHP = maxHP;
		int currentMaxE = maxEnergy;
		int currentMaxM = maxMotivation;
		
		for(Item a : equipped){
		 currentMaxHP = maxHP + a.getHpModifier();
		 currentMaxE = maxEnergy + a.getEnergyModifier();
		 currentMaxHP = maxMotivation + a.getMotivationModifier();
		}
		
		this.maxHP = currentMaxHP;
		this.maxEnergy = currentMaxE;
		this.maxMotivation = currentMaxM;
		if(maxHP < currentHP) {
			currentHP = maxHP;
		}
		
		if(maxEnergy < currentEnergy) {
			currentEnergy = maxEnergy;
		}
		
		if(maxMotivation < currentMotivation) {
			currentMotivation = maxMotivation;
		}
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
		this.currentEnergy = currentEnergy;
		model.update();
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
		model.update();
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
		model.update();
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		model.update();
	}

	public void setCurrentMotivation(int currentMotivation) {
		this.currentMotivation = currentMotivation;
		model.update();
	}

	
}
