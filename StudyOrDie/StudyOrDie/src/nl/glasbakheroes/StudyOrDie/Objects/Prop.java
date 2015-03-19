package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Prop extends GameObject{

	public static final String PROP_TABLE_GREY_IMAC = "ImacTable";
	public static final String PROP_TABLE_GREY = "EmptyGreyTable";
	public static final String PROP_CHAIR_GREEN = "GreenChair";
	public String propImageID;
	
	
	public Prop(String image){
		this.propImageID = image;
	}
	
	@Override
	public String getImageId() { 
		switch (propImageID) {
		case "TableGreyImac" : return PROP_TABLE_GREY_IMAC;
		case "TableGreyEmpty" : return PROP_TABLE_GREY;
		case "ChairGreen" : return PROP_CHAIR_GREEN;
		default : return null;
		}
	}
	
	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	} 
	
}