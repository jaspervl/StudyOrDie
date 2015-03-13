package nl.glasbakheroes.StudyOrDie.custom;

public class Item {
	String name;
	String description;
	int addHP;
	
	
	public Item(String name, String description, int addHP) {
		this.name = name;
		this.description = description;
		this.addHP = addHP;
		
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
	

}
