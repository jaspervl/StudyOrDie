package nl.glasbakheroes.StudyOrDie.model;

import java.util.ArrayList;
import java.util.Observable;

import android.util.Log;
import nl.glasbakheroes.StudyOrDie.Objects.Boss;
import nl.glasbakheroes.StudyOrDie.custom.Avatar;
import nl.glasbakheroes.StudyOrDie.custom.Item;
import nl.glasbakheroes.StudyOrDie.custom.LevelLoader;

public class StudyOrDieModel extends Observable {

	private Avatar avatar;
	private LevelLoader loader;
	private ArrayList<Boss> bosses;
	private ArrayList<Item> itemList;
	
	public StudyOrDieModel() {
		avatar = new Avatar();
		bosses = new ArrayList<Boss>();
		itemList = new ArrayList<Item>();
		fillArray();
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
	
	public LevelLoader getLoader() {
		return loader;
		
	}

	public void setLoader(LevelLoader levelLoader) {
		loader = levelLoader;
	}
	
	public void addBoss(String name) {
		bosses.add(new Boss(name));
	}
	
	public Boss getBoss(String bossName) {
		for (Boss b : bosses) { 		 
			if (b.getName().equals(bossName)) {
				return b;
			}
		}
		Log.w("StudyOrDieModel.getBoss", "Boss not found, NullPointerException incoming");
		return null;
	}
	
	public boolean bossExcist(String bossName) {
		for (Boss b : bosses) {
			if (b.getName().equals(bossName)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}
	public void removeItem(Item item)
	{
		getItemList().remove(item);
		setChanged();
		notifyObservers();
	}
	
	private void fillArray(){
			itemList.add(new Item("Niels sigar", "Smoking is bad for you", -5,true));
			itemList.add(new Item("Percy DJ kit", "party..huh", 10,false));
			itemList.add(new Item("Thomas pencilcase", "I ran out of ideas", 2,false));
			itemList.add(new Item("Ruuds' Iphone", "None is as feared", 5,false));
			itemList.add(new Item("Niels handtasje", "Its scary and pink", 10,false));
			itemList.add(new Item("Niels sig..", "Guess I did run out of ideas", 20,false));
	}
	
	public void addItemToAvatar(Item item) {
		avatar.addItem(item);
		setChanged();
		notifyObservers();
	}

}
