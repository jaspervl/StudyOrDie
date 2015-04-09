package nl.glasbakheroes.StudyOrDie.Objects;

import java.util.ArrayList;

import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

/**
 * Vending machine class. You can trade with it as it has its own list of items
 * @author Jasper
 *
 */
public class VendingMachine extends GameObject {

	public static final String VENDING_IMAGE = "Vendingmachine";
	/*
	 * Shop items
	 */
	private ArrayList<Item> shopItems;

	public VendingMachine(ArrayList<Item> items) {
		if (items == null) {
			shopItems = new ArrayList<Item>();
		} else {
			shopItems = items;
		}
	}

	@Override
	public String getImageId() {
		return VENDING_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// not used
	}
	/**
	 * Returns all item names
	 * @return
	 */
	public CharSequence[] getShopList()
	{
		CharSequence[] list = new CharSequence[shopItems.size()];
		for(Item a : shopItems)
		{
			String padding = "";
			int i = 0;
			while(i < (20 - a.getName().length())){
				padding += " ";
				i++;
			}
			
			list[shopItems.indexOf(a)] = a.getName() + padding + a.getBuyCosts();
			

		}
		return list;
	}

	/**
	 * Retrieve item from a certain position
	 */
	public Item getItem(int pos) {
		return shopItems.get(pos);
	}
	
	/**
	 *  Adds an item to the shops itemlist
	 * @param item
	 */
	public void addShopItem(Item item) {
		shopItems.add(item);
	}
	/**
	 * Removes an item from the shops list
	 * @param pos
	 */
	public void deleteItem(int pos)
	{
		shopItems.remove(pos);
	}
}
