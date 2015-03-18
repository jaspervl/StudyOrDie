package nl.glasbakheroes.StudyOrDie.custom;

/**
 * Construct a Item.
 * @param name	The name of the item.
 * @param description	The description of the item.
 * @param hpModifier	The amount of HP the item adds or substracts if negative	
 * @param energyMotifier	The amount of energy the item adds or substracts if negative	
 * @param motivationModifier The amount of HP motivation item adds or substracts if negative	
 * @param consumesOnUse	True if the item is a consumable, false if not.
 */
public class Item {
	private String name;
	private String description;
	private int hpModifier;
	private int energyModifier;
	private int motivationModifier;
	private boolean equipped;
	private boolean consumesOnUse;
	
	public Item(String name, String description, int hpModifier, int energyModifier, int motivationModifier, boolean consumesOnUse) {
		this.name = name;
		this.description = description;
		this.hpModifier = hpModifier;
		this.energyModifier = energyModifier;
		this.motivationModifier = motivationModifier;
		this.equipped = false;
		this.consumesOnUse = consumesOnUse;
		
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
	
	public void setHpModifier(int addHP) {
		this.hpModifier = addHP;
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
	

}
