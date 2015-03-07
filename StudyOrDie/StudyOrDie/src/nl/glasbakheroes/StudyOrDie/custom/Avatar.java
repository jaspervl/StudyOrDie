package nl.glasbakheroes.StudyOrDie.custom;


import nl.glasbakheroes.StudyOrDie.model.GameBoard;
import nl.glasbakheroes.StudyOrDie.model.GameObject;

public class Avatar extends GameObject{
	public final static String AVATAR_IMAGE = "Avatar";
	
	@Override
	public String getImageId() {
		return AVATAR_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		
	} 
	
	
	

}
