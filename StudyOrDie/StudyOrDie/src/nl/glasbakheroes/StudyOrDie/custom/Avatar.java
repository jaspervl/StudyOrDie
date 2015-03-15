package nl.glasbakheroes.StudyOrDie.custom;


import java.util.ArrayList;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/**
 * Main avatar in the game which the player controls.
 * @author enjee & Jasper & Thomas
 */
public class Avatar extends GameObject {
	public final static String AVATAR_FRONT = "AvatarFront";
	public final static String AVATAR_BACK = "AvatarBack";
	public final static String AVATAR_LEFT = "AvatarLeft";
	public final static String AVATAR_RIGHT = "AvatarRight";
	
	private String currentImage = AVATAR_FRONT;
	private int currentHP = 100;
	private int maxHP = 100;
	private String name = "Avatar_name";
	private ArrayList<Item> equipped = new ArrayList<>();
	/* Amount of keys the avatar (picked up - amount used) */
	private int numberOfKeys;
	
	
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
	
	public int getCurrentHP() {
		return currentHP;
	}
	
	public int getMaxHP() {
		return maxHP;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addItem(Item item)
	{
		
		int index = equipped.indexOf(item);
		if(index != -1)
		{
			maxHP = maxHP + (equipped.get(index).addHP * -1);
			equipped.remove(index);
			return;
		}
		equipped.add(item);
		checkItems();
	}
	
	private void checkItems(){
		int currentMax = maxHP;
		for(Item a : equipped){
		 currentMax = maxHP + a.getAddHP();
		}
		
		this.maxHP = currentMax;
		if(maxHP < currentHP)
		{
			currentHP = maxHP;
		}
	}
	
}
