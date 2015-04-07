package nl.glasbakheroes.StudyOrDie.custom;

/**
 * 
 * @author Jasper
 * 
 * Our item class which contains information for the items the avatar can wield and use.
 */

public class Item {
	// Indicate position of equipment slot
	public static final int HEAD = 1;
	public static final int HAND = 2;
	public static final int BODY = 3;
	public static final int LEGS = 4;
	public static final int FEET = 5;
	
	private int type;
	private String name;
	private String description;
	private int hpModifier;
	private int energyModifier;
	private int motivationModifier;
	private boolean equipped;
	private boolean consumesOnUse;
	private int buyCost;
	private int sellCost;
	private boolean looted = false;
	
	
	/**
	 * 
	 * Construct a Item.
	 * @param type 					The type of item. This determines which slot the item will be equipped in.
	 * @param name					The display name of the item.
	 * @param description			The description of the item.
	 * @param hpModifier			The amount of HP the item adds or substracts if negative	
	 * @param energyMotifier		The amount of energy the item adds or substracts if negative	
	 * @param motivationModifier 	The amount of HP motivation item adds or substracts if negative	
	 * @param consumesOnUse			True if the item is a consumable, false if not.
	 * @param costs					The price this item will cost at a vending machine.
	 */
	public Item(int type,String name, String description, int hpModifier, int energyModifier, int motivationModifier, boolean consumesOnUse, int costs) {
		if(type == 1){
			type = 1;
		}else if(type > 5){
			type = 5;
		}
		this.type = type;
		this.name = name;
		this.description = description;
		this.hpModifier = hpModifier;
		this.energyModifier = energyModifier;
		this.motivationModifier = motivationModifier;
		this.equipped = false;
		this.consumesOnUse = consumesOnUse;
		this.buyCost = costs;
		this.sellCost = (int)(costs/2.0);
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getHpModifier() {
		return hpModifier;
	}
	
	public void setHpModifier(int hpModifier) {
		this.hpModifier = hpModifier;
	}
	
	public int getEnergyModifier() {
		return energyModifier;
	}
	
	public void setEnergyModifier(int energyModifier) {
		this.energyModifier = energyModifier;
	}
	
	public int getMotivationModifier() {
		return motivationModifier;
	}
	
	public void setMotivationModifier(int motivationModifier) {
		this.motivationModifier = motivationModifier;
	}
	
	
	public boolean getEquipped()
	{
		return equipped;
	}
	
	public void equip()
	{
		if (this.equipped)
		{
			this.equipped = false;
		}
		else
		{
			this.equipped = true;
		}
	}
	
	public boolean isConsumable()
	{
		return consumesOnUse;
	}
	
	public int getBuyCosts() {
		return buyCost;
	}
	
	public int getSellCosts() {
		return sellCost;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isLooted() {
		return looted;
	}
	public void setLooted(boolean looted) {
		this.looted = looted;
	}

}
