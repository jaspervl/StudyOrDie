package nl.glasbakheroes.StudyOrDie.custom;


import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Avatar extends GameObject{
	public final static String AVATAR_IMAGE = "rock";
	
	@Override
	public String getImageId() {
		return AVATAR_IMAGE;
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
