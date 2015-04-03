package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Prop extends GameObject{

	public static final String PROP_TABLE_GREY_IMAC = "ImacTable";
	public static final String PROP_TABLE_GREY = "EmptyGreyTable";
	public static final String PROP_CHAIR_GREEN = "GreenChair";
	public static final String PROP_TABLE_CANTINE = "TableCantine";
	public static final String PROP_COLLEGE_SEAT = "CollegeSeat";
	public static final String PROP_SPEECH = "Speach";
	public static final String PROP_TEACHER_DESK = "TeacherDesk";
	public static final String PROP_CLASS_CHAIR = "ClassChair";
	public static final String PROP_CLASS_CHAIR_BACK = "ClassChairBack";
	public static final String PROP_CLASS_TABLE = "ClassTable";
	public String propImageID;
	
	
	public Prop(String image){
		this.propImageID = image;
	}
	
	@Override
	public String getImageId() { 
		switch (propImageID) {
		case "ImacTable" : return PROP_TABLE_GREY_IMAC;
		case "EmptyGreyTable" : return PROP_TABLE_GREY;
		case "ChairGreen" : return PROP_CHAIR_GREEN;
		case "TableCantine" : return PROP_TABLE_CANTINE;
		case "CollegeSeat" : return PROP_COLLEGE_SEAT;
		case "Speach" : return PROP_SPEECH;
		case "TeacherDesk" : return PROP_TEACHER_DESK;
		case "ClassChair" : return PROP_CLASS_CHAIR;
		case "ClassChairBack" : return PROP_CLASS_CHAIR_BACK;
		case "ClassTable" : return PROP_CLASS_TABLE;
		default : return null;
		}
	}
	
	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	} 
	
}
