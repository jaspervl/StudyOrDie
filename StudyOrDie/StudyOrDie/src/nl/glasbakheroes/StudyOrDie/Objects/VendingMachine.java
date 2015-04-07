package nl.glasbakheroes.StudyOrDie.Objects;

import java.util.ArrayList;

import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class VendingMachine extends GameObject {

	public static final String VENDING_IMAGE = "Vendingmachine";
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

	}
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

	public Item getItem(int pos) {
		return shopItems.get(pos);
	}

	public void addShopItem(Item item) {
		shopItems.add(item);
	}
	public void deleteItem(int pos)
	{
		shopItems.remove(pos);
	}
}
