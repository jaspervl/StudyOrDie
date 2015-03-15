package nl.glasbakheroes.StudyOrDie.custom;

public class Item {
	String name;
	String description;
	int addHP;
	boolean equipped;
	
	
	public Item(String name, String description, int addHP) {
		this.name = name;
		this.description = description;
		this.addHP = addHP;
		this.equipped = false;
		
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
	public int getAddHP() {
		return addHP;
	}
	public void setAddHP(int addHP) {
		this.addHP = addHP;
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
	

}
