package nl.glasbakheroes.StudyOrDie.custom;


import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Character extends GameObject{
	public final static String IMAGE = "rock";
	
	@Override
	public String getImageId() {
		return IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		int newX = getPositionX();
		int newY = 0;
		if (newX >= gameBoard.getWidth()) {
			return;
		}
		
	}
	
	
	

}
