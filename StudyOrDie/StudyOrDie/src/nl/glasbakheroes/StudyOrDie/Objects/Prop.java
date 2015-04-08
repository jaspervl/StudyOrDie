package nl.glasbakheroes.StudyOrDie.Objects;

import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Prop extends GameObject{

	public static final String PROP_CHAIR_GREEN = "GreenChair";
	public static final String PROP_TABLE_CANTINE = "TableCantine";
	public static final String PROP_TABLE_CANTINE_BURGER = "TableCantineBurger";
	public static final String PROP_BENCH_LUNCHTABLE = "CantineBank";
	public static final String PROP_BENCH_LUNCHTABLE_BOTTOM = "CantineBankBottom";
	public static final String PROP_BENCH_LUNCHTABLE_TOP = "CantineBankTop";
	
	public static final String PROP_COLLEGE_SEAT = "CollegeSeat";
	public static final String PROP_SPEECH = "Speach";
	public static final String PROP_TEACHER_DESK = "TeacherDesk";
	public static final String PROP_CLASS_CHAIR = "ClassChair";
	public static final String PROP_CLASS_CHAIR_BACK = "ClassChairBack";
	public static final String PROP_CLASS_TABLE = "ClassTable";
	public static final String PROP_BENCH = "Bench";
	public static final String PROP_BENCH_RIGHT = "BenchRight";
	public static final String PROP_PLANT1 = "Plant1";
	public static final String PROP_PLANT2 = "Plant2";
	public static final String PROP_FURNACE = "Furnace";

	public static final String PROP_TABLE_GREY_IMAC = "ImacTable";
	public static final String PROP_TABLE_GREY = "EmptyGreyTable";
	public static final String PROP_TABLE_GREY_LEFT = "GreyTableLeft";
	public static final String PROP_TABLE_GREY_RIGHT = "GreyTableRight";	
	public static final String PROP_TABLE_GREY_LAPTOPBACK = "GreyTableLaptopFront";
	public static final String PROP_TABLE_GREY_LAPTOPFRONT = "GreyTableLaptopBack";
	public static final String PROP_TABLE_GREY_BOOKPENCIL = "GreyTableBookPencil";
	
	public static final String PROP_TABLE_BROWN = "BrownTable";
	public static final String PROP_TABLE_BROWN_RIGHT = "BrownTableRight";
	public static final String PROP_TABLE_BROWN_RIGHT_BOOKPENCIL = "BrownTableRightBookpencil";	
	public static final String PROP_TABLE_BROWN_RIGHT_LAPTOPBACK = "BrownTableRightLaptopback";
	public static final String PROP_TABLE_BROWN_LEFT = "BrownTableLeft";
	public static final String PROP_TABLE_BROWN_LEFT_LAPTOP = "BrownTableLeftLaptop";
	
	

	
	public String propImageID;
	
	
	public Prop(String image){
		this.propImageID = image;
	}
	
	@Override
	public String getImageId() { 
		switch (propImageID) {
		
		case "ImacTable" : return PROP_TABLE_GREY_IMAC;
		case "EmptyGreyTable": return PROP_TABLE_GREY;
		case "GreyTableLeft": return PROP_TABLE_GREY_LEFT;
		case "GreyTableRight": return PROP_TABLE_GREY_RIGHT;	
		case "GreyTableLaptopFront": return PROP_TABLE_GREY_LAPTOPFRONT;
		case "GreyTableLaptopBack": return PROP_TABLE_GREY_LAPTOPBACK;
		case "GreyTableBookPencil": return PROP_TABLE_GREY_BOOKPENCIL;
		
		case "BrownTable": return PROP_TABLE_BROWN;
		case "BrownTableRight": return PROP_TABLE_BROWN_RIGHT;
		case "BrownTableRightBookpencil": return PROP_TABLE_BROWN_RIGHT_BOOKPENCIL;	
		case "BrownTableRightLaptopback": return PROP_TABLE_BROWN_RIGHT_LAPTOPBACK;
		case "BrownTableLeft": return PROP_TABLE_BROWN_LEFT;
		case "BrownTableLeftLaptop": return PROP_TABLE_BROWN_LEFT_LAPTOP;
	
		case "TableCantine" : return PROP_TABLE_CANTINE;
		case "TableCantineBurger": return PROP_TABLE_CANTINE_BURGER;
		case "CantineBank" : return PROP_BENCH_LUNCHTABLE;
		case "CantineBankBottom" : return PROP_BENCH_LUNCHTABLE_BOTTOM;
		case "CantineBankTop" : return PROP_BENCH_LUNCHTABLE_TOP;
		case "GreenChair" : return PROP_CHAIR_GREEN;
		
		case "CollegeSeat" : return PROP_COLLEGE_SEAT ;
		case "Speach" : return PROP_SPEECH;
		case "TeacherDesk" : return PROP_TEACHER_DESK;
		case "ClassChair" : return PROP_CLASS_CHAIR;
		case "ClassChairBack" : return PROP_CLASS_CHAIR_BACK;
		case "ClassTable" : return PROP_CLASS_TABLE;
		case "Bench" : return PROP_BENCH;
		case "BenchRight" : return PROP_BENCH_RIGHT;
		case "Plant1" : return PROP_PLANT1;
		case "Plant2" : return PROP_PLANT2;
		case "Furnace" : return PROP_FURNACE;

		default : return null;
		}
	}
	
	@Override
	public void onTouched(GameBoard gameBoard) {
		// Do nothing for now
	} 
	
}
