package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;
/**
 * 
 * @author Jasper
 *
 *  Wrapper class for an item. Makes it possible to pick up items from the gameboard
 */
public class ItemWrap extends GameObject {
	private Item item;
	// static strings to be used for loading images (keys)
	public static final String HEAD_IMAGE = "Head";
	public static final String HAND_IMAGE = "Hand";
	public static final String BODY_IMAGE = "Body";
	public static final String LEGS_IMAGE = "Legs";
	public static final String FEET_IMAGE = "Feet";


	public ItemWrap(Item item) {
		this.item = item;
	}

	@Override
	public String getImageId() {
		switch(item.getType()){
		case 1: return HEAD_IMAGE;
		case 2: return HAND_IMAGE;
		case 3: return BODY_IMAGE;
		case 4: return LEGS_IMAGE;
		case 5: return FEET_IMAGE;
		default: return HEAD_IMAGE;
		
		}
		
	}
	
	public Item getItem()
	{
		return item;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	}
}
